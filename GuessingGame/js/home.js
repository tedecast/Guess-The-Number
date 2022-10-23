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
  // include if no rounds exists.
  // include if no GameID exits. Enter a valid.
  $("#roundsError").empty();

  if ($("#gameIDSearch").val() == "") {
    $("#roundsError").append(
      $("<li>")
        .attr({ class: "list-group-item list-group-item-danger" })
        .text("ERROR: Please enter a valid Game ID.")
    );
  } else {
    let roundContents = $("#roundContents");
    $.ajax({
      type: "GET",
      url: "http://localhost:8080/api/rounds/" + $("#gameIDSearch").val(),
      success: function (roundArray) {
        if (roundArray.length > 0) {
          $("#roundsPage").hide();
          $("#roundResults").show();
          $.each(roundArray, function (index, round) {
            let roundID = round.roundID;
            let guess = round.guess;
            let result = round.result;
            let guessTime = round.guessTime;
            let gameID = round.gameID;

            $(".gameIDHeader").text("Game " + gameID);
            let row = "<tr>";
            row += "<td>" + roundID + "</td>";
            row += "<td>" + guess + "</td>";
            row += "<td>" + result + "</td>";
            row += "<td>" + guessTime + "</td>";
            row += "</tr>";

            roundContents.append(row);
          });
        } else {
          $("#roundsError").append(
            $("<li>")
              .attr({ class: "list-group-item list-group-item-danger" })
              .text("ERROR: No Rounds exists. Please try again.")
          );
        }
      },
      error: function (result) {
        $("#roundsError").append(
          $("<li>")
            .attr({ class: "list-group-item list-group-item-danger" })
            .text("ERROR: Please enter a valid Game ID.")
        );
      },
    });
  }
});

$("#backButtonThree").click(function (event) {
  $(".gameIDHeader").text("");
  $("#roundsError").empty();
  $("#roundContents").empty();
  $("#gameIDSearch").val("");
  $("#roundsPage").hide();
  $("#backButtonThree").hide();
  $("#mainPage").show();
});

$("#goBackButton").click(function (event) {
  $(".gameIDHeader").text("");
  $("#roundsError").empty();
  $("#roundResults").hide();
  $("#roundContents").empty();
  $("#gameIDSearch").val("");
  $("#roundsPage").show();
});

$("#continueButton").click(function (event) {
  $("#continueMessages").empty();
  $("#continueRows").empty();
  $("#mainPage").hide();
  $("#continuePage").show();
  $("#backButtonFour").show();

  $.ajax({
    type: "GET",
    url: "http://localhost:8080/api/game",
    success: function (continueArray) {
      $.each(continueArray, function (index, game) {
        let gameID = game.gameID;
        let gameStatus = game.gameStatus;

        if (gameStatus == "FINISHED") {
          // do nothing..
        } else {
          let row = "<tr>";
          row +=
            '<td><a href="#" onclick="continueGame(' +
            gameID +
            ')">' +
            gameID +
            "</a></td>";
          row += "</tr>";
          $("#continueRows").append(row);
        }
      });
    },
  });
});

$("#backButtonFour").click(function (event) {
  $("#continueMessages").empty();
  $("#continueRows").empty();
  $("#continuePage").hide();
  $("#backButtonFour").hide();
  $("#mainPage").show();
});

function continueGame(gameID) {
  $("#continuePage").hide();
  $("#backButtonFour").hide();
  $("#guessingGame").show();
  $("#guessRows").empty();
  $("#backButtonFive").show();
  $.ajax({
    type: "GET",
    url: "http://localhost:8080/api/rounds/" + gameID,
    success: function (roundArray) {
      $.each(roundArray, function (index, round) {
        $(".gameIDHeaderTwo").text("Game " + round.gameID);

        let roundID = round.roundID;
        let guess = round.guess;
        let result = round.result;

        let row = "<tr>";
        row += "<td>" + roundID + "</td>";
        row += "<td>" + guess + "</td>";
        row += "<td>" + result + "</td>";
        row += "</tr>";

        $("#guessRows").append(row);
      });
    },
    error: function () {
      $("#guessError").append(
        $("<li>")
          .attr({ class: "list-group-item list-group-item-danger" })
          .text("ERROR: Please enter a valid Game ID.")
      );
    },
  });
}

$("#backButtonFive").click(function (event) {
  let confirm = window.confirm("Are you sure you want to exit this game?");
  if (confirm == true) {
    $("#guessEror").empty();
    $("#guessRows").empty();
    $("#guessingGame").hide();
    $("#backButtonFive").hide();
    $("#mainPage").show();
  }
});
