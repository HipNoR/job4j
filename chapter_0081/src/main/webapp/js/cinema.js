function checkSelected() {
    var checked = $("#seatCheck input:checked").length > 0;
    if(!checked) {
        var message = "<div class=\"alert alert-danger\">Выберите хотябы одно место</div>"
        document.getElementById("alert-msg").innerHTML = message;
    }
    return checked;
}

function checkReserved() {
    $.ajax({
        url: "hall",
        type: "post",
        success: function (data) {
            var rows = Object.keys(data);
            rows.forEach(function (row) {
                var rowSeats = data[row];
                rowSeats.forEach(function (seat) {
                    if(seat.reserved != 0) {
                        var place = seat.row + "." + seat.seat;
                        $("input").filter(function () {
                            return $(this).attr("id") == place;
                        }).attr('disabled', 'disabled');
                    }
                })
            })
        }
    });
}

$(document).ready(function () {
    checkReserved();
})

setInterval(function () {
    checkReserved();
}, 5000);