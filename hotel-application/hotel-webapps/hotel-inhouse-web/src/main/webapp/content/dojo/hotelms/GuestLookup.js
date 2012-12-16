define([
    "dojo/_base/declare",
    "dijit/_WidgetBase",
    "dijit/_TemplatedMixin",
    "dijit/_WidgetsInTemplateMixin",
    "dojo/text!./templates/GuestLookup.html",
    "dijit/form/FilteringSelect",
    "dojo/_base/lang",
    "dojo/on",
    "hotelms/MyQueryReadStore",
    "dojo/store/JsonRest",
    "dojo/_base/lang",
    "dojo/store/Memory"
], function(declare, _WidgetBase, _TemplatedMixin,
            _WidgetsInTemplateMixin, template, FilteringSelect, lang, on, MyQueryReadStore, JsonRest, lang, Memory) {

    return declare("hotelms.GuestLookup", [_WidgetBase, _TemplatedMixin, _WidgetsInTemplateMixin
    ], {
        templateString: template,

        url : null,

        _findGuestSelect : null,

        _debugBtn : null,

        // The constructor
        constructor: function(args){
            declare.safeMixin(this,args);
        },

        postCreate : function()
        {
            console.log("postCreate was called");

            // Run any parent postCreate processes - can be done at any point
            this.inherited(arguments);
        },

        startup : function()
        {
            var store = new JsonRest({ target : this.url });
            console.log("query read store url is:" + this.url);
            this._findGuestSelect.set("searchAttr", "displayed");

            // debug
            this.store = store;

            on(this._debugBtn, "click", lang.hitch(this,function(){
                console.log("debug button clicked");
                console.log(this.store);
            }));

            on(this._findGuestSelect, "keydown", lang.hitch(this, function(){
               console.log("ktos cos wpisal");
               console.log(this._findGuestSelect.get("textbox").value);
               this.store.query("?q="+this._findGuestSelect.get("textbox").value).then( lang.hitch(this,function(results){
                console.log(results);
                var memStore = new Memory({idProperty: "id", data : results})
                this._findGuestSelect.set("store", memStore);
                }));
            }));
        }
    });
});