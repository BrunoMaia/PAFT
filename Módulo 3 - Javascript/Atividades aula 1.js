// Exercício números de collatz
function Collatz(num){
    console.log(num);
    if(num === 1) return 1;
    else {return Collatz(num % 2 === 0 ? num /2 : (3 * num) +1); }
}

const valor = parseInt(prompt("Digite um numero"));
if(valor && !isNaN(valor) && valor > 0){
    Collatz(valor);
}

// Exercício altura

console.log("Exercício das alturas!");
let alturaChico = 1.5;
let alturaZe = 1.1;
while(alturaChico > alturaZe){
    alturaChico += 0.02;
    alturaZe += 0.03;
    console.log(`Altura do Chico ${alturaChico.toFixed(2)}, altura do Zé ${alturaZe.toFixed(2)}`);
}

// Exercício lista
function HelperLista(lista){
    let somaValores = 0;
    for(const objeto of lista){
        console.log(objeto);
        if(!isNaN(objeto)){
            somaValores += objeto;
        }
    }
    console.log("A média dos valores é: ",(somaValores/lista.length).toFixed(2));
}

HelperLista([1,2,3,4,5,6,7,"8",9]);

// Exercicio pessoas
let cores = ["branco","negro","pardo","outro"];
let pessoas = [];

for(let i = 0; i<16; i++){
    pessoas[i] = {
        cor: cores[Math.floor(Math.random()*pessoas.length)]
    };
}
const contagem ={
    "branco" : 0,
    "negro" : 0,
    "pardo" : 0,
    "outro" : 0,
    undefined : 0
}
for(const pessoa of pessoas){
    console.log(pessoa.cor);
    contagem[pessoa.cor] ++;
}
console.log(contagem);
