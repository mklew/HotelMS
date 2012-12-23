define [
  "dojo/_base/declare"
  "dijit/_WidgetBase"
  "dijit/_TemplatedMixin"
  "dijit/_WidgetsInTemplateMixin"
  "dojo/text!./templates/GuestLookup.html"
  "dijit/form/FilteringSelect"
  "dojo/_base/lang"
  "dojo/on"
  "dojo/store/JsonRest"
  "dojo/store/Memory"
],
(declare, _WidgetBase, _TemplatedMixin,_WidgetsInTemplateMixin, template,
FilteringSelect, lang, dojoOn, JsonRest, Memory) ->
  declare "hotelms.GuestLookup",
  [
    _WidgetBase
    _TemplatedMixin
    _WidgetsInTemplateMixin
  ],

  templateString: template

  url : null

  _findGuestSelect : null

  _debugBtn : null

  constructor : (args) ->
    declare.safeMixin this, args

  postCreate : ->
    console.log("postCreate was called");
    #Run any parent postCreate processes - can be done at any point
    @inherited arguments

  startup : ->
    store = new JsonRest { target : @url }
    console.log "Query read store url is: #{@url}"
    @_findGuestSelect.set "searchAttr", "displayed"

    # debug
    @store = store

    dojoOn @_debugBtn, "click", =>
      console.log "debug button clicked"
      console.log @store

    dojoOn @_findGuestSelect, "keydown", =>
      console.log "ktos cos wpisal"
      console.log (@_findGuestSelect.get "textbox").value
      (@store.query "?q=#{@_findGuestSelect.get("textbox").value}").then (results) =>
        console.log results
        memStore = new Memory { idProperty : "id", data : results }
        @_findGuestSelect.set "store", memStore

    return null
