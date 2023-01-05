$(document).ready(function () {
    $("#password").on("change", function () {
        checkPassword();
    });

    $("#password_verif").on("change", function () {
        checkPassword();
    });

    function checkPassword() {
        let password = $("#password").val();
        let passwordVerify = $("#password_verif").val();
        if (passwordVerify.length > 0) {
            if (password === passwordVerify) {
                $("#password_verif").removeClass("is-invalid");
                $("#password_verif").addClass("is-valid");
            } else {
                $("#password_verif").removeClass("is-valid");
                $("#password_verif").addClass("is-invalid");
            }
        }
    }
});