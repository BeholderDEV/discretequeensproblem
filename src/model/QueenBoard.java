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
public class QueenBoard {
    private QueenSolver solver = new QueenSolver(this);
    private int n;
    
    // Indice do vetor = coluna do tabuleiro ; Numero = linha do tabuleiro
    private int queenPosicoes[];
    
    public QueenBoard(int n) {
        this.n = n;
        this.queenPosicoes = new int[n];
    }

    public void iniciarSolucao(){
        this.queenPosicoes = this.solver.encontrarSolucao();
    }
    
    public int getN() {
        return n;
    }
    
    public void setN(int n) {
        this.n = n;
        this.queenPosicoes = new int[n];
    }
}
