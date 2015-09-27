define([
    'backbone',
    'tmpl/game'
], function(
    Backbone,
    tmpl
){

    var GameView = Backbone.View.extend({
        el: '.page',
        template: tmpl,
        events: {
        
        },
        initialize: function () {
            console.log("GameView has been created");
        },
        render: function () {
            this.$el.html(this.template)
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
