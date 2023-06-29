{
function showPageX(_pageName, _param = ""){
    Array.prototype.forEach.call(document.body.childNodes, (elem => {
        if(elem.nodeName.toLowerCase() === "div"){
            elem.removeAttribute("class");
            if(elem.getAttribute("id") === _pageName){
                elem.classList.add("shown_content");
            }
            else{
                elem.classList.add("hidden_content");
            }
        }
    }));

    if(_pageName === "acquistoPage"){
        new AcquistoPageUtils().getData(_param);
    }
    if(_pageName === "vendoPage"){
        new VendoPageUtils().getData();
    }
    if(_pageName === "auctionDetails"){
        new AuctionDetailsPageUtils().getData(_param);
    }
}

function makeCall(method, url, formElement, cback, reset = true) {
    let req = new XMLHttpRequest(); // visible by closure
    req.onreadystatechange = function() {
        cback(req);
    }; // closure
    req.open(method, url);
    if (formElement === null) {
        req.send();
    } else {
        req.send(new FormData(formElement));
    }
    if (formElement !== null && reset === true) {
        formElement.reset();
    }
}

function makeCallParams(method, url, data, callback) {
    let req = new XMLHttpRequest(); // visible by closure
    req.onreadystatechange = function() {
        callback(req);
    }; // closure

    if (method === "GET") {
        req.open(method, url + data);
        req.send();
    } else {
        req.open(method, url);
        req.send(data);
    }
}

function createTableRows(parentTable, elementsList) {
    elementsList.forEach(rows => {
        var row = document.createElement("tr");
        for(var key in rows){
            var col = document.createElement("td");
            col.textContent = rows[key];
            row.appendChild(col);
        }
        parentTable.appendChild(row);
        //append auctionDetails
    })
}

function createTableRowsAsteAperte(parentTable, elementsList, vendo, lastSeen = false) {
        if (elementsList !== null){
            elementsList.forEach(rows => {
                if(lastSeen && !canDisplayAuctionForLastSeen(rows["id"])){

                }
                else{
                    var row = document.createElement("tr");
                    var auctionID = document.createElement("td");
                    auctionID.textContent = rows["id"];
                    row.appendChild(auctionID);

                    var timeTillClosure = document.createElement("td");
                    timeTillClosure.textContent = rows["oreRimanentiFormatted"]; //calculate endingTime
                    row.appendChild(timeTillClosure);

                    var endingDate = document.createElement("td");
                    endingDate.textContent = rows["dataScadenza"];
                    row.appendChild(endingDate);
                    if(!vendo){
                        var creator = document.createElement("td");
                        creator.textContent = rows["userNameCreatore"];
                        row.appendChild(creator);
                    }

                    var lastBid = document.createElement("td");
                    lastBid.textContent = rows["prezzoRaggiunto"] > 0 ? rows["prezzoRaggiunto"] : "No Bids";
                    row.appendChild(lastBid);


                    var details = document.createElement("td");
                    var butt = document.createElement("button");
                    butt.textContent = "Open Details";
                    butt.addEventListener("click", function(e){
                        e.preventDefault();
                        addFlagForLastSeen(rows["id"]);
                        showPageX("auctionDetails", rows["id"]);
                    })
                    details.appendChild(butt);

                    row.appendChild(details);

                    parentTable.appendChild(row);
                    //append auctionDetails
                }
            });
        }
    }
function createTableRowsAsteChiuse(parentTable, elementsList, vendo) {
        if(elementsList !== null){
            elementsList.forEach( rows => {
                var row = document.createElement("tr");
                var auctionID = document.createElement("td");
                auctionID.textContent = rows["id"];
                row.appendChild(auctionID);

                if(!vendo){
                    var creator = document.createElement("td");
                    creator.textContent = rows["userNameCreatore"];
                    row.appendChild(creator);
                }

                var lastBid = document.createElement("td");
                lastBid.textContent = rows["prezzoRaggiunto"] > 0 ? rows["prezzoRaggiunto"] : "No Bids";
                row.appendChild(lastBid);

                var winner = document.createElement("td");
                winner.textContent = rows["userNameVincitore"];
                row.appendChild(winner);

                var shippingAddr = document.createElement("td");
                shippingAddr.textContent = rows["indSped"]; //calculate endingTime
                row.appendChild(shippingAddr);


                var details = document.createElement("td");
                var butt = document.createElement("button");
                butt.textContent = "Open Details";
                butt.addEventListener("click", function(e){
                    e.preventDefault();
                    showPageX("auctionDetails", rows["id"]);
                })
                details.appendChild(butt);

                row.appendChild(details);

                parentTable.appendChild(row);
                //append auctionDetails
            });
        }
    }
function createTableRowsArticles(parentTable, elementsList, vendo) {
        if(elementsList !== null){
            elementsList.forEach(rows => {
                var row = document.createElement("tr");

                if(!vendo){
                    var price = document.createElement("td");
                    price.textContent = rows["prezzo"];
                    row.appendChild(price);
                }

                var name = document.createElement("td");
                name.textContent = rows["name"];
                row.appendChild(name);

                var description = document.createElement("td");
                description.textContent = rows["description"]; //calculate endingTime
                row.appendChild(description);

                var articleImage = document.createElement("td");
                var properImage = document.createElement("img");

                properImage.src = rows["image"];
                properImage.alt = "No Image Found";
                properImage.classList.add("image_preview");

                articleImage.appendChild(properImage);
                row.appendChild(articleImage);

                if(vendo){
                    var checkCol = document.createElement("td");
                    var check = document.createElement("input");
                    check.type = "checkbox";
                    check.name = rows["id"];
                    checkCol.appendChild(check);
                    row.appendChild(checkCol);
                }

                parentTable.appendChild(row);
                //append auctionDetails
            });
        }
    }
function createTableRowsOffers(parentTable, elementsList) {
    if(elementsList !== null){
        elementsList.forEach(rows => {
            var row = document.createElement("tr");

            var placer = document.createElement("td");
            placer.textContent = rows["idCreatore"];
            row.appendChild(placer);

            var offeredPrice = document.createElement("td");
            offeredPrice.textContent = rows["prezzoOfferto"];
            row.appendChild(offeredPrice);

            var offerDate = document.createElement("td");
            offerDate.textContent = rows["dataOfferta"];
            row.appendChild(offerDate);

            parentTable.appendChild(row);
            //append auctionDetails
        })
    }

}

function generateAuctionDescription(parentDiv, auction){
    var creatorName = document.createElement("p");
    creatorName.textContent = "Creator: " + auction.userNameCreatore;
    parentDiv.appendChild(creatorName);

    if(auction.idVincitore > 0){
        var winner = document.createElement("p");
        winner.textContent = "Winner: " + auction.userNameVincitore;
        parentDiv.appendChild(winner);
    }

    var lastBid = document.createElement("p");
    lastBid.textContent = auction.idVincitore === -1 ? "Last Bid: " + auction.prezzoRaggiunto : auction.idVincitore === 0 ? "No Winner" : "Winning Price: " + auction.prezzoRaggiunto;
    parentDiv.appendChild(lastBid);

    if(auction.idVincitore === -1){
        var minBidWedge = document.createElement("p");
        var minimumBid = auction.prezzoRaggiunto + auction.rialzoMinimo;
        minBidWedge.textContent = "Minimum bid requested: " + minimumBid + " €";
        parentDiv.appendChild(minBidWedge);

        var remainingTime = document.createElement("p");
        remainingTime.textContent = "Remaining Time: " + auction.oreRimanentiFormatted;
        parentDiv.appendChild(remainingTime);

    }

    if(auction.idVincitore > 0){
        var shippingAddr = document.createElement("p");
        shippingAddr.textContent = "Shipping Address: " + auction.indSped;
        parentDiv.appendChild(shippingAddr);
    }



}

function generateReqParametersFromJson(json){
    var toReturn = "?";
    for(var key in json){
        toReturn += key + "=" + json[key] +"&";
    }

    return toReturn;
}


function findCookieValue(key){
    var toReturn = "";
    document.cookie.split(";").forEach(cookie => {
        var pairKeyValue = cookie.split("=");
        if(pairKeyValue[0].includes(key)){
            toReturn = pairKeyValue[1];
        }
    });

    return toReturn;

}

function showLastSeenAuctions(){
    showPageX("acquistoPage", "lastSeen");
}

function addFlagForLastSeen(idAsta){
    var lastSeenString = findCookieValue("lastSeen" + sessionStorage.getItem("userID"));
    if(lastSeenString){
        setCookie("lastSeen" + sessionStorage.getItem("userID"), lastSeenString.concat(idAsta + ","), 31);
    }
    else{
        setCookie("lastSeen" + sessionStorage.getItem("userID"), idAsta + ",", 31);
    }
}

function setCookie(name, value, days) {
    var expirationDate = new Date();
    expirationDate.setDate(expirationDate.getDate() + days);

    var cookieValue = value + "; expires=" + expirationDate.toUTCString();

    document.cookie = name + "=" + cookieValue;
}

function canDisplayAuctionForLastSeen(idAsta){
    return findCookieValue("lastSeen" + sessionStorage.getItem("userID")).includes(idAsta);
}

}