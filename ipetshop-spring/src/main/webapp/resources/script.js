'use strict';

document.addEventListener('DOMContentLoaded', function () {


    let formImg = document.getElementById('form-img'),
        popup = document.querySelector('.popup');

    document.addEventListener('click', function (EO) {
        EO = EO || window.event;
        console.log(EO.target);


        if (EO.target == formImg){
            popup.classList.remove('hiden');

        }
        if (EO.target != popup && EO.target != formImg){
            popup.classList.add('hiden');
        }

    })
});