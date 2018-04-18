var userName = ""

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