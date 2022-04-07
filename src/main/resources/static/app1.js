var stompClient = null;
var elem = null;
function connect_start() {
    var socket = new SockJS('/gs-guide-websocket');
    elem = document.getElementById('chat');
    //e connect() function uses SockJS and stomp.js to open a connection to /gs-guide-websocket
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        $("#conversation").show();
        //$("#greetings").html("");

        stompClient.subscribe('/topic/greetings', function (greeting) {
            let receivedPayload = JSON.parse(greeting.body).userId +" : " + JSON.parse(greeting.body).content
            showGreeting(receivedPayload);
            /*
            Upon a successful connection, the client subscribes to the
            topic/greetings destination, where the server will publish greeting messages.
            When a greeting is received on that destination,
            it will append a paragraph element to the DOM to display the greeting message.
            */
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendContent() {

    // Get saved data from sessionStorage
    let userId = sessionStorage.getItem('userId');
    let payload = {'userId': userId,'content':$("#chat-content").val()}

    stompClient.send("/app/chat", {}, JSON.stringify(payload));
    document.getElementById('chat-content').value = ''
    /*
    The sendName() function retrieves the name entered by the user and uses the STOMP client
    to send it to the /app/hello destination (where GreetingController.greeting() will receive it).
    */
}

function showGreeting(message) {
    $("#chat").append("<div>" + message+"</div>")
    scrollDown();
}

function scrollDown(){
    elem.scrollTop = elem.scrollHeight;
}


$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    //$( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendContent(); });
});

async function postData(url = '', data = {}) {
    // Default options are marked with *

    const response = await fetch(url, {
        method: 'POST', // *GET, POST, PUT, DELETE, etc.
        mode: 'cors', // no-cors, *cors, same-origin
        cache: 'no-cache', // *default, no-cache, reload, force-cache, only-if-cached
        credentials: 'include', // include, *same-origin, omit
        headers: {
        'Content-Type': 'application/json'
        // 'Content-Type': 'application/x-www-form-urlencoded',
        },
        redirect: 'follow', // manual, *follow, error
        referrerPolicy: 'no-referrer', // no-referrer, *no-referrer-when-downgrade, origin, origin-when-cross-origin, same-origin, strict-origin, strict-origin-when-cross-origin, unsafe-url
        body: JSON.stringify(data), // body data type must match "Content-Type" header
    });
    result = await response.json()
    return result;
}

function inputToJsonById(array){
    result = {}
    array.forEach(element => {
        result[element] = document.getElementById(element).value
    });
    return result
}

async function checkDuplicatesAndNext(url = '', data = {}) {
    //Default options are marked with *
    //data = {"userId" : document.getElementById("userId").value, "password" : document.getElementById("password").value}

    let result = await postData(url = url, data = data);

    if(result.didLogin){
        const userId = document.getElementById("userId").value;
        //TODO : USE SESSION
        // Save data to sessionStorage
        sessionStorage.setItem('userId', userId);
        window.location.href='gamechat.html'
    }else{ // login fail
        //TODO : login fail
        alert( 'Your nickname is already being used. Try another nickname please.' );
    }
}
