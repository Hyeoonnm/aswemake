const form = document.getElementById('form');

form.addEventListener('submit', (e) => {
    e.preventDefault();

    const payload = new FormData(form);

    fetch('../member/api/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/application/json'
        },
        body: payload,
    })
        .then(res => res.json())
        .then(data => console.log(data));
});