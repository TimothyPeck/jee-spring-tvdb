$(document).ready(function () {
    $("#duration").on("change", function () {
        let duration = $("#duration").val();
        duration = duration.split(":");
        let hours = Number(duration[0]) - 1;
        let minutes = Number(duration[1]);
        let timeInMinutes = hours * 60 + minutes;
        $("#duration_minutes").val(timeInMinutes);
    });
});