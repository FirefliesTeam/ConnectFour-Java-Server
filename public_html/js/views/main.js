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
            "click .js_logout_btn": "logout"
        },
        
        initialize: function () {
            console.log("MainView has been created");
        },
        
        render: function () {
            this.$el.html(this.template);
            $(".js_logout_btn").hide();     
            this.checkAuth();
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
        },
        
        logout: function() {
            $.get("/exit", function(response){
                if (response.exit) {
                    $(".js_logout_btn").hide();
                    $(".js_login_btn").show();
                }
            }, "json")
        },
        
        checkAuth: function() {
            $.get("/checkAuth", function(response){
                if(response.auth) {
                    $(".js_login_btn").hide();
                    $(".js_logout_btn").show();
                }
            }, "json")
        }
    });

    return new MainView();
});
