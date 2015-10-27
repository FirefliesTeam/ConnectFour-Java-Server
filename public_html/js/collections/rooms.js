define([
    'backbone',
    'models/room'
], function(
    Backbone,
    Room
){

    var Collection = Backbone.Collection.extend({
        model: Room, 
        initialize: function() {
            date = new Date(Date.now());
            dateStr = date.toDateString() + " " + date.toTimeString().substr(0, 8);

        },
        
        addRooms: function(roomHolders) {
            var models = [];
            for(var i = 0; i < roomHolders.length; i++) {
                models.push({ holderName : roomHolders[i] });
            }
            this.set(JSON.stringify(models));
        }  
              
    });
        
    return new Collection();
});
