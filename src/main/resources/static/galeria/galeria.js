
// Seleciona o botão e o input
const addImageButton = document.getElementById("addImageButton");
const imageInput = document.getElementById("imageInput");
const photoGallery = document.getElementById("photoGallery");

// Exibe o explorador de arquivos quando o botão é clicado
addImageButton.addEventListener("click", () => {
    imageInput.click();
});

function OnSubmit(){
    console.log("chamou aq");
    //Chamar os services de Post de Cadastro aqui
};

// Lida com as imagens selecionadas
imageInput.addEventListener("change", () => {
    const files = imageInput.files;

    if (files.length === 0) {
        alert("Por favor, selecione uma ou mais fotos para adicionar.");
        return;
    }

    for (const file of files) {
        // Cria uma URL temporária para a imagem
        const fileURL = URL.createObjectURL(file);

        // Cria um elemento <img> para exibir a foto
        const img = document.createElement("img");
        img.src = fileURL;
        img.alt = "Foto adicionada";
        img.className = "uploaded-photo";

        // Adiciona a imagem na galeria
        photoGallery.appendChild(img);

        // Mensagem no console para simular envio ao servidor
        console.log(`Foto "${file.name}" adicionada à galeria (simulação de envio).`);
    }

    // Limpa o input para permitir novas seleções
    imageInput.value = "";
});
