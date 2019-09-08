var accounts = [];

function loadAccounts() {
  let xmlHttp = new XMLHttpRequest();
  xmlHttp.open("GET", "http://localhost:8080/api/admin/account/getAll", false);
  xmlHttp.send(null);
  accounts = JSON.parse(xmlHttp.responseText);

  var playerListView = document.getElementById("playerList");

  for (let account of accounts) {
    let item = document.createElement("div");
    item.className = "item";

    item.innerHTML = account["login"];

    playerListView.appendChild(item);
  }
}