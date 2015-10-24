define([
    'backbone',
    'tmpl/login',
    'views/abstract/baseView'
], function(
    Backbone,
    tmpl,
    baseView
){

    var LoginView = Backbone.View.extend({
        __proto__: baseView,
        el: '.signin',
        template: tmpl,
        events: {
            'click .login__js_log_in': 'login',
            'click .login__js_btn_close': 'loginErrClose',
            'show': 'show'    
        },
        initialize: function () {
            console.log("LoginView has been created");
            this.render();
            this.hide();
        },
        render: function () {
            this.$el.html(this.template)
        },
        loginErrClose: function() {
            $(".login_fixed").hide();  
        },
        login: function() {
            $.post($(".login__form").attr("action"), $(".login__form").serialize(), function(response) {
                console.log(response);
                if(response.auth) {
                    $(".login__js_btn_back").trigger("click");
                } else {
                    $(".login_fixed").show();
                    setTimeout(function(){$('.login_fixed').fadeOut('fast')}, 5000);  
                }
            }, "json");
        }

    });
    return new LoginView();
});
