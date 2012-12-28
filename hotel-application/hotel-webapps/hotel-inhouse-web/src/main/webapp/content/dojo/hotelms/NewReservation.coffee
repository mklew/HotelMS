define [
  "dojo/_base/declare"
  "dijit/_WidgetBase"
  "dijit/_OnDijitClickMixin"
  "dijit/_TemplatedMixin"
  "dijit/_WidgetsInTemplateMixin"
  "dijit/form/Button"
  "dojo/text!./templates/NewReservation.html"
  "dijit/form/Form"
  "dijit/form/FilteringSelect"
  "dijit/form/DateTextBox"
  "dijit/form/NumberTextBox"
  "dijit/form/CurrencyTextBox"
  "dojo/store/Memory"
  "dojo/request"
  "dojo/_base/lang"
  "dojo/on"
  "dojo/date/locale"
  "dijit/form/TextBox"
  "hotelms/GuestLookup"
  "dijit/form/CheckBox"
  "dijit/form/ComboBox"
  "dijit/form/SimpleTextarea"
  "dijit/layout/ContentPane"
  "dijit/form/TimeTextBox"
],
(declare, _WidgetBase, _OnDijitClickMixin, _TemplatedMixin,
_WidgetsInTemplateMixin,Button, template, Form, FilteringSelect, DateTextBox,
 NumberTextBox, CurrencyTextBox, Memory, request, lang, dojoOn, locale,
TextBox, GuestLookup, CheckBox, ComboBox, SimpleTextarea, ContentPane,
TimeTextBox) ->
  declare "hotelms.NewReservation",
  [
    _WidgetBase
    _OnDijitClickMixin
    _TemplatedMixin
    _WidgetsInTemplateMixin
  ],

  reservationForm : null

  templateString: template

  roomTypesStore: null

  roomsStore: null

  roomChooser : null

  roomTypeChooser : null

  extraBedsChooser : null

  extraBedsStore : null

  rateType : null

  rateCharge : null

  checkinDate : null

  checkoutDate : null

  findGuest : null

  methodOfPaymentChooser : null

  creditCardDetails : null

  # Reservation owner details

  ownerId : null

  firstName : null

  surname : null

  messageLabel : null # dom node

  postCreate : ->
    console.log("postCreate was called")
    # Run any parent postCreate processes - can be done at any point
    @inherited(arguments)
    return null

  startup : ->
    console.log("startup")
    request("/rest/room/types", { handleAs : "json" }).then(
      lang.hitch this, (data) ->
        @roomTypesStore = new Memory { data : data } 
        @roomTypeChooser.set "store", @roomTypesStore
      (err) -> console.log("error occured #{err}")
      (event) -> console.log("event occured #{event}")
    )

    request("/rest/room", { handleAs : "json" }).then(
      lang.hitch this, (data) ->
        @roomsStore = new Memory { data :data, idProperty : 'id' }
        @roomChooser.set 'store', @roomsStore
      (err) -> console.log("error occured #{err}")
      (event) -> console.log("event occured #{event}")
    )

    dojoOn @roomTypeChooser, "change", =>
      @roomChooser.set 'disabled', false
      @roomChooser.query.roomType = @roomTypeChooser.item.id || /.*/

    dojoOn @roomChooser, "change", =>
      @extraBedsChooser.set 'disabled', false
      max = @roomsStore.get(@roomChooser.item.id).maxExtraBeds
      newStoreData = @extraBedsStore.query().filter (item) -> item.extraBed <= max
      @extraBedsChooser.set 'store', new Memory {idProperty: 'extraBed', data: newStoreData}
      request("/rest/rates/#{@roomChooser.item.number}", { handleAs : 'json' }).then(
        lang.hitch this, (data) ->
          @rateType.set "store", new Memory({idProperty: 'name', data: data})
          @rateType.set "disabled", false
        (err) -> console.log("error occured #{err}")
        (event) -> console.log("event occured #{event}")
      )

    @rateCharge.set "readOnly", true

    dojoOn @rateType, "change", =>
      localeParserOptions = { datePattern : "yyyy-MM-dd", selector : "date" }
      checkinDateValue = @checkinDate.get "value"
      checkin = locale.format checkinDateValue, localeParserOptions
      checkoutDateValue = @checkoutDate.get "value"
      checkout = locale.format checkoutDateValue, localeParserOptions
      url = "/rest/charge/room/#{@roomChooser.item.number}/rate/#{@rateType.item.name}?checkin=#{checkin}&checkout=#{checkout}"

      request(url, { handleAs : "json" }).then(
        lang.hitch this, (data) ->
          @rateCharge.set "currency", data.currency
          @rateCharge.set "value", data.amount
        (err) -> console.log "error occured #{err}"
        (event) -> console.log "event occured #{event}"
      )

    dojoOn @methodOfPaymentChooser, "change", =>
      chosenMethod = @methodOfPaymentChooser.get "value"
      @creditCardDetails.domNode.style.display = if chosenMethod is "creditCard" then "block" else "none"

    @reservationForm.onSubmit = (event) =>
      console.log "submit event triggered"
      event.preventDefault()
      return false unless @reservationForm.validate()
      console.log "form is valid and can be submitted further"
      formData = @reservationForm.get 'value'
      # formData has dates as objects and not strings, need to change that.
      formData.checkin = @checkinDate.valueNode.value
      formData.checkout = @checkoutDate.valueNode.value
      console.log "DateTextBox widget, checkinDate"
      console.log @checkinDate
      console.log formData
      console.log "now it is time to send xhr"

      promise = request "/rest/reservations/", 
        method : "POST"
        data : formData
      promise.then(
        (resp) ->
          console.log "got response"
          console.log "promise"
          console.log promise
          console.log "reponse"
          console.log resp 
          headers = promise.response.getHeaders "ERROR-MESSAGE"
          console.log "headers"
          console.log headers

        (err) -> 
          console.log "error occured #{err}"
          console.log "promise"
          console.log promise
          # headers = promise.response.getHeaders()
          # console.log headers
        (event) -> console.log "event occured #{event}"
      )
      return false

    @findGuest.callbackWhenGuestFound = (guest) =>
      console.log "Found guest, time to populate form with his details"
      console.log guest
      @firstName.set "value", guest.firstName
      @surname.set "value", guest.surname
      @ownerId.set "value", guest.id
      # TODO populate rest of guest details

    @findGuest.startup()
    return null
