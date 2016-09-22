/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import javax.imageio.ImageIO;
import model.QueenSolver;

/**
 *
 * @author 5674867
 */
public class ExternalIOController {
    
    public void gravarResultadoTeste(QueenSolver solver) throws IOException{
        File f = new File("ResultadoExperimento.csv");
        PrintWriter pw;

        String[] novoDado = {"" + solver.getN() ,"" + solver.getIteracoes() , "" + solver.getUltimoTempo()};
        if(f.isFile() && f.canRead()){
            pw = new PrintWriter(new FileOutputStream(f, true));
        }else{
            pw = new PrintWriter(new File("ResultadoExperimento.csv"));
            pw.write("Tamanho N,Iteracoes,Tempo de servico\n");
        }

        pw.write(solver.getN() + "," + solver.getIteracoes() + "," + solver.getUltimoTempo() + "\n");
        pw.close();
    }
    
    public BufferedImage getImage(URL url) throws IOException{
        return ImageIO.read(url);
    }
}
