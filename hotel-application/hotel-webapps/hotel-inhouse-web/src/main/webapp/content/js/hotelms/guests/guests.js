var hotelms = angular.module('hotelms');

hotelms.filter('prettyDocumentType', function() {
           return function(input) {
             if(input === "driverLicense")
               return "Driver License";
             else if(input === "personalID")
               return "Personal ID";
             else if(input === "passport")
               return "Passport";
             else return input;
           }
         });

hotelms.controller('GuestsListCtrl', ['$scope', 'GuestsStore', function ($scope, GuestsStore) {
   $scope.guests = GuestsStore.query();
}]);


hotelms.controller('CreateGuestCtrl', ['$scope', 'GuestsStore', '$state', function ($scope, GuestsStore, $state) {
  $scope.newGuest = true;

  $scope.save = function() {
    GuestsStore.save($scope.guest, function(guest) {
      $state.transitionTo('guests.edit', { guestId: guest.id }, true);
    });
  }
}])

hotelms.controller('GuestEditCtrl', ['$scope', '$stateParams', '$state', 'GuestsStore',
function ($scope, $stateParams, $state, GuestsStore)
{
  var self = this;

  GuestsStore.get({ id: $stateParams.guestId }, function(guest){
     self.original = guest;
    $scope.original = guest;
    $scope.guest = new GuestsStore(self.original);
  });

  $scope.isClean = function() {
    return angular.equals(self.original, $scope.guest);
  }

  $scope.destroy = function() {
    self.original.destroy(function() {
      $state.transitionTo('guests.list')
    });
  };

  $scope.save = function() {
    $scope.guest.update(function() {
        $state.transitionTo('guests.list')
    });
  };
}]);

