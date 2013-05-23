var hotelms = angular.module('hotelms')

hotelms.controller('DashboardController', ['$scope', 'routes', '$http', function($scope, routes, $http){


    $http.get(routes.room.resourceRoot + '/stats').success(function(stats){
        $scope.roomStats = stats;
        var ctx = document.getElementById("roomStatusChart").getContext("2d");
        var data = [
                    {
                        value: stats.available,
                        color:"#00B74A"
                    },
                    {
                        value : stats.unavailable,
                        color : "#FF3100"
                    }
                 ]
        var myNewChart = new Chart(ctx).Pie(data);
    });

    $http.get(routes.reservation.resourceRoot + '/stats').success(function(stats){
        $scope.reservationStats = stats;
        var ctx = document.getElementById("reservationChart").getContext("2d");
        var resData = {
            labels : ["In house","Check-ins","Check-outs"],
            datasets : [
                {
                    fillColor : "rgba(220,220,220,0.5)",
                    strokeColor : "rgba(220,220,220,1)",
                    data : [stats.inhouse,stats.checkin,stats.checkout]
                }
            ]
        }
        new Chart(ctx).Bar(resData, {
            scaleOverride : true,
            scaleSteps : 15,
            scaleStepWidth : 1,
            scaleStartValue : 0
        });
    });

}])