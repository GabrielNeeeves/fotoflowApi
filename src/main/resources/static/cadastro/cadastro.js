document.getElementById('cadastroForm').addEventListener('submit', function (event) {
    event.preventDefault(); // Evita o recarregamento da página

    const formData = new FormData(event.target);
    const data = {
        nome: formData.get('nome'),
        email: formData.get('email'),
        senha: formData.get('senha'),
        tel: formData.get('tel'),
        ende: formData.get('ende'),
        //fotografo: formData.get('fotografo') === 'on'
    };

    fetch('http://localhost:8080/clientes/post', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
        .then(response => {
            if (response.ok) {
                alert('Cadastro realizado com sucesso!');
                window.location.href = '../login/login.html';
            } else {
                throw new Error('Erro ao realizar o cadastro.');
            }
        })
        .catch(error => {
            console.error(error);
            alert('Erro ao realizar o cadastro.');
        });
});
