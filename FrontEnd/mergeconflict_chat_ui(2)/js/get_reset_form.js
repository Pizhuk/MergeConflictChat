const reset_pass_qs = document.querySelector('.registration_input').oninput = function (){
    let emailInput = document.getElementById("email_reset");
    let val = this.value;
    let item = document.querySelectorAll('.input_warning');
    if (!emailInput.checkValidity() || val.length === 0){
        item.forEach(function (elem){
            elem.classList.remove('hide');
        })
    }
    else {
        if (val.length !== 0){
            item.forEach(function (elem){
                elem.classList.add('hide');
            })
            document.getElementById("btn").disabled = false;
            sendForm();
        }
    }
}

function sendForm(){
    document.getElementById('reset_form').addEventListener('submit', function (e) {
        let email = "";
        e.preventDefault()

        let inputs = document.querySelectorAll("input")

        for (let q=0; q<inputs.length; ++q) {
            if (inputs[q].name === "email") {
                email = inputs[q].value;
            }
        }
        parseToJson(email)
    })
}


function parseToJson(email){
    let log_info = {
        email: email
    }
    let log_info_json = JSON.stringify(log_info);
    axios.post("http://localhost:8090/reset", log_info_json)
        .then((response) => {
            console.log(response)
        }, (error) => {
            console.log(error);
        });
    console.log(log_info_json)
}