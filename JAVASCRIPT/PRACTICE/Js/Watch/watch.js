const format = (hours, minutes, seconds ) =>{

    if(hours >= 0 && hours <=9){
        hours = '0' + hours;
    }

    if(minutes >= 0 && minutes <=9){
        minutes = '0' + minutes;
    }

    if(seconds >= 0 && seconds <=9){
        seconds = '0' + seconds;
    }

    return hours + ":" + minutes + ":" + seconds;

};

const getTime = () =>{

const date = new Date();

const hours = date.getHours();
const minutes = date.getMinutes();
const seconds = date.getSeconds();

const t = document.getElementsByClassName('time');

t[0].innerHTML = format( hours , minutes, seconds);

setTimeout(getTime, 1000);

}

getTime();








