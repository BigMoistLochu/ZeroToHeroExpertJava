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
        .then(response => {return response.json()})
        .then(data => {
                console.log(data)
            }
        ).catch(error =>
        console.error('Error:', error)
    );
});




