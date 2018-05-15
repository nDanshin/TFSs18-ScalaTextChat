var userName = ""
var roomId = ""

$(document).ready(function(){

    $.post( "http://localhost:8080/history", function( data ) {
      alert( "Data Loaded: " + data );
    });

    // rooms click handler
    $(document).on('click', '.room-link', function(e) {
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

function makeNewRoom() {
    var newRoomId = document.getElementById("new-id").value
    $.post( "http://localhost:8080/create/" + newRoomId);
    var preRoom = document.createElement('li');
    preRoom.className = 'room';
    preRoom.innerHTML = '<a class="room-link" href=#>' + newRoomId + '</a></li>';
    document.getElementById("rooms-list").appendChild(preRoom);
}

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