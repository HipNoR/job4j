function getAllCars() {

    $.ajax({
        url: "carslist",
        method: "get",
        complete: function (data) {
            var result = "";
            var cars = JSON.parse(data.responseText);
            for (var i = 0; i < cars.length; i++) {
                result += "<tr>"
                    + "<td>" + cars[i].brandedModel.brand.name + "</td>"
                    + "<td>" + cars[i].brandedModel.model.name + "</td>"
                    + "<td>" + cars[i].engine.name + "</td>"
                    + "<td>" + cars[i].gearbox.name + "</td>"
                    + "<td>" + cars[i].body.name + "</td>"
                    + "<td>" + cars[i].colour.name + "</td>"
                    + "<td>" + cars[i].person.name + "</td>"
                    + "<td>" + cars[i].sold + "</td>"
                    + "<td>" + cars[i].description + "</td>"
                    + "</tr>";
            }
            var table = document.getElementById("cars-table");
            table.innerHTML = result;
        }
    });
}


$(document).ready(function () {
    getAllCars();
});