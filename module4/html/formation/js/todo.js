
function Todo(titre, description, priorite) {
    this.titre = titre;
    this.description = description;
    this.priorite = priorite;
    this.termine = false;
}

Todo.prototype = Object.create({});
Todo.prototype.constructor = Todo;

Todo.prototype.createDiv = function () {
    var div = document.createElement("div");
    div.className = "todoElement " + ((this.termine)? "termine" : "planifie");
    var h3 = document.createElement("h3");
    h3.innerText = this.titre;
    div.appendChild(h3);
    var p = document.createElement("p");
    // transformer les retours a la ligne en <br />
    // pour l'affichage
    p.innerHTML = this.description.replace(/\r?\n/g, "<br />");
    div.appendChild(p);
    var span = document.createElement("span");
    span.innerText = "priorite: " + this.priorite;
    div.appendChild(span);
    return div;
};
Todo.prototype.clickOnDiv = function() {
    if (this.termine) return true; // enlever le div
    this.termine = true; // passe de planifié à terminé
    return false; // pas de retrait nécéssaire
}


