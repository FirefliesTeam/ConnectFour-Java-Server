define([
    'backbone',
    'tmpl/game',
    'collections/cells',
    'models/gameinfo',
    'models/player',
    'views/abstract/baseView',
    'webService/webSocket'
], function(
    Backbone,
    tmpl,
    gamefield,
    gameinfo,
    player,
    baseView,
    webSocket
){

    var GameView = Backbone.View.extend({
        __proto__: baseView,
        el: '.game',
        template: tmpl,
        model: gamefield,
        cell_index: null,
        animate: null,
        events: {
            'click .gamefield__column': 'dropChip',
            'mouseenter .gamefield__column': 'dropAnimationStart',
            'mouseleave .gamefield__column': 'dropAnimationStop',
            'show': 'show',
            'click .js_change_status' : 'js_change_status',
            'click .js_add_cell' : 'js_add_cell',
            'click .js_change_turn': 'js_change_turn'
        },
        initialize: function () {
            console.log("GameView has been created");
            this.render();
            this.hide();
            
            cell_index = 0;
            
            this.listenTo(player, "change:isMyTurn", this.changeTurn);
            this.listenTo(gameinfo, "change:status", this.changeStatus);
            this.listenTo(gamefield, "change", this.render);
            
            player.set("chipColor", "blue");  
            
            webSocket.initialize(); 
        },
        
        render: function () {
            console.log("render func");
            this.$el.html(this.template(this.model.toJSON()));
        },

        chooseColumn : function(event) {
            console.log("chooseColumn");
            column__id = event.currentTarget.attributes.getNamedItem("id").value;
            arrow_margin = 90 * column__id + 40;
            $(".gamefield__arrow").css({ marginLeft: arrow_margin});
        },

        changeTurn: function () {
            console.log("changeTurn");
        },
        changeStatus: function () {
            console.log("changeStatus");
        },
        js_change_turn: function() {
            player.set("isMyTurn", !player.get("isMyTurn"));
        },
        
        js_change_status: function() {
            if(gameinfo.get('status')==="ready"){
                gameinfo.set("status", "run");
            } else {
                gameinfo.set("status", "ready");
            }
        },
        
        js_add_cell: function () {
            gamefield.fillCell(cell_index, player.get("chipColor"));
            console.log(gamefield.models);
            console.log("add chip in " + cell_index);
            webSocket.sendCollumnChoosedMsg(cell_index % 6);
            cell_index++;
        },
        
        dropAnimationStart: function (event) {
            console.log("dropAnimationStart");
            this.chooseColumn(event);
        },
        
        dropAnimationStop: function() {
            console.log("dropAnimationStop");
        }
        
    });

    return new GameView();
});
