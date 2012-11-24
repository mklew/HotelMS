define(["dojo/request", "dojo/dom-construct", "dijit/layout/ContentPane", "dojo/io-query", "dojo/json"],
function(request, domConstruct, ContentPane, ioQuery, json){
     return {
        tab : function(urlToView, options){
            request(urlToView).then(function(data){
                var dataDOM = domConstruct.toDom(data);
                options.content = dataDOM;
                var cp = new ContentPane(options);
                MainTabContainer.addChild(cp);
                if(options && options.script == true)
                {
                    request(urlToView + 'Script', { handleAs: 'javascript' }).then(function(data){
                        console.log('retrived script');
                        }, function(err){
                             console.log(err);
                        }, function(event){
                             console.log(event);
                        });
                }
                }, function(err){
                    console.log(err);
                }, function(event){
                    console.log(event);
            });
        },

        requestJson : function(urlToView, queryHash, callback){
          var queryStr = ioQuery.objectToQuery(queryHash);
          request(urlToView, { query : queryStr, handleAs : 'text'}).then(function(data){
                console.log('received data');
                console.log(data);
                var obj = json.parse(data);
                console.log('parsed data to json obj:');
                console.log(obj);
                console.log('calling callback');
                callback(data);
          }, function(err){
                console.log("ERROR OCCURRED");
                console.log(err);
           }, function(evt){
                console.log(evt);
           });
        }
     };
});