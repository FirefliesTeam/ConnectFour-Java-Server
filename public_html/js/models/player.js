define([
    'backbone'
], function(
    Backbone
){

    var playerModel = Backbone.Model.extend({
        
        defaults: {
            name : "",
            auth : false
        },
        
        initialize : function() {
            console.log("PlayerModel is created");
        }
        
    });
    
    return playerModel;
});
