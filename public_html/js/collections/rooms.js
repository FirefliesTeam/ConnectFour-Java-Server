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
            this.add([ 
                { "holderName" : "Vasyaaaa", "createDate": dateStr },
                { "holderName" : "Pauvel", "createDate": dateStr },
                { "holderName" : "Ann", "createDate": dateStr }    
             
            ])
        },
        
    });
        
    return new Collection();
});
