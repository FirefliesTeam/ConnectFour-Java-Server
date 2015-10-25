define([
    'backbone'
], function(
    Backbone
){

    var playerModel = Backbone.Model.extend({
        
        defaults: {
            name : "",
            chipColor: "",
            isAuth: false,
            isMyTurn: false,
            roundWins: 0,
            gameWon: 0,
            gamePlayed: 0
        },
        
        initialize : function() {
            console.log("PlayerModel is created");
        }
        
    });
    
    return new playerModel();
});
