async function postData(url = '', data = {}) {
    // Default options are marked with *

    const response = await fetch(url, {
        method: 'POST', // *GET, POST, PUT, DELETE, etc.
        mode: 'cors', // no-cors, *cors, same-origin
        cache: 'no-cache', // *default, no-cache, reload, force-cache, only-if-cached
        credentials: 'include', // include, *same-origin, omit
        headers: {
        'Content-Type': 'application/json'
        // 'Content-Type': 'application/x-www-form-urlencoded',
        },
        redirect: 'follow', // manual, *follow, error
        referrerPolicy: 'no-referrer', // no-referrer, *no-referrer-when-downgrade, origin, origin-when-cross-origin, same-origin, strict-origin, strict-origin-when-cross-origin, unsafe-url
        body: JSON.stringify(data), // body data type must match "Content-Type" header
    });
    result = await response.json()
    return result;
}

function inputToJsonById(array){
    result = {}
    array.forEach(element => {
        result[element] = document.getElementById(element).value
    });
    return result
}

async function checkDuplicatesAndNext(url = '', data = {}) {
    //Default options are marked with *
    //data = {"userId" : document.getElementById("userId").value, "password" : document.getElementById("password").value}

    let result = await postData(url = url, data = data);

    if(result.didLogin){
        const userName = document.getElementById("userId").value;
        //TODO : USE SESSION
        window.location.href='gamechat.html'
    }else{ // login fail
        //TODO : login fail
        alert( 'Your nickname is already being used. Try another nickname please.' );
    }
}