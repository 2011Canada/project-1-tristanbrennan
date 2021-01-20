

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

        let res = await fetch("http://localhost:8080/ServletDemo/auth",{
        //let res = await fetch("../auth",{
            method:"POST",
            body: JSON.stringify(credentials),
            headers:{
                "Content-Type" : "application/json"
            }
        })

        let user = await res.json()
        console.log(user);
        // if(user.role_id == 0){
        //     window.location=("http://localhost:8080/ServletDemo/table/table.html");
        // }
        // else if(user.role_id == 1){
        //     window.location=("http://localhost:8080/ServletDemo/mastertable/mastertable.html");
        // }
        if(user.role_id == 0){
                window.location= "/ServletDemo/table/table.html";
            }
            else if(user.role_id == 1){
                window.location.href= "./mastertable/mastertable.html";
            }
    } catch( e) {
        console.log(e);
    }
}


document.getElementsByTagName("form")[0].addEventListener('submit', loginSubmit)