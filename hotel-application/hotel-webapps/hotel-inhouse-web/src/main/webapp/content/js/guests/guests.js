angular.module('GuestsApp', ['GuestsStoreModule','http-auth-interceptor']).
config(function($routeProvider) {
    $routeProvider.
      when('/', {controller:GuestsListCtrl, templateUrl:'list.html'}).
      when('/edit/:guestId', {controller:GuestEditCtrl, templateUrl:'detail.html'}).
       when('/new', {controller:CreateGuestCtrl, templateUrl:'detail.html'}).
      otherwise({redirectTo:'/'});
  }).filter('prettyDocumentType', function() {
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

angular.module('GuestsApp').directive('guestApplication', function() {
    return {
      restrict: 'C',
      link: function(scope, elem, attrs) {
        //once Angular is started, remove class:
        elem.removeClass('waiting-for-angular');
        
        var login = elem.find('#login-holder');
        var main = elem.find('#content');
        
        login.hide();
        
        scope.$on('event:auth-loginRequired', function() {
          login.slideDown('slow', function() {
            main.hide();
          });
        });
        scope.$on('event:auth-loginConfirmed', function() {
          main.show();
          login.slideUp();
        });
      }
    }
  });

function GuestsListCtrl($scope, GuestsStore) {
  $scope.guests = GuestsStore.query();
}

function LoginController($scope, $http, $window, authService) {
    $scope.submit = function() {
      var toEncode = $scope.username + ":" + $scope.password;
      var encoded = $window.btoa(unescape(encodeURIComponent( toEncode )));

      $http({
        method : "POST",
        url : "/rest/login",
        headers : {
          "Authorization" : "Basic " + encoded
        } 
      }).success(function(){
    authService.loginConfirmed();
      });
    }
  }

  function CreateGuestCtrl($scope, $location, GuestsStore) {
    $scope.newGuest = true;
    
    $scope.save = function() {
      GuestsStore.save($scope.guest, function(guest) {
        $location.path('/edit/' + guest.id);
      });
    }
}

function GuestEditCtrl($scope, $routeParams, $location, GuestsStore)
{
  var self = this;

  GuestsStore.get({ id: $routeParams.guestId }, function(guest){
     self.original = guest;
    $scope.original = guest;
    $scope.guest = new GuestsStore(self.original);
  });

  $scope.isClean = function() {
    return angular.equals(self.original, $scope.guest);
  }
 
  $scope.destroy = function() {
    self.original.destroy(function() {
      $location.path('/');
    });
  };
 
  $scope.save = function() {
    $scope.guest.update(function() {
      $location.path('/');
    });
  };
}
