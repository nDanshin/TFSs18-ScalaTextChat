  var wsUri = "ws://localhost:8080/ws-chat/";

  function init()
  {
    output = document.getElementById("output");
  }

  function createWebSocket()
  {
    websocket = new WebSocket(wsUri + roomId + "?name=" + userName);
    /*websocket.onopen = function(evt) { onOpen(evt) };*/
    websocket.onclose = function(evt) { onClose(evt) };
    websocket.onmessage = function(evt) { onMessage(evt) };
    websocket.onerror = function(evt) { onError(evt) };
  }

/*
  function onOpen(evt)
  {
    doSend("WebSocket hello");
  }*/

  function onClose(evt)
  {
    writeToScreen("<span>DISCONNECTED</span>");
  }

  function onMessage(evt)
  {var preRoom = document.createElement
    writeToScreen('<span>'+ evt.data + '</span>');
  }

  function onError(evt)
  {
    //writeToScreen('<span style="color: red;">[System] ERROR:</span>' + evt.data);
  }

  function sendMessage()
  {
    message = document.getElementById("input-message").value;
    document.getElementById("input-message").value = "";
    if (message != "")
      websocket.send(message);
  }

  function writeToScreen(message)
  {
    var pre = document.createElement("p");
    pre.style.wordWrap = "break-word";
    pre.innerHTML = message;
    output.appendChild(pre);
  }

  window.addEventListener("load", init, false);
