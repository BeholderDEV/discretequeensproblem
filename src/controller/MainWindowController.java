/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

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
//        this.mainWindow = new MainWindow(this);
//        this.mainWindow.setLocationRelativeTo(null);
//        this.mainWindow.setVisible(true);
        this.solver.encontrarSolucao();
    }
}
