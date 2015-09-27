define([
    'backbone',
    'tmpl/signup'
], function(
    Backbone,
    tmpl
){

    var signUpView = Backbone.View.extend({
        el: '.page',
        template: tmpl,
        events: {
            'click .js_sign_up': 'signUpAction'
        },
        initialize: function () {
            console.log("SignupView has been created");
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
        },
        
        signUpAction: function() {
            
        }

    });

    return new signUpView();
});
