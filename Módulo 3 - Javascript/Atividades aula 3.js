 // atividades aula 3
const albuns = [
    {nome: "Mais", cantor: "Marisa monte", ano: 1991, nota: 8.5},
    {nome: "A tempestade", cantor: "Legião Urbana", ano: 1996, nota: 9.5},
    {nome: "Domingo", cantor: "Titãs", ano: 1995, nota: 7.5},
    {nome: "Ouro de Minas", cantor: "Roupa Nova", ano: 2001, nota: 8},
    {nome: "Como estão vocês", cantor: "Titãs", ano: 2003, nota: 7},
    {nome: "Troco Likes", cantor: "Thiago Iorc", ano: 2015, nota: 4.5},
    {nome: "Dois", cantor: "Legião Urbana", ano: 1986, nota: 10},
    {nome: "Radiola", cantor: "Skank", ano: 2004, nota: 6.5},
    {nome: "Roupa acústico", cantor: "Roupa Nova", ano: 2004, nota: 9},
    {nome: "Umbilical", cantor: "Thiago Iorc", ano: 2011, nota: 3.5},
    {nome: "Barulhinho bom", cantor: "Marisa monte", ano: 1996, nota: 7.5}
];

const cantores = [
    {nome: "Vinícius de Moraes", estilo: "MPB"},
    {nome: "Rita Lee", estilo: "Rock"},
    {nome: "Marisa monte", estilo: "MPB"},
    {nome: "Legião Urbana", estilo: "Rock"},
    {nome: "Titãs", estilo: "Rock"},
    {nome: "Roupa Nova", estilo: "Pop rock"},
    {nome: "Thiago Iorc", estilo: "Nova MPB"},
    {nome: "Skank", estilo: "Pop rock"}
];

// atividade 1
let albunsMarisaMonte =
albuns.filter(x => x.cantor === "Marisa monte").map(x => ({nome:x.cantor,ano: x.ano}));
console.log(albunsMarisaMonte);

// atividade 2
let cantoresEAlbuns = albuns.flatMap(x => [x.cantor,x.nome]);
console.log(cantoresEAlbuns);

// atividade 3
let media = albuns.filter(x => x.ano < 2005).reduce((a,e,i,arr)=>{
  if(i === arr.length-1) return (a+e.nota)/(i+1);
  return a + e.nota;
},0)
console.log(media.toFixed(2));

// atividade 4
let albunsCantor = albuns.reduce((contagem, a) => {
  if(!contagem[a.cantor]){
    contagem[a.cantor] = 1;
  }
  else{
    contagem[a.cantor] ++;
  }
  return contagem;
},{});
console.log(albunsCantor);
console.log(Object.keys(albunsCantor));

// atividade 5
const mapCantores = cantores.reduce((m,c) => {m[c.nome] = c; return m;},{});
albuns.forEach(a => a.cantor = mapCantores[a.cantor]);
console.log(albuns);

// atividade 6
const albunsOrdenados = [...albuns]
   .sort((a, b) => a.ano - b.ano);
console.log(albunsOrdenados);

// atividade 7
function paginador(lista, tamanhoPagina = 10) {
    return function(nPagina = 0) {
        const inicio = nPagina * tamanhoPagina;
        const fim = (nPagina + 1) * tamanhoPagina;
        return lista.slice(inicio, fim);
    }
}

