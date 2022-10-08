// declaração de funções
const mais = function(a,b){
    return a+b;
};

function maisSegundo(a,b){
    return a+b;
}

const maisTerceiro = (a,b) => a+b;

console.log(mais(1,2));
console.log(maisSegundo(3,4));
console.log(maisTerceiro(5,6));

// funções aceitam menos parâmetros
function menos(a,b){
    if(!b === undefined){
        return a - b;
    }
    return 0 - a;
}

console.log(menos(10));
console.log(menos(5,2));

// mais listas
function eCrescente(lista){
    let valorAnterior = 0;
    for(valor of lista){
        if(valor > valorAnterior){
            valorAnterior = valor;
            continue;
        }
        else {
            return false;
        }
    }
    return true;
}

const listaCrescente = [1,2,3,4,10,55,63,99,192];
const listaNaoCrescente = [1,2,5622,126];

console.log(eCrescente(listaCrescente));
console.log(eCrescente(listaNaoCrescente));

// spread operators
function maior(... valores){
    let maior;
    for(let i = 0; i < valores.length; i ++){
        if(i === 0) maior = valores[i];
        else if(valores[i] > maior) maior = valores[i];
    }
    return maior;
}

console.log(maior(1,10,-1,5));
console.log(maior(1,-100,5));

// parametro padao
function join(lista, separador = ','){
    let retorno = '';
    for(valor of lista){
        retorno += valor;
        retorno += separador;
    }
    return retorno.substring(0,retorno.length -1);
}

let lista = ["o","João","foi","na","casa","do","Pedrinho","n°",52];
console.log(join(lista));

// fibonacci recursiva e não recursiva

function fibonacciNaoRecursiva(indice){
    let n1 = 1;
    let n2 = 1;
    let resultado = n1;
    for (const i = 2; i <= indice; i++) {
        resultado = n1 + n2;
        n1 = n2;
        n2 = resultado;
    }
    return resultado;
}

function fibonacciRecursiva(valor){
    if(valor === 0 || valor === 1) return 1;
    return fibonacciRecursiva(valor - 1) + fibonacciRecursiva(valor - 2)
}

console.log(fibonacciRecursiva(6));

// atividade mapear
function mapear(array, funcao){
    let retorno = [];
    for(elemento of array){
        retorno.push(funcao(elemento));
    }
    return retorno;
}

const dobro = mapear([1,2,3,4], x => x * 2)
console.log(dobro);

// collaztz com closures
function collatz(valor){
    let col;
    return function(){
        if (col === 1) return col
        if(col === undefined)  {
            col = valor % 2 === 0 ? valor /= 2 : (3 * valor) + 1;
        }else{
            col = col % 2 === 0 ? col /= 2 : (3 * col) + 1;
        }
        return col;
    }
}

const seq = collatz(5);
console.log(seq());
console.log(seq());
console.log(seq());
console.log(seq());
console.log(seq());

// decorators
function verbose(funcao){
    return function(... args){
    const resultadoFuncao = funcao(...args);
        console.log(funcao.name + "("+args.join() +") = "+ resultadoFuncao);
      return resultadoFuncao;
    }
}

const soma = (a, b) => a + b;
const sum = verbose(soma);
sum(5,2); //Imprime soma(5,2) = 10

// fixador de parâmetros
function fixar(funcao, ...args){
    return function(...outroArgs){
        const resultado = funcao(...args,...outroArgs);
        console.log(resultado);
        return resultado;
    }
}

function log(modulo, nivel, texto) {
    console.log(`${nivel}: ${texto} (${modulo})`)
}
let logAula = fixar(log, `aula.js`, 'INFO');
logAula("Exemplo");  //Imprime INFO: Exemplo (aula.js)
logAula("PUCPR");  //Imprime INFO: PUCPR (aula.js) 