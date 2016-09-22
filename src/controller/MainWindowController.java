/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import model.QueenSolver;
import model.QueenSolverDemonstration;
import view.MainWindow;

/**
 *
 * @author Augustop
 */
public class MainWindowController {
    private MainWindow mainWindow;
    private QueenSolver solver;
    private URL urlImagemQueen = getClass().getResource("/QueenPieceWhite.png");
    private ExternalIOController externalIOController = new ExternalIOController();
    private TestWindowController testWindowController;
    
    public MainWindowController() {
        this.mainWindow = new MainWindow(this);
        this.mainWindow.setSize(1020, 699);
        this.pintarBoard(8, Color.BLACK, false);
        this.mainWindow.setLocationRelativeTo(null);
        this.mainWindow.setVisible(true);
    }
    
    private void pintarQueen(JPanel square, Dimension squareSize){
        try {
            BufferedImage queenImage = this.externalIOController.getImage(this.urlImagemQueen);
            square.setLayout(new BorderLayout());
            Image scaledImage = queenImage.getScaledInstance(squareSize.width, squareSize.height, queenImage.SCALE_SMOOTH);
            JLabel picLabel = new JLabel(new ImageIcon(scaledImage));
            square.add(picLabel);
            square.repaint();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(mainWindow, "Erro Catastrófico e Imprevisível");
        }
    }
    
    public void pintarBoard(int tamanho, Color secondTile, boolean pintarQueens){
        this.mainWindow.getChessboardPanel().removeAll();
        this.mainWindow.getChessboardPanel().setLayout(new GridLayout(tamanho, tamanho));
        Dimension squareSize = new Dimension((this.mainWindow.getChessboardPanel().getHeight() - 5) / tamanho, (this.mainWindow.getChessboardPanel().getHeight() - 5) / tamanho);
        int posicoes[] = null;
        if(pintarQueens){
            posicoes = this.solver.getPosicoesQueen();
        }
        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                JPanel square = new JPanel();
                square.setPreferredSize(squareSize);
                square.setPreferredSize(squareSize);
                if((i + j) % 2 == 0){
                   square.setBackground(Color.WHITE);
                }else{
                   square.setBackground(secondTile);
                }
                if(pintarQueens && posicoes[j] == i){
                    this.pintarQueen(square, squareSize);
                }
                this.mainWindow.getChessboardPanel().add(square);
            }
        }
        this.mainWindow.pack();
        this.mainWindow.repaint();
    }
    
    private Color definirCorBoard(){
        String cor = this.mainWindow.getComboCor().getSelectedItem().toString();
        switch(cor){
            case "Azul":
                return new Color(0, 0, 140);
            case "Amarelo":
                return new Color(224, 224, 0);
            case "Cinza":
                return Color.DARK_GRAY;
            case "Preto":
                return Color.BLACK;
            case "Verde":
                return new Color(0, 102, 51);
            case "Vermelho":
                return Color.RED;
            default:
                return Color.BLACK;
        }
    }
    
    public void iniciarExecucao(){
        if(this.solver == null){
            this.solver = new QueenSolverDemonstration(this);
        }
        if(this.solver.isThreadExecucao()){
            this.mainWindow.getLabelFalha().setText("Solução em Execução");
            return;
        }
        this.mainWindow.getLabelFalha().setText("");
        this.mainWindow.getLabelSucesso().setText("");
        int n = 0;
        try {
            n = Integer.parseInt(this.mainWindow.getInputTamanho().getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(mainWindow, "Erro na entrada do tamanho do tabuleiro!");
            return;
        }
        if(n < 4){
            this.mainWindow.getLabelFalha().setText("O tamanho precisa ser maior que 3");
            return;
        }
        this.mainWindow.getLabelSucesso().setText("...");
        this.solver.setN(n);
        this.solver.setThreadExecucao(true);
        Thread t = new Thread(this.solver);
        t.start();
        if(n > 50){
            this.mainWindow.getLabelFalha().setText("Tamanho muito grande para renderizar");
            return;
        }
    }
    
    public synchronized void terminarExecucao(boolean pintarBoard){
        this.mainWindow.getOutputTempo().setText("" + this.solver.getUltimoTempo());
        this.mainWindow.getOutputIteracoes().setText("" + this.solver.getIteracoes());
        if(this.solver.getN() <= 50 && pintarBoard == true){
            this.mainWindow.setSize(1020, 699);
            this.pintarBoard(this.solver.getN(), this.definirCorBoard(), true);
            this.mainWindow.setLocationRelativeTo(null);
        }
        this.mainWindow.getLabelSucesso().setText("Solucionado");
        try {
            this.externalIOController.gravarResultadoTeste(this.solver);
        } catch (IOException ex) {
            Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void iniciarModuloTeste(){
        this.mainWindow.setVisible(false);
        this.testWindowController = new TestWindowController(this, this.solver);
    }
    
    public void reiniciarModoDemonstracao(){
        this.mainWindow.setVisible(true);
    }
}
