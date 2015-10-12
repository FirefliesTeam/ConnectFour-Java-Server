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
            'click .js_log_in': 'login'    
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
        },
        
        login: function() {
            $.post($(".form").attr("action"), $(".form").serialize(), function(response) {
                if(response.auth) {
                    $(".js_btn_back").trigger("click");
                } else {
                    $(".login_fixed").show();
                    setTimeout(function(){$('.login_fixed').fadeOut('fast')}, 5000);  
                }
            }, "json");
        }

    });
    return new LoginView();
});
