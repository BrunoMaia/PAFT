const XIS = 0;
const BOLINHA = 1;
const VAZIA = 2;
const valores = {"X": XIS, "O": BOLINHA,"☐": VAZIA};
const valoresReverso = {0: "X", 1: "O", 2: "☐"};
const botaoXis = document.getElementById("botaoXis");
const botaoBolinha = document.getElementById("botaoBolinha");
let variaveis = {"celulasVazias": [1,2,3,4,5,6,7,8,9],"nJogada": 1};

function trocaJogador(opcao){
    if(opcao === BOLINHA){
        variaveis.jogador = BOLINHA;
        variaveis.computador = XIS;
        botaoXis.disabled = true;
        botaoBolinha.classList.add("ativo");
    }
    else if(opcao === XIS){
        variaveis.jogador = XIS;
        variaveis.computador = BOLINHA;
        botaoBolinha.disabled = true;
        botaoXis.classList.add("ativo");
    }
    
}

function selecionaCelula(celula,eJogador, simbolo = variaveis.jogador){
    if (isNaN(simbolo)){
      return  
    } 
    let botaoCelula = document.getElementById(celula);
    botaoCelula.innerHTML = valoresReverso[simbolo];
    botaoCelula.disabled = true;
    if(eJogador){
        computadorFazJogada();
    }
    if (simbolo !== VAZIA){
        verificaVencedor();
    }
    variaveis.celulasVazias = variaveis.celulasVazias.filter(item => item !== celula);
    variaveis.nJogada ++;
}

function computadorFazJogada(){
    verificaVencedor();
    const jogada = variaveis.celulasVazias[Math.floor(Math.random() * variaveis.celulasVazias.length*10)];
    let celulaJogada = document.getElementById(jogada);
    if(celulaJogada.disabled !== true){
        selecionaCelula(jogada,false,variaveis.computador)
    }
    else{
        selecionaCelula(variaveis.celulasVazias[0],false,variaveis.computador);
    }
    console.log(jogada);
}

function sorteiaNumero(minimo, maximo) {
    return Math.floor(Math.random() * (maximo - minimo + 1) + minimo)
}

function obtemJogadaCelula(celula){
    const botaoCelula = document.getElementById(celula);
    if(botaoCelula.disabled){
        return valores[botaoCelula.innerHTML]
    }
    return -1
}

function verificaVencedor(){
    const condicoesVencedoras = [
        [1, 2, 3],
        [4, 5, 6],
        [7, 8, 9],
        [1, 4, 7],
        [2, 5, 8],
        [3, 6, 9],
        [1, 5, 9],
        [3, 5, 7]
     ];
     if(variaveis.nJogada === 9){
        resetaJogo();
        alert("O jogo terminou!");
     }
}

function resetaJogo(){
    window.location.reload();
}

function limpaTabuleiro(){
    for(let i = 1; i < 10; i++){
        selecionaCelula(i,false,VAZIA);
        document.getElementById(i).disabled = false;
    }
}