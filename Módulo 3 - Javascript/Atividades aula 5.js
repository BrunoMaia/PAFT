 // atividade 1
const obj = {
  raio : 3,
  pi: Math.PI,
  area : function(){
    return this.pi*(this.raio**2);
  },
  perimetro: function(){
    return 2 * this.pi * this.raio;
  }
};
console.log(obj.area(), obj.perimetro());

// atividade 2
console.log("atividade 2");
function Circulo(raio){
  this.raio = raio;
}

Circulo.prototype.area = function(){
  return Math.PI * (this.raio**2);
}
Circulo.prototype.perimetro = function(){
    return 2 * Math.PI * this.raio;
}

const circulo1 = new Circulo(3);
const circulo2 = new Circulo(10);
console.log(circulo1.area(), circulo1.perimetro());
console.log(circulo2.area(),circulo2.perimetro());

// atividade 3
console.log("atividade 3");
class CirculoClass{
  #raio;
  #area;
  #perimetro;
  constructor(raio){
    this.raio = raio;
  };
  static criar(... valores){
    let retorno = [];
    for(let valor of valores){
      if(typeof(valor) == 'number' && valor > 0){
        retorno.push(new CirculoClass(valor))
      }
    }
    return retorno;
  }
  toString(){
    return `Circulo de raio ${this.raio}`;
  };
  get raio(){
    return this.#raio.toFixed(2);
  };
  set raio(raio){
    this.#raio = Math.abs(raio);
  };
  get area(){
    return Math.PI * (this.raio**2);
  };
  get perimetro(){
    return 2 * Math.PI * this.raio;
  };
}

let c1 = new CirculoClass(3);
let c2 = new CirculoClass(-10);
console.log(c1.area, c1.perimetro);
console.log(c2.area,c2.perimetro);

// atividade 4
console.log("atividade 4");
console.log(`${c1} ${c2}`);
console.log("Circulos: "+CirculoClass.criar(1,2,4,"daosk"));
