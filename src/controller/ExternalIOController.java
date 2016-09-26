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
    
    public static void gravarResultadoTeste(QueenSolver solver) throws IOException{
        File f = new File("ResultadoExperimento.xls");
        HSSFWorkbook workbook = null;
        HSSFSheet sheet = null;  
        FileOutputStream fileOut = null;
        HSSFRow row = null;
        
        if(f.isFile() && f.canRead()){
            workbook = new HSSFWorkbook(new FileInputStream(f));
            sheet = workbook.getSheetAt(0);
            row = sheet.createRow(sheet.getLastRowNum() + 1);
            row.createCell(0).setCellValue(sheet.getLastRowNum());
            row.createCell(1).setCellValue(solver.getN());
            row.createCell(2).setCellValue(solver.getIteracoes());
            row.createCell(3).setCellValue(solver.getUltimoTempo());
            fileOut = new FileOutputStream(f);
        }else{
            workbook = new HSSFWorkbook();
            sheet = workbook.createSheet("TestSheet");
            HSSFRow rowhead = sheet.createRow(0);
            rowhead.createCell(0).setCellValue("Numero");
            rowhead.createCell(1).setCellValue("N");
            rowhead.createCell(2).setCellValue("Iterações");
            rowhead.createCell(3).setCellValue("Tempo de Serviço (MS)");
            row = sheet.createRow(1);
            row.createCell(0).setCellValue(sheet.getLastRowNum());
            row.createCell(1).setCellValue(solver.getN());
            row.createCell(2).setCellValue(solver.getIteracoes());
            row.createCell(3).setCellValue(solver.getUltimoTempo());
            fileOut = new FileOutputStream(new File("ResultadoExperimento.xls"));
        }
        workbook.write(fileOut);
        fileOut.close();
    }
    
    public static void gravarNumerosDistribuição() throws IOException{
        File f = new File("ResultadoExperimento.xls");
        HSSFWorkbook workbook = null;
        HSSFSheet sheet = null;  
        FileOutputStream fileOut = null;
        HSSFRow row = null;
        
        if(f.isFile() && f.canRead()){
            workbook = new HSSFWorkbook(new FileInputStream(f));
            sheet = workbook.getSheetAt(0);
            row = sheet.getRow(0);
            row.createCell(4).setCellValue("Números Gerados por Distribuição");
            for (int i = 0; i < DistributionController.getNumerosGerados().size(); i++) {
                row = sheet.getRow(i + 1);
                if(row == null){
                    row = sheet.createRow(sheet.getLastRowNum() + 1);
                }
                row.createCell(4).setCellValue(DistributionController.getNumerosGerados().get(i));
            }
            fileOut = new FileOutputStream(f);
        }else{
            throw new IOException();
        }
        workbook.write(fileOut);
        fileOut.close();
    }
    
    public static void limparTestes(){
        File f = new File("ResultadoExperimento.xls");
        if(f.exists()){
            f.delete();
        }
    }
    
    public static BufferedImage getImage(URL url) throws IOException{
        return ImageIO.read(url);
    }
}
