/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import model.QueenSolver;
import view.MainWindow;

/**
 *
 * @author Augustop
 */
public class MainWindowController {
    private MainWindow mainWindow;
    private QueenSolver solver = new QueenSolver();
    
    public MainWindowController() {
        this.mainWindow = new MainWindow(this);
        this.mainWindow.setSize(1020, 700);
        this.pintarBoard(8);
        this.mainWindow.setLocationRelativeTo(null);
        this.mainWindow.setVisible(true);
    }
    
    
    public void pintarBoard(int tamanho){
        this.mainWindow.getChessboardPanel().removeAll();
        this.mainWindow.getChessboardPanel().setLayout(new GridLayout(tamanho, tamanho));
        Dimension squareSize = new Dimension(this.mainWindow.getHeight() / tamanho, this.mainWindow.getHeight() / tamanho);
        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                JPanel square = new JPanel();
                square.setPreferredSize(squareSize);
                square.setPreferredSize(squareSize);
                if((i + j) % 2 == 0){
                   square.setBackground(Color.white);
                }else{
                   square.setBackground(Color.BLACK);
                }
                this.mainWindow.getChessboardPanel().add(square);
            }
        }
        this.mainWindow.pack();
        this.mainWindow.repaint();
    }
    
    public void iniciarExecucao(){
        this.mainWindow.getLabelFalha().setText("");
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
        this.solver.setN(n);
        this.solver.encontrarSolucao();
        if(n > 50){
            this.mainWindow.getLabelFalha().setText("Tamanho muito grande para renderizar");
            return;
        }
        this.mainWindow.setSize(1020, 700);
        this.pintarBoard(n);
        this.mainWindow.setLocationRelativeTo(null);
    }
}
