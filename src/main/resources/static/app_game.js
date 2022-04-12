function makeMap(){
    let step;
    for (step = 0; step < 25; step++) {
        $("#game-map").append("<div class='item' style='background-color: ivory' id ="+ step + ">" +"</div>")
    }
}

$(function () {
    $( ".item" ).click( function() {
        checkAndPost('','',this)
    });
});

async function checkAndPost(url = '', data = {}, cur) {
    //1. check if current box is colored
    //2. if colored : post
    //   else: ignore
    // Default options are marked with *
    console.log(cur.id);
    console.log(cur.style.backgroundColor);
    cur.style.backgroundColor = "#00FF00";
}