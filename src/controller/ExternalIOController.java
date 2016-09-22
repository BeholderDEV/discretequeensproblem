/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import model.QueenSolver;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

/**
 *
 * @author 5674867
 */
public class ExternalIOController {
    
    public void gravarResultadoTeste(QueenSolver solver) throws IOException{
        File f = new File("ResultadoExperimento.xls");
        HSSFWorkbook workbook = null;
        HSSFSheet sheet = null;  
        FileOutputStream fileOut = null;
        HSSFRow row = null;
        
        if(f.isFile() && f.canRead()){
            workbook = new HSSFWorkbook(new FileInputStream(f));
            sheet = workbook.getSheetAt(0);
            row= sheet.createRow(sheet.getLastRowNum() + 1);
            row.createCell(0).setCellValue(solver.getN());
            row.createCell(1).setCellValue(solver.getIteracoes());
            row.createCell(2).setCellValue(solver.getUltimoTempo());
            fileOut = new FileOutputStream(f);
        }else{
            workbook = new HSSFWorkbook();
            sheet = workbook.createSheet("TestSheet");
            HSSFRow rowhead = sheet.createRow((short)0);
            rowhead.createCell(0).setCellValue("N");
            rowhead.createCell(1).setCellValue("Iterações");
            rowhead.createCell(2).setCellValue("Tempo de Serviço (MS)");
            row = sheet.createRow(1);
            row.createCell(0).setCellValue(solver.getN());
            row.createCell(1).setCellValue(solver.getIteracoes());
            row.createCell(2).setCellValue(solver.getUltimoTempo());
            fileOut = new FileOutputStream(new File("ResultadoExperimento.xls"));
        }
        workbook.write(fileOut);
        fileOut.close();
    }
    
    public void limparTestes(){
        File f = new File("ResultadoExperimento.xls");
        PrintWriter pw = null;
        if(!f.isFile() || !f.canRead()){
            return;
        }else{
            try {
                pw = new PrintWriter(new FileOutputStream(f, false));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ExternalIOController.class.getName()).log(Level.SEVERE, null, ex);
            }
            pw.write("");
            pw.close();
        }
    }
    
    public BufferedImage getImage(URL url) throws IOException{
        return ImageIO.read(url);
    }
}
