define([
    'backbone'
], function(
    Backbone
){

    var cellModel = Backbone.Model.extend({
        
        defaults: {
            //cell may be blue, orange or undefined
            cell: "", 
            //column__line
            place: ""        
        },
        
        initialize : function() {
            console.log("CellModel is created");
        }
        
    });
    
    return cellModel;
});
