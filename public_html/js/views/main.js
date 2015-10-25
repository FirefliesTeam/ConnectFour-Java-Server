define([
    'backbone',
    'tmpl/main',
    'views/abstract/baseView',
    'models/player'
], function(
    Backbone,
    tmpl,
    baseView,
    player
){

    var MainView = Backbone.View.extend({
        __proto__: baseView, 
        el: '.main',        
        template: tmpl,
        events: {
            "click .menu__js_logout_btn": "logout",
            'show': 'checkAuth'  
        },
        
        initialize: function () {
            console.log("MainView has been created");
            this.render();
            this.hide();
        },
        
        render: function () {
            this.$el.html(this.template);
            $(".menu__js_logout_btn").hide();     
        },
        
        logout: function() {
            $.get("/exit", function(response){
                if (response.exit) {
                    player.set("isAuth", false);
                    $(".menu__js_logout_btn").hide();
                    $(".menu__js_signup_btn").show();
                    $(".menu__js_login_btn").show();
                }
            }, "json")
        },
        
        checkAuth: function() {
            $.get("/checkAuth", function(response){
                if(response.auth) {
                    player.set("isAuth", true);
                    $(".menu__js_login_btn").hide();
                    $(".menu__js_signup_btn").hide();
                    $(".menu__js_logout_btn").show();
                }
            }, "json")
        }
    });

    return new MainView();
});
