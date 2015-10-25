define([
    'backbone',
    'views/main',
    'views/game',
    'views/login',
    'views/signup',
    'views/scoreboard'
], function(
    Backbone,
    main_view,
    game_view,
    login_view,
    signup_view,
    scoreboard_view
){

    var ViewManager = Backbone.View.extend({
        
        
        GAME: "game",
        SCOREBOARD: "scoreboard",
        MAIN: "main",
        LOGIN: "login",
        SIGNUP: "signup",
        
        
        views: {
            game: null,
            main: null,
            login: null,
            signup: null,
            scoreboard:null,
        },
        
        
        current_view : null,
        previous_view : null,
        
        initialize: function () {
            console.log("ViewManager has been created");
            
            this.views.game = game_view;
            this.views.main = main_view;
            this.views.scoreboard = scoreboard_view;
            this.views.login = login_view;
            this.views.signup = signup_view;
            
                            
            this.listenTo(game_view, 'show', this.hide_previous);
            this.listenTo(main_view, 'show', this.hide_previous);
            this.listenTo(scoreboard_view, 'show', this.hide_previous);
            this.listenTo(signup_view, 'show', this.hide_previous);
            this.listenTo(login_view, 'show', this.hide_previous);
            
        },
        
        hide_previous : function () {
            if(this.previous_view !== null) {
                this.previous_view.hide();
                console.log("hide_previous");
            }
        },
        
        show_current : function (viewName) {
        
        
            loading_view = this.views[viewName];
        /*    
            if(this.current_view === "") {  
                loading_view = this.views.main;
            }
          */  
            if(loading_view !== null && loading_view !== this.current_view) {
                console.log("show_current");
                this.previous_view = this.current_view;
                this.current_view = loading_view;
                loading_view.trigger('show');
                loading_view.show();
            }
        
        }
    });

    return new ViewManager();
});




