
function test1() {
    // mot clef var permet de déclarer une variable

    var a = 15;
    console.log("a = " + a);
    console.log("type de a = " + typeof(a));
    a = "hello";
    console.log("a = " + a);
    console.log("type de a = " + typeof(a));
    
    var b; // une variable non "définie" est de type 'undefined'
    console.log("b = " + b);
    console.log("type de b = " + typeof(b));

    var c = true;
    console.log("c = " + c);
    console.log("type de c = " + typeof(c));

    var d = 3.1415;
    console.log("d = " + d);
    console.log("type de d = " + typeof(d));

    // les objets
    var e = {
        "nom" : "steak de lama des andes",
        "prix" : 19.99
    };

    console.log("type de e = " + typeof(e));
    console.log(e);

    e = null;
    console.log("type de e = " + typeof(e));
    console.log(e);

    // si j'affecte undefined, le type passe aussi a undefined
    e = undefined;
    console.log("type de e = " + typeof(e));
    console.log(e);

    // en javascript, les tableaux sont des objets "spéciaux"
    var g = [1, 2, 3, 4];
    console.log(g);
    console.log("typr de g = " + typeof(g));

}

function test2() {
    var a,b,c;

    a = 10;
    b = 15;
    c = "3";

    var result = a + b + c;
    console.log("result = " + result);
    result = c + a  + b;
    console.log("result = " + result);

    a = 3;
    b = 3;
    console.log("(a == b ) -> " + (a == b));
    console.log("(a == c ) -> " + (a == c));
    // egalite stricte, même valeur et même type
    console.log("(a === b ) -> " + (a === b));
    console.log("(a === c ) -> " + (a === c));

    console.log("(undefined == null) -> " + (undefined == null));
    console.log("(undefined === null) -> " + (undefined === null));

    // le type d'une fonction est 'function'
    // en javascript les fonctions sont traitée comme des variables
    // contenant du code
    console.log("type de test1 -> " + typeof(test1));
    console.log("test1 = " + test1);

}

function test3() {
    // appel de fonction abec parametres
    console.log("multiplication(3,4) -> " + multiplication(3 , 4));
    console.log("multiplication(3,4,5) -> " + multiplication(3 , 4, 5));
    console.log("multiplication(3) -> " + multiplication(3));
    console.log(moyenne(3, 12, 15));

    var tab1 = ["lundi", "mardi", "mercredi"];
    // tab1.poulet = "frites";
    for( prop in tab1) {
        console.log(prop + " -> " + tab1[prop]);
    }
    // introduit plus récemment: le for/of
    // ne marche que sur les iterable
    for( value of tab1) {
        console.log(value);
    }

    var obj1 = {
        "nom" : "steak de lama des andes",
        "prix" : 19.99,
        "poids" : 0.75,
        "toString": function() {
            // en javascript, le this n'est pas facultatif
            return "produit '" + this.nom + "' de prix " + this.prix;
        }
    }

    // je peux aussi enumerer les propriétés d'un objet donc
    for (prop in obj1) {
        console.log(prop + " -> " + obj1[prop]);
    }
    console.log(obj1.toString());

    var i = 0;
    while(i < 10) {
        i++;
        console.log("i = " + i);
        if (i == 6)
            break;
    }
}

function multiplication(x, y) {
    // si le parametre y est non présent, un par defaut
    if (y === undefined) {
        y = 1;
    }
    //y = y || 1;

    return x * y;
}

function moyenne() {
    //console.log(arguments);
    if (arguments.length  == 0)
        return NaN;
    var total = 0;
    for(var idx = 0; idx < arguments.length; idx++) {
        total += arguments[idx];
    }
    return total / arguments.length;
}
