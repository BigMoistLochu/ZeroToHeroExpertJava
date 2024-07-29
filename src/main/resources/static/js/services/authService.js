document.getElementById('loginForm').addEventListener('submit', function(event) {
    event.preventDefault(); // Zatrzymanie domyślnego wysyłania formularza

    // Pobranie danych z formularza
    const formData = new FormData(event.target);

    // Konwersja danych do obiektu JSON
    const jsonData = {};
    formData.forEach((value, key) => {
        jsonData[key] = value;
    });

    // Wysłanie danych w formacie JSON przy użyciu Fetch API
    fetch('http://localhost:8080/auth', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(jsonData)
    }).then(response => response.json())
        .then(data => {
                localStorage.setItem("JWT", data.acces_token);

            }
        ).catch(error =>
        console.error('Error:', error)
    );
});