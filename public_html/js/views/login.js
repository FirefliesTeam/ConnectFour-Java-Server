define([
    'backbone',
    'tmpl/login'
], function(
    Backbone,
    tmpl
){

    var LoginView = Backbone.View.extend({
        el: '.page',
        template: tmpl,
        events: {
            'click js_log_in': 'login'    
        },
        initialize: function () {
            console.log("LoginView has been created");
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

    return new LoginView();
});
