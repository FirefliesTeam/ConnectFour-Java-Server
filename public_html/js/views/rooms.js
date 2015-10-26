define([
    'backbone',
    'tmpl/rooms',
    'collections/rooms',
    'views/abstract/baseView',
    'models/player',
    'webService/webSocket'
], function(
    Backbone,
    tmpl,
    Rooms,
    baseView,
    player,
    webSocket
){

    var RoomsView = Backbone.View.extend({
        __proto__: baseView,
        events: {
            'show': 'show',
            'click .js_create_btn': 'createGame',
            'click .js_play_btn': 'playGame'
        },
        el: '.rooms',
        template: tmpl,
        model: Rooms,
        
        initialize: function () {
            console.log("RoomsView has been created");
            this.render();
            this.hide();
        },
        
        render: function () {
            this.$el.html(this.template(this.model.toJSON()));
        },

        createGame: function () {
            console.log("createGame");
            webSocket.connect();
            webSocket.sendPlayMsg("user1");
        },
        
        playGame: function () {
            console.log("playGame");
            webSocket.connect();
            webSocket.sendJoinMsg("user2");
        }
        

    });

    return new RoomsView();
});
