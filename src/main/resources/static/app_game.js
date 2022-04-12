function makeMap(){
    let step;
    for (step = 0; step < 25; step++) {
        $("#game-map").append("<div class='item' style='background-color: ivory' id ="+ step + ">" +"</div>")
    }
}

$(function () {
    $( ".item" ).click( function() {
        checkAndPost('','',this)
    });
});

function checkAndPost(url = '', data = {}, cur) {
    //1. check if current box is colored
    //2. if colored : post
    //   else: ignore
    // Default options are marked with *
    if(cur.style.backgroundColor == "lime"){ // colored
        //send

    }else{ // NOT colored!  color : ivory
        //ignore
    }
}

function sendContent() {

    let payload = {'userId': userId,'content':$("#chat-content").val()}

    stompClient.send("/app/chat", {}, JSON.stringify(payload));
    document.getElementById('chat-content').value = ''
    /*
    The sendName() function retrieves the name entered by the user and uses the STOMP client
    to send it to the /app/hello destination (where GreetingController.greeting() will receive it).
    */
}