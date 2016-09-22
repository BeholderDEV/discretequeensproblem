/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Augustop
 */
public class CasoTeste {
    private int tamanho;
    private int numeroRepeticoes;

    public CasoTeste(int tamanho, int numeroRepeticoes) {
        this.tamanho = tamanho;
        this.numeroRepeticoes = numeroRepeticoes;
    }

    public int getTamanho() {
        return tamanho;
    }

    public int getNumeroRepeticoes() {
        return numeroRepeticoes;
    }
}
