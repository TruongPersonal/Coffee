
package com.project.GUI;
import com.project.BUS.*;

import com.project.DTO.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.BuiltinFormats;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class ExportWareHouse extends javax.swing.JFrame {
    public static final int COLUMN_INDEX_ID = 0;
    public static final int COLUMN_INDEX_NAME = 1;
    public static final int COLUMN_INDEX_UNIT = 2;
    public static final int COLUMN_INDEX_QUANTITY = 3;
    public static final int COLUMN_INDEX_DATECREATE = 4;
    public static final int COLUMN_INDEX_DATEUPDATE = 5;
    private static CellStyle cellStyleFormatNumber = null;
   WareHouseService wareHouseService;
    public ExportWareHouse() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jcfExport = new javax.swing.JFileChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jcfExport.setDialogType(javax.swing.JFileChooser.SAVE_DIALOG);
        jcfExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcfExportActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jcfExport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jcfExport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jcfExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcfExportActionPerformed
         wareHouseService = new WareHouseService();
         List<WareHouse> warehouses = null;
        try {
            warehouses = wareHouseService.getAllWareHouse();
        } catch (Exception ex) {
            Logger.getLogger(ExportWareHouse.class.getName()).log(Level.SEVERE, null, ex);
        }
        String excelFilePath = jcfExport.getSelectedFile().getPath() + ".xlsx";

        try {
            writeExcel(warehouses, excelFilePath);
            JOptionPane.showMessageDialog(this, "Lưu vào excel thành công");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jcfExportActionPerformed
    
    public static void writeExcel(List<WareHouse> wareHouses, String excelFilePath) throws IOException {
        Workbook workbook = getWorkWareHouse(excelFilePath);
        Sheet sheet = workbook.createSheet("WareHouses");
        int rowIndex = 0;
        writeHeader(sheet, rowIndex);
        rowIndex++;
        for (WareHouse warehouse : wareHouses) {
            Row row = sheet.createRow(rowIndex);
            writeWareHouse(warehouse, row);
            rowIndex++;
        }
        int numberOfColumn = sheet.getRow(0).getPhysicalNumberOfCells();
        autosizeColumn(sheet, numberOfColumn);
        createOutputFile(workbook, excelFilePath);
        System.out.println("Done!!!");
    }
 
 
    // Create workbook
    private static Workbook getWorkWareHouse(String excelFilePath) throws IOException {
        Workbook workbook = null;
 
        if (excelFilePath.endsWith("xlsx")) {
            workbook = new XSSFWorkbook();
        } else if (excelFilePath.endsWith("xls")) {
            workbook = new HSSFWorkbook();
        } else {
            throw new IllegalArgumentException("The specified file is not Excel file");
        }
 
        return workbook;
    }
 
    private static void writeHeader(Sheet sheet, int rowIndex) {
        // create CellStyle
        CellStyle cellStyle = createStyleForHeader(sheet);
         
        // Create row
        Row row = sheet.createRow(rowIndex);
         
        // Create cells
        Cell cell = row.createCell(COLUMN_INDEX_ID);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Id");
 
        cell = row.createCell(COLUMN_INDEX_NAME);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Name");
 
        cell = row.createCell(COLUMN_INDEX_UNIT);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Unit");
 
        cell = row.createCell(COLUMN_INDEX_QUANTITY);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Quantity");
 
        cell = row.createCell(COLUMN_INDEX_DATECREATE);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Date Create");
        
    }
 
    // Write data
    private static void writeWareHouse(WareHouse warehouse, Row row) {
        short format = (short)BuiltinFormats.getBuiltinFormat("#,##0");
        Workbook workbook = row.getSheet().getWorkbook();
        cellStyleFormatNumber = workbook.createCellStyle();
        cellStyleFormatNumber.setDataFormat(format);         
        Cell cell = row.createCell(COLUMN_INDEX_ID);
        cell.setCellValue(warehouse.getId());
 
        cell = row.createCell(COLUMN_INDEX_NAME);
        cell.setCellValue(warehouse.getName());
        
       

        cell = row.createCell(COLUMN_INDEX_UNIT);
        cell.setCellValue(warehouse.getUnit());
//        cell.setCellStyle(cellStyleFormatNumber);
 
        cell = row.createCell(COLUMN_INDEX_QUANTITY);
        cell.setCellValue(warehouse.getQuantity());
        

        
        CellStyle timestampStyle = workbook.createCellStyle();
        CreationHelper createHelper = workbook.getCreationHelper();
        timestampStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd/MM/yyyy HH:mm:ss"));

        // Thiết lập CellStyle cho ô chứa thời gian
        Cell timestampCell = row.createCell(COLUMN_INDEX_DATECREATE);
        timestampCell.setCellValue(warehouse.getCreateDate());
        timestampCell.setCellStyle(timestampStyle);
        
    }
 
    // Create CellStyle for header
    private static CellStyle createStyleForHeader(Sheet sheet) {
        // Create font
        Font font = sheet.getWorkbook().createFont();
        font.setFontName("Times New Roman"); 
        font.setBold(true);
        font.setFontHeightInPoints((short) 14); // font size
        font.setColor(IndexedColors.WHITE.getIndex()); // text color
 
        // Create CellStyle
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setFillForegroundColor(IndexedColors.BLUE.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        return cellStyle;
    }
     
     
    // Auto resize column width
    private static void autosizeColumn(Sheet sheet, int lastColumn) {
        for (int columnIndex = 0; columnIndex < lastColumn; columnIndex++) {
            sheet.autoSizeColumn(columnIndex);
        }
    }
     
    // Create output file
    private static void createOutputFile(Workbook workbook, String excelFilePath) throws IOException {
        try (OutputStream os = new FileOutputStream(excelFilePath)) {
            workbook.write(os);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFileChooser jcfExport;
    // End of variables declaration//GEN-END:variables
}
