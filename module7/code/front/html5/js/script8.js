$(document).ready(function(){
    console.log("hello");
    var canvas = document.getElementById("dessin");
    // le contexte permet de "dessiner" dans le canvas
    var ctx = canvas.getContext('2d');
    var color = 255;
    ctx.clearRect(0, 0, 600, 600);
    ctx.save();
   /* ctx.fillStyle = 'rgb(100, 200, 50)';
    ctx.fillRect(0,0, 200,200);*/
    // gradiant circulaire centré sur 0 0 de rayon 600 et partant de noir vers blanc
   /* var grad = ctx.createRadialGradient(0,0,0,0,0,600);
    grad.addColorStop(0, '#000');
    grad.addColorStop(1, 'rgb(' +color + ', ' + color + ', ' + color + ')');
    ctx.fillStyle = grad;
    ctx.fillRect(0,0, 600,600);
    ctx.save();
*/
    var xdown = null;
    var ydown = null;
    var etat = 0;
    $("body").mousedown(function (event) {
        console.log("bouton appuyé -> " + event.buttons);
        etat = 1;
        // taille de la fenetre
        var width = window.innerWidth;
        var height = window.innerHeight;
        // coordonnées de la souris dans la fenetre
        var x = event.clientX;
        var y = event.clientY;

        // conversion vers coordonnées canvas
        var rx = x * (600 / width);
        var ry = y * (600 / height);
        xdown = rx;
        ydown = ry;
    });
    $("body").mouseup(function (event) {
        console.log("bouton relaché -> " + event.buttons);
        if (etat > 0) {
            etat = 0;
            // taille de la fenetre
            var width = window.innerWidth;
            var height = window.innerHeight;
            // coordonnées de la souris dans la fenetre
            var x = event.clientX;
            var y = event.clientY;
    
            // conversion vers coordonnées canvas
            var rx = x * (600 / width);
            var ry = y * (600 / height);
            ctx.fillStyle = 'red';
            ctx.fillRect(xdown, ydown,  rx - xdown, ry - ydown);
            xdown = null;
            ydown = null;
        }
    });
    
    $("body").mousemove(function (event) {
        // taille de la fenetre
        var width = window.innerWidth;
        var height = window.innerHeight;
        // coordonnées de la souris dans la fenetre
        var x = event.clientX;
        var y = event.clientY;

        // conversion vers coordonnées canvas
        var rx = x * (600 / width);
        var ry = y * (600 / height);
        if( etat == 1) {
            ctx.clearRect(0, 0, 600, 600);    
            ctx.strokeStyle="blue";
            ctx.strokeRect(xdown, ydown, rx - xdown, ry - ydown);
        }
   
        // valeur absolue (en optimisé)
  /*      var xc = ~~(256 * x /width);
        var yc = ~~(256 * y /height);
        grad = ctx.createRadialGradient(rx, ry, 0, rx, ry, 600);
        grad.addColorStop(0, '#000');
        grad.addColorStop(1, 'rgb(' + xc + ', ' + (255-xc) + ', ' + yc + ')');
        ctx.fillStyle = grad;
        ctx.fillRect(0, 0, 600, 600);*/
    });
});