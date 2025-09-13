package com.project.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.stream.Stream;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.project.BUS.AccountBUS;
import com.project.BUS.OrderBUS;
import com.project.BUS.PaymentMethodBUS;
import com.project.BUS.ProductBUS;
import com.project.Common.Common;
import com.project.DTO.AccountDTO;
import com.project.DTO.EmployeeDTO;
import com.project.DTO.OrderDTO;
import com.project.DTO.OrderDetailDTO;
import com.project.DTO.PaymentMethodDTO;
import com.project.DTO.ProductDTO;
import com.project.Util.Formatter;

/**
 *
 * @author LuanLe
 */
public class OrderDetail extends javax.swing.JFrame {

    public OrderDetail(OrderDTO order, String emp_name, String emp_email) {
        setTitle("Chi tiết đơn hàng");
        initComponents();
        loadData(order, emp_name, emp_email);
    }

    private void loadData(OrderDTO order, String emp_name, String emp_email) {
        PaymentMethodDTO paymentMethod = new PaymentMethodBUS().getByID(order.getPaymentMethod_id());
        PaymentMethod.setText(paymentMethod.getPayment_name());
        if(emp_email != null) {
            Email.setText(emp_email);
        }
        if(emp_name != null){
            FullName.setText(emp_name);
        }

        CreatedAt.setText(String.valueOf(Common.formatedDateTime(order.getCreatedAt())));
        OrderID.setText(String.valueOf(order.getId()));
        Status.setText(order.getOrder_status());

        DefaultTableModel dtm = new DefaultTableModel() {
                        @Override
                        public boolean isCellEditable(int row, int column) {
                                return false;
                        }
                };
        dtm.addColumn("STT");
        dtm.addColumn("Mã sản phẩm");
        dtm.addColumn("Tên sản phẩm");
        dtm.addColumn("Đơn giá");
        dtm.addColumn("Số lượng");
        dtm.addColumn("Tổng");

        ArrayList<OrderDetailDTO> orderDetails = new OrderBUS().getOrderDetails(order.getId());
        ProductDTO product = null;
        OrderDetailDTO orderDetail = null;
        ProductBUS productBUS = new ProductBUS();
        float total = 0;
        for (int i = 0; i < orderDetails.size(); i++) {
            orderDetail = orderDetails.get(i);
            product = productBUS.getProductByID_IgnoreActiveState(orderDetail.getProduct_id());
            float subTotal = orderDetail.getQuantity() * product.getPrice();

            dtm.addRow(new Object[] { i + 1,
                    product.getId(),
                    product.getProduct_name(),
                    Formatter.getFormatedPrice(product.getPrice()),
                    orderDetail.getQuantity(),
                    Formatter.getFormatedPrice(subTotal)
            });

            total += subTotal;
        }

        Total.setText(Formatter.getFormatedPrice(total) + " VNĐ");

        TableOrderDetail.setModel(dtm);
        Formatter.centerAlignTableCells(TableOrderDetail);

        Formatter.setBoldHeaderTable(TableOrderDetail);
    }

    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        pnTitle = new javax.swing.JPanel();
        Title = new javax.swing.JLabel();
        pnInfo = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        lblEmail = new javax.swing.JLabel();
        Email = new javax.swing.JTextField();
        lblFullName = new javax.swing.JLabel();
        FullName = new javax.swing.JTextField();
        lblCreatedAt = new javax.swing.JLabel();
        CreatedAt = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        OrderID = new javax.swing.JTextField();
        lblPaymentmethod = new javax.swing.JLabel();
        PaymentMethod = new javax.swing.JTextField();
        lblStatus = new javax.swing.JLabel();
        Status = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        pnTable = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableOrderDetail = new javax.swing.JTable();
        pnTotal = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        Total = new javax.swing.JLabel();
        pnBottom = new javax.swing.JPanel();
        pnButton = new javax.swing.JPanel();
        btnPrint = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.Y_AXIS));
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMinimumSize(new java.awt.Dimension(1000, 100));
        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.Y_AXIS));

        jSeparator1.setMaximumSize(new java.awt.Dimension(32767, 10));
        jSeparator1.setPreferredSize(new java.awt.Dimension(900, 10));
        jPanel1.add(jSeparator1);

        pnTitle.setBackground(new java.awt.Color(255, 153, 102));
        pnTitle.setMaximumSize(new java.awt.Dimension(32767, 30));
        pnTitle.setPreferredSize(new java.awt.Dimension(900, 30));

        Title.setBackground(new java.awt.Color(255, 255, 255));
        Title.setFont(new java.awt.Font("Arial", 1, 18));
        Title.setForeground(new java.awt.Color(255, 255, 255));
        Title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Title.setText("CHI TIẾT ĐƠN HÀNG");
        Title.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout pnTitleLayout = new javax.swing.GroupLayout(pnTitle);
        pnTitle.setLayout(pnTitleLayout);
        pnTitleLayout.setHorizontalGroup(
                pnTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 1000, Short.MAX_VALUE)
                        .addGroup(pnTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(pnTitleLayout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(Title)
                                        .addGap(0, 0, Short.MAX_VALUE))));
        pnTitleLayout.setVerticalGroup(
                pnTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 30, Short.MAX_VALUE)
                        .addGroup(pnTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(pnTitleLayout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(Title)
                                        .addGap(0, 0, Short.MAX_VALUE))));

        jPanel1.add(pnTitle);

        pnInfo.setBackground(new java.awt.Color(255, 255, 255));
        pnInfo.setMaximumSize(new java.awt.Dimension(32767, 100));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setMaximumSize(new java.awt.Dimension(32767, 50));
        jPanel4.setMinimumSize(new java.awt.Dimension(600, 35));
        jPanel4.setPreferredSize(new java.awt.Dimension(980, 50));
        jPanel4.setLayout(new java.awt.GridLayout(2, 6, 5, 5));

        lblEmail.setFont(new java.awt.Font("Arial", 0, 16));
        lblEmail.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEmail.setText("Email: ");
        lblEmail.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel4.add(lblEmail);

        Email.setFont(new java.awt.Font("Arial", 0, 14));
        Email.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Email.setText("NULL");
        Email.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        Email.setEditable(false);
        Email.setMaximumSize(new java.awt.Dimension(150, 50));
        jPanel4.add(Email);

        lblFullName.setFont(new java.awt.Font("Arial", 0, 16));
        lblFullName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFullName.setText("Họ tên nhân viên: ");
        lblFullName.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel4.add(lblFullName);

        FullName.setFont(new java.awt.Font("Arial", 0, 14));
        FullName.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        FullName.setText("NULL");
        FullName.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        FullName.setEditable(false);
        FullName.setMaximumSize(new java.awt.Dimension(150, 50));
        jPanel4.add(FullName);

        lblCreatedAt.setFont(new java.awt.Font("Arial", 0, 16));
        lblCreatedAt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCreatedAt.setText("Ngày tạo: ");
        lblCreatedAt.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel4.add(lblCreatedAt);

        CreatedAt.setFont(new java.awt.Font("Arial", 0, 15));
        CreatedAt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        CreatedAt.setText("11/11/2023");
        CreatedAt.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        CreatedAt.setEditable(false);
        CreatedAt.setMaximumSize(new java.awt.Dimension(150, 50));
        jPanel4.add(CreatedAt);

        jLabel4.setFont(new java.awt.Font("Arial", 0, 16));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Mã đơn hàng: ");
        jLabel4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel4.add(jLabel4);

        OrderID.setFont(new java.awt.Font("Arial", 0, 16));
        OrderID.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        OrderID.setText("28");
        OrderID.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        OrderID.setEditable(false);
        OrderID.setMaximumSize(new java.awt.Dimension(150, 50));
        jPanel4.add(OrderID);

        lblPaymentmethod.setFont(new java.awt.Font("Arial", 0, 16));
        lblPaymentmethod.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPaymentmethod.setText("Hình thức thanh toán: ");
        lblPaymentmethod.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel4.add(lblPaymentmethod);

        PaymentMethod.setFont(new java.awt.Font("Arial", 0, 14));
        PaymentMethod.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        PaymentMethod.setText("Chuyển khoản");
        PaymentMethod.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        PaymentMethod.setEditable(false);
        PaymentMethod.setMaximumSize(new java.awt.Dimension(150, 50));
        jPanel4.add(PaymentMethod);

        lblStatus.setFont(new java.awt.Font("Arial", 0, 16));
        lblStatus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblStatus.setText("Trạng thái: ");
        lblStatus.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel4.add(lblStatus);

        Status.setFont(new java.awt.Font("Arial", 0, 16));
        Status.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Status.setText("successful");
        Status.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        Status.setEditable(false);
        Status.setMaximumSize(new java.awt.Dimension(150, 50));
        jPanel4.add(Status);

        pnInfo.add(jPanel4);

        jPanel1.add(pnInfo);

        jSeparator3.setBackground(new java.awt.Color(255, 255, 255));
        jSeparator3.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator3.setMaximumSize(new java.awt.Dimension(32767, 15));
        jSeparator3.setPreferredSize(new java.awt.Dimension(900, 15));
        jPanel1.add(jSeparator3);

        pnTable.setLayout(new javax.swing.BoxLayout(pnTable, javax.swing.BoxLayout.Y_AXIS));
        pnTable.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane1.setMaximumSize(new java.awt.Dimension(1000, 32767));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(1000, 100));

        TableOrderDetail.setFont(new java.awt.Font("Arial", 0, 14));
        TableOrderDetail.setMinimumSize(new java.awt.Dimension(1000, 150));
        TableOrderDetail.setPreferredSize(new java.awt.Dimension(1000, 200));
        TableOrderDetail.setMaximumSize(new java.awt.Dimension(1000, 327676));
        TableOrderDetail.setShowGrid(true);
        TableOrderDetail.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(TableOrderDetail);

        pnTable.add(jScrollPane1);

        pnTotal.setBackground(new java.awt.Color(255, 255, 255));
        pnTotal.setMaximumSize(new java.awt.Dimension(1000, 30));
        pnTotal.setPreferredSize(new java.awt.Dimension(1000, 30));
        pnTotal.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setMaximumSize(new java.awt.Dimension(32767, 22));
        jPanel7.setMinimumSize(new java.awt.Dimension(200, 22));

        jPanel7.setLayout(new java.awt.BorderLayout());

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Arial", 1, 18));
        jLabel7.setText("Tổng: ");
        jPanel7.add(jLabel7, java.awt.BorderLayout.WEST);

        Total.setFont(new java.awt.Font("Arial", 0, 18));
        Total.setText("");
        Total.setMaximumSize(new java.awt.Dimension(32767, 22));
        Total.setMinimumSize(new java.awt.Dimension(800, 22));
        jPanel7.add(Total, java.awt.BorderLayout.EAST);

        pnTotal.add(jPanel7);

        pnTable.add(pnTotal);

        pnBottom.setBackground(new java.awt.Color(255, 255, 255));
        pnBottom.setMaximumSize(new java.awt.Dimension(120, 40));
        pnBottom.setMinimumSize(new java.awt.Dimension(120, 40));
        pnBottom.setPreferredSize(new java.awt.Dimension(120, 40));
        pnBottom.setLayout(new java.awt.BorderLayout());

        pnButton.setBackground(new java.awt.Color(255, 255, 255));
        pnButton.setMaximumSize(new java.awt.Dimension(120, 40));
        pnButton.setMinimumSize(new java.awt.Dimension(120, 40));
        pnButton.setPreferredSize(new java.awt.Dimension(120, 40));
        pnButton.setLayout(new java.awt.BorderLayout());

        btnPrint.setText("In");
        btnPrint.setFont(new java.awt.Font("Arial", 1, 18));
        btnPrint.setBackground(new java.awt.Color(0, 191, 255));
        btnPrint.setForeground(new java.awt.Color(255, 255, 255));
        btnPrint.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPrint.setMaximumSize(new java.awt.Dimension(80, 50));
        btnPrint.setMinimumSize(new java.awt.Dimension(80, 50));
        btnPrint.setPreferredSize(new java.awt.Dimension(80, 50));

        btnPrint.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                exportPF();
            }

        });

        pnButton.add(btnPrint, java.awt.BorderLayout.CENTER);

        pnBottom.add(pnButton, java.awt.BorderLayout.CENTER);

        pnTable.add(pnBottom);

        jPanel1.add(pnTable);

        jSeparator4.setBackground(new java.awt.Color(255, 255, 255));
        jSeparator4.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator4.setMaximumSize(new java.awt.Dimension(32767, 15));
        jSeparator4.setPreferredSize(new java.awt.Dimension(900, 15));
        jPanel1.add(jSeparator4);

        getContentPane().add(jPanel1);

        pack();

        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    private void exportPF() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setSelectedFile(new java.io.File("HD" + OrderID.getText()));
        int userSelection = fileChooser.showSaveDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            boolean rs = false;
            try {
                String path = fileChooser.getSelectedFile().getAbsolutePath();
                Document document = new Document();
                PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(path + ".pdf"));
                document.open();
                rs = addComponentToDocument(document);
                document.close();
                writer.close();

            } catch (Exception e) {
                e.printStackTrace();
            }

            if (rs) {
                JOptionPane.showMessageDialog(null, "Xuất file PDF thành công!", "Thông báo",
                        JOptionPane.INFORMATION_MESSAGE);
                        
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Có lỗi xảy ra!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private boolean addComponentToDocument(Document document) {
        boolean rs = false;
        try {
            Font normalFont = new Font(
                    BaseFont.createFont("src\\assets\\VietFontsWeb1_ttf\\vuTimes.ttf", BaseFont.IDENTITY_H,
                            BaseFont.EMBEDDED),
                    13, Font.NORMAL);
            Font boldFont = new Font(
                    BaseFont.createFont("src\\assets\\VietFontsWeb1_ttf\\vuTimesBold.ttf",
                            BaseFont.IDENTITY_H,
                            BaseFont.EMBEDDED),
                    13, Font.NORMAL);

            Paragraph separator = new Paragraph(
                    "-----------------------------------------------------------------------------------");
            separator.setAlignment(Paragraph.ALIGN_CENTER);

            Paragraph title = new Paragraph("Coffe-Shop", new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD));
            title.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(title);

            Paragraph shopInfo = new Paragraph("273 An Dương Vương, Phường 3, Quận 5, TP.HCM\n+0123456789", normalFont);
            shopInfo.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(shopInfo);

            document.add(separator);

            Paragraph orderInfo1 = new Paragraph();
            orderInfo1.setAlignment(Paragraph.ALIGN_CENTER);
            orderInfo1.add(new Chunk("Mã HD: " + OrderID.getText(), normalFont));
            orderInfo1.add("                       ");
            orderInfo1.add(new Chunk("Ngày: " + CreatedAt.getText(), normalFont));
            orderInfo1.add(Chunk.NEWLINE);
            document.add(orderInfo1);

            Paragraph orderInfo2 = new Paragraph();
            orderInfo2.add(new Chunk("Nhân viên: " + FullName.getText(), normalFont));
            orderInfo2.add(Chunk.NEWLINE);
            orderInfo2.add(new Chunk("Hình thức thanh toán: " + PaymentMethod.getText(), normalFont));
            orderInfo2.setIndentationLeft(120);
            document.add(orderInfo2);

            document.add(separator);

            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(55);
            table.setWidths(new float[] { 1, 2.8f, 1.2f, 1.2f, 2 });
            Stream.of("STT", "Tên sản phẩm", "Đơn giá", "Số lượng", "Tổng")
                    .forEach(columnTitle -> {
                        PdfPCell header = new PdfPCell();
                        header.setBorderWidth(2);
                        header.setPhrase(new Phrase(columnTitle, boldFont));
                        header.setHorizontalAlignment(Phrase.ALIGN_CENTER);
                        header.setBorder(Rectangle.NO_BORDER);
                        table.addCell(header);
                    });

            for (int row = 0; row < TableOrderDetail.getRowCount(); row++) {
                for (int col = 0; col < TableOrderDetail.getColumnCount(); col++) {
                    if (col == 1)
                        continue;
                    PdfPCell cell = new PdfPCell();
                    cell.setPhrase(new Phrase(TableOrderDetail.getValueAt(row, col).toString(), normalFont));
                    cell.setHorizontalAlignment(Phrase.ALIGN_CENTER);
                    cell.setBorder(Rectangle.NO_BORDER);
                    table.addCell(cell);
                }
            }

            document.add(table);
            document.add(separator);

            Paragraph total = new Paragraph();
            total.add(new Chunk("Tổng cộng: ", boldFont));
            total.add(new Chunk(Total.getText(), normalFont));
            total.setAlignment(Paragraph.ALIGN_RIGHT);
            total.setIndentationRight(100);
            document.add(total);

            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            Paragraph thanks = new Paragraph("Thank you! Please visit us again!", boldFont);
            thanks.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(thanks);

            rs = true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    private javax.swing.JTextField Email;
    private javax.swing.JTextField CreatedAt;
    private javax.swing.JTextField FullName;
    private javax.swing.JTextField OrderID;
    private javax.swing.JTextField PaymentMethod;
    private javax.swing.JTextField Status;
    private javax.swing.JLabel Title;
    private javax.swing.JLabel Total;
    private javax.swing.JButton btnPrint;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblFullName;
    private javax.swing.JLabel lblCreatedAt;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel lblPaymentmethod;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel pnButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTable TableOrderDetail;
    private javax.swing.JPanel pnBottom;
    private javax.swing.JPanel pnInfo;
    private javax.swing.JPanel pnTable;
    private javax.swing.JPanel pnTitle;
    private javax.swing.JPanel pnTotal;
}
