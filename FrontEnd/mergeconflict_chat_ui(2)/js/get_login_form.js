const email_qs = document.querySelector('.login_input').oninput = function (){
    let emailInput = document.getElementById("login_input");
    let val = this.value;
    let item = document.querySelectorAll('.input_warning.email');
    if (!emailInput.checkValidity() || val.length === 0){
        item.forEach(function (elem){
            elem.classList.remove('hide');
        })
    }
    else {
        item.forEach(function (elem){
            elem.classList.add('hide');
        })
        login_bool=true;
    }
}

const password_qs = document.querySelector('.login_input.password_input').oninput = function (){
    let emailInput = document.getElementById("password");
    let val = this.value;
    let item = document.querySelectorAll('.input_warning.password');
    if (!emailInput.checkValidity() || val.length === 0){
        item.forEach(function (elem){
            elem.classList.remove('hide');
        })
    }
    else {
        item.forEach(function (elem){
            elem.classList.add('hide');
        })
        password_bool=true;
        if (login_bool && password_bool){
            document.getElementById("btn").disabled = false;
            sendForm();
        }
    }
}

let login_bool = false;
let password_bool = false;

function sendForm(){
    login_bool = false;
    password_bool = false;
    document.getElementById('login_form').addEventListener('submit', function (e) {
        let email = "";
        let password = "";
        e.preventDefault()

        let inputs = document.querySelectorAll("input")

        for (let q=0; q<inputs.length; ++q) {
            if (inputs[q].name === "email") {
                email = inputs[q].value;
            }
            if (inputs[q].name === "password") {
                password = inputs[q].value;
            }
        }
        parseToJson(email, password);
    })
}

function parseToJson(email, password){
    let log_info = {
        login: email,
        password: password
        }
    let log_info_json = JSON.stringify(log_info);
    axios.post("http://localhost:8090/auth", log_info_json)
        .then((response) => {
            console.log(response)
        }, (error) => {
            console.log(error);
        });
    console.log(log_info_json)
}