function Produit(nom, prix, poids) {
    this.nom = nom;
    this.prix = prix;
    this.poids = poids;

    this.toString = function() {
        return "produit: " + this.nom + ", " + this.prix + ", " + this.poids;
    };
}

function test1() {

    var p1 = new Produit("steak de lama", 19.99, 0.75);
    console.log(p1.toString());

    // je creer un objet ps1 qui a pour prototype p1
    var ps1 = Object.create(p1);
    console.log(ps1.toString());

    ps1.poids = 0.65;
    console.log(ps1.toString());

    p1.prix = 22.99;
    ps1.categorie = "boucherie";
    console.log(ps1.toString());

    delete ps1.poids;
    // delete ps1.poids;
    console.log(ps1.toString());
    p1.description = "ce lama a crache trop de fois";
    console.log(ps1.description);
    console.log(ps1.categorie);

}

function test2() {

    var bob = new Personne("eponge", "bob");
    console.log(bob.toString());

    var patrick = new Client("etoile", "patrick", "patrick@etoile.com");
    console.log(patrick.toString());
    patrick.contacter();
}

function test3() {
    // je met une fonction dans une variable
    var fct1 = function() {
        console.log("Bonjour depuis fct1");
    };

    // je peux l'appeler ensuite
    fct1();

    // même regle d'affectation que pour des types classiques
    var fct2 = fct1;
    fct2();

    // la variable est de type fonction
    console.log(typeof fct1);

    var fctCapitalise = function(chaine) {
        return chaine.charAt(0).toUpperCase() + chaine.substr(1);
    }

    var salutation = function(nom, pretraitement) {
        console.log("Bonjour, " + pretraitement(nom));
    }
    // comme un lambda, une fonction peut être passer en parametre à une autre fonction
    // pour etre appelé
    salutation("vincent", fctCapitalise);

    var tabclient = [];
    tabclient.push(new Client("eponge", "bob", "bob@yahoo.fr"));
    tabclient.push(new Client("etoile", "patrick", "pat@yahoo.fr"));
    tabclient.push(new Client("tentacule", "carlo", "carlo@yahoo.fr"));
    tabclient.push(new Client("ecureil", "sandy", "sandy@yahoo.fr"));

    tabclient.sort(function(c1,c2) {
        return c1.prenom.localeCompare(c2.prenom);
    });
    console.log(tabclient);
}

function initialiser() {

    var compteur = 1;
    window.incrementer = function() {
        compteur++;
        this.console.log("compteur = " + compteur);
    }
}

function augmenter() {
    incrementer();
}

function test4() {
    var nom = document.getElementById("nom").value;
    var compteur = 1;
    // setInterval demande au navigateur de rappeler la fonction en parametre
    // tout les x millisecondes
    window.setInterval(function() {
        console.log(nom + " : " + compteur++);
    }, 2000);
}

function Article(nom, prix, poids) {
    // attribut "publique"
    this.nom = nom;

    this.getPrix = function () {
        return prix;
    }
    this.setPrix = function(nPrix) {
        prix= nPrix;
    }
    this.getPoids = function() {
        return poids;
    }
    this.toString = function() {
        return this.nom + ", " + this.getPrix()  + ", " + this.getPoids();
    }
}

function test5() {
    var a1 = new Article("tie fighter", 15000, 2300);
    console.log(a1.toString());
    console.log(a1.poids);
    console.log(a1.getPoids());
    a1.setPrix(13500);
    console.log(a1.toString());
}
