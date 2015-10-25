define([
    'backbone'
], function(
    Backbone
){

    var gamefieldModel = Backbone.Model.extend({
        
        defaults: {
            status: "",
            turnTime: null,
            roundNum: 0,
            win: false
        },
        
        initialize : function() {
            console.log("GamefieldModel is created");
        }
        
    });
    
    return new gamefieldModel();
});
