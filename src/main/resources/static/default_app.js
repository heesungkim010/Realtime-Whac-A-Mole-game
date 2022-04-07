var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    console.log("connect start")
    var socket = new SockJS('/gs-guide-websocket');
    //e connect() function uses SockJS and stomp.js to open a connection to /gs-guide-websocket
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        stompClient.subscribe('/topic/greetings', function (greeting) {
            showGreeting(JSON.parse(greeting.body).content);
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

function sendName() {
    stompClient.send("/app/hello", {}, JSON.stringify({'name': $("#name").val()}));
    /*
    The sendName() function retrieves the name entered by the user and uses the STOMP client
    to send it to the /app/hello destination (where GreetingController.greeting() will receive it).
    */
}

function showGreeting(message) {
    $("#greetings").append("<tr><td>" + message + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); });
});

