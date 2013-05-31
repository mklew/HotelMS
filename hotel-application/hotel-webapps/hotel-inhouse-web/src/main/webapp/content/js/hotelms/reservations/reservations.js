var hotelms = angular.module('hotelms')

hotelms.factory('Reservation',['$resource', '$http', 'routes', function($resource, $http, routes){
    var Reservation = $resource(routes.reservation.resourceRoot + '/:id',
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
      url = routes.reservation.resourceRoot + '/' + that.reservationId + '/' + operation;
      $http({method: 'POST', url: url}).success(function() {
          var f = (cb || angular.noop);
          f.apply();
        });
    }

    return Reservation;

}]);

hotelms.controller('ReservationsBrowseList', ['$scope', '$location', '$http', 'Reservation', '$state',
function ($scope, $location, $http, Reservation, $state) {

    $scope.reservations = Reservation.query();

    function currentReservation(index)
    {
        return $scope.reservations[index];
    }

    function goToReservationsBrowse(){
        $scope.reservations = Reservation.query();
    }

    $scope.checkIn = function(index)
    {
        var reservation = currentReservation(index);
        reservation.checkIn(goToReservationsBrowse);
    }

    $scope.checkOut = function(index)
    {
        var reservation = currentReservation(index);
        reservation.checkOut(goToReservationsBrowse);
    }

    $scope.cancel = function(index)
    {
        var reservation = currentReservation(index);
        reservation.cancel(goToReservationsBrowse);
    }

    $scope.destroy = function(index)
    {
        var reservation = currentReservation(index);
        reservation.destroy(function(){
            $scope.reservations = Reservation.query();
        });
    }

    $scope.hasPerm = function(perm)
    {
      $http.get('/rest/perm', {params : {"perm" : perm}}).success(function (){
        $scope._canDelete = true;
      }).error(function (){
        $scope._canDelete = false;
      });
    }

    $scope._canDelete = undefined;

    $scope.canDelete = function()
    {
        if(angular.isUndefined($scope._canDelete))
        {
            $scope.hasPerm('reservation:delete');
        }
        return $scope._canDelete;
    }
}]);

hotelms.controller('ReservationEditCtrl', ['$scope', '$stateParams', '$http', 'Reservation', '$state',
function ($scope, $stateParams, $http, Reservation, $state)
{

//  $scope.chargesCalculated = false;


}]);

hotelms.controller('ReservationsWithStatusCtrl', ['$scope', '$state', 'RequestWithPromise', 'routes',
 function($scope, $state, RequestWithPromise, routes){

     $scope.reservations = RequestWithPromise.req({
        method : "GET",
        url : routes.reservation.resourceRoot + '/status/' + $state.current.data.status
     });
 }]);

