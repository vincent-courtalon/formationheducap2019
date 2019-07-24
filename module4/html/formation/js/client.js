/*
    classe Personne
*/
function Personne(nom, prenom) {
    this.nom = nom;
    this.prenom = prenom;
}

Personne.prototype = Object.create({ constructor: Personne});
Personne.prototype.toString = function() {
    return this.nom + ", " + this.prenom;
}

/*
 classe Client qui herite de Personne
*/

function Client(nom, prenom, email) {
    Personne.call(this, nom, prenom);
    this.email = email;
}

Client.prototype = Object.create(Personne.prototype);
Client.prototype.constructor = Client;

Client.prototype.contacter = function() {
    console.log("envoie email a " + this.nom + " adresse " + this.email);
}
