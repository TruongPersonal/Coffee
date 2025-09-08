
package com.project.GUI;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import javax.swing.JOptionPane;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.project.BUS.AccountBUS;
import com.project.BUS.UserService;
import com.project.DTO.AccountDTO;
import com.project.DTO.User;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Element;

public class PrintUser extends javax.swing.JFrame {
    AccountDTO accountDTO;
    AccountBUS accountBUS;
    public PrintUser() {
        initComponents();
        accountBUS = new AccountBUS();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jfcPrint = new javax.swing.JFileChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jfcPrint.setDialogType(javax.swing.JFileChooser.SAVE_DIALOG);
        jfcPrint.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jfcPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jfcPrintActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jfcPrint, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jfcPrint, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 12, Short.MAX_VALUE)));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jfcPrintActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jfcPrintActionPerformed

        String path = jfcPrint.getSelectedFile().getPath() + ".pdf";
        UserService userService = new UserService();
        try {
            Font normalFont = new Font(
                BaseFont.createFont("src\\assets\\VietFontsWeb1_ttf\\vuTimes.ttf", BaseFont.IDENTITY_H,
                        BaseFont.EMBEDDED),
                15, Font.NORMAL);
            Font boldFont = new Font(
                BaseFont.createFont("src\\assets\\VietFontsWeb1_ttf\\vuTimesBold.ttf",
                        BaseFont.IDENTITY_H,
                        BaseFont.EMBEDDED),
                17, Font.NORMAL);
            Document document = new Document(PageSize.A3.rotate());
            PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(path));
            document.addHeader("Header", "Danh sách nhân viên"); // Thêm tiêu đề
            document.open();
            Paragraph paragraph = new Paragraph("Danh sách nhân viên", boldFont);
            paragraph.setAlignment(Element.ALIGN_CENTER);
            document.add(paragraph);
            document.add(new Paragraph("\n")); 
            PdfPTable table = new PdfPTable(9);
            table.setWidthPercentage(100);
            float[] columnWidths = { 2f, 2f, 2f, 3f, 2f, 2f, 2f, 2f, 2f };
            table.addCell(new PdfPCell(new Phrase("Mã nhân viên", boldFont)));
            table.addCell(new PdfPCell(new Phrase("Họ và tên", boldFont)));
            table.addCell(new PdfPCell(new Phrase("Ngày sinh", boldFont)));
            table.addCell(new PdfPCell(new Phrase("Địa chỉ", boldFont)));
            table.addCell(new PdfPCell(new Phrase("Chức vụ", boldFont)));
            table.addCell(new PdfPCell(new Phrase("Số điện thoại", boldFont)));
            table.addCell(new PdfPCell(new Phrase("Lương", boldFont)));
            table.addCell(new PdfPCell(new Phrase("Ngày tạo", boldFont)));
            table.addCell(new PdfPCell(new Phrase("tài khoản", boldFont)));

            List<User> users = userService.getAllUser();
            for (User user : users) {
                table.addCell(new PdfPCell(new Phrase(String.valueOf(user.getId()), normalFont)));
                table.addCell(new PdfPCell(new Phrase(String.valueOf(user.getName()), normalFont)));
                table.addCell(new PdfPCell(new Phrase(String.valueOf(user.getDate()), normalFont)));
                table.addCell(new PdfPCell(new Phrase(String.valueOf(user.getAddress()), normalFont)));
                table.addCell(new PdfPCell(new Phrase(String.valueOf(user.getPosition()), normalFont)));
                table.addCell(new PdfPCell(new Phrase(String.valueOf(user.getPhone()), normalFont)));
                table.addCell(new PdfPCell(new Phrase(String.valueOf(user.getSalary()), normalFont)));
                table.addCell(new PdfPCell(new Phrase(String.valueOf(user.getDateCreate()), normalFont)));
                accountDTO = accountBUS.getIdAccountUser(user.getId());
                table.addCell(new PdfPCell(new Phrase(String.valueOf(accountDTO.getEmail()), normalFont)));
            }

            document.add(table);
            document.close();
            pdfWriter.close();
            JOptionPane.showMessageDialog(this, "Tạo tệp PDF thành công.");
            dispose();
        } catch (DocumentException | FileNotFoundException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi tạo tệp PDF.");
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi lưu file.");
        }
    }// GEN-LAST:event_jfcPrintActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFileChooser jfcPrint;
    // End of variables declaration//GEN-END:variables
}
