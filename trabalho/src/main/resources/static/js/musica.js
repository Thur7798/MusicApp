document.getElementById("pesquisa").addEventListener("input", function () {
    const query = this.value.trim();

    if (query.length > 0) {
        const xhr = new XMLHttpRequest();
        xhr.open("GET", `/pesquisar?query=${query}`, true);
        xhr.onload = function () {
            if (xhr.status === 200) {
                document.getElementById("resultados").innerHTML = xhr.responseText;

                // Vinculando o evento de clique às músicas após o AJAX
                document.querySelectorAll('.musica-item').forEach(item => {
                    item.addEventListener('click', function () {
                        const nomeMusica = this.textContent; // Obtém o nome da música
                        const urlMusica = this.getAttribute('data-url'); // Obtém a URL da música associada
                        const idMusica = this.getAttribute("data-id")
                        tocarMusica(nomeMusica, urlMusica, idMusica); // Chama a função para tocar a música
                    });
                });
            }
        };
        xhr.onerror = function () {
            document.getElementById("resultados").innerHTML = "<p>Erro ao carregar resultados. Tente novamente mais tarde.</p>";
        };
        xhr.send();
    } else {
        document.getElementById("resultados").innerHTML = "";
    }
});

// Função para reproduzir música ao clicar
function tocarMusica(nome, url, id) {
    document.getElementById("musica-nome").textContent = nome; // Atualiza o nome da música
    const audioPlayer = document.getElementById("audio-player");
    document.getElementById("musica-url").src = url; // Atualiza a URL da música
    document.getElementById("IdMusica").value = id;
    audioPlayer.load(); // Recarrega o player com a nova música
    audioPlayer.play(); // Reproduz automaticamente
}
function carregarPlaylists() {
    const xhr = new XMLHttpRequest();
    xhr.open("GET", "/playlist/listar", true); // Chama o método listar via GET
    xhr.onload = function () {
        if (xhr.status === 200) {
            document.getElementById("playlists-container").innerHTML = xhr.responseText;
        } else {
            document.getElementById("playlists-container").innerHTML = "<p>Erro ao carregar playlists.</p>";
        }
    };
    xhr.onerror = function () {
        document.getElementById("playlists-container").innerHTML = "<p>Erro de conexão. Tente novamente.</p>";
    };
    xhr.send();
}

// Chamar a função ao carregar a página
window.onload = carregarPlaylists;

function adicionarPlaylist() {
    const xhr = new XMLHttpRequest();
    xhr.open("GET", "/playlist/listarPlaylist", true); // Chama o método listar via GET
    xhr.onload = function () {
        if (xhr.status === 200) {
            document.getElementById("select-container").innerHTML = xhr.responseText;
        } else {
            document.getElementById("select-container").innerHTML = "<p>Erro ao carregar playlists.</p>";
        }
    };
    xhr.onerror = function () {
        document.getElementById("select-container").innerHTML = "<p>Erro de conexão. Tente novamente.</p>";
    };
    xhr.send();

    const btn = document.querySelector(".btnAddPlaylist")
    btn.style.display = "block"

    const select = document.querySelector(".select")
    select.style.display = "block"

    const btn2 = document.querySelector(".btnAdd")
    btn2.style.display = "none"

}

document.getElementById('adicionarMusicaForm').addEventListener('submit', function (e) {
    e.preventDefault(); // Impede o envio padrão do formulário

    const formData = new FormData(this);

    fetch(this.action, {
        method: 'POST',
        body: formData
    })
        .then(response => {
            if (response.ok) {
                const btn = document.querySelector(".btnAddPlaylist")
                btn.style.display = "none"

                const select = document.querySelector(".select")
                select.style.display = "none"

                const btn2 = document.querySelector(".btnAdd")
                btn2.style.display = "block"
                return response.text(); // Captura a resposta
            }
            throw new Error('Erro ao adicionar a música');
        })
        .then(data => {
            alert(data); // Exibe a mensagem de sucesso
            // Aqui você pode também atualizar a UI se necessário
        })
        .catch(error => {
            console.error('Erro:', error);
        });
});

function voltarPlaylist(){
    const div = document.querySelector(".playlists")
    div.innerHTML = ""

    const h2=document.createElement("h2")
    h2.innerText="Minhas Playlists"

    const container=document.createElement("div")
    container.setAttribute("id", "playlists-container")

    div.appendChild(h2)
    div.appendChild(container)
    carregarPlaylists()
}

function abrirPlaylist() {
    const playlist = document.querySelectorAll(".playlist-item")
    playlist.forEach(item => {
        item.addEventListener('click', function () {
            const div = document.querySelector(".playlists")

            const nome = this.textContent
            const id = this.getAttribute("data-id")

            

            const h2 = document.createElement("h2")
            h2.innerText = nome

            div.innerHTML = ""
            div.appendChild(h2)
            const xhr = new XMLHttpRequest();
            xhr.open("GET", `/playlist/musicas/${id}`, true); // Chama o método listar via GET
            xhr.onload = function () {
                if (xhr.status === 200) {
                    div.insertAdjacentHTML('beforeend', xhr.responseText);
                    document.querySelectorAll('.musica-item').forEach(musica => {
                        musica.addEventListener('click', function () {
                            const nomeMusica = this.textContent; // Obtém o nome da música
                            const urlMusica = this.getAttribute('data-url'); // Obtém a URL da música associada
                            const idMusica = this.getAttribute("data-id")
                            tocarMusica(nomeMusica, urlMusica, idMusica); // Chama a função para tocar a música
                        });
                    });
                } else {
                    document.querySelector(".playlists").innerHTML = "<p>Erro ao carregar playlists.</p>";
                }
            };
            xhr.onerror = function () {
                document.querySelector(".playlists").innerHTML = "<p>Erro de conexão. Tente novamente.</p>";
            };
            xhr.send();
            const voltar=document.createElement("h7")
            voltar.innerText="Voltar"
            voltar.addEventListener("click", voltarPlaylist)

            div.appendChild(voltar)


        })
    })
}
function abrirAlbum(){
    const playlist = document.querySelectorAll(".album-item")
    playlist.forEach(item => {
        item.addEventListener('click', function () {
            
            const div = document.querySelector(".playlists")
            div.innerHTML = ""
            const nome = this.textContent
            const id = this.getAttribute("data-id")

            

            const h2 = document.createElement("h2")
            h2.innerText = nome

            div.appendChild(h2)
            const xhr = new XMLHttpRequest();
            xhr.open("GET", `/album/musicas/${id}`, true); 
            xhr.onload = function () {
                if (xhr.status === 200) {
                    div.insertAdjacentHTML('beforeend', xhr.responseText);
                    document.querySelectorAll('.musica-item').forEach(musica => {
                        musica.addEventListener('click', function () {
                            const nomeMusica = this.textContent; 
                            const urlMusica = this.getAttribute('data-url'); 
                            const idMusica = this.getAttribute("data-id")
                            tocarMusica(nomeMusica, urlMusica, idMusica); 
                        });
                    });
                } else {
                    document.querySelector(".playlists").innerHTML = "<p>Erro ao carregar playlists.</p>";
                }
            };
            xhr.onerror = function () {
                document.querySelector(".playlists").innerHTML = "<p>Erro de conexão. Tente novamente.</p>";
            };
            xhr.send();
            const voltar=document.createElement("h7")
            voltar.innerText="Voltar"
            voltar.addEventListener("click", voltarPlaylist)

            div.appendChild(voltar)


        })
    })
}


