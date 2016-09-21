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
//        this.solver.encontrarSolucao();
    }
    
    
    public void pintarBoard(int tamanho){
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
    
}
