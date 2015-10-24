define([
    'models/player',
    'models/gameinfo',
    'collections/cells'
], function(
    player,
    gameinfo,
    gamefield
){
    var msgHandler =  { 
    
        READY : {
            status: "ready"
        },
        
        COLLUMN_CHOOSED: {
            status: "collumnChoosed",
            collumn: null
        },
        
        PLAY_AGAIN: {
            status: "playAgain",
            answer: null             
        },
        
        onready : function (event) {
            player.set("chipColor", event.chipColor);
            player.set("isMyTurn", event.isMyTurn);
            gameinfo.set("status", event.status);
            gameinfo.set("roundNum", event.roundNum);
        },

        onrun : function (event) {
            gameinfo.set("status", event.status);
            gameinfo.set("turnTime", event.turnTime);
        },

        onchangeTurn : function (event) {
            gameinfo.set("status", event.status);
            player.set("isMyTurn", event.isMyTurn);
            gamefield.fillCell(event.filledCell, player.get("chipColor")); 
        },
        
        onroundOver : function (event) {
            gameinfo.set("status", event.status);
            gameinfo.set("win", event.win);
        },        

        ongameOver : function (event) {
            gameinfo.set("status", event.status);
            gameinfo.set("win", event.win);
        }
        
    }
    
    return msgHandler;

});
