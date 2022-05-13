function showPassword() {
    const btn1 = document.querySelector('.password_btn');
    const input1 = document.querySelector('.password_input');

    btn1.addEventListener('click', () => {
        btn1.classList.toggle('active');

        if(input1.getAttribute('type') === 'password'){
            input1.setAttribute('type', 'text');
        }else {
            input1.setAttribute('type', 'password');
        }
    })
}
showPassword();