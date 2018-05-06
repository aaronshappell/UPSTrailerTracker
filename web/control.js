/*global fetch*/
"use strict";

 (function () {
    window.onload = function() {
        $('submit').onclick = checkLoginInfo;
    };

    function checkLoginInfo() {
        userLogin = $('login').innerText;
        userPass = $('pass').innerText;
        if (userLogin == "login" && userPass == "pass") {
            console.log("true");
            //location.href = "core.html";
        }
    }

    function $(id) {
        return document.getElementById(id);
    }
 });