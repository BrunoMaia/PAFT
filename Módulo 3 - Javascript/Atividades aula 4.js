 
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
const lista = albuns.map(a => {
  const {nome, cantor, ano} = a;
  return `${nome} (${cantor}, ${ano})`;
})
console.log(lista);

// atividade 2
let a = 10;
let b = 20;
[a,b] = [b,a];
console.log(a,b);

// atividade 3
function justDate(data){
  let copy = new Date(data);
  copy.setHours(0);
  copy.setMinutes(0);
  copy.setSeconds(0);
  copy.setMilliseconds(0);

  return copy;
}

let agora = new Date();
let agoraZerado = justDate(agora);
console.log(agora, agoraZerado);

// atividade 4
function isDateBefore(data, base, inclusive = false){
  if(!inclusive){
    return data.getTime() < base.getTime();
  }
  return data.getTime() <= base.getTime();
}

function isDateAfter(data, base, inclusive = false){
  if(!inclusive){
    return data.getTime() > base.getTime();
  }
  return data.getTime() >= base.getTime();
}

let agora2 = new Date();
console.log(isDateBefore(agora2,agora));
console.log(isDateAfter(agora2,agora));

// atividade 5
function betweenDates(data,inicio, fim,{inclusiveStart = false,inclusiveEnd = false} = {}){
  if(inclusiveEnd && inclusiveStart){
    return (inicio.getTime() <= data.getTime()) && (data.getTime() <= fim.getTime());
  }
  else if(inclusiveStart && !inclusiveEnd){
    return (inicio.getTime() <= data.getTime()) && (data.getTime() < fim.getTime());
  }
  else if(!inclusiveStart && inclusiveEnd){
    return (inicio.getTime() < data.getTime()) && (data.getTime() <= fim.getTime());
  }
  return (inicio.getTime() < data.getTime()) && (data.getTime() < fim.getTime());
}

let agora3 = new Date();
console.log(betweenDates(agora2,agora,agora3));

// atividade 6
function nextNDates(data,n,intervalo = "d"){
  let copyData = new Date(data);
  let obj = {
    "y" : function(){copyData.setFullYear(copyData.getFullYear() + n);return copyData;},
    "m" : function(){copyData.setMonth(copyData.getMonth() + n); return copyData;},
    "d" : function(){copyData.setDate(copyData.getDate() + n); return copyData;},
    "h" : function(){copyData.setHours(copyData.getHours() + n); return copyData;},
    "mm" :function(){ copyData.setMinutes(copyData.getMinutes() + n); return copyData;},
    "s" : function(){copyData.setSeconds(copyData.getSeconds() + n); return copyData;},
    "ms" : function(){copyData.setMilliseconds(copyData.getMilliseconds() + n); return copyData;},
  }
  return obj[intervalo]?.();
}
console.log(agora);
console.log(nextNDates(agora,5,"y"));
