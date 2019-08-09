$(document).ready(function() {

    var mavideo = document.getElementById("mavideo");
    var play = $("#play");
    var stop = $("#stop");
    var readyplay = false;
    
    mavideo.oncanplay = function() {
        readyplay = true;
    };
    mavideo.ontimeupdate = function() {
        $("#duration").text("position: " + mavideo.currentTime);
    }
    play.click(function() {
        if (readyplay) {
            if (mavideo.paused) {
                if (mavideo.ended) {
                    mavideo.currentTime = 0;
                }
                mavideo.play();
                $(this).text("pause");
            }
            else {
                mavideo.pause();
                $(this).text("play");
            }
            
        }
    })
});
