define([
    'backbone',
    'tmpl/scoreboard',
    'collections/scores'
], function(
    Backbone,
    tmpl,
    Scores
){

    var ScoreboardView = Backbone.View.extend({
        el: '.page',
        template: tmpl,
        model: Scores,
        initialize: function () {
            console.log("ScoreboardView has been created");
        },
        render: function () {
            this.$el.html(this.template(this.model.toJSON()));
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

    return new ScoreboardView();
});
