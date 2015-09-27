define([
    'backbone',
    'tmpl/main'
], function(
    Backbone,
    tmpl
){

    var MainView = Backbone.View.extend({
        el: '.page',        
        template: tmpl,
        events: {

        },
        
        initialize: function () {
            console.log("MainView has been created");
        },
        
        render: function () {
            this.$el.html(this.template) 
        },
        show: function () {
            $(this.el).show();
        },
        
        hide: function () {
            $(this.el).hide();
        },
        
        load: function () {
            this.render();
            this.show();
        }
        
        //------ EVENT FUNCTIONS ------------//
    });

    return new MainView();
});
