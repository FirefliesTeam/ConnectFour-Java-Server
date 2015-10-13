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
            'click .js_log_in': 'login',
            'click .js_sign_up': 'signUp',
            'click .js_btn_close': 'signUpClose',
            'change #password1': 'arePasswordsEqual',
            'keyup #password2': 'arePasswordsEqual',
            'change #email': 'checkEmailInput',
            'change #username': 'checkUsernameInput',
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
        
        arePasswordsEqual : function() {
            if ($("#password1").val() === $("#password2").val()) {
                $(".form__passwords-different").hide();
                return true;
            } else {
                $(".form__passwords-different").show();
                return false;
            }
        },
        
        checkUsernameInput : function() {
            $(".form__username-exists").hide();   
            if ($("#username").val() === "") {
                $(".form__username-invalid").show();
                return false;
            } else {
                $(".form__username-invalid").hide();
                return true;                
            }
        },
        
        checkEmailInput : function() {
            if ($("#email").val() === "") {
                $(".form__email-invalid").show();
                return false;
            } else {
                $(".form__email-invalid").hide();
                return true;                
            }
        },
        
        checkPasswordInput : function() {
            if ($("#password1").val() === "") {
                $(".form__password-invalid").show();
                return false;
            } else {
                $(".form__password-invalid").hide();
                return true;                
            }
        },
        login : function() {
            $(".signup_fixed").hide();
        }, 
        signUpClose : function() {
            $(".signup_fixed").hide();
        },
        
        signUp : function() {
            var username = $("#username").val();
            if ( this.arePasswordsEqual() && this.checkUsernameInput() && this.checkEmailInput() && this.checkPasswordInput() ) {
                $.post($(".form").attr("action"), $(".form").serialize(), function(response) {
                console.log(response);
                  if(response.signup) {
                        $(".signup_fixed").show();
                        $(".welcome__name").text(username); 
                  } else {  
                        if(response.login === "exists") {
                            $(".form__username-exists").show();
                        } else if (!response.email) {
                            $(".form__email-invalid").show();
                        } else if (!response.password) {
                            $(".form__password-invalid").show();
                        }
                  }
                }, "json");               
            }

        }

    });

    return new signUpView();
});
