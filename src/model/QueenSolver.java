/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Random;

/**
 *
 * @author Augustop
 */
public class QueenSolver {
    // Indice do vetor = coluna do tabuleiro ; Numero = linha do tabuleiro
    private int posicoesQueen[];
    private int n = 4;
    private Random rand = new Random();
    
    private void gerarEstadoAleatorio(){
        for (int i = 0; i < this.n; i++) {
            this.posicoesQueen[i] = rand.nextInt(this.n);
        }
    }
    
    private int determinarNumeroConflitos(int linha, int coluna){
        int nc = 0;
        for (int i = 1; i < this.n; i++) {
            
        }
        return nc;
    }
    
    // Utilizando heurística de busca local - Minimum Conflict
    public void encontrarSolucao(){
        this.posicoesQueen = new int[this.n];
        
        this.gerarEstadoAleatorio();
        
        
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(this.posicoesQueen[j] == i){
                    System.out.print("1 ");
                }else{
                    System.out.print("0 ");
                }
            }
            System.out.print("\n");
        }
    }
    

    
    public int getN() {
        return n;
    }
    
    public void setN(int n) {
        this.n = n;
        this.posicoesQueen = new int[n];
    }
    
}
