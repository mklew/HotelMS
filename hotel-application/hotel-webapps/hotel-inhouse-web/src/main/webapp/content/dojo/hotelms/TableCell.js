define([
    "dojo/_base/declare",
    "dojo/date",
    "dojo/date/locale",
    "./ReservationWrapper",
    "dojo/dom-construct",
    "dojo/dom-class"
], function(declare, date, locale, ReservationWrapper, domConstruct, domClass){
    return declare("hotelms.TableCell", null, {
    	
    	cellDate : undefined,
    	
    	hasReservations : false,
    	
    	overbooked : false,
    	
    	wrappedReservations : undefined,
	    
    	roomName : undefined,

    	isRoom : false,

    	isFirst : false,

	    // The constructor
	    // cellDate needs to be passed in constructor
	    constructor: function(args){
	        declare.safeMixin(this,args);
	        this.wrappedReservations = [];
	    },

	    setRoom : function(roomName)
	    {
	    	this.roomName = roomName;
	    	this.isRoom = true;
	    },
	    
	    addReservation : function(res)
	    {
	    	if(this.wrappedReservations.length > 0)
	    		this.overbooked = true;
	    	this.hasReservations = true;

	    	// calculate flags..
	    	startsFlag = date.compare(res.from, this.cellDate, "date") == 0;
	    	endsFlag = date.compare(res.to, this.cellDate, "date") == 0;
	    	console.log(this.cellDate);
	    	var wrapper = new ReservationWrapper({starts : startsFlag, ends : endsFlag,
	    										 reservation : res});
	    	console.log(wrapper);
	    	this.wrappedReservations.push(wrapper);
	    },
	    
	    belongsTo : function(reservation)
	    {
	    	var r1 = date.compare(reservation.from, this.cellDate, "date");
	    	if(r1 == 1)
	    		return false;
	    	var r2 = date.compare(reservation.to, this.cellDate, "date");
	    	if(r2 != -1)
	    		return true;
	    },

	    buildTdDOM : function()
	    {
	    	var td = domConstruct.create("td");
			if (this.isRoom)
			{
				td.innerHTML = this.roomName;
				return td;
			}	    	

	    	if(this.hasReservations){
	    		console.log("i am here");
	    		return this._buildTdWithReservation(td);
	    	}
	    	else
	    	{
	    		return td;
	    	}
	    },

	    _buildTdWithReservation : function(td)
	    {
	    	if(this.overbooked)
	    	{
	    		return this._buildOverbooked(td);
	    	}
	    	else
	    	{
	    		// has single reservation
	    		var wrapper = this.wrappedReservations[0];
	    		domClass.add(td, wrapper.reservation.status);
	    		if (wrapper.starts && wrapper.ends)
	    		{
	    			domClass.add(td, "single-day");
	    			td.innerHTML = wrapper.getLabel();
	    		}
	    		else if(wrapper.starts)
	    		{
	    			domClass.add(td, "round-only-left");
	    			td.innerHTML = wrapper.getLabel();
	    		}
	    		else if(wrapper.ends)
	    		{
	    			domClass.add(td, "round-only-right");
	    		}
	    		else if(this.isFirst)
	    		{
	    			td.innerHTML = wrapper.getLabel();
	    		}
	    		return td;
	    	}	
	    },

	    _buildOverbooked : function(td)
	    {
	    		// TODO
	    		// current implementation handles only overbooking of two reservation, if there are more than two
	    		// then I don't support this and tbh don't know how it should look.
	    		var wrapper = this.wrappedReservations[1];
	    		if (wrapper.starts && wrapper.ends)
	    		{
	    			domClass.add(td, "single-day");
	    			td.innerHTML = wrapper.getLabel();
	    		}
	    		else if(wrapper.starts)
	    		{
	    			td.innerHTML = wrapper.getLabel();
	    		}
	    		domClass.add(td, "overbooked");
	    		return td;
	    }
    });
});