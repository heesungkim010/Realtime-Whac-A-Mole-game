function makeMap(){
    let step;
    for (step = 0; step < 25; step++) {
        $("#game-map").append("<div class='item' style='background-color: ivory' id ="+ step + ">" +"</div>")
    }
}

$(function () {
    $( ".item" ).click( function() {
        checkAndSend(this)
    });
});

function checkAndSend(cur) {
    //1. check if current box is colored
    //2. if colored : post
    //   else: ignore
    // Default options are marked with *
    if(cur.style.backgroundColor == "lime"){ // colored
        sendGameContent( sessionStorage.getItem('userId')) // needs refactoring
        //send

    }else{ // NOT colored!  color : ivory
        //ignore
    }
}

function sendGameContent(userId) {
    console.log(userId)
    let payload = {'userId': userId}

    stompClient.send("/app/game", {}, JSON.stringify(payload));
}