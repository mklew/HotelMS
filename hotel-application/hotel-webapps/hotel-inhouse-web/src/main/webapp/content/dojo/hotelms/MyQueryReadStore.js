define([
    "dojo/_base/declare",
    "dojox/data/QueryReadStore"
], function (declare, QueryReadStore) {
    return declare("hotelms.MyQueryReadStore", QueryReadStore, {

        // The constructor
        constructor: function (args) {
            declare.safeMixin(this, args);
        },

        fetch: function (request) {
            request.serverQuery = {q: request.query.name};
            return this.inherited("fetch", arguments);
        }
    });
});