define([
    'backbone',
    'tmpl/main',
    'views/abstract/baseView'
], function(
    Backbone,
    tmpl,
    baseView
){

    var MainView = Backbone.View.extend({
        __proto__: baseView, 
        el: '.main',        
        template: tmpl,
        events: {
            "click .menu__js_logout_btn": "logout",
            'show': 'show'  
        },
        
        initialize: function () {
            console.log("MainView has been created");
            this.render();
            this.hide();
        },
        
        render: function () {
            this.$el.html(this.template);
            $(".menu__js_logout_btn").hide();     
            this.checkAuth();
        },
        
        logout: function() {
            $.get("/exit", function(response){
                if (response.exit) {
                    $(".menu__js_logout_btn").hide();
                    $(".menu__js_signup_btn").show();
                    $(".menu__js_login_btn").show();
                }
            }, "json")
        },
        
        checkAuth: function() {
            $.get("/checkAuth", function(response){
                if(response.auth) {
                    $(".menu__js_login_btn").hide();
                    $(".menu__js_signup_btn").hide();
                    $(".menu__js_logout_btn").show();
                }
            }, "json")
        }
    });

    return new MainView();
});
