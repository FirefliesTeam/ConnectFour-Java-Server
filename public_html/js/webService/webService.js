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
        
        PLAY_GAME: {
            status: "newGame",
            creator: ""
        },
        
        JOIN_GAME: {
            status: "joinGame",
            roomHolder: ""
        },
        
        onwait : function (data) {
            gameinfo.set("status", data.status);
        },
        
        onready : function (data) {
            player.set("chipColor", data.chipColor);
            player.set("enemyChipColor", data.chipColor);
            player.set("enemyName", data.enemyName);
            player.set("isMyTurn", data.isMyTurn);
            gameinfo.set("status", data.status);
            //console.log(player.toJSON());
            //console.log(gameinfo.toJSON);
            
        },

        onrun : function (event) {
            gameinfo.set("status", event.status);
            gameinfo.set("turnTime", event.turnTime);
        },

        onchangeTurn : function (event) {
            gameinfo.set("status", event.status);
            player.set("isMyTurn", event.isMyTurn);
            gamefield.fillCell(event.filledCell, player.get("chipColor"));
            gamefield.fillCell(event.fillCell, player.get("enemyChipColor")); 
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
