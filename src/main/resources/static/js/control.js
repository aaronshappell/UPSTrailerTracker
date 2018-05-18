/*global fetch*/
"use strict";

 (function () {
    window.onload = function() {
        $('submit').onclick = checkLoginInfo;
    };

    function checkLoginInfo() {
        let userLogin = $('login').value;
        let userPass = $('pass').value;
        //console.log(userLogin);
        //console.log(userPass);
        if (userLogin == "login" && userPass == "pass") {

            console.log("true");
            location.href = "/";
        }
    }

    function $(id) {
        return document.getElementById(id);
    }
 })();