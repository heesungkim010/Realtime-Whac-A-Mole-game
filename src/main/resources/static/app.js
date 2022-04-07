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

function sendContent() {
    stompClient.send("/app/chat", {}, JSON.stringify({'content':$("#chat-content").val()}));
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