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
public class QueenSolver implements Runnable{
    // Indice do vetor = coluna do tabuleiro ; Numero = linha do tabuleiro
    private int posicoesQueen[];
    private int n = 8;
    private Random rand;
    private double ultimoTempoMilli;
    private int iteracoes;
    private boolean threadExecucao = false;
    private MainWindowController mainWindowController;

    public QueenSolver(MainWindowController ctr) {
        this.mainWindowController = ctr;
    }
    
    @Override
    public void run() {
        this.encontrarSolucao();
        this.mainWindowController.terminarExecucao();
        this.threadExecucao = false;
    }
    
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
        int novaPosicao = 0; // Posição default para escapar de ótimos locais
        int custoConflitoTemporario;
        int novoCustoConflito = this.determinarNumeroConflitos(this.posicoesQueen[coluna], coluna);
        
        for (int i = 0; i < this.n; i++) {
            if(i == this.posicoesQueen[coluna]){
                continue;
            }
            custoConflitoTemporario = this.determinarNumeroConflitos(i, coluna);
            if(custoConflitoTemporario <= novoCustoConflito){
                if(custoConflitoTemporario == novoCustoConflito){
                    if(rand.nextInt(2) == 1){
                        continue;
                    }
                }
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
        this.rand = new Random(System.nanoTime());
        
        double tempoInicial = System.nanoTime();
        this.posicoesQueen = new int[this.n];
        this.iteracoes = 0;
        
        // Estado Inicial
        // TODO - Definir se é melhor o estado inicial ser aleatório ou utilizar algum tipo de Greedy Search
        this.gerarEstadoAleatorio();
        
        // TODO - Lidar com Local Minima (Ótimos locais que impedem que a heurística realize algum movimento na busca local)
        while(!determinarSolucaoEncontrada()){// && iteracoes <= 200){
            
            // TODO - Definir se é melhor determinar as novas posições sequencialmente ou
            //        Criar uma lista ordenada da coluna com maior conflito para a menor
            for (int i = 0; i < this.n; i++) {
                if(this.determinarNumeroConflitos(this.posicoesQueen[i], i) > 0){
                    this.definirNovaPosicao(i);
                }
            }
            this.iteracoes++;
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
