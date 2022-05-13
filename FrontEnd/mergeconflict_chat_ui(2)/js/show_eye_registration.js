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

    const btn2 = document.querySelector('.password_btn2');
    const input2 = document.querySelector('.password_input2');

    btn2.addEventListener('click', () => {
        btn2.classList.toggle('active');

        if(input2.getAttribute('type') === 'password'){
            input2.setAttribute('type', 'text');
        }else {
            input2.setAttribute('type', 'password');
        }
    })
}
showPassword();