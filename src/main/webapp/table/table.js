//the submit event
async function fetchTable(){

    try{

        let res = await fetch("http://localhost:8080/ServletDemo/account",{
        //let res = await fetch("../auth",{
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