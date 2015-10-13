define([
    'backbone'
], function(
    Backbone
){

    var Router = Backbone.Router.extend({
        routes: {
            'scoreboard': 'scoreboardAction',
            'game': 'gameAction',
            'login': 'loginAction',
            'signup': 'signupAction',
            '*default': 'defaultActions'
        },
        
        navigateTo: function(url) {
            console.log("navigate to" + url);
            this.navigate(url, {trigger:true});
        },
        
        defaultActions: function () {
            console.log("url = #");
            require(['views/main'], function(mainView){
                mainView.load();
            })
        },
            
        scoreboardAction: function () {
            console.log("url = #scoreboard");
            require(['views/scoreboard'], function(scoreboardView){
                scoreboardView.load();
            })
        },
        gameAction: function () {
            console.log("url = #game");
            require(['views/game'], function(gameView){
                gameView.load();
            })
        },
        loginAction: function () {
            console.log("url = #login");
            require(['views/login'], function(loginView){
                loginView.load();
            })
        },
        signupAction: function() {
            console.log("url = #signup");
            require(['views/signup'], function(signupView){
                signupView.load();
            })
        }
        
        
    });

    return new Router();
});
