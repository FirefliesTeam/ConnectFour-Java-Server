define([
    'backbone',
    'models/cell'
], function(
    Backbone, 
    Cell
){
    var gamefieldModel = Backbone.Model.extend({     
        field: new Array(),
        initialize : function() {
            console.log("gamefieldModel is created");
            this.fill();
        },
        
        fill : function() {
            columns = 7;
            rows = 6;
            this.field = Array();
            for(var i=0; i<columns; i++){
                this.field[i] = new Array();
                for(var j=0; j<rows; j++){
                    rand = Math.random();
                    if(rand < 0.5) {
                        this.field[i][j] = new Cell({ cell:"column__cell-blue", place: "{i}__{j}" });
                    } else {
                        this.field[i][j] = new Cell({ cell:"column__cell-orange", place: "{i}__{j}" });
                    }
                }
            }

        }
        
    });
    
    return  new gamefieldModel();
});
