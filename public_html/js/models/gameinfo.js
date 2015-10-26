define([
    'backbone'
], function(
    Backbone
){

    var gamefieldModel = Backbone.Model.extend({
        
        defaults: {
            status: "",
            turnTime: 20,
            roundNum: 0,
            win: null
        },
        
        initialize : function() {
            console.log("GamefieldModel is created");
        }
        
    });
    
    return new gamefieldModel();
});
