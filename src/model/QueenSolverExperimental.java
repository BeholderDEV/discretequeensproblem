/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.TestWindowController;
import java.util.List;

/**
 *
 * @author Augustop
 */
public class QueenSolverExperimental extends QueenSolver{
    private List<CasoTeste> rotina;
    private TestWindowController testController;
    
    public QueenSolverExperimental(List<CasoTeste> rotina, TestWindowController ctr) {
        this.rotina = rotina;
        this.testController = ctr;
    }
    
    @Override
    public void run() {
        for (CasoTeste r : rotina) {
            this.n = r.getTamanho();
            for (int i = 0; i < r.getNumeroRepeticoes(); i++) {
                this.encontrarSolucao();
                this.testController.gravarTeste();
            }
        }
        this.testController.finalizarRotina();
        this.threadExecucao = false;
    }
}
