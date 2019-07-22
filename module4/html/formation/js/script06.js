
// ouvre un popup
// javascript (sauf webworker) est monothread
// c.a.d qu'il n'y a, dans une page, qu'une seule execution du javascript
// alert est un popup qu'on appelle un modal
// cela bloque l'execution de javascript en attendant le click de l'utilisateur
// c'est la première et dernière fois qu'on utilisera alert

//alert('bonjour depuis javascript');

// on va plutot utiliser, pour debugger/apprendre le console.log
// qui permet d'afficher un message dans la console debugger du navigateur

console.log('et bonjour dans la console');


function test1() {
    console.log("vous avez cliqué le " + new Date());
    document.getElementById('message').innerText = "salut, le " + new Date();

}