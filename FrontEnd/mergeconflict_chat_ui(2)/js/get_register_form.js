    const login_qs = document.querySelector('.registration_input.login').oninput = function (){
        let emailInput = document.getElementById("login");
        let val = this.value;
        let item = document.querySelectorAll('.input_warning.login');
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

    const password_qs = document.querySelector('.registration_input.password_input').oninput = function (){
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
        }
    }

    const email_qs = document.querySelector('.registration_input.email').oninput = function (){
        let emailInput = document.getElementById("email");
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
            email_bool=true;
        }
    }

    const company_qs = document.querySelector('.registration_input.company').oninput = function (){
        let emailInput = document.getElementById("company");
        let item = document.querySelectorAll('.input_warning.company');
        if (!emailInput.checkValidity()){
            item.forEach(function (elem){
                elem.classList.remove('hide');
            })
        }
        else {
            item.forEach(function (elem){
                elem.classList.add('hide');
            })
        }
    }

    const phone = document.querySelector('.registration_input.phone').oninput = function (){
        let emailInput = document.getElementById("phone");
        let val = this.value;
        let item = document.querySelectorAll('.input_warning.phone');
        if (!emailInput.checkValidity() || val.length === 0){
            item.forEach(function (elem){
                elem.classList.remove('hide');
            })
        }
        else {
            item.forEach(function (elem){
                elem.classList.add('hide');
            })
            phone_bool=true;
        }
        if (phone_bool && email_bool && password_bool && login_bool){
            document.getElementById("btn").disabled = false;
            sendForm();
        }
    }

    let login_bool = false;
    let password_bool = false;
    let email_bool = false;
    let phone_bool = false;

    let modal = document.getElementById("my_modal");
    let span = document.getElementsByClassName("close_modal_window")[0];

    function sendForm(){
        login_bool = false;
        password_bool = false;
        email_bool = false;
        phone_bool = false;
        document.getElementById('registration_form').addEventListener('submit', function (e) {
            let login = "";
            let password = "";
            let confirm_password = "";
            let email = "";
            let phone_num = "";
            let company = "";

            e.preventDefault()

            let inputs = document.querySelectorAll("input")

            for (let q=0; q<inputs.length; ++q) {
                if (inputs[q].name === "login") {
                    login = inputs[q].value;
                }
                if (inputs[q].name === "password") {
                    password = inputs[q].value;
                }
                if (inputs[q].name === "confirm_password") {
                    confirm_password = inputs[q].value;
                }
                if (inputs[q].name === "email") {
                    email = inputs[q].value;
                }
                if (inputs[q].name === "phone_num") {
                    phone_num = inputs[q].value;
                }
                if (inputs[q].name === "company") {
                    company = inputs[q].value;
                }
            }
            if (password !== confirm_password){
                modal.style.display = "block";
            }
            else {
                parseToJson(login, password, email, phone_num, company)
            }
        })
    }


    function parseToJson(login, password, email, phone_num, company){
        let log_info = {
            login: login,
            password: password,
            email: email,
            phoneNumber: phone_num,
            company: company
        }
        let log_info_json = JSON.stringify(log_info);
        axios.post("http://localhost:8090/reg", log_info_json)
            .then((response) => {
                console.log(response.status);
                console.log(response.statusText);
                alert(response.info)
            }, (error) => {
                // console.log(error.status);
                // console.log(error.response.data);
                // console.log(error.response.status);
                // console.log(error.response.headers);
                console.log(error.response);
                // console.log(error.statusText);
                alert(error.info);
            });
        console.log(log_info_json)
    }

    span.onclick = function () {
        modal.style.display = "none";
    }

    window.onclick = function (event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    }