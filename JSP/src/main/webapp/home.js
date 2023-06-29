

{

    let homePage = document.getElementById("homePage");
    let acquistoPage = document.getElementById("acquistoPage");
    let vendoPage = document.getElementById("vendoPage");
    let placeBidButton = document.getElementById("placeBidButton");


    window.addEventListener("load", function(){
        new BindObjects();
        let acquistoPageUtils = new AcquistoPageUtils();
        let vendoPageUtils =  new VendoPageUtils();
        let auctionDetailsPageUtils =  new AuctionDetailsPageUtils();

        if(findCookieValue("lastAction") === "VENDO"){
            showPageX("vendoPage");
        }
        else{
            showLastSeenAuctions();
        }



    }, false);


    function BindObjects(){
        var menu_buttons = document.getElementsByClassName("menu_button");
        var home_buttons = document.getElementsByClassName("home_button");

        Array.prototype.forEach.call(menu_buttons, (menu_button => {
            if(menu_button.getAttribute("name") !== "LogOut"){
                menu_button.addEventListener("click", function (e){
                    showPageX(menu_button.getAttribute("name"));
                });
            }
            else{
                menu_button.addEventListener("click", function (e){
                    makeCall("GET", "LogOut", null, function(req){
                        if(req.readyState === 4 && req.status === 200){
                            if(req.responseText === "LogOutSuccessful"){
                                window.location.href = "index.html";
                            }
                        }
                    });
                });
            }
        }));

        Array.prototype.forEach.call(home_buttons, (home_button => {
            home_button.addEventListener("click", function (e){
                showPageX(home_button.getAttribute("name"));
            });
        }));


        document.getElementById("submitQuery").addEventListener("click", function (e){
            e.preventDefault();
            json = {
                'query' : document.getElementById('query').value
            }

            acquistoPageUtils.getData(json);
        });

        document.getElementById("createArticleButton").addEventListener("click", function (e){
            let form1 = e.target.closest("form");
            if(form1.checkValidity()){
                e.preventDefault();
                makeCall("POST", "CreateArticle", e.target.closest("form"), function(req){
                    if (req.readyState === 4) {
                        if (req.status === 200) {
                            let articleCreated = req.responseText;
                            if (articleCreated === "Success") {
                                showPageX("vendoPage");
                                setCookie("lastAction", "ACQUISTO", 31);
                            }
                            else{
                                document.getElementById("errorInCreateArticle").textContent = 'Something went wrong - please check your submission';
                            }
                        }
                    }});
            }
        }, false);

        document.getElementById("createAuctionButton").addEventListener("click", function (e){
            let form2 = e.target.closest("form");
            if(form2.checkValidity()){
                e.preventDefault();
                makeCall("POST", "CreateAuction", e.target.closest("form"), function(req){
                    if (req.readyState === 4) {
                        if (req.status === 200) {
                            let auctionCreated = req.responseText;
                            if (auctionCreated === "Success") {
                                showPageX("vendoPage");
                                setCookie("lastAction", "VENDO", 31);
                            }
                            else{
                                document.getElementById("errorInCreateAuction").textContent = 'Something went wrong - please check your submission';
                            }
                        }
                    }});
            }
        }, false);

        placeBidButton.addEventListener("click", function(e){
            e.preventDefault();

            json = {
                'idAsta': document.getElementById("idAstaPlaceBid").value,
                'bid' : document.getElementById("bid").value
            }

            document.getElementById("placeABidForm").reset();
            makeCallParams("GET", "AddOffer", generateReqParametersFromJson(json), function(req){
                if(req.readyState === 4 && req.status === 200){
                    if(req.responseText !== "Error"){
                        showPageX("auctionDeatils", req.responseText);
                        setCookie("lastAction", "ACQUISTO", 31);
                    }
                    else document.getElementById("errorBid").textContent = "Bid Not Valid";
                }
            });
        });

        document.getElementById("closeButton").addEventListener("click", function(e){
            e.preventDefault();

            json = {
                'idAsta': document.getElementById("idAstaPlaceBid").value
            }

            makeCallParams("GET", "CloseAuction", generateReqParametersFromJson(json), function(req){
                if(req.readyState === 4 && req.status === 200){
                    if(req.responseText !== "Error"){
                        showPageX("vendoPage");
                        setCookie("lastAction", "ACQUISTO", 31);
                    }
                    else document.getElementById("errorClose").textContent = "Something went wrong while closing - please retry";
                }
            })
        });


    }

    function AcquistoPageUtils(){

         this.getData = function (_paramsData = {
                                                                'query' : ""
                                                                }){
             //document.getElementById("query").getAttribute("value")
             let lastSeen = false;
             if(_paramsData === "lastSeen"){
                 _paramsData = {
                     'query' : ""
                 };
                 lastSeen = findCookieValue("lastSeen" + sessionStorage.getItem("userID"));
             }
             makeCallParams("GET", "Acquisto", generateReqParametersFromJson(_paramsData), function(req){
                 if(req.readyState === 4 && req.status === 200){
                     var openAuctionTable = document.getElementById("asteAperteAcquisto");
                     var closedAuctionTable = document.getElementById("asteChiuseAcquisto");

                     while(openAuctionTable.children.length >= 2){
                         openAuctionTable.removeChild(openAuctionTable.lastChild);
                     }
                     while(closedAuctionTable.children.length >= 2){
                         closedAuctionTable.removeChild(closedAuctionTable.lastChild);
                     }
                     const JsonResp = JSON.parse(req.responseText);

                     createTableRowsAsteAperte(openAuctionTable, JSON.parse(JsonResp["asteAperte"]), false, lastSeen);
                     createTableRowsAsteChiuse(closedAuctionTable, JSON.parse(JsonResp["asteChiuse"]), false);

                 }
             });
         }
    }
    function VendoPageUtils(){


        this.getData = function (){
            //document.getElementById("query").getAttribute("value")
            makeCall("GET", "Vendo", null, function(req){
                if(req.readyState === 4 && req.status === 200){
                    var openAuctionTable = document.getElementById("asteAperteVendo");
                    var closedAuctionTable = document.getElementById("asteChiuseVendo");
                    var sellableArticles = document.getElementById("sellableArticlesList");

                    while(openAuctionTable.children.length >= 2){
                        openAuctionTable.removeChild(openAuctionTable.lastChild);
                    }
                    while(closedAuctionTable.children.length >= 2){
                        closedAuctionTable.removeChild(closedAuctionTable.lastChild);
                    }
                    while(sellableArticles.children.length >= 2){
                        sellableArticles.removeChild(sellableArticles.lastChild);
                    }

                    const JsonResp = JSON.parse(req.responseText);

                    createTableRowsAsteAperte(openAuctionTable, JSON.parse(JsonResp["asteAperte"]), true);
                    createTableRowsAsteChiuse(closedAuctionTable, JSON.parse(JsonResp["asteChiuse"]), true);
                    createTableRowsArticles(sellableArticles, JSON.parse(JsonResp["sellableArticles"]), true);
                }
            });
        }


    }
    function AuctionDetailsPageUtils(){

        this.getData = function (_idAsta){
            //document.getElementById("query").getAttribute("value")
            json = {
                'idAsta' : _idAsta
            }

            document.getElementById("idAstaPlaceBid").value = _idAsta;

            makeCallParams("GET", "AuctionDetail", generateReqParametersFromJson(json), function(req){
                if(req.readyState === 4 && req.status === 200){

                    var articlesList = document.getElementById("articleList");
                    var offersList = document.getElementById("offersList");
                    var auctionInfoDiv = document.getElementById("infoDiv");



                    while(offersList.children.length >= 2){
                        offersList.removeChild(offersList.lastChild);
                    }
                    while(articlesList.children.length >= 2){
                        articlesList.removeChild(articlesList.lastChild);
                    }
                    while(auctionInfoDiv.children.length > 0){
                        auctionInfoDiv.removeChild(auctionInfoDiv.lastChild);
                    }

                    const JsonResp = JSON.parse(req.responseText);


                    var user = JSON.parse(JsonResp.user);
                    var auction = JSON.parse(JsonResp.auction);

                    if(user.id === auction.idCreatore && auction.idVincitore < 0 && auction.oreRimanenti >= 0){
                        document.getElementById("closeButton").removeAttribute("class");
                        document.getElementById("closeButton").classList.add("shown_content");
                    }

                    if(user.id !== auction.idCreatore && auction.oreRimanentiFormatted !== "0d 0h"){
                        document.getElementById("placeABidForm").removeAttribute("class");
                        document.getElementById("placeABidForm").classList.add("shown_content");
                    }

                    createTableRowsArticles(articlesList, JSON.parse(JsonResp["articles"]), false);
                    createTableRowsOffers(offersList, JSON.parse(JsonResp["offers"]));
                    generateAuctionDescription(auctionInfoDiv, auction);

                }
            });
        }
    }
}