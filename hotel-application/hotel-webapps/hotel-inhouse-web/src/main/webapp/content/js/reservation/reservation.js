angular.module('reservationResource', ['ngResource']).
  factory('Reservation', function($resource, $http) {
    var Reservation = $resource('/rest/reservations/:id',
					    	{},
					    	{update : {method : "PUT"}}
	);

    Reservation.prototype.update = function(cb) {
        return Reservation.update({id: this.reservationId},
            angular.extend({}, this, {id:this.reservationId}), cb);
    };
 
	  Reservation.prototype.destroy = function(cb) {
	    return Reservation.remove({id: this.reservationId}, cb);
	  };

	  Reservation.prototype.checkIn = function(cb)
    {
        _StatusChange(this, 'checkIn', cb);
    }

    Reservation.prototype.checkOut = function(cb)
    {
        _StatusChange(this, 'checkOut', cb);
    }

    Reservation.prototype.cancel = function(cb)
    {
        _StatusChange(this, 'cancel', cb);
    }

    function _StatusChange(that, operation, cb)
    {
      var url;
      url = '/rest/reservations/' + that.reservationId + '/' + operation;
      $http({method: 'POST', url: url}).
        success(function(data, status, headers, config) {
          (cb || angular.noop)();
        });
    }


    return Reservation;
});