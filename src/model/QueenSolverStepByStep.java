package model;

import controller.MainWindowController;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gabriel Luis
 */
public class QueenSolverStepByStep extends QueenSolver {

    public QueenSolverStepByStep(MainWindowController ctr) {
        this.mainWindowController = ctr;
    }
    
    @Override
    public void run(){
        this.encontrarSolucao();
        this.mainWindowController.terminarExecucao(true, false);
        this.threadExecucao = false;
    }
    
    @Override
    public void gerarEstadoAleatorio(){
        for (int i = 0; i < super.n; i++) {
            super.posicoesQueen[i] = super.rand.nextInt(this.n);
            super.mainWindowController.pintarBoard(super.n, this.mainWindowController.getUltimaCor(), true);
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(QueenSolverDemonstration.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @Override
    public void definirNovaPosicao(int coluna){
        int novaPosicao = 0;
        int custoConflitoTemporario;
        int novoCustoConflito = super.determinarNumeroConflitos(super.posicoesQueen[coluna], coluna);
        
        for (int i = 0; i < super.n; i++) {
            if(i == super.posicoesQueen[coluna]){
                continue;
            }
            custoConflitoTemporario = super.determinarNumeroConflitos(i, coluna);
            if(custoConflitoTemporario <= novoCustoConflito){
                if(custoConflitoTemporario == novoCustoConflito){
                    if(super.rand.nextInt(2) == 1){
                        continue;
                    }
                }
                novoCustoConflito = custoConflitoTemporario;
                novaPosicao = i; 
            }
        }
        super.posicoesQueen[coluna] = novaPosicao;
        this.mainWindowController.pintarBoard(super.n, this.mainWindowController.getUltimaCor(), true);
        try {
            Thread.sleep(250);
        } catch (InterruptedException ex) {
            Logger.getLogger(QueenSolverDemonstration.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void encontrarSolucao(){
        super.rand = new Random(System.nanoTime());
        super.posicoesQueen = new int[super.n];
        super.iteracoes = 0;
        for (int i = 0; i < super.n; i++) {
            super.posicoesQueen[i] = -1;
        }
        
        try {
            Thread.sleep(250 - (super.n * 3));
        } catch (InterruptedException ex) {
            Logger.getLogger(QueenSolverStepByStep.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.gerarEstadoAleatorio();
        
        while(!super.determinarSolucaoEncontrada()){
            for (int i = 0; i < super.n; i++) {
                if(super.determinarNumeroConflitos(super.posicoesQueen[i], i) > 0){
                    this.definirNovaPosicao(i);
                }
            }
            super.iteracoes++;
            
            this.mainWindowController.mostrarIteracoes();
            
            if(super.iteracoes % (super.n * 10) == 0){
                this.gerarEstadoAleatorio();
            }
        }
    }
    
}
