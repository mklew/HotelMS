var hotelms = angular.module('hotelms');

hotelms.factory('BookingSheetService', ['$http', 'routes', '$q', function($http, routes, $q){

    var dateFormat = "YYYY-MM-DD";
    var service = {};

    function getMoment(dateStr) { return moment(dateStr, dateFormat); }

    function getJSDate(dateStr) { return getMoment(dateStr).toDate(); }

    function parseReservationDatesToJSDates(reservations) {
        _.each(reservations, function(reservation){
            reservation.from = getJSDate(reservation.from);
            reservation.to = getJSDate(reservation.to);
        });
    }

    function getDatesInRange(fromDate, toDate)
    {
         var dates = [fromDate];

         var currentDate = moment(fromDate);
         for(var i = 1 ; currentDate.toDate().getTime() < toDate.getTime() ; i++)
         {
             currentDate = currentDate.add("days", 1);
             dates.push(new Date(currentDate.toDate().getTime()));
         }
         return dates;
    }

    function dayEqualTo(d1, d2)
    {
        return d1.setHours(0,0,0,0) == d2.setHours(0,0,0,0);
    }

    function Day(date, reservation)
    {
        this.getStatus = function(){ return reservation.status; };
        this.isFirstDay = function(){ return dayEqualTo(date, reservation.from) && !dayEqualTo(date, reservation.to) ; };
        this.isLastDay = function(){ return dayEqualTo(date, reservation.to) && !dayEqualTo(date, reservation.from) ; };
        this.isSingleDay = function(){ return dayEqualTo(date, reservation.from) && dayEqualTo(date, reservation.to); };
        this.reservation = reservation;
        this.isTodayAndEmpty = function(){
            return false;
        }
        this.isEmpty = false;
    }

    function EmptyDay(date) {
            this.getStatus = function(){ return null; };
            this.isFirstDay = function(){ return false; };
            this.isLastDay = function(){ return false; };
            this.isSingleDay = function(){ return false; };
            this.isTodayAndEmpty = function(){
                return dayEqualTo(date, new Date());
            }
            this.isEmpty = true;
    }

    service.isReservationAtDate = function(reservation, date)
    {
        return reservation.from.getTime() <= date.getTime() && date.getTime() <= reservation.to.getTime();
    }

    service.getRooms = function(sheetData, fromDate, toDate)
    {
        _.each(sheetData, function(room){
            parseReservationDatesToJSDates(room.reservations);
            room.days = _.map(this.getDates(fromDate, toDate), function(date){

                var day = {};

                var resOpt = _.find(room.reservations, function(reservation){
                    return this.isReservationAtDate(reservation, date);
                }, this);

                if(resOpt)
                {
                   return new Day(date, resOpt);
                }
                else {
                   return new EmptyDay(date);
                }
            }, this);

        }, this);
        return sheetData;
    }

    service.getDates = function(fromDate, toDate)
    {
        return  getDatesInRange(fromDate, toDate);
    }

    service.getSheetData = function(fromDate, toDate)
    {
        var deferred = $q.defer();
        $http({ method: "GET", url : routes.reservation.resourceRoot + "/sheetData",
            params : {
                from : fromDate.getTime(),
                to : toDate.getTime()
            }
        }).success(angular.bind(this, function(response){
            var data = {};
            data.rooms = this.getRooms(response, fromDate, toDate);
            data.dates = this.getDates(fromDate, toDate);
            deferred.resolve(data);
        })).error(angular.bind(this, function(){
            console.log('Request for datasheet failed ! ! !');
            deferred.reject();
        }));
        return deferred.promise;
    }

    return service;
}]);

hotelms.controller('ReservationsSheetCtrl', ['$scope', 'BookingSheetService', '$state', function($scope,
 BookingSheetService, $state){

    var datepicker = {
        fromDate: moment().subtract("days", 2).toDate(),
        toDate: moment().add("days", 11).toDate(),
        currentDateRange : 14,
        rangeEnabled : true
    };
    $scope.datepicker = datepicker;

    $scope.$watch("datepicker", function(newDatePicker, oldDatePicker){
        if(newDatePicker.rangeEnabled)
        {
            if(newDatePicker.fromDate.getTime() !== oldDatePicker.fromDate.getTime())
            {
                console.log('fromDate changed');
                newDatePicker.toDate = moment(newDatePicker.fromDate).add("days", newDatePicker.currentDateRange - 1).toDate();
                $scope.sheetData = BookingSheetService.getSheetData(newDatePicker.fromDate, newDatePicker.toDate);
            }
            else {
                newDatePicker.fromDate = moment(newDatePicker.toDate).subtract("days", newDatePicker.currentDateRange - 1).toDate();
                $scope.sheetData = BookingSheetService.getSheetData(newDatePicker.fromDate, newDatePicker.toDate);
            }
        }
        else if(newDatePicker.fromDate.getTime() >= newDatePicker.toDate.getTime())
        {
            newDatePicker.fromDate = oldDatePicker.fromDate;
            newDatePicker.toDate = oldDatePicker.toDate;
        }
    }, true);

    var sheetData = BookingSheetService.getSheetData(datepicker.fromDate, datepicker.toDate);

    $scope.sheetData = sheetData;

    $scope.isToday = function(date)
    {
        var todaysDate = new Date();
        return date.setHours(0,0,0,0) == todaysDate.setHours(0,0,0,0);
    }

    $scope.goToReservationAt = function(day)
    {
        if(!day.isEmpty)
        {
            $state.transitionTo('reservations.edit', { reservationId: day.reservation.reservationNumber }, true);
        }
    }

    $scope.changeRangeTo = function(ofDays)
    {
        datepicker.currentDateRange = ofDays;
        datepicker.rangeEnabled = true;
        $scope.datepicker.toDate = moment(datepicker.fromDate).add("days", ofDays - 1).toDate();

        $scope.sheetData = BookingSheetService.getSheetData(datepicker.fromDate, datepicker.toDate);
    }

    $scope.subtractOneDay = function(){
        datepicker.fromDate = moment(new Date(datepicker.fromDate)).subtract("days", 1).toDate()

        $scope.sheetData = BookingSheetService.getSheetData(datepicker.fromDate, datepicker.toDate);
    }

    $scope.addOneDay = function(){
        datepicker.toDate = moment(new Date(datepicker.toDate)).add("days", 1).toDate()
        $scope.sheetData = BookingSheetService.getSheetData(datepicker.fromDate, datepicker.toDate);
    }

}]);