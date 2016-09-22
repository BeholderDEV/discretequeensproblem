/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.CasoTeste;
import model.QueenSolver;
import model.QueenSolverExperimental;
import view.TestWindow;

/**
 *
 * @author Augustop
 */
public class TestWindowController {
    private MainWindowController mainWindowController;
    private TestWindow testWindow;
    private QueenSolver solver;
    private ExternalIOController externalIOController = new ExternalIOController();
    private List<CasoTeste> rotina;
    
    public TestWindowController(MainWindowController mainWindowController, QueenSolver solver) {
        this.mainWindowController = mainWindowController;
        this.solver = solver;
        this.rotina = new ArrayList<CasoTeste>();
        this.testWindow = new TestWindow(this);
        this.testWindow.setSize(1020, 550);
        this.testWindow.setLocationRelativeTo(null);
        this.testWindow.setVisible(true);
    }
    
    public void iniciarLimpezaTestes(){
        this.testWindow.getLabelMensagem().setText("");
        this.externalIOController.limparTestes();
    }
    
    public void inserirTeste(){
        this.testWindow.getLabelMensagem().setText("");
        this.testWindow.getLabelMensagem().setForeground(Color.RED);
        int n = 0;
        int repeticoes = 0;
        try {
            n = Integer.parseInt(this.testWindow.getInputTamanho().getText());
            repeticoes = Integer.parseInt(this.testWindow.getInputRepeticoes().getText());
        } catch (Exception e) {
            this.testWindow.getLabelMensagem().setText("Falha no input");
            return;
        }
        if(n < 4 || repeticoes <= 0){
            this.testWindow.getLabelMensagem().setText("Falha no input");
            return;
        }
        this.rotina.add(new CasoTeste(n, repeticoes));
        this.testWindow.getOutputRotinas().append("" + n + ", " + repeticoes + "\n");
    }
    
    public void iniciarRotina(){
        if(this.solver == null){
            this.solver = new QueenSolverExperimental(rotina, this);
        }else if(this.solver.isThreadExecucao()){
            return;
        }
        if(this.rotina.isEmpty()){
            this.testWindow.getLabelMensagem().setForeground(Color.RED);
            this.testWindow.getLabelMensagem().setText("Rotina Vazia");
            return;
        }
        this.solver = new QueenSolverExperimental(rotina, this);
        this.testWindow.getLabelMensagem().setForeground(Color.BLUE);
        this.testWindow.getLabelMensagem().setText("Executando...");
        this.solver.setThreadExecucao(true);
        Thread t = new Thread(solver);
        t.start();

    }
    
    public void gravarTeste(){
        try {
            this.externalIOController.gravarResultadoTeste(this.solver);
        } catch (IOException ex) {
            Logger.getLogger(TestWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void finalizarRotina(){
        this.testWindow.getLabelMensagem().setText("Rotina Finalizada");
    }
    
    public void limparRotina(){
        this.testWindow.getLabelMensagem().setText("");
        this.testWindow.getOutputRotinas().setText("");
        this.rotina.clear();
    }
    
    public void finalizarTestes(){
        this.testWindow.setVisible(false);
        this.mainWindowController.reiniciarModoDemonstracao();
        this.testWindow.dispose();
    }
}
