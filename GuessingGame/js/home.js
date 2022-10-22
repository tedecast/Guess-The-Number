$(document).ready(function () {
    loadGames();
});


$("#rulesButton").click(function(event){
    $("#errorMessages").empty();
    $("#mainPage").hide();
    $("#rulesPage").show();
    $("#returnButton").show();
})

$("#returnButton").click(function(event){
    $("#rulesPage").hide();
    $("#returnButton").hide();
    $("#mainPage").show();
})
// $("#previousButton").click(function (event) {
function loadGames() {
    var previousRows = $("#previousRows");
  // Display header

  $.ajax({
    type: 'GET',
    url: 'http://localhost:8080/api/game',
    success: function (gameArray) {
      $.each(gameArray, function (index, game) {
        let gameID = game.gameID;
        let winningNumbers = game.winningNumbers;
        let gameStatus = game.gameStatus;

        let row = "<tr>";
        row += "<td>" + gameID + "</td>";
        row += "<td>" + winningNumbers + "</td>";
        row += "<td>" + gameStatus + "</td>";
        row += "</tr>";

        previousRows.append(row);
      });
    },
    error: function () {
      $("#errorMessages").append(
        $("<li>")
          .attr({ class: "list-group-item list-group-item-danger" })
          .text("Error calling web service. Please try again later.")
      );
    },
  });
}
// );
