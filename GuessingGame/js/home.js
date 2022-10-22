$(document).ready(function () {});

$("#rulesButton").click(function (event) {
  $("#errorMessages").empty();
  $("#mainPage").hide();
  $("#rulesPage").show();
  $("#backButtonOne").show();
});

$("#backButtonOne").click(function (event) {
  $("#rulesPage").hide();
  $("#backButtonOne").hide();
  $("#previousGames").hide();
  $("#mainPage").show();
});

$("#previousButton").click(function (event) {
  let previousRows = $("#previousRows");
  previousRows.empty();
  $("#mainPage").hide();
  $("#previousGames").show();
  $("#backButtonTwo").show();

  $.ajax({
    type: "GET",
    url: "http://localhost:8080/api/game",
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
});

$("#backButtonTwo").click(function (event) {
  $("#previousGames").hide();
  $("#backButtonTwo").hide();
  $("#mainPage").show();
});

$("#roundsButton").click(function (event) {
  $("#mainPage").hide();
  $("#roundsPage").show();
  $("#backButtonThree").show();
});

$("#enterButton").click(function (event) {
  let roundContents = $("#roundContents");

  $.ajax({
    type: "GET",
    url: "http://localhost:8080/api/rounds/" + $("#gameIDSearch").val(),
    success: function (roundArray) {
      $.each(roundArray, function (index, round) {
        let roundID = round.roundID;
        let guess = round.guess;
        let result = round.result;
        let guessTime = round.guessTime;

        let row = "<tr>";
        row += "<td>" + roundID + "</td>";
        row += "<td>" + guess + "</td>";
        row += "<td>" + result + "</td>";
        row += "<td>" + guessTime + "</td>";
        row += "</tr>";

        roundContents.append(row);
      });
    },
    error: function (result) {
      $("#errorMessages").append(
        $("<li>")
          .attr({ class: "list-group-item list-group-item-danger" })
          .text(result.responseJSON.message)
      );
    },
  });
});

$("#backButtonThree").click(function (event) {
  $("#roundContents").empty();
  $("#gameIDSearch").val("");
  $("#roundsPage").hide();
  $("#backButtonThree").hide();
  $("#mainPage").show();
});
