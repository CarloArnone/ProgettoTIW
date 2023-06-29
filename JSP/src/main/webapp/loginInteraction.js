{

    let loginForm = document.getElementById("loginForm");
    let submitButton = document.getElementById("loginButton")

    window.addEventListener("load", function(){
        new LoginForm(findCookieValue("lastUserName"));
        }, false);

    function LoginForm(_uName){

        this._uName = _uName;
        document.getElementById("uName").setAttribute('value', this._uName);

        submitButton.addEventListener("click", function (e){
            let form1 = e.target.closest("form");
            if(form1.checkValidity()){
                e.preventDefault();
                makeCall("POST", "LoginPage", e.target.closest("form"), function(req){
                    if (req.readyState === 4) {
                        if (req.status === 200) {
                            let loginResult = req.responseText;
                            if (loginResult.includes("Success")) {
                                setCookie("lastUserName", document.getElementById("uName").value, 7);
                                sessionStorage.setItem("userID", loginResult.split(",")[1]);
                                window.location.href = "Home.html";
                            }
                            else{
                                document.getElementById("errormessage").setAttribute('value', 'Wrong - Username or Password');
                            }
                        }
                    }});
            }
        }, false);

        this.hide = function(){
            document.getElementById("loginForm").style.visibility = "hidden";
        }
    }
}