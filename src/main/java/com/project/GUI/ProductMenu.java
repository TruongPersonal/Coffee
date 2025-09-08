/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.project.BUS.ProductBUS;
import com.project.BUS.SupplierBUS;
import com.project.Common.Common;
import com.project.DTO.PermissionAccount;
import com.project.DTO.ProductDTO;
import com.project.DTO.SupplierDTO;
import com.project.Util.Formatter;

/**
 *
 * @author HP
 */
public class ProductMenu extends javax.swing.JPanel {
        private ArrayList<ProductDTO> listProduct;
        private PermissionAccount permissionList;

        /**
         * Creates new form ProductMenu
         */

        public ProductMenu() {
                initComponents();
                permissionList = PermissionAccount.getInstance();

                listProduct = ProductBUS.getAllProduct();

                DefaultTableModel model = new DefaultTableModel() {
                        @Override
                        public Class<?> getColumnClass(int columnIndex) {
                                if (columnIndex == 2) { // Kiểm tra cột "Hình ảnh"
                                        return ImageIcon.class; // Trả về kiểu dữ liệu ImageIcon
                                }
                                return super.getColumnClass(columnIndex);
                        }

                        @Override
                        public boolean isCellEditable(int row, int column) {
                                return false;
                        }
                };

                // DefaultTableModel model = new DefaultTableModel();
                model.addColumn("Mã sản phẩm");
                model.addColumn("Tên sản phẩm");
                model.addColumn("Hình ảnh");
                model.addColumn("Giá");
                model.addColumn("Số lượng");
                model.addColumn("Loại sản phẩm");
                model.addColumn("Ngày tạo");
                model.addColumn("Cập nhật cuối");

                for (ProductDTO productDTO : listProduct) {
                        Object[] rowData = {
                                        productDTO.getId(),
                                        productDTO.getProduct_name(),
                                        new ImageIcon(productDTO
                                                        .getUrl_image()),
                                        Formatter.getFormatedPrice(productDTO
                                                        .getPrice()),
                                        productDTO.getQuantity(),
                                        productDTO.getCategory_name(),
                                        Common.formatedDateTime(productDTO
                                                        .getCreatedAt()),
                                        Common.formatedDateTime(productDTO.getUpdatedAt())

                        };
                        model.addRow(rowData);

                }
                Table.setModel(model);
                Formatter.setBoldHeaderTable(Table);
                Formatter.centerAlignTableCells(Table);

                // Tạo một custom renderer để hiển thị hình ảnh scaled trong cột "Hình ảnh"
                Table.getColumnModel().getColumn(2).setCellRenderer(new DefaultTableCellRenderer() {
                        @Override
                        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                        boolean hasFocus, int row, int column) {
                                // Gọi phương thức của superclass để lấy JLabel mặc định
                                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected,
                                                hasFocus, row, column);

                                // Kiểm tra nếu giá trị của cell là ImageIcon
                                if (value instanceof ImageIcon) {
                                        // Scale hình ảnh
                                        ImageIcon originalIcon = (ImageIcon) value;
                                        Image originalImage = originalIcon.getImage();
                                        Image scaledImage = originalImage.getScaledInstance(50, 50,
                                                        Image.SCALE_SMOOTH); // Thiết lập kích thước scaled
                                        ImageIcon scaledIcon = new ImageIcon(scaledImage);

                                        // Thiết lập hình ảnh scaled cho JLabel
                                        label.setIcon(scaledIcon);
                                        label.setText(null); // Xóa văn bản

                                        // Căn giữa hình ảnh trong cell
                                        label.setHorizontalAlignment(JLabel.CENTER);
                                        label.setVerticalAlignment(JLabel.CENTER);
                                }

                                return label;
                        }
                });

        }

        /**
         * This method is called from within the constructor to initialize the form.
         * WARNING: Do NOT modify this code. The content of this method is always
         * regenerated by the Form Editor.
         */
        @SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">
        private void initComponents() {

                buttonGroup1 = new javax.swing.ButtonGroup();
                PanelSouth = new javax.swing.JPanel();
                PanelSouth1 = new javax.swing.JPanel();
                jSplitPane1 = new javax.swing.JSplitPane();
                PanelTimkiem = new javax.swing.JPanel();
                lblMucGia = new javax.swing.JLabel();
                cmbGia = new javax.swing.JComboBox<>();
                lblSoluong = new javax.swing.JLabel();
                cmbSoluong = new javax.swing.JComboBox<>();
                lblLoaisp = new javax.swing.JLabel();
                rbtnTrs = new javax.swing.JRadioButton();
                rbtnSoda = new javax.swing.JRadioButton();
                rbtnCf = new javax.swing.JRadioButton();
                btnLoc = new javax.swing.JButton();
                PanelTable = new javax.swing.JPanel();
                jScrollPane1 = new javax.swing.JScrollPane();
                Table = new javax.swing.JTable();
                PanelNorth = new javax.swing.JPanel();
                PanelSp = new javax.swing.JPanel();
                panellblSp = new javax.swing.JPanel();
                lblSanpham = new javax.swing.JLabel();
                PanelBtn = new javax.swing.JPanel();
                Btn = new javax.swing.JPanel();
                Btn1 = new javax.swing.JPanel();
                btnThem = new javax.swing.JButton();
                btnChitiet = new javax.swing.JButton();
                btnXoa = new javax.swing.JButton();
                btnXuat = new javax.swing.JButton();
                PanellblTimkiem = new javax.swing.JPanel();
                PanellblTimkiem1 = new javax.swing.JPanel();
                btnTimkiem = new javax.swing.JButton();
                txt = new javax.swing.JTextField();
                btnRefresh = new javax.swing.JButton();

                setBackground(new java.awt.Color(255, 255, 255));
                setMinimumSize(new java.awt.Dimension(1085, 768));
                setLayout(new java.awt.BorderLayout());

                PanelSouth.setBackground(new java.awt.Color(255, 255, 255));
                PanelSouth.setMinimumSize(new java.awt.Dimension(1085, 608));
                PanelSouth.setPreferredSize(new java.awt.Dimension(1085, 600));
                PanelSouth.setLayout(new javax.swing.BoxLayout(PanelSouth, javax.swing.BoxLayout.X_AXIS));

                PanelSouth1.setBackground(new java.awt.Color(255, 255, 255));

                jSplitPane1.setPreferredSize(new java.awt.Dimension(600, 200));

                PanelTimkiem.setBackground(new java.awt.Color(255, 255, 255));
                PanelTimkiem.setPreferredSize(new java.awt.Dimension(200, 606));
                PanelTimkiem.setLayout(new java.awt.GridLayout(10, 1, 0, 25));

                lblMucGia.setBackground(new java.awt.Color(255, 255, 255));
                lblMucGia.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
                lblMucGia.setText("Mức giá:");
                PanelTimkiem.add(lblMucGia);

                cmbGia.setModel(
                                new javax.swing.DefaultComboBoxModel<>(
                                                new String[] { "Tăng dần", "Giảm dần" }));
                cmbGia.setPreferredSize(new java.awt.Dimension(72, 15));
                PanelTimkiem.add(cmbGia);

                lblSoluong.setBackground(new java.awt.Color(255, 255, 255));
                lblSoluong.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
                lblSoluong.setText("Loại sản phẩm:");
                PanelTimkiem.add(lblSoluong);

                cmbSoluong.setModel(
                                new javax.swing.DefaultComboBoxModel<>(
                                                new String[] { "Tất cả", "Cà phê", "Trà sữa", "Soda" }));
                PanelTimkiem.add(cmbSoluong);

                // lblLoaisp.setBackground(new java.awt.Color(255, 255, 255));
                // lblLoaisp.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
                // lblLoaisp.setText("Loại sản phẩm: ");
                // PanelTimkiem.add(lblLoaisp);

                // rbtnTrs.setBackground(new java.awt.Color(255, 255, 255));
                // buttonGroup1.add(rbtnTrs);
                // rbtnTrs.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
                // rbtnTrs.setText("Trà sữa");
                // PanelTimkiem.add(rbtnTrs);

                // rbtnSoda.setBackground(new java.awt.Color(255, 255, 255));
                // buttonGroup1.add(rbtnSoda);
                // rbtnSoda.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
                // rbtnSoda.setText("Soda");
                // PanelTimkiem.add(rbtnSoda);

                // rbtnCf.setBackground(new java.awt.Color(255, 255, 255));
                // buttonGroup1.add(rbtnCf);
                // rbtnCf.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
                // rbtnCf.setSelected(true);
                // rbtnCf.setText("Cà phê");
                // rbtnCf.addActionListener(new java.awt.event.ActionListener() {
                // public void actionPerformed(java.awt.event.ActionEvent evt) {
                // rbtnCfActionPerformed(evt);
                // }
                // });
                // PanelTimkiem.add(rbtnCf);

                btnLoc.setBackground(new java.awt.Color(255, 255, 255));
                btnLoc.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
                btnLoc.setIcon(new javax.swing.ImageIcon(("src/assets/icon/filter.png"))); // NOI18N
                btnLoc.setText("Lọc");
                btnLoc.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
                btnLoc.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                btnLoc.setCursor(new java.awt.Cursor(Cursor.HAND_CURSOR));
                btnLoc.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                btnLocMouseClicked(evt);
                        }

                });
                PanelTimkiem.add(btnLoc);

                jSplitPane1.setLeftComponent(PanelTimkiem);

                PanelTable.setBackground(new java.awt.Color(255, 255, 255));
                PanelTable.setLayout(new javax.swing.BoxLayout(PanelTable, javax.swing.BoxLayout.LINE_AXIS));

                Table.setGridColor(new java.awt.Color(204, 204, 204));
                Table.setRowHeight(80);
                jScrollPane1.setViewportView(Table);
                Table.setFont(new java.awt.Font("Arial", 0, 14));
                Table.setShowGrid(true);
                PanelTable.add(jScrollPane1);

                jSplitPane1.setRightComponent(PanelTable);

                javax.swing.GroupLayout PanelSouth1Layout = new javax.swing.GroupLayout(PanelSouth1);
                PanelSouth1.setLayout(PanelSouth1Layout);
                PanelSouth1Layout.setHorizontalGroup(
                                PanelSouth1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGap(0, 1085, Short.MAX_VALUE)
                                                .addGroup(PanelSouth1Layout.createParallelGroup(
                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(jSplitPane1,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                1085,
                                                                                Short.MAX_VALUE)));
                PanelSouth1Layout.setVerticalGroup(
                                PanelSouth1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGap(0, 608, Short.MAX_VALUE)
                                                .addGroup(PanelSouth1Layout.createParallelGroup(
                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(jSplitPane1,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                608,
                                                                                Short.MAX_VALUE)));

                PanelSouth.add(PanelSouth1);

                add(PanelSouth, java.awt.BorderLayout.CENTER);

                PanelNorth.setBackground(new java.awt.Color(255, 255, 255));
                PanelNorth.setMinimumSize(new java.awt.Dimension(1085, 160));
                PanelNorth.setPreferredSize(new java.awt.Dimension(1085, 160));
                PanelNorth.setLayout(new javax.swing.BoxLayout(PanelNorth, javax.swing.BoxLayout.Y_AXIS));

                PanelSp.setBackground(new java.awt.Color(255, 255, 255));
                PanelSp.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
                PanelSp.setPreferredSize(new java.awt.Dimension(1085, 80));

                panellblSp.setBackground(new java.awt.Color(255, 255, 255));

                lblSanpham.setBackground(new java.awt.Color(255, 255, 255));
                lblSanpham.setFont(new java.awt.Font("Arial", 0, 32)); // NOI18N
                lblSanpham.setIcon(new javax.swing.ImageIcon(("src/assets/icon/icons8-product-64.png"))); // NOI18N
                lblSanpham.setText("SẢN PHẨM");
                lblSanpham.setPreferredSize(new java.awt.Dimension(600, 70));
                lblSanpham.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

                javax.swing.GroupLayout panellblSpLayout = new javax.swing.GroupLayout(panellblSp);
                panellblSp.setLayout(panellblSpLayout);
                panellblSpLayout.setHorizontalGroup(
                                panellblSpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(lblSanpham, javax.swing.GroupLayout.Alignment.TRAILING));
                panellblSpLayout.setVerticalGroup(
                                panellblSpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(panellblSpLayout.createSequentialGroup()
                                                                .addComponent(lblSanpham)
                                                                .addGap(0, 26, Short.MAX_VALUE)));

                PanelSp.add(panellblSp);

                PanelNorth.add(PanelSp);

                PanelBtn.setBackground(new java.awt.Color(255, 255, 255));
                PanelBtn.setPreferredSize(new java.awt.Dimension(1085, 80));
                PanelBtn.setLayout(new java.awt.BorderLayout());

                Btn.setBackground(new java.awt.Color(255, 255, 255));
                Btn.setMinimumSize(new java.awt.Dimension(410, 80));
                Btn.setPreferredSize(new java.awt.Dimension(410, 80));
                Btn.setLayout(new java.awt.BorderLayout());

                Btn1.setBackground(new java.awt.Color(255, 255, 255));
                Btn1.setPreferredSize(new java.awt.Dimension(390, 75));
                Btn1.setLayout(new java.awt.GridLayout(1, 0));

                btnThem.setBackground(new java.awt.Color(255, 255, 255));
                btnThem.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
                btnThem.setIcon(new javax.swing.ImageIcon(("./src/assets/icon/plus.png"))); // NOI18N
                btnThem.setText("Thêm");
                btnThem.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
                // btnThem.setBorderPainted(false);
                btnThem.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                btnThem.setIconTextGap(10);
                btnThem.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
                btnThem.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnThemActionPerformed(evt);
                        }
                });
                btnThem.setCursor(new java.awt.Cursor(Cursor.HAND_CURSOR));
                Btn1.add(btnThem);

                btnChitiet.setBackground(new java.awt.Color(255, 255, 255));
                btnChitiet.setIcon(new javax.swing.ImageIcon(("./src/assets/icon/info-rgb.png"))); // NOI18N
                btnChitiet.setFont(new java.awt.Font("Arial", 0, 14));
                btnChitiet.setText("Chi tiết");
                btnChitiet.setBorder(
                                javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
                // btnChitiet.setBorderPainted(false);
                btnChitiet.setCursor(new java.awt.Cursor(Cursor.HAND_CURSOR));
                btnChitiet.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                btnChitiet.setIconTextGap(10);
                btnChitiet.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
                btnChitiet.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnChitietActionPerformed(evt);
                        }
                });
                Btn1.add(btnChitiet);

                btnXoa.setBackground(new java.awt.Color(255, 255, 255));
                btnXoa.setIcon(new javax.swing.ImageIcon(("./src/assets/icon/cancel.png"))); // NOI18N
                btnXoa.setFont(new java.awt.Font("Arial", 0, 14));
                btnXoa.setText("Xóa");
                btnXoa.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
                // btnXoa.setBorderPainted(false);
                btnXoa.setCursor(new java.awt.Cursor(Cursor.HAND_CURSOR));
                btnXoa.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                btnXoa.setIconTextGap(10);
                btnXoa.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
                btnXoa.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnXoaActionPerformed(evt);
                        }

                });
                Btn1.add(btnXoa);

                btnXuat.setBackground(new java.awt.Color(255, 255, 255));
                btnXuat.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
                btnXuat.setIcon(new javax.swing.ImageIcon(("./src/assets/icon/xls.png"))); // NOI18N
                btnXuat.setFont(new java.awt.Font("Arial", 0, 14));
                btnXuat.setText("Xuất");
                btnXuat.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
                // btnXuat.setBorderPainted(false);
                btnXuat.setCursor(new java.awt.Cursor(Cursor.HAND_CURSOR));
                btnXuat.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                btnXuat.setIconTextGap(10);
                btnXuat.setMinimumSize(new java.awt.Dimension(33, 59));
                btnXuat.setPreferredSize(new java.awt.Dimension(33, 59));
                btnXuat.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                btnXuatMouseClicked(evt);
                        }

                });
                btnXuat.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
                Btn1.add(btnXuat);

                Btn.add(Btn1, java.awt.BorderLayout.EAST);

                PanelBtn.add(Btn, java.awt.BorderLayout.WEST);

                PanellblTimkiem.setMinimumSize(new java.awt.Dimension(685, 80));
                PanellblTimkiem.setPreferredSize(new java.awt.Dimension(675, 80));

                PanellblTimkiem1.setBackground(new java.awt.Color(255, 255, 255));
                PanellblTimkiem1.setPreferredSize(new java.awt.Dimension(600, 40));

                btnTimkiem.setBackground(new java.awt.Color(255, 255, 255));
                btnTimkiem.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
                btnTimkiem.setIcon(new javax.swing.ImageIcon("./src/assets/icon/magnifying-glass.png"));
                btnTimkiem.setCursor(new java.awt.Cursor(Cursor.HAND_CURSOR));

                txt.setPreferredSize(new java.awt.Dimension(90, 22));
                Formatter.setPlaceHolder(txt, "Nhập từ khóa tìm kiếm");
                txt.getDocument().addDocumentListener(new DocumentListener() {
                        @Override
                        public void insertUpdate(DocumentEvent e) {
                                updateLabel();
                        }

                        @Override
                        public void removeUpdate(DocumentEvent e) {
                                updateLabel();
                        }

                        @Override
                        public void changedUpdate(DocumentEvent e) {
                                updateLabel();
                        }

                });

                btnRefresh.setBackground(new java.awt.Color(255, 255, 255));
                btnRefresh.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
                btnRefresh.setIcon(new javax.swing.ImageIcon(("src/assets/icon/refresh (1).png"))); // NOI18N
                btnRefresh.setText("Làm mới");
                // btnRefresh.setBorder(null);
                btnRefresh.setCursor(new java.awt.Cursor(Cursor.HAND_CURSOR));
                btnRefresh.setPreferredSize(new java.awt.Dimension(100, 41));
                btnRefresh.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnRefreshActionPerformed(evt);
                        }

                });
                javax.swing.GroupLayout PanellblTimkiem1Layout = new javax.swing.GroupLayout(PanellblTimkiem1);
                PanellblTimkiem1.setLayout(PanellblTimkiem1Layout);
                PanellblTimkiem1Layout.setHorizontalGroup(
                                PanellblTimkiem1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(PanellblTimkiem1Layout.createSequentialGroup()
                                                                .addGap(118, 118, 118)
                                                                .addComponent(btnTimkiem)
                                                                .addGap(5, 5, 5)
                                                                .addComponent(txt,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                284,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(5, 5, 5)
                                                                .addComponent(btnRefresh,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                122,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)));
                PanellblTimkiem1Layout.setVerticalGroup(
                                PanellblTimkiem1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                PanellblTimkiem1Layout.createSequentialGroup()
                                                                                .addContainerGap(26, Short.MAX_VALUE)
                                                                                .addGroup(PanellblTimkiem1Layout
                                                                                                .createParallelGroup(
                                                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                                false)
                                                                                                .addComponent(btnRefresh,
                                                                                                                javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                Short.MAX_VALUE)
                                                                                                .addGroup(PanellblTimkiem1Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                                .addComponent(txt,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                41,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(btnTimkiem,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                41,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                                .addContainerGap()));

                javax.swing.GroupLayout PanellblTimkiemLayout = new javax.swing.GroupLayout(PanellblTimkiem);
                PanellblTimkiem.setLayout(PanellblTimkiemLayout);
                PanellblTimkiemLayout.setHorizontalGroup(
                                PanellblTimkiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                PanellblTimkiemLayout.createSequentialGroup()
                                                                                .addGap(0, 0, 0)
                                                                                .addComponent(PanellblTimkiem1,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                675,
                                                                                                Short.MAX_VALUE)));
                PanellblTimkiemLayout.setVerticalGroup(
                                PanellblTimkiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(PanellblTimkiem1,
                                                                javax.swing.GroupLayout.Alignment.TRAILING,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 80,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE));

                PanelBtn.add(PanellblTimkiem, java.awt.BorderLayout.EAST);

                PanelNorth.add(PanelBtn);

                add(PanelNorth, java.awt.BorderLayout.NORTH);
        }// </editor-fold>

        private void btnRefreshActionPerformed(ActionEvent evt) {
                // Xóa dữ liệu hiện tại trong JTable
                DefaultTableModel model = (DefaultTableModel) Table.getModel();
                model.setRowCount(0); // Xóa tất cả các hàng

                listProduct = ProductBUS.getAllProduct();

                // Thêm dữ liệu mới vào JTable
                for (ProductDTO productDTO : listProduct) {
                        Object[] rowData = {
                                        productDTO.getId(),
                                        productDTO.getProduct_name(),
                                        new ImageIcon(productDTO
                                                        .getUrl_image()),
                                        Formatter.getFormatedPrice(productDTO
                                                        .getPrice()),
                                        productDTO.getQuantity(),
                                        productDTO.getCategory_name(),
                                        Common.formatedDateTime(productDTO
                                                        .getCreatedAt()),
                                        Common.formatedDateTime(productDTO.getUpdatedAt())

                        };
                        model.addRow(rowData);

                }
        }

        private void rbtnCfActionPerformed(java.awt.event.ActionEvent evt) {
                // TODO add your handling code here:
        }

        private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {
                if (permissionList.hasPermission("CREATE_PRODUCT")) {

                        new CreateProductForm().setVisible(true);

                } else {
                        JOptionPane.showMessageDialog(null, "Bạn không có quyền truy cập");
                        return;
                }
        }

        private void btnChitietActionPerformed(java.awt.event.ActionEvent evt) {
                int selectedRow = Table.getSelectedRow();
                if (selectedRow != -1) { // Nếu có hàng được chọn

                        int id = (int) Table.getValueAt(selectedRow, 0);

                        for (ProductDTO productDTO : listProduct) {
                                if (productDTO.getId() == id) {
                                        new DetailProduct(productDTO).setVisible(true);
                                        return;
                                }
                        }

                } else {
                        JOptionPane.showMessageDialog(this,
                                        "Vui lòng chọn một hàng để hiển thị thông tin.", "Thông báo",
                                        JOptionPane.WARNING_MESSAGE);
                }

        }

        public static void AddRow(Object[] dataRow) {
                DefaultTableModel model = (DefaultTableModel) Table.getModel();
                model.addRow(dataRow);
        }

        // sự kiện nút xoá
        private void btnXoaActionPerformed(ActionEvent evt) {

                if (permissionList.hasPermission("DELETE_PRODUCT")) {
                        int selectedRow = Table.getSelectedRow();
                        if (selectedRow != -1) {
                                try {
                                        int id = (int) Table.getValueAt(selectedRow, 0);
                                        int quantity = (int) Table.getValueAt(selectedRow, 4);
                                        if (quantity > 0) {
                                                JOptionPane.showMessageDialog(null,
                                                                "Sản phẩm còn số lượng bán không thể xoá");
                                                return;
                                        }
                                        int option = JOptionPane.showConfirmDialog(null, "Bạn chắc chắn muốn xóa ? ",
                                                        "Xác nhận", JOptionPane.YES_NO_OPTION);
                                        if (option == JOptionPane.YES_OPTION) {
                                                ProductBUS.deleteProductBUS(id);
                                        }
                                } catch (Exception e) {
                                        // TODO: handle exception
                                }
                        } else {
                                JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm cần xóa");
                        }
                } else {
                        JOptionPane.showMessageDialog(null, "Bạn không có quyền truy cập");
                        return;
                }

        }

        private void updateLabel() {
                String search = txt.getText();
                DefaultTableModel model = (DefaultTableModel) Table.getModel();
                model.setRowCount(0);

                ArrayList<ProductDTO> listProduct = ProductBUS.searchProductbyNameBUS(search);

                for (ProductDTO productDTO : listProduct) {
                        Object[] rowData = {
                                        productDTO.getId(),
                                        productDTO.getProduct_name(),
                                        new ImageIcon(productDTO
                                                        .getUrl_image()),
                                        Formatter.getFormatedPrice(productDTO
                                                        .getPrice()),
                                        productDTO.getQuantity(),
                                        productDTO.getCategory_name(),
                                        Common.formatedDateTime(productDTO
                                                        .getCreatedAt()),
                                        Common.formatedDateTime(productDTO.getUpdatedAt())

                        };
                        model.addRow(rowData);

                }

        }

        private void btnXuatMouseClicked(MouseEvent evt) {
                exportToExcel();
        }

        private void exportToExcel() {
                JFileChooser fileChooser = new JFileChooser(); // Tạo một JFileChooser

                // Thiết lập hộp thoại để chọn tệp và đặt tiêu đề
                fileChooser.setDialogTitle("Chọn nơi lưu tệp Excel");

                int userSelection = fileChooser.showSaveDialog(this); // Hiển thị hộp thoại và chờ người dùng chọn nơi
                                                                      // lưu

                // Kiểm tra xem người dùng đã chọn "Save" hay chưa
                if (userSelection == JFileChooser.APPROVE_OPTION) {
                        try {
                                File fileToSave = fileChooser.getSelectedFile(); // Lấy đường dẫn được chọn bởi người
                                                                                 // dùng

                                // Ghi dữ liệu vào tệp Excel
                                try (Workbook workbook = new XSSFWorkbook()) {
                                        Sheet sheet = workbook.createSheet("Sheet1");

                                        // Lấy mô hình của JTable
                                        DefaultTableModel model = (DefaultTableModel) Table.getModel();
                                        // Viết tiêu đề cột
                                        Row headerRow = sheet.createRow(0);
                                        for (int col = 0; col < model.getColumnCount(); col++) {
                                                headerRow.createCell(col).setCellValue(model.getColumnName(col));
                                        }

                                        // Viết dữ liệu từ JTable vào tệp Excel
                                        for (int row = 0; row < model.getRowCount(); row++) {
                                                Row excelRow = sheet.createRow(row + 1); // Bắt đầu từ hàng thứ 2 (hàng
                                                                                         // đầu tiên là tiêu đề)
                                                for (int col = 0; col < model.getColumnCount(); col++) {
                                                        Object cellValue = model.getValueAt(row, col);
                                                        if (cellValue != null) {
                                                                Cell excelCell = excelRow.createCell(col);
                                                                if (cellValue instanceof String) {

                                                                        excelCell.setCellValue((String) cellValue);

                                                                } else if (cellValue instanceof Integer) {

                                                                        excelCell.setCellValue((Integer) cellValue);

                                                                } else if (cellValue instanceof LocalDate) {

                                                                        System.out.println(cellValue);
                                                                        String stringValue = ((LocalDate) cellValue)
                                                                                        .format(DateTimeFormatter
                                                                                                        .ofPattern("yyyy-MM-dd"));
                                                                        excelCell.setCellValue(stringValue);

                                                                } else if (cellValue instanceof ImageIcon) {
                                                                        // Chuyển ImageIcon thành byte array
                                                                        ImageIcon icon = (ImageIcon) cellValue;
                                                                        BufferedImage bufferedImage = new BufferedImage(
                                                                                        icon.getIconWidth(),
                                                                                        icon.getIconHeight(),
                                                                                        BufferedImage.TYPE_INT_RGB);
                                                                        Graphics2D graphics = bufferedImage
                                                                                        .createGraphics();
                                                                        icon.paintIcon(null, graphics, 0, 0);
                                                                        graphics.dispose();

                                                                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                                                                        ImageIO.write(bufferedImage, "png", baos);
                                                                        baos.flush();
                                                                        byte[] imageInByte = baos.toByteArray();
                                                                        baos.close();

                                                                        // Thêm hình ảnh vào tệp Excel
                                                                        int pictureIdx = workbook.addPicture(
                                                                                        imageInByte,
                                                                                        Workbook.PICTURE_TYPE_PNG);
                                                                        CreationHelper helper = workbook
                                                                                        .getCreationHelper();
                                                                        Drawing drawing = sheet
                                                                                        .createDrawingPatriarch();
                                                                        ClientAnchor anchor = helper
                                                                                        .createClientAnchor();

                                                                        // Thiết lập vị trí của hình ảnh
                                                                        anchor.setCol1(col); // Cột bắt đầu
                                                                        anchor.setRow1(row + 1); // Hàng bắt đầu (bỏ qua
                                                                                                 // hàng tiêu đề)
                                                                        anchor.setCol2(col + 1); // Cột kết thúc
                                                                        anchor.setRow2(row + 2); // Hàng kết thúc

                                                                        // // Đính kèm hình ảnh vào tệp Excel
                                                                        Picture pict = drawing.createPicture(anchor,
                                                                                        pictureIdx);
                                                                        // pict.resize(); // Tự động điều chỉnh kích
                                                                        // thước

                                                                }
                                                        }
                                                }
                                        }

                                        // Ghi vào tệp Excel
                                        try (FileOutputStream outputStream = new FileOutputStream(
                                                        fileToSave + ".xlsx")) {
                                                workbook.write(outputStream);
                                        }
                                        JOptionPane.showMessageDialog(null, "Xuất file excel thành công.");
                                }
                        } catch (IOException e) {
                                e.printStackTrace();
                        }
                }
        }

        private void btnLocMouseClicked(MouseEvent evt) {
                int sortByPrice = cmbGia.getSelectedIndex();
                int filterByCategory = cmbSoluong.getSelectedIndex();

                DefaultTableModel model = (DefaultTableModel) Table.getModel();
                model.setRowCount(0); // Xóa tất cả các hàng

                ArrayList<ProductDTO> list_product_filter = ProductBUS.filterProductBUS(sortByPrice, filterByCategory);

                for (ProductDTO productDTO : list_product_filter) {
                        Object[] rowData = {
                                        productDTO.getId(),
                                        productDTO.getProduct_name(),
                                        new ImageIcon(productDTO
                                                        .getUrl_image()),
                                        Formatter.getFormatedPrice(productDTO
                                                        .getPrice()),
                                        productDTO.getQuantity(),
                                        productDTO.getCategory_name(),
                                        Common.formatedDateTime(productDTO
                                                        .getCreatedAt()),
                                        Common.formatedDateTime(productDTO.getUpdatedAt())

                        };
                        model.addRow(rowData);

                }
        }

        // private class Highlight extends DefaultTableCellRenderer {

        // DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();

        // public Component getTableCellRendererComponent(JTable table, Object value,
        // boolean isSelected, boolean hasFocus, int row, int column) {

        // Component c = super.getTableCellRendererComponent(table, value, isSelected,
        // hasFocus, row,
        // column);
        // String searchText = txt.getText();
        // String cellValue = String.valueOf(value);

        // // Highlight the cell with yellow background if the cell value matches the
        // // search text
        // if (cellValue.equals(searchText)) {

        // c.setBackground(new Color(204, 204, 204));
        // } else {
        // c.setBackground(table.getBackground());
        // }
        // for (int i = 0; i < table.getColumnCount(); i++) {
        // table.getColumnModel().getColumn(i).setCellRenderer(renderer);
        // }

        // return c;

        // }

        // }

        // Variables declaration - do not modify
        private javax.swing.JPanel Btn;
        private javax.swing.JPanel Btn1;
        private javax.swing.JPanel PanelBtn;
        private javax.swing.JPanel PanelNorth;
        private javax.swing.JPanel PanelSouth;
        private javax.swing.JPanel PanelSouth1;
        private javax.swing.JPanel PanelSp;
        private javax.swing.JPanel PanelTable;
        private javax.swing.JPanel PanelTimkiem;
        private javax.swing.JPanel PanellblTimkiem;
        private javax.swing.JPanel PanellblTimkiem1;
        private static javax.swing.JTable Table;
        private javax.swing.JButton btnChitiet;
        private javax.swing.JButton btnLoc;
        private javax.swing.JButton btnRefresh;
        private javax.swing.JButton btnThem;
        private javax.swing.JButton btnTimkiem;
        private javax.swing.JButton btnXoa;
        private javax.swing.JButton btnXuat;
        private javax.swing.ButtonGroup buttonGroup1;
        private javax.swing.JComboBox<String> cmbGia;
        private javax.swing.JComboBox<String> cmbSoluong;
        private javax.swing.JScrollPane jScrollPane1;
        private javax.swing.JSplitPane jSplitPane1;
        private javax.swing.JLabel lblLoaisp;
        private javax.swing.JLabel lblMucGia;
        private javax.swing.JLabel lblSanpham;
        private javax.swing.JLabel lblSoluong;
        private javax.swing.JPanel panellblSp;
        private javax.swing.JRadioButton rbtnCf;
        private javax.swing.JRadioButton rbtnSoda;
        private javax.swing.JRadioButton rbtnTrs;
        private javax.swing.JTextField txt;
        // End of variables declaration

}
