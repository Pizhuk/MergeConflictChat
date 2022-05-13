const search_qs = document.querySelector('.search_field').oninput = function (){
    let val = this.value.trim().toLowerCase();
    let chatItems = document.querySelectorAll('.chat h2');
    if (val != ''){
        chatItems.forEach(function (elem){
            if (!elem.innerText.toLowerCase().startsWith(val)){
                elem.closest('.chat').classList.add('hide');
            }
            else {
                elem.closest('.chat').classList.remove('hide');
            }
        })
    }
    else {
        chatItems.forEach(function (elem){
            elem.closest('.chat').classList.remove('hide');
        })
    }
}