var hotelms = angular.module('hotelms')

hotelms.controller('NightauditCtrl', ['$scope','routes', 'RequestWithPromise', function($scope, routes, RequestWithPromise){

    $scope.runNightAudit = function()
    {
        RequestWithPromise.req({
                method : "POST",
                url : routes.nightaudit.resourceRoot
        }).then(function(auditResults){
            $scope.auditResults = auditResults;
            $scope.isRunning = false;
            $scope.isDone = true;
        })
        $scope.isRunning = true;
    }

}]);