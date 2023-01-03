$(document).ready(function () {
    $("#password").on("keyup", function () {
        checkPassword();
    });

    $("#password-verif").on("keyup", function () {
        checkPassword();
    });

    function checkPassword() {
        let password = $("#password").val();
        let passwordVerify = $("#password-verif").val();
        if (passwordVerify.length > 0) {
            if (password === passwordVerify) {
                $("#password-verif").removeClass("is-invalid");
                $("#password-verif").addClass("is-valid");
            } else {
                $("#password-verif").removeClass("is-valid");
                $("#password-verif").addClass("is-invalid");
            }
        }
    }
});