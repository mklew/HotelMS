define([
    "dojo/_base/declare",
    "dijit/_WidgetBase",
    "dijit/_OnDijitClickMixin",
    "dijit/_TemplatedMixin",
    "dijit/_WidgetsInTemplateMixin",
    "dijit/form/Button",
    "dojo/text!./templates/NewReservation.html",
    "dijit/form/Form",
    "dijit/form/FilteringSelect",
    "dijit/form/DateTextBox",
    "dijit/form/NumberTextBox",
    "dojo/store/Memory",
    "dojo/request",
    "dojo/_base/lang",
    "dojo/on"
], function(declare, _WidgetBase, _OnDijitClickMixin, _TemplatedMixin,
            _WidgetsInTemplateMixin, Button, template, Form, FilteringSelect,
            DateTextBox, NumberTextBox, Memory, request, lang, on) {

    return declare("hotelms.NewReservation", [_WidgetBase, _OnDijitClickMixin,
        _TemplatedMixin, _WidgetsInTemplateMixin
    ], {
        templateString: template,

        roomTypesStore: null,

        roomsStore: null,

        roomChooser : null,

        roomTypeChooser : null,

        extraBedsChooser : null,

        extraBedsStore : null,

        rateType : null,

        postCreate : function()
        {
            console.log("postCreate was called");

            // Run any parent postCreate processes - can be done at any point
            this.inherited(arguments);
        },

        startup : function()
        {
            console.log("startup");
            request("/rest/room/types", { handleAs : 'json' }).then(lang.hitch(this, function(data){
                this.roomTypesStore = new Memory({data : data});
                this.roomTypeChooser.store = this.roomTypesStore;
                console.log(this.roomTypeChooser);
            }), function(err){
                console.log(err);
            }, function(event){
                console.log(event);
            });

            request("/rest/room", { handleAs : 'json' }).then(lang.hitch(this, function(data){
                this.roomsStore = new Memory({data : data, idProperty: 'id'});
                this.roomChooser.store =  this.roomsStore;
            }), function(err){
                console.log(err);
            }, function(event){
                console.log(event);
            });

            on(this.roomTypeChooser, "change", lang.hitch(this, function(){
                this.roomChooser.set('disabled', false);
                this.roomChooser.query.roomType = this.roomTypeChooser.item.id || /.*/;
            }));

            on(this.roomChooser, "change", lang.hitch(this, function(){
                this.extraBedsChooser.set('disabled', false);
                var max = this.roomsStore.get(this.roomChooser.item.id).maxExtraBeds;
                var newStoreData = this.extraBedsStore.query().filter(function(item){
                    return item.extraBed <= max;
                });
                this.extraBedsChooser.set('store', new Memory({idProperty: 'extraBed', data: newStoreData}));

                request("/rest/rates/" + this.roomChooser.item.number, { handleAs : 'json' }).then(lang.hitch(this, function(data){
                    this.rateType.set("store", new Memory({idProperty: 'name', data: data})).set("disabled", false);
                }), function(error){
                    console.log("error occured");
                    console.log(error);
                }, function(evt){
                    console.log("event occured");
                });
            }));
        }
    });
});