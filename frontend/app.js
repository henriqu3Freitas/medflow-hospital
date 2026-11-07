const API_URL = 'http://localhost:8080';

async function api(path, options = {}) {
    const response = await fetch(`${API_URL}${path}`, {
        headers: { 'Content-Type': 'application/json' },
        ...options
    });
    const body = await response.json().catch(() => ({}));
    if (!response.ok) throw new Error(body.erro || 'Não foi possível concluir a operação.');
    return body;
}

function mostrarMensagem(texto, tipo = 'sucesso') {
    const elemento = document.querySelector('#mensagem');
    if (!elemento) return;
    elemento.textContent = texto;
    elemento.className = `mensagem ${tipo}`;
    setTimeout(() => elemento.className = 'mensagem', 4000);
}

function valor(id) { return document.querySelector(`#${id}`).value.trim(); }
function preencherTabela(id, linhas, vazio = 'Nenhum registro encontrado.') {
    const tabela = document.querySelector(`#${id}`);
    tabela.innerHTML = linhas || `<tr><td class="vazio" colspan="10">${vazio}</td></tr>`;
}
function formatarData(data) {
    return data ? new Date(data).toLocaleString('pt-BR', { dateStyle: 'short', timeStyle: 'short' }) : '-';
}
function menuAtivo(pagina) {
    document.querySelectorAll('.lista-menu a').forEach(link => {
        link.classList.toggle('selecionado', link.dataset.pagina === pagina);
    });
}
