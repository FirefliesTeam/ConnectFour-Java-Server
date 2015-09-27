define([
    'backbone'
], function(
    Backbone
){

    var Model = Backbone.Model.extend({
        
        defaults: {
            //Gamer name
            name: "",            
            //Number of person's wins
            score: 0,  
        },
        
        initialize : function() {
            console.log("ScoreModel is created");
        }
        
    });
    
    return Model;
});
