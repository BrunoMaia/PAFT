package br.pucpr.rpg.system;
/**
 * Representa qualquer objeto que possa ser rolado como um conjunto de dados.
 */
public interface Rollable {
    /**
     * Joga um conjunto de dados e retorna seu resultado.
     * @return O resultado
     */
    int roll();
}