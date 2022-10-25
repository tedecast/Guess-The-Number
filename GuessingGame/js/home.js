$(document).ready(function () {});

$(".rules-button").click(function (event) {
  $("#error-messages").empty();
  $("#main-page").hide();
  $("#rules-page").show();
  $("#back-button-one").show();
});

$("#back-button-one").click(function (event) {
  $("#rules-page").hide();
  $("#back-button-one").hide();
  $("#previous-games").hide();
  $("#main-page").show();
});

$(".previous-button").click(function (event) {
  let previousRows = $("#previous-rows");
  previousRows.empty();
  $("#main-page").hide();
  $("#previous-games").show();
  $("#back-button-two").show();

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
      $("#error-messages").append(
        $("<li>")
          .attr({ class: "list-group-item list-group-item-danger" })
          .text("Error calling web service. Please try again later.")
      );
    },
  });
});

$("#back-button-two").click(function (event) {
  $("#previous-games").hide();
  $("#back-button-two").hide();
  $("#main-page").show();
});

$(".rounds-button").click(function (event) {
  $("#main-page").hide();
  $("#rounds-page").show();
  $("#back-button-three").show();
});

$("#enter-button").click(function (event) {
  $("#rounds-error").empty();

  if ($("#game-id-search").val() == "") {
    $("#rounds-error").append(
      $("<li>")
        .attr({ class: "list-group-item list-group-item-danger" })
        .text("ERROR: Please enter a valid Game ID.")
    );
  } else {
    let roundContents = $("#round-contents");
    $.ajax({
      type: "GET",
      url: "http://localhost:8080/api/rounds/" + $("#game-id-search").val(),
      success: function (roundArray) {
        if (roundArray.length > 0) {
          $("#rounds-page").hide();
          $("#round-results").show();
          $.each(roundArray, function (index, round) {
            let roundID = round.roundID;
            let guess = round.guess;
            let result = round.result;
            let guessTime = round.guessTime;
            let gameID = round.gameID;

            $("#game-id-header").text("Game " + gameID);
            let row = "<tr>";
            row += "<td>" + roundID + "</td>";
            row += "<td>" + guess + "</td>";
            row += "<td>" + result + "</td>";
            row += "<td>" + guessTime + "</td>";
            row += "</tr>";

            roundContents.append(row);
          });
        } else {
          $("#rounds-error").append(
            $("<li>")
              .attr({ class: "list-group-item list-group-item-danger" })
              .text("ERROR: No Rounds exists. Please try again.")
          );
        }
      },
      error: function (result) {
        $("#rounds-error").append(
          $("<li>")
            .attr({ class: "list-group-item list-group-item-danger" })
            .text("ERROR: Please enter a valid Game ID.")
        );
      },
    });
  }
});

$("#back-button-three").click(function (event) {
  $(".game-id-header").text("");
  $("#rounds-error").empty();
  $("#round-contents").empty();
  $("#game-id-search").val("");
  $("#rounds-page").hide();
  $("#back-button-three").hide();
  $("#main-page").show();
});

$("#go-back-button").click(function (event) {
  $(".game-id-header").text("");
  $("#rounds-error").empty();
  $("#round-results").hide();
  $("#round-contents").empty();
  $("#game-id-search").val("");
  $("#rounds-page").show();
});

$(".continue-button").click(function (event) {
  $("#continue-messages").empty();
  $("#continue-rows").empty();
  $("#main-page").hide();
  $("#continue-page").show();
  $("#back-button-four").show();

  $.ajax({
    type: "GET",
    url: "http://localhost:8080/api/game",
    success: function (continueArray) {
      $.each(continueArray, function (index, game) {
        let gameID = game.gameID;
        let gameStatus = game.gameStatus;

        if (gameStatus == "IN PROGRESS") {
          let row = "<tr>";
          row +=
            '<td><a href="#" class="game-link" onclick="continueGame(' +
            gameID +
            ')">';
          row += gameID + "</a></td>";
          row += "</tr>";
          $("#continue-rows").append(row);
          $("#game-id").text("Game " + gameID);
        }
      });
    },
  });
});

$("#back-button-four").click(function (event) {
  $("#continue-messages").empty();
  $("#continue-rows").empty();
  $("#continue-page").hide();
  $("#back-button-four").hide();
  $("#main-page").show();
});

function getGameID() {
  $.ajax({
    type: "GET",
    url: "http://localhost:8080/api/game",
    success: function (gameIDArray) {
      $.each(gameIDArray, function (index, game) {
        let gameID = game.gameID;
      });
    },
  });
}

// create as function for just the round table.
function continueGame(gameID) {
  $("#game-id").text("Game " + gameID);
  $("#continue-page").hide();
  $("#back-button-four").hide();
  $("#guessing-game").show();
  $("#guess-rows").empty();
  $("#back-button-five").show();

  $.ajax({
    type: "GET",
    url: "http://localhost:8080/api/rounds/" + gameID,
    success: function (roundArray) {
      $.each(roundArray, function (index, round) {
        // need to fix this below

        let roundID = round.roundID;
        let guess = round.guess;
        let result = round.result;

        let row = "<tr>";
        row += "<td>" + roundID + "</td>";
        row += "<td>" + guess + "</td>";
        row += "<td>" + result + "</td>";
        row += "</tr>";

        $("#guess-rows").append(row);
      });
    },
    error: function () {
      $("#guess-error").append(
        $("<li>")
          .attr({ class: "list-group-item list-group-item-danger" })
          .text("Error calling web service. Please try again later.")
      );
    },
  });
}

$("#back-button-five").click(function (event) {
  let confirm = window.confirm("Are you sure you want to exit this game?");
  if (confirm == true) {
    $("#guess-error").hide();
    $("#guess-input").val("");
    $("#guess-rows").empty();
    // $("#game-id").text("");
    $("#guessing-game").hide();
    $("#back-button-five").hide();
    $("#main-page").show();
  }
});

$(".num").each(function (i) {
  $(this).click(function (e) {
    if ($("#guess-input").val().length < 4) {
      $("#guess-input").val($("#guess-input").val() + $(this).text());
    }
  });
});

$("#clear-button").click(function (event) {
  $("#guess-input").val("");
});

$("#enter-game-button").click(function (event) {
  $("#guess-error").hide();
  $.ajax({
    type: "POST",
    url: "http://localhost:8080/api/guess/",
    data: JSON.stringify({
      gameID: $("#game-id").text().split(" ")[1],
      guess: $("#guess-input").val(),
    }),
    contentType: "application/json",
    success: function () {
      $("#guess-input").val("");
      $("#guess-error").empty();
      continueGame($("#game-id").text().split(" ")[1]);
    },
    error: function (result) {
      guessError(result.responseJSON.message);
    },
  });
});

function guessError(string) {
  $("#guess-error").text(string).show();
}

$(".begin-button").click(function (event) {
  $("#main-page").hide();
  $("#guessing-game").show();
  $("#guess-rows").empty();
  $("#back-button-five").show();

  $.ajax({
    type: "POST",
    url: "http://localhost:8080/api/begin/",
  });
});
