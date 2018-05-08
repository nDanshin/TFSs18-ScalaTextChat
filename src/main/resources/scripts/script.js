var userName = ""
var roomId = ""

$(document).ready(function(){

    $.post( "http://localhost:8080/history", function( data ) {
      alert( "Data Loaded: " + data );
    });

    // rooms click handler
    $("#rooms-list a").on('click', function(e) {
        roomId = $(this).text();
        $('#output').html("");
        $('#room-name a').html("Room #" + roomId);
        createWebSocket();
    });

    $("#input-message").keypress(function(e) {
        if (e.which == 13) {
            e.preventDefault();
            sendMessage();
        }
    });
});

function fakeAuth() {
    $("#b-popup").show()
}

function fakeLogin() {
    userName = document.getElementById("uname").value;
    $("#b-popup").hide()
}

function chatMenu() {
    document.getElementById("submenu").classList.toggle("show")
    /*document.getElementById("chat-menu").classList.toggle("active")*/
}

window.onclick = function(event) {
    if (!event.target.matches('.chat-menu-btn')) {
        var dropdowns = this.document.getElementById("submenu");
        if (dropdowns.classList.contains('show')) {
            dropdowns.classList.remove("show")
        }
    }
}

function showContacts() {
    $("#contactsModal").show();
}