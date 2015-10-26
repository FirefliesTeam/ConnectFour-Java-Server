define([
    'backbone',
    'tmpl/rooms',
    'collections/rooms',
    'views/abstract/baseView',
    'models/player'
], function(
    Backbone,
    tmpl,
    Rooms,
    baseView,
    player
){

    var RoomsView = Backbone.View.extend({
        __proto__: baseView,
        events: {
            'show': 'show'
        },
        el: '.rooms',
        template: tmpl,
        model: Rooms,
        
        initialize: function () {
            console.log("RoomsView has been created");
            this.render();
            this.hide();
        },
        
        render: function () {
            this.$el.html(this.template(this.model.toJSON()));
        }

    });

    return new RoomsView();
});
