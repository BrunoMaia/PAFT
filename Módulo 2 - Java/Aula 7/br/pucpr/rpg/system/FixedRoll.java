package br.pucpr.rpg.system;

import java.util.ArrayList;
import java.util.List;

public class FixedRoll implements Rollable{
    private List<Integer> valoresRolagem = new ArrayList<>();
    private int iRolagem = 0;

    public FixedRoll(int ... valores){
        for (int valor: valores){
            valoresRolagem.add(Math.max(0,valor));
        }
    }

    @Override
    public int roll() {
        int retorno = 0;
        if (iRolagem < valoresRolagem.size()){
            retorno = valoresRolagem.get(iRolagem);
            iRolagem += 1;
        }else {
            iRolagem = 0;
            retorno = valoresRolagem.get(iRolagem);
            iRolagem += 1;
        }
        return retorno;
    }
}
