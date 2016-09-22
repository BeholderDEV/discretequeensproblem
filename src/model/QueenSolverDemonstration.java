/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.MainWindowController;

/**
 *
 * @author Augustop
 */
public class QueenSolverDemonstration extends QueenSolver{

    public QueenSolverDemonstration(MainWindowController ctr) {
        this.mainWindowController = ctr;
    }

    @Override
    public void run() {
        this.encontrarSolucao();
        this.mainWindowController.terminarExecucao(true);
        this.threadExecucao = false;
    }
    
}
