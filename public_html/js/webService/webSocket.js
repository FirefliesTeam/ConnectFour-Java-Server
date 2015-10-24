define([
    'webService/msgHandler'
], function(
    msgHandler
){
        var Socket =  { 
    
        socket : null,
        
        initialize: function () {
            socket = new WebSocket("ws://localhost:8090/gameplay/");
            
            console.log("ws is created");
            
            socket.onopen = this.onopen;
            socket.onclose = this.onclose;
            socket.onmessage = this.onmessage;
            socket.error = this.error;
        },

        onopen : function() {
          alert("Соединение установлено.");
        },

        onclose : function(event) {
          if (event.wasClean) {
            alert('Соединение закрыто чисто');
          } else {
            alert('Обрыв соединения'); // например, "убит" процесс сервера
          }
          alert('Код: ' + event.code + ' причина: ' + event.reason);
        },

        onmessage : function(event) {            
            switch(event.status) {
                case "ready":
                    console.log("ready");
                    break;

                case "run":
                    console.log("run");
                    break;
                
                case "changeTurn":
                    console.log("changeTurn");
                    break;
                
                case "roundOver":
                    console.log("roundOver");
                    break;
                
                case "gameOver":
                    console.log("gameOver");
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
