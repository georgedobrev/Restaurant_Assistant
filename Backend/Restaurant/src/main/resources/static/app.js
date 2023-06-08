var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    } else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    var socket = new SockJS('/stomp-endpoint');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/notifications', function (notify) {
            showNotification(JSON.parse(notify.body));
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

function sendMenuNotification() {
    var notification = {
        appUser: {
            id: 1
        },
        appTable: {
            id: 1
        },
        requestType: "Menu",
        message: "Give me the menu",
        approved: false
    };

    stompClient.send("/app/notify", {}, JSON.stringify(notification));
}
function sendWaiterNotification() {
    var notification = {
        appUser: {
            id: 1
        },
        appTable: {
            id: 1
        },
        requestType: "Waiter",
        message: "Can the waiter come",
        approved: false
    };

    stompClient.send("/app/notify", {}, JSON.stringify(notification));
}
function sendBillNotification() {
    var notification = {
        appUser: {
            id: 1
        },
        appTable: {
            id: 1
        },
        requestType: "Bill",
        message: "The bill is: 50 leva",
        approved: false
    };

    stompClient.send("/app/notify", {}, JSON.stringify(notification));
}

function showNotification(notification) {
    $("#greetings").append("<tr><td>" + notification.message + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $("#connect").click(function () {
        connect();
    });
    $("#disconnect").click(function () {
        disconnect();
    });
    $("#send").click(function () {
        sendMenuNotification();
    });
    $("#send1").click(function () {
        sendWaiterNotification();
    });
    $("#send2").click(function () {
        sendBillNotification();
    });
});