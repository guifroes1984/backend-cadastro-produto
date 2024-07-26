
//Masks
$("#inputPreco").mask("000.000.000.000.000,00", { reverse: true });

function convertToNumber(precoFormat){
    return precoFormat.replace(/\./g, '').replace(',', '.');
}

var produtos = [
    {
        id: 1, 
        nome: "Computador M1-TX", 
        descricao: "Intel i7, 16GB, SSD 256, HD 1TB", 
        preco: 4900, 
        categoria: 1, 
        promocao: true, 
        novo: true
    }, 
    {
        id: 2, 
        nome: "Computador M2-TX", 
        descricao: "Intel i7, 16GB, SSD 512, HD 1TB", 
        preco: 5900, 
        categoria: 2,  
        promocao: false, 
        novo: true
    }, 
    {
        id: 3, 
        nome: "Computador M1-T", 
        descricao: "Intel i5, 8GB, HD 1TB", 
        preco: 2900, 
        categoria: 3,  
        promocao: false, 
        novo: false
    }
];

var categorias = [
    { id: 1, nome: "Produção Própria" }, 
    { id: 2, nome: "Nacional" }, 
    { id:3, nome: "Importado" }
];

carregarProdutos();

function carregarProdutos() {
    for(let prod of produtos) {
        adicionarNovaLinha(prod);
    }
}

//Salva o produto
function save() {

    var prod = {
        id: produtos.length + 1, 
        nome: document.getElementById("inputNome").value, 
        descricao: document.getElementById("inputDescricao").value, 
        preco: convertToNumber(document.getElementById("inputPreco").value), 
        categoria: document.getElementById("selectCategoria").value, 
        promocao: document.getElementById("CheckBoxPromocao").checked, 
        novo: document.getElementById("CheckBoxNovoProduto").checked
    };

    adicionarNovaLinha(prod);
    produtos.push(prod);

    document.getElementById("formProduto").reset();

}

//Adiciona nova linha
function adicionarNovaLinha(prod) {
    var table = document.getElementById("produtosTable");

    var novaLinha = table.insertRow();

    //Insere id produto na tabela
    var idNode = document.createTextNode(prod.id);
    novaLinha.insertCell().appendChild(idNode);

    //Insere produto na tabela
    var nomeNode = document.createTextNode(prod.nome);
    novaLinha.insertCell().appendChild(nomeNode);

    //Insere descrição na tabela
    var descricaoNode = document.createTextNode(prod.descricao);
    var cell =  novaLinha.insertCell();
    cell.className="d-none d-md-table-cell";
    cell.appendChild(descricaoNode);

    var formatter = new Intl.NumberFormat("pt-BR", {
        style: "currency", 
        currency: "BRL", 
    });

    //Insere preço na tabela
    var precoNode = document.createTextNode(formatter.format(prod.preco));
    novaLinha.insertCell().appendChild(precoNode);

    //Insere categoria na tabela
    var categoriaNode = document.createTextNode(categorias[prod.categoria - 1].nome);
    novaLinha.insertCell().appendChild(categoriaNode);

    //Insere produto opções
    var opcoes = '';
    if (prod.promocao) {
        opcoes = "<span class='badge bg-success me-1'>P</span>";
    }

    if (prod.novo) {
        opcoes += "<span class='badge bg-primary'>L</span>";
    }

    cell = novaLinha.insertCell();
    cell.className="d-none d-md-table-cell";
    cell.innerHTML = opcoes;

}