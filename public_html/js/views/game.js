define([
    'backbone',
    'tmpl/game',
    'models/gamefield'
], function(
    Backbone,
    tmpl,
    gamefield
){

    var GameView = Backbone.View.extend({
        el: '.page',
        template: tmpl,
        model: gamefield,
        events: {
        
        },
        initialize: function () {
            console.log("GameView has been created");
        },
        render: function () {
            this.$el.html(this.template)
            console.log(this.model.toJSON());
        },
        show: function () {
            $(this.el).show()
        },
        hide: function () {
            $(this.el).hide()
        },
        load: function () {
            this.render();
            this.show();
        }

    });

    return new GameView();
});
