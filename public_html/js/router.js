define([
    'backbone',
    'views/ViewManager'
], function(
    Backbone,
    ViewManager
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
            ViewManager.show_current(ViewManager.MAIN);
        },
            
        scoreboardAction: function () {
            console.log("url = #scoreboard");
            ViewManager.show_current(ViewManager.SCOREBOARD);
        },
        gameAction: function () {
            console.log("url = #game");
            ViewManager.show_current(ViewManager.GAME);
        },
        loginAction: function () {
            console.log("url = #login");
            ViewManager.show_current(ViewManager.LOGIN);
        },
        signupAction: function() {
            console.log("url = #signup");
            ViewManager.show_current(ViewManager.SIGNUP);
        }
        
        
    });

    return new Router();
});
