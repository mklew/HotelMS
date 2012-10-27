define([
    "dojo/_base/declare",
], function(declare){
    return declare("hotelms.ReservationWrapper", null, {

    	starts : false,
    	ends : false,
    	reservation : undefined,

	    // The constructor
	    constructor: function(args){
	        declare.safeMixin(this,args);
	    },

        getLabel : function()
        {
            return this.reservation.firstName + " " + this.reservation.lastName;
        }
    });
});