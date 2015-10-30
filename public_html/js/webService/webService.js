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
            if(data.chipColor == 1) {
                player.set("enemyChipColor", "red");
                player.set("enemyName", "blue");
            } else {
                player.set("enemyChipColor", "blue");
                player.set("enemyName", "red");                
            }
            player.set("isMyTurn", data.isMyTurn);
            gameinfo.set("status", data.status);
        },

        onrun : function (event) {
            gameinfo.set("status", event.status);
            gameinfo.set("turnTime", event.turnTime);
        },

        onchangeTurn : function (event) {
            console.log()
            gameinfo.set("status", event.status);
            if (player.get("isMyTurn")) {
                gamefield.fillCell(event.cell, player.get("chipColor"));            
            } else {
                gamefield.fillCell(event.cell, player.get("enemyChipColor"));             
            }
            player.set("isMyTurn", event.isMyTurn); 
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
