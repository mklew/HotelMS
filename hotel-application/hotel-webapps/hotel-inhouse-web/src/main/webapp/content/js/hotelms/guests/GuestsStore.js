var hotelms = angular.module('hotelms');

hotelms.factory('GuestsStore',['$resource', function($resource) {
  var GuestsStore = $resource('/rest/guests/guest/:id',
                          {},
                          {update : {method : "PUT"}}
  );

  GuestsStore.prototype.update = function(cb) {
      console.log(this);
      console.log(angular.extend({}, this, {id:undefined}));

      // justPropertiesToPayload(this);

      return GuestsStore.update({id: this.id},
          angular.extend({}, this, {id:this.id}), cb);
  };

    GuestsStore.prototype.destroy = function(cb) {
      return GuestsStore.remove({id: this.id}, cb);
    };

    function justPropertiesToPayload(resource)
    {
      var out = {}
      for (var prop in resource) {
          if( resource.hasOwnProperty( prop ) ) {
              var str = '' + prop;
              if(str[0] !== '$' && !(angular.isFunction(resource[prop])))
              {
                  out[str] = resource[prop];
              }
              // if( resource[prop] )
              // result += objName + "." + prop + " = " + obj[prop] + "\n";
          }
          }
          console.log(out);
          console.log($.param(out));
          return $.param(out);
    }

  return GuestsStore;
}]);