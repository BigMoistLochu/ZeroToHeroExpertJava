document.getElementById('loginForm').addEventListener('submit', function(event) {
    event.preventDefault(); // Zatrzymanie domyślnego wysyłania formularza

    // Pobranie danych z formularza
    const formData = new FormData(event.target);

    // Konwersja danych do obiektu JSON
    const jsonData = {}

    formData.forEach((value, key) => {
        jsonData[key] = value;
    });

    // Wysłanie danych w formacie JSON przy użyciu Fetch API
    fetch('http://localhost:8080/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(jsonData)

    })
        .then(response => {return response.json()})
        .then(data => {
                localStorage.setItem("JWT", data.access_token);
                window.location.assign("account")

            }
        ).catch(error =>
        console.error('Error:', error)
    );
});


document.getElementById('registerForm').addEventListener('submit', function(event) {
    event.preventDefault(); // Zatrzymanie domyślnego wysyłania formularza

    // Pobranie danych z formularza
    const formData = new FormData(event.target);

    // Konwersja danych do obiektu JSON
    const jsonData = {}

    formData.forEach((value, key) => {
        jsonData[key] = value;
    });

    // Wysłanie danych w formacie JSON przy użyciu Fetch API
    fetch('http://localhost:8080/register', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(jsonData)

    })
        .then(response => {
            if(response.status === 201){
                console.log("Utworzone zostalo konto")
            }
        })
        .catch(error =>
        console.error('Error:', error)
    );
});


document.getElementById("githubOAuth").addEventListener("click",function (){
    var client_ID = "Ov23liFFZf0jP1qF0dl2";

    var REDIRECT_URI = "http://localhost:8080/auth";
    var SCOPE = "user";
    // fetch("https://github.com/login/oauth/authorize?client_id="+client_ID+"&redirect_uri="+REDIRECT_URI+"&scope="+SCOPE)
    var url = "https://github.com/login/oauth/authorize?client_id="+client_ID+"&redirect_uri="+REDIRECT_URI+"&scope="+SCOPE;
    // window.location.href = url;
    var currentWindow = window;

    // Otwieramy nowe okno
    var newWindow = window.open(url, '_blank', 'width=600,height=400');
})


document.getElementById("googleOAuth").addEventListener("click",function (){
    var client_ID = "Ov23liFFZf0jP1qF0dl2";

    var REDIRECT_URI = "http://localhost:8080/auth";
    var SCOPE = "user";
    // fetch("https://github.com/login/oauth/authorize?client_id="+client_ID+"&redirect_uri="+REDIRECT_URI+"&scope="+SCOPE)
    var url = "https://github.com/login/oauth/authorize?client_id="+client_ID+"&redirect_uri="+REDIRECT_URI+"&scope="+SCOPE;
    // window.location.href = url;
    var currentWindow = window;

    // Otwieramy nowe okno
    var newWindow = window.open(url, '_blank', 'width=600,height=400');
})




