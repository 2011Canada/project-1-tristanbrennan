

//the submit event
async function loginSubmit(e){
    e.preventDefault();

    let username = document.getElementById("username-input").value
    let password = document.getElementById("password-input").value

    //enhanced object literals
    const credentials = {
        username,//this is the same as below
        password:password
    }

    try{

        let res = await fetch("http://localhost:8080/firstservlets/login",{
            method:"POST",
            body: JSON.stringify(credentials),
            headers:{
                "Content-Type" : "application/json"
            }
        })

        let user = await res.json()
        console.log(user);
    } catch( e) {
        console.log(e);
    }
}


document.getElementsByTagName("form")[0].addEventListener('submit', loginSubmit)