define([
    'webService/webService'
], function(
    msgHandler
){
        var Socket =  { 
    
        socket : null,
        
        initialize: function () {
            URL = "ws://" + location.host + "/gameplay"
            socket = new WebSocket(URL);
            
            console.log("ws is created on URL " + URL);
            
            socket.onopen = this.onopen;
            socket.onclose = this.onclose;
            socket.onmessage = this.onmessage;
            socket.error = this.error;
            
        },

        onopen : function() {
          console.log("Соединение установлено.");
        },

        onclose : function(event) {
          if (event.wasClean) {
            console.log('Соединение закрыто чисто');
          } else {
            console.log('Обрыв соединения'); // например, "убит" процесс сервера
          }
          console.log('Код: ' + event.code + ' причина: ' + event.reason);
        },

        onmessage : function(event) {            
            switch(event.status) {
                case "ready":
                    console.log("ready");
                    msgHandler.onready(event);
                    break;

                case "run":
                    console.log("run");
                    msgHandler.onrun(event);
                    break;
                
                case "changeTurn":
                    console.log("changeTurn");
                    msgHandler.onchangeTurn(event);
                    break;
                
                case "roundOver":
                    console.log("roundOver");
                    msgHandler.onroundOver(event);
                    break;
                
                case "gameOver":
                    console.log("gameOver");
                    msgHandler.ongameOver(event);
                    break;
            }
        },

        onerror : function(error) {
          alert("Ошибка " + error.message);
        },
        
        sendReadyMsg : function () {
            this.socket.send(JSON.stringify(msgHandler.READY));
        },
        
        sendCollumnChoosedMsg: function(collumnNum) {
            msgHandler.COLLUMN_CHOOSED.collumn = collumnNum;
            this.socket.send(JSON.stringify(msgHandler.COLLUMN_CHOOSED));
        },
        
        sendPlayAgainMsg : function(answer) {
            msgHandler.PLAY_AGAIN.answer = answer;
            this.socket.send(JSON.stringify(msgHandler.PLAY_AGAIN));
        }
           
    }
    
    return Socket;

});
