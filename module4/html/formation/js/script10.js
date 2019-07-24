
function init() {
    document.getElementById("addition")
            .addEventListener("click", function(evt) { return calcul(evt, "+")});
    document.getElementById("soustraction")
            .addEventListener("click", function(evt) { return calcul(evt, "-")});
    document.getElementById("multiplication")
            .addEventListener("click", function(evt) { return calcul(evt, "*")});    
    document.getElementById("division")
            .addEventListener("click", function(evt) { return calcul(evt, "/")});
    document.getElementById("ajout")
            .addEventListener("click", addEmail);
    // pas de var devant, c'est global
    emails = [];
    // en fait c'est equivalent à window.emails = [];

}


function calcul(evt, operation) {
    var op1 = Number(document.getElementById("operande1").value);
    var op2 = Number(document.getElementById("operande2").value);
    switch (operation) {
        case "+": showResult(op1 + op2); break;
        case "-": showResult(op1 - op2); break;
        case "*": showResult(op1 * op2); break;
        case "/": showResult(op1 / op2); break;
    }
    evt.preventDefault();
    return false;
}

function showResult(resultat) {
    var message = "resultat: " + resultat;
    console.log(message);
    document.getElementById("reponsecalcul").innerHTML = "<p>" + resultat + "</p>";
}

function refreshEmails() {
    var listeemails = document.getElementById("listeemails");
    listeemails.innerHTML = "";
    for (var idx in emails) {
        var div = document.createElement("div");
        div.innerText = emails[idx];
        div.className="divemail";
        div.setAttribute('data-idx', "" + idx);
        listeemails.appendChild(div);
        div.addEventListener("click", removeEmail);
    }
}

function addEmail(evt) {
    var pattern = /^[a-zA-Z0-9]+([.][a-zA-Z0-9]+)*@[a-zA-Z0-9]+([.][a-zA-Z0-9]+)*([.][a-zA-Z0-9]{2,4})$/i
    var saisie = document.getElementById("email").value;
    if (pattern.test(saisie)) {
        emails.push(saisie);
        refreshEmails();
        document.getElementById("email").className="valide";
    }
    else {
        document.getElementById("email").className="invalide";
    }
    evt.preventDefault();
    return false;
}

function removeEmail(evt) {
    // this est positionné sur le div cliqué
    this.removeEventListener("click", removeEmail);
    var pos = Number(this.getAttribute('data-idx'));
    emails.splice(pos, 1);
    refreshEmails();
    evt.preventDefault();
    return false;
}