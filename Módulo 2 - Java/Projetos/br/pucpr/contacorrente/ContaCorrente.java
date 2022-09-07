package pucpr.contacorrente;

import java.util.Objects;

public class ContaCorrente {
    private double saldo;
    public double sacar(double valor){
        if (valor <= saldo){
            saldo = saldo - valor;
            return saldo;
        }
        else {
            throw new IllegalArgumentException("Saldo Insuficiente");
        }
    }
    public double depositar(double valor){
        saldo += valor;
        return saldo;
    }

    public String imprimirOperacao(String operacao, double valor){
        if (Objects.equals(operacao,"sacar")){
            double saldoAtual = saldo;
            double saldoFinal = sacar(valor);
            return String.format("%.2f - %.2f = %.2f",saldoAtual,valor,saldoFinal);
        }else if (Objects.equals(operacao,"depositar")){
            double saldoAtual = saldo;
            double saldoFinal = depositar(valor);
            return String.format("%.2f + %.2f = %.2f",saldoAtual,valor,saldoFinal);
        }
        return "";
    }
    public ContaCorrente(double saldo){
        this.saldo = saldo;
    }
    public ContaCorrente(){
        saldo = 0;
    }

    public static void main(String[] args) {
        ContaCorrente conta = new ContaCorrente(10);
        System.out.println(conta.imprimirOperacao("depositar",100));
        System.out.println(conta.imprimirOperacao("sacar",1.33));
        System.out.println(conta.imprimirOperacao("sacar",1.26));
        ContaCorrente conta2 = new ContaCorrente();
        System.out.println(conta2.imprimirOperacao("depositar",100));
        System.out.println(conta2.imprimirOperacao("sacar",150));
    }
}
