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
            if(coluna + i < this.n && this.posicoesQueen[coluna + i] == linha){
                nc++;
            }
            if(coluna - i >= 0 && this.posicoesQueen[coluna - i] == linha){
                nc++;
            }
            if(coluna + i < this.n && this.posicoesQueen[coluna + i] == linha + i){
                nc++;
            }
            if(coluna + i < this.n && this.posicoesQueen[coluna + i] == linha - i){
                nc++;
            }
            if(coluna - i >= 0 && this.posicoesQueen[coluna - i] == linha + i){
                nc++;
            }
            if(coluna - i >= 0 && this.posicoesQueen[coluna - i] == linha - i){
                nc++;
            }
        }
        return nc;
    }
    
    private void definirNovaPosicao(int coluna){
        int novaPosicao = 0;
        int custoConflitoTemporario;
        int novoCustoConflito = -1; // this.determinarNumeroConflitos(this.posicoesQueen[coluna], coluna);
        
        for (int i = 0; i < this.n; i++) {
            custoConflitoTemporario = this.determinarNumeroConflitos(i, coluna);
            if(novoCustoConflito == -1 || custoConflitoTemporario < novoCustoConflito){
                novoCustoConflito = custoConflitoTemporario;
                novaPosicao = i;
            }
        }
        this.posicoesQueen[coluna] = novaPosicao;
    }
    
    private boolean determinarSolucaoEncontrada(){
        for (int i = 0; i < this.n; i++) {
            if(this.determinarNumeroConflitos(this.posicoesQueen[i], i) != 0){
                return false;
            }
        }      
        return true;
    }
    
    // Utilizando heurística de busca local - Minimum Conflict
    public void encontrarSolucao(){
        this.posicoesQueen = new int[this.n];
        int iteracoes = 0;
        
        this.gerarEstadoAleatorio();
        
        while(!determinarSolucaoEncontrada() && iteracoes <= 50){
            for (int i = 0; i < this.n; i++) {
                this.definirNovaPosicao(i);
            }
            iteracoes++;
        }
        System.out.println("Numero de iterações: " + iteracoes);
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
