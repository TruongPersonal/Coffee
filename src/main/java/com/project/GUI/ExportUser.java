
package com.project.GUI;
import com.project.BUS.*;
import com.project.DAO.mysqlConnect;
import com.project.DTO.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.BuiltinFormats;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class ExportUser extends javax.swing.JFrame {
    public static final int COLUMN_INDEX_ID = 0;
    public static final int COLUMN_INDEX_NAME = 1;
    public static final int COLUMN_INDEX_DATE = 2;
    public static final int COLUMN_INDEX_ADDRESS = 3;
    public static final int COLUMN_INDEX_POSITION = 4;
    public static final int COLUMN_INDEX_PHONE = 5;
    public static final int COLUMN_INDEX_SALARY = 6;
    public static final int COLUMN_INDEX_DATECREATE = 7;
    public static final int COLUMN_INDEX_IDACCOUNT = 8;
    private static CellStyle cellStyleFormatNumber = null;
    UserService userService;

    public ExportUser() {
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
            .addComponent(jcfExport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jcfExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcfExportActionPerformed
         userService = new UserService();
         List<User> users = null;
        try {
            users = userService.getAllUser();
        } catch (Exception ex) {
            Logger.getLogger(ExportUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        String excelFilePath = jcfExport.getSelectedFile().getPath() + ".xlsx";

        try {
            writeExcel(users, excelFilePath);
            JOptionPane.showMessageDialog(this, "Lưu vào excel thành công");
            dispose();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jcfExportActionPerformed
    
    public static void writeExcel(List<User> users, String excelFilePath) throws IOException {
        Workbook workbook = getWorkUser(excelFilePath);
        Sheet sheet = workbook.createSheet("Users");
        int rowIndex = 0;
        writeHeader(sheet, rowIndex);
        rowIndex++;
        for (User user : users) {
            Row row = sheet.createRow(rowIndex);
            writeUser(user, row);
            rowIndex++;
        }
        int numberOfColumn = sheet.getRow(0).getPhysicalNumberOfCells();
        autosizeColumn(sheet, numberOfColumn);
        createOutputFile(workbook, excelFilePath);
        System.out.println("Done!!!");
    }
 
 
    // Create workbook
    private static Workbook getWorkUser(String excelFilePath) throws IOException {
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
        cell.setCellValue("Mã");
 
        cell = row.createCell(COLUMN_INDEX_NAME);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Họ và tên");
 
        cell = row.createCell(COLUMN_INDEX_DATE);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Ngày sinh");
 
        cell = row.createCell(COLUMN_INDEX_ADDRESS);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Địa chỉ");
 
        cell = row.createCell(COLUMN_INDEX_POSITION);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Chức vụ");
        
        cell = row.createCell(COLUMN_INDEX_PHONE);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Số điện thoại");
        
        cell = row.createCell(COLUMN_INDEX_SALARY);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Lương");
        
        cell = row.createCell(COLUMN_INDEX_DATECREATE);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Ngày tạo");
        
        cell = row.createCell(COLUMN_INDEX_IDACCOUNT);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Tài khoản");
    }
 
    // Write data
    private static void writeUser(User user, Row row) {
        short format = (short)BuiltinFormats.getBuiltinFormat("#,##0");
        Workbook workbook = row.getSheet().getWorkbook();
        cellStyleFormatNumber = workbook.createCellStyle();
        cellStyleFormatNumber.setDataFormat(format);         
        Cell cell = row.createCell(COLUMN_INDEX_ID);
        cell.setCellValue(user.getId());
 
        cell = row.createCell(COLUMN_INDEX_NAME);
        cell.setCellValue(user.getName());
        
        CellStyle dateStyle = workbook.createCellStyle();
        CreationHelper createHelper = workbook.getCreationHelper();
        dateStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd/MM/yyyy"));
        
        Cell dateCell = row.createCell(COLUMN_INDEX_DATE);
        dateCell.setCellValue(user.getDate());
        dateCell.setCellStyle(dateStyle);

        cell = row.createCell(COLUMN_INDEX_ADDRESS);
        cell.setCellValue(user.getAddress());
        cell.setCellStyle(cellStyleFormatNumber);
 
        cell = row.createCell(COLUMN_INDEX_POSITION);
        cell.setCellValue(user.getPosition());
        
cell = row.createCell(COLUMN_INDEX_PHONE);
        cell.setCellValue(user.getPhone());
        
        cell = row.createCell(COLUMN_INDEX_SALARY);
        cell.setCellValue(user.getSalary());
        
        CellStyle timestampStyle = workbook.createCellStyle();
        timestampStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd/MM/yyyy HH:mm:ss"));

        // Thiết lập CellStyle cho ô chứa thời gian
        Cell timestampCell = row.createCell(COLUMN_INDEX_DATECREATE);
        timestampCell.setCellValue(user.getDateCreate());
        timestampCell.setCellStyle(timestampStyle);
        
        cell = row.createCell(COLUMN_INDEX_IDACCOUNT);
        cell.setCellValue(getEmailUser(user.getId()));
    }
    private static String getEmailUser(int id)
    {   
        AccountDTO accountDTO = new AccountDTO();
        AccountBUS accountBUS = new AccountBUS();
        accountDTO = accountBUS.getIdAccountUser(id);
        String email = String.valueOf(accountDTO.getEmail());
        return email;
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
