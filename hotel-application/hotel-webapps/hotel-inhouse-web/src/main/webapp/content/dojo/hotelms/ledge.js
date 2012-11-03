define(["dojo/request", "dojo/dom-construct", "dijit/layout/ContentPane"], function(request, domConstruct, ContentPane){
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
        }
     };
});