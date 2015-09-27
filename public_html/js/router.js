define([
    'backbone',
    'views/main',
    'views/scoreboard',
    'views/login',
    'views/game',
    'views/signup'
], function(
    Backbone, MainView, ScoreboardView, LoginView, GameView, SignUpView
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
            MainView.load();
        },
            
        scoreboardAction: function () {
            console.log("url = #scoreboard");
            ScoreboardView.load();
        },
        gameAction: function () {
            console.log("url = #game");
            GameView.load();
        },
        loginAction: function () {
            console.log("url = #login");
            LoginView.load();
        },
        signupAction: function() {
            console.log("url = #register");
            SignUpView.load();
        }
        
        
    });

    return new Router();
});
