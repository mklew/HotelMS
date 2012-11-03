define(["dojo/request", "dojo/dom-construct", "dijit/layout/ContentPane", "dojo/io-query"],
function(request, domConstruct, ContentPane, ioQuery){
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

        requestJson : function(urlToView, queryHash){
          var queryStr = ioQuery.objectToQuery(queryHash);
          request.post(urlToView, { query : queryStr, handleAs : 'json'}).then(function(data){
                return data;
           }, function(err){
                console.log(err);
           }, function(evt){
                console.log(evt);
           });
        }
     };
});