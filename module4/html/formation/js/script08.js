
// si on veut acceder au dom "complet"
// il faut attendre la fin du chargement de la page
function init() {
    var elem = document.getElementById("message");
    elem.innerText = "bonjour!";
    var bouton2 = document.getElementById("bouton2");
    // attention, sans les parentheses
    // on passe la fonction au bouton, et non pas
    // le resultat de son execution
    bouton2.addEventListener("click", ajoutli);
    var bouton3 = document.getElementById("bouton3");
    bouton3.addEventListener("click", function(e) {
        console.log(document.getElementById("saisie").value);
        // on veut eviter le comprtement par defaut
        // à savoir, l'envoie du formulaire ou est placé
        // le bouton
        e.preventDefault();
        return false;
    });
}


function ajoutli() {
    // je recupere la balise input
    var champ = document.getElementById("choix");
    // je recupere le contenu du champ
    var choix = champ.value;
    console.log(choix);
    // je recupere le ul a remplir
    var ul = document.getElementById("listechoix");
    // je creer un li vide
    var li = document.createElement("li");
    // je creer un noeud texte
    var texte = document.createTextNode(choix);
    // j'ajoute le texte dans le li
    li.appendChild(texte);
    // j'ajoute le li dans le ul
    ul.appendChild(li);
    // rendre visible le choix
    ul.className = "visible";

    //------
    var saisienombre = "25";
    // conversion de texte vers chiffre
    var nombre = Number(saisienombre);
    console.log(typeof(nombre));

    // regex en javascript
    var saisiecode="75013";
    // le pattern est défini via la syntaxe /pattern/option
    var pattern = /[0-9]{5}/i;
    console.log(typeof(pattern));
    console.log("code postal ? " + pattern.test(saisiecode));
    saisiecode="7501A";
    console.log("code postal ? " + pattern.test(saisiecode));
    console.log(nombre);
}