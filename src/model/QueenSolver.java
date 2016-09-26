/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.MainWindowController;
import java.util.Random;

/**
 *
 * @author Augustop
 */
public abstract class QueenSolver implements Runnable{
    // Indice do vetor = coluna do tabuleiro ; Numero = linha do tabuleiro
    protected int posicoesQueen[];
    protected int n = 8;
    protected Random rand;
    protected double ultimoTempoMilli;
    protected int iteracoes;
    protected boolean threadExecucao = false;
    protected MainWindowController mainWindowController;
    
    @Override
    public void run() {}
    
    protected void gerarEstadoAleatorio(){
        for (int i = 0; i < this.n; i++) {
            this.posicoesQueen[i] = this.rand.nextInt(this.n);
        }
    }
    
    protected int determinarNumeroConflitos(int linha, int coluna){
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
    
    protected void definirNovaPosicao(int coluna){
        int novaPosicao = 0; // Posição default para escapar de ótimos locais (50% de chance de forçadamente ir para a posição
                             // default caso não haja melhora possível na posição atual
        int custoConflitoTemporario;
        int novoCustoConflito = this.determinarNumeroConflitos(this.posicoesQueen[coluna], coluna);
        
        for (int i = 0; i < this.n; i++) {
            if(i == this.posicoesQueen[coluna]){
                continue;
            }
            custoConflitoTemporario = this.determinarNumeroConflitos(i, coluna);
            if(custoConflitoTemporario <= novoCustoConflito){
                if(custoConflitoTemporario == novoCustoConflito){
                    if(this.rand.nextInt(2) == 1){
                        continue;
                    }
                }
                novoCustoConflito = custoConflitoTemporario;
                novaPosicao = i; 
            }
        }
        this.posicoesQueen[coluna] = novaPosicao;
    }
    
    protected boolean determinarSolucaoEncontrada(){
        for (int i = 0; i < this.n; i++) {
            if(this.determinarNumeroConflitos(this.posicoesQueen[i], i) != 0){
                return false;
            }
        }      
        return true;
    }
    
    // Utilizando heurística de busca local (Hill Climbing) - Minimum Conflict
    public void encontrarSolucao(){
        this.rand = new Random(System.nanoTime());
        double tempoInicial = System.nanoTime();
        this.posicoesQueen = new int[this.n];
        this.iteracoes = 0;
        
        // Estado Inicial
        // Possível melhora - Definir se é melhor o estado inicial ser aleatório ou utilizar algum tipo de Greedy Search
        this.gerarEstadoAleatorio();
        
        // Possível melhora - Lidar com Local Minima (Ótimos locais que impedem que a heurística realize algum movimento na busca local)
        while(!determinarSolucaoEncontrada()){
            
            // Possível melhora - Definir se é melhor determinar as novas posições sequencialmente ou
            //                    Criar uma lista ordenada da coluna com maior conflito para a menor
            for (int i = 0; i < this.n; i++) {
                if(this.determinarNumeroConflitos(this.posicoesQueen[i], i) > 0){
                    this.definirNovaPosicao(i);
                }
            }
            this.iteracoes++;
            
            // Possível melhora - Definir número correto de iterações para dar reset no tabuleiro
            if(this.iteracoes % (this.n * 10) == 0){
                this.gerarEstadoAleatorio();
            }
        }
        this.ultimoTempoMilli = (System.nanoTime() - tempoInicial) / 1000000.0;
    }
    
    public int getN() {
        return n;
    }
    
    public void setN(int n) {
        this.n = n;
        this.posicoesQueen = new int[n];
    }

    public int[] getPosicoesQueen() {
        return posicoesQueen;
    }

    public int getIteracoes() {
        return iteracoes;
    }

    public double getUltimoTempo() {
        return ultimoTempoMilli;
    }

    public synchronized boolean isThreadExecucao() {
        return threadExecucao;
    }

    public synchronized void setThreadExecucao(boolean threadExecucao) {
        this.threadExecucao = threadExecucao;
    }
    
}
