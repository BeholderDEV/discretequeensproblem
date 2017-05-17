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
import javax.swing.JOptionPane;
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
    private List<CasoTeste> rotina;
    
    public TestWindowController(MainWindowController mainWindowController, QueenSolver solver) {
        this.mainWindowController = mainWindowController;
        this.solver = solver;
        this.rotina = new ArrayList<CasoTeste>();
        this.testWindow = new TestWindow(this);
        this.testWindow.setSize(930, 360);
        this.testWindow.setLocationRelativeTo(null);
        this.testWindow.setVisible(true);
    }
    
    public void iniciarLimpezaTestes(){
        if(this.solver != null && !this.solver.isThreadExecucao()){
            this.testWindow.getLabelMensagem().setForeground(Color.BLUE);
            this.testWindow.getLabelMensagem().setText("Documento Limpo");
            ExternalIOController.limparTestes();
        }
    }
    
    public boolean validarInputTeste(String inputN, String inputRepeticoes){
        int n = 0;
        int repeticoes = 0;
        try {
            n = Integer.parseInt(inputN);
            repeticoes = Integer.parseInt(inputRepeticoes);
        } catch (Exception e) {
            this.testWindow.getLabelMensagem().setText("Falha no input");
            return false;
        }
        if(n < 4 || repeticoes <= 0){
            this.testWindow.getLabelMensagem().setText("Falha no input");
            return false;
        }
        return true;
    }
    
    public void inserirTeste(){
        this.testWindow.getLabelMensagem().setText("");
        this.testWindow.getLabelMensagem().setForeground(Color.RED);
        
        if(!validarInputTeste(this.testWindow.getInputTamanho().getText(), this.testWindow.getInputRepeticoes().getText())){
            return;
        }
        this.testWindow.getOutputRotinas().append(this.testWindow.getInputTamanho().getText() + "," + this.testWindow.getInputRepeticoes().getText() + "," + "\n");
    }
    
    public void iniciarRotina(){
        if(this.solver == null){
            this.solver = new QueenSolverExperimental(rotina, this);
        }else if(this.solver.isThreadExecucao()){
            return;
        }
        this.testWindow.getLabelMensagem().setForeground(Color.RED);
        String data[] = this.testWindow.getOutputRotinas().getText().replaceAll("\\s+","").split(",");
        for (int i = 0; i < data.length; i += 2) {
            if(i + 1 >= data.length){
                this.rotina.clear();
                break;
            }
            if(!validarInputTeste(data[i], data[i + 1])){
                return;
            }else{
                this.rotina.add(new CasoTeste(Integer.parseInt(data[i]), Integer.parseInt(data[i + 1])));
            }
        }
        if(this.rotina.isEmpty()){
            this.testWindow.getLabelMensagem().setText("Rotina Vazia ou Preenchida Incorretamente");
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
            ExternalIOController.gravarResultadoTeste(this.solver);
        } catch (IOException ex) {
            Logger.getLogger(TestWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void finalizarRotina(){
        this.testWindow.getLabelMensagem().setText("Rotina Salva em ResultadoExperimento.xls");
    }
    
    public void limparRotina(){
        this.testWindow.getLabelMensagem().setText("");
        this.testWindow.getOutputRotinas().setText("");
        this.rotina.clear();
    }
    
    public void gerarNumerosDistribuicaoLog(){
        try {
            Double media = Double.parseDouble(this.testWindow.getInputMedia().getText());
            Double desvio = Double.parseDouble(this.testWindow.getInputDesvio().getText());
            Double extra = Double.parseDouble(this.testWindow.getInputExtra().getText());
            int repeticoes = Integer.parseInt(this.testWindow.getInputDistRepeticoes().getText());
            DistributionController.logNormal(media, desvio, repeticoes, extra);
            ExternalIOController.gravarNumerosDistribuição();
            this.testWindow.getLabelMensagem().setForeground(Color.BLUE);
            this.testWindow.getLabelMensagem().setText("Numeros gravados em ResultadoExperimento.xls");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(testWindow, "Falha no Input de Dados");
        }
    }
    
    public void gerarNumerosDistribuicaoExp(){
        try {
            Double media = Double.parseDouble(this.testWindow.getInputLambda().getText());
            Double extra = Double.parseDouble(this.testWindow.getInputExtraExp().getText());
            int repeticoes = Integer.parseInt(this.testWindow.getInputDistRepExp().getText());
            DistributionController.exponential(media, extra, repeticoes);
            ExternalIOController.gravarNumerosDistribuição();
            this.testWindow.getLabelMensagem().setForeground(Color.BLUE);
            this.testWindow.getLabelMensagem().setText("Numeros gravados em ResultadoExperimento.xls");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(testWindow, "Falha no Input de Dados");
        }
    }
    
    public void iniciarTesteSequencial(){
        if(this.solver == null){
            this.solver = new QueenSolverExperimental(rotina, this);
        }else if(this.solver.isThreadExecucao()){
            return;
        }
        this.testWindow.getLabelMensagem().setText("");
        this.testWindow.getLabelMensagem().setForeground(Color.RED);
        if(!validarInputTeste(this.testWindow.getInputTamanho().getText(), this.testWindow.getInputRepeticoes().getText())){
            return;
        }
        int numeroRepeticoes = Integer.parseInt(this.testWindow.getInputRepeticoes().getText());

        this.rotina.clear();
        for (int i = 5; i <= 500; i++) {
            this.rotina.add(new CasoTeste(i, numeroRepeticoes));
        }
        this.solver = new QueenSolverExperimental(this.rotina, this);
        this.testWindow.getLabelMensagem().setForeground(Color.BLUE);
        this.testWindow.getLabelMensagem().setText("Executando...");
        this.solver.setThreadExecucao(true);
        Thread t = new Thread(solver);
        t.start();
    }
    
    public void finalizarTestes(){
        if(this.solver != null && this.solver.isThreadExecucao()){
            return;
        }
        this.testWindow.setVisible(false);
        this.mainWindowController.reiniciarModoDemonstracao();
        this.testWindow.dispose();
    }
}
