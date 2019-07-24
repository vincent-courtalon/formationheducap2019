$(document).ready(function() {
    // code d'initialisation
    $("#switchPromotion").click(switchPromotions);
    $("h3#titrePlats").click(switchPlats);
    $("h3#titreDesserts").click(switchDesserts);
});

function switchPromotions() {
    $("ul.menu li.promotion").fadeToggle(500);
}

function switchPlats() {
    $("ul.plats").slideToggle(700);
}

function switchDesserts() {
    $("ul.desserts").slideToggle(700);
}