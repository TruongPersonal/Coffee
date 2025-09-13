/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.project.GUI;

import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.project.BUS.AccountBUS;
import com.project.BUS.EmployeeBUS;
import com.project.BUS.OrderBUS;
import com.project.BUS.PaymentMethodBUS;
import com.project.Common.Common;
import com.project.DTO.AccountDTO;
import com.project.DTO.EmployeeDTO;
import com.project.DTO.OrderDTO;
import com.project.DTO.PaymentMethodDTO;
import com.project.DTO.PermissionAccount;
import com.project.Util.Formatter;

/**
 *
 * @author LuanLe
 */
public class OrderMenu extends javax.swing.JPanel {
        private PermissionAccount permissionList;

        private static ArrayList<PaymentMethodDTO> paymentMethods = new PaymentMethodBUS().getAll();
        private static OrderBUS orderBUS = new OrderBUS();
        private static EmployeeBUS empBUS = new EmployeeBUS();

        private LinkedHashMap<OrderDTO, Float> orders;
        private ArrayList<EmployeeDTO> empList;
        private ArrayList<AccountDTO> accList;

        private EditOrderForm editOrderForm;
        private OrderDetail orderDetailForm;
        private BuyProduct BuyProductPanel;
        private JFrame BuyProductForm;

        public OrderMenu() {
                initComponents();
                permissionList = PermissionAccount.getInstance();

                loadData();
        }

        private void loadData() {

                loadData(orderBUS.getAllWithTotal());
        }

        private void loadData(LinkedHashMap<OrderDTO, Float> orders) {
                this.orders = orders;
                if (orders != null) {
                        DefaultTableModel dtm = (DefaultTableModel) tbTableOrder.getModel();
                        dtm.setRowCount(0);

                        ArrayList<Integer> accountID_List = new ArrayList<Integer>();
                        for (OrderDTO o : orders.keySet()) {
                                accountID_List.add(o.getAcount_id());
                        }
                        empList = empBUS.getEmpList_ByAccountID(accountID_List);
                        accList = AccountBUS.getAllAccount(accountID_List);
                        int i = 0;
                        for (OrderDTO o : orders.keySet()) {
                                Float total = orders.get(o);

                                dtm.addRow(new Object[] {
                                                o.getId(),
                                                accList.get(i) == null ? "NULL" : accList.get(i).getEmail(),
                                                empList.get(i) == null ? "NULL" : empList.get(i).getName(),
                                                Formatter.getFormatedPrice(total),
                                                paymentMethods.get(o.getPaymentMethod_id() - 1).getPayment_name(),
                                                Common.formatedDateTime(o.getCreatedAt()),
                                                o.getOrder_status()
                                });
                                i++;
                        }

                        Formatter.centerAlignTableCells(tbTableOrder);
                } else {
                        JOptionPane.showMessageDialog(this, "Có lỗi xảy ra!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
        }

        private void initComponents() {

                pnTitle = new javax.swing.JPanel();
                lblTitle = new javax.swing.JLabel();
                pnCenter = new javax.swing.JPanel();
                jPanel1 = new javax.swing.JPanel();
                jPanel2 = new javax.swing.JPanel();
                GroupButton = new javax.swing.JPanel();
                btnCreate = new javax.swing.JButton();
                btnEdit = new javax.swing.JButton();
                btnViewDetails = new javax.swing.JButton();
                btnExportExcel = new javax.swing.JButton();
                cbSearch = new javax.swing.JComboBox<>();
                txtInputSearch = new javax.swing.JTextField();
                btnRefresh = new javax.swing.JButton();
                jSplitPane = new javax.swing.JSplitPane();
                pnContentFilter = new javax.swing.JPanel();
                pnStatusOrder = new javax.swing.JPanel();
                pnPaymentMethods = new javax.swing.JPanel();
                lblOrderStatus = new javax.swing.JLabel();
                lblPaymentMethods = new javax.swing.JLabel();
                cbStatus = new javax.swing.JComboBox<>();
                cbPaymentMethods = new javax.swing.JComboBox<>();
                pnPickStartDate = new javax.swing.JPanel();
                lblStartDate = new javax.swing.JLabel();
                dcStartDate = new com.toedter.calendar.JDateChooser();
                pnPickEndDate = new javax.swing.JPanel();
                lblEndDate = new javax.swing.JLabel();
                dcEndDate = new com.toedter.calendar.JDateChooser();
                pnMinPrice = new javax.swing.JPanel();
                lblMinPrice = new javax.swing.JLabel();
                txtInputMinPrice = new javax.swing.JTextField();
                pnMaxPrice = new javax.swing.JPanel();
                lblMaxPrice = new javax.swing.JLabel();
                txtInputMaxPrice = new javax.swing.JTextField();
                jPanel3 = new javax.swing.JPanel();
                btnFilter = new javax.swing.JButton();
                jScrollPane1 = new javax.swing.JScrollPane();
                tbTableOrder = new javax.swing.JTable();

                setBackground(new java.awt.Color(255, 255, 255));
                setMinimumSize(new java.awt.Dimension(1085, 768));
                setPreferredSize(new java.awt.Dimension(1085, 768));
                setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.Y_AXIS));

                pnTitle.setBackground(new java.awt.Color(255, 255, 255));
                pnTitle.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
                pnTitle.setPreferredSize(new java.awt.Dimension(244, 70));
                pnTitle.setLayout(new javax.swing.BoxLayout(pnTitle, javax.swing.BoxLayout.LINE_AXIS));

                lblTitle.setBackground(new java.awt.Color(255, 255, 255));
                lblTitle.setFont(new java.awt.Font("Arial", 1, 24));
                lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lblTitle.setIcon(new javax.swing.ImageIcon("./src/assets/icon/order-fulfillment.png"));
                lblTitle.setText("QUẢN LÝ ĐƠN HÀNG");
                lblTitle.setMaximumSize(new java.awt.Dimension(32767, 60));
                lblTitle.setMinimumSize(new java.awt.Dimension(1085, 60));
                lblTitle.setPreferredSize(new java.awt.Dimension(1085, 60));
                pnTitle.add(lblTitle);

                add(pnTitle);

                pnCenter.setPreferredSize(new java.awt.Dimension(1085, 600));

                jPanel1.setBackground(new java.awt.Color(255, 255, 255));
                jPanel1.setPreferredSize(new java.awt.Dimension(1085, 637));

                jPanel2.setBackground(new java.awt.Color(255, 255, 255));
                jPanel2.setPreferredSize(new java.awt.Dimension(636, 80));

                GroupButton.setBackground(new java.awt.Color(255, 255, 255));
                GroupButton.setLayout(new java.awt.GridLayout(1, 0, 5, 0));

                btnCreate.setFont(new java.awt.Font("Arial", 0, 16));
                btnCreate.setIcon(new javax.swing.ImageIcon("./src/assets/icon/plus.png"));
                btnCreate.setText("Thêm");
                btnCreate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnCreate.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                btnCreate.setMaximumSize(new java.awt.Dimension(30, 30));
                btnCreate.setMinimumSize(new java.awt.Dimension(30, 30));
                btnCreate.setPreferredSize(new java.awt.Dimension(30, 30));
                btnCreate.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
                btnCreate.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnCreateActionPerformed(evt);
                        }
                });
                GroupButton.add(btnCreate);

                btnEdit.setFont(new java.awt.Font("Arial", 0, 16));
                btnEdit.setIcon(new javax.swing.ImageIcon("./src/assets/icon/edit.png"));
                btnEdit.setText("Sửa");
                btnEdit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnEdit.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                btnEdit.setMaximumSize(new java.awt.Dimension(30, 30));
                btnEdit.setMinimumSize(new java.awt.Dimension(30, 30));
                btnEdit.setPreferredSize(new java.awt.Dimension(30, 30));
                btnEdit.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
                btnEdit.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnEditActionPerformed(evt);
                        }
                });
                GroupButton.add(btnEdit);

                btnViewDetails.setFont(new java.awt.Font("Arial", 0, 16));
                btnViewDetails.setIcon(new javax.swing.ImageIcon("./src/assets/icon/info-rgb.png"));
                btnViewDetails.setText("Chi tiết");
                btnViewDetails.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnViewDetails.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                btnViewDetails.setMaximumSize(new java.awt.Dimension(30, 30));
                btnViewDetails.setMinimumSize(new java.awt.Dimension(30, 30));
                btnViewDetails.setPreferredSize(new java.awt.Dimension(30, 30));
                btnViewDetails.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
                btnViewDetails.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnViewDetailsActionPerformed(evt);
                        }
                });
                GroupButton.add(btnViewDetails);

                btnExportExcel.setFont(new java.awt.Font("Arial", 0, 16));
                btnExportExcel.setIcon(new javax.swing.ImageIcon("./src/assets/icon/xls.png"));
                btnExportExcel.setText("Xuất");
                btnExportExcel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnExportExcel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                btnExportExcel.setMaximumSize(new java.awt.Dimension(30, 30));
                btnExportExcel.setMinimumSize(new java.awt.Dimension(30, 30));
                btnExportExcel.setPreferredSize(new java.awt.Dimension(30, 30));
                btnExportExcel.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
                btnExportExcel.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnExportExcelActionPerformed(evt);
                        }
                });
                GroupButton.add(btnExportExcel);

                cbSearch.setFont(new java.awt.Font("Arial", 0, 14));
                cbSearch.setModel(

                                new javax.swing.DefaultComboBoxModel<>(
                                                new String[] { "Mã đơn hàng", "Họ tên nhân viên" }));
                cbSearch.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                cbSearch.setMaximumSize(new java.awt.Dimension(60, 30));
                cbSearch.setMinimumSize(new java.awt.Dimension(60, 30));
                cbSearch.setPreferredSize(new java.awt.Dimension(60, 50));

                cbSearch.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                cbSearch.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                txtInputSearch.requestFocus();
                        }

                });

                txtInputSearch.setFont(new java.awt.Font("Arial", 0, 14));
                txtInputSearch.setMaximumSize(new java.awt.Dimension(100, 30));
                txtInputSearch.setMinimumSize(new java.awt.Dimension(100, 30));
                txtInputSearch.setPreferredSize(new java.awt.Dimension(100, 50));

                Formatter.setPlaceHolder(txtInputSearch, "Nhập từ khóa tìm kiếm");
                txtInputSearch.addKeyListener(new KeyAdapter() {
                        @Override
                        public void keyReleased(KeyEvent e) {
                                handleChangeInputSearch();
                        }

                });

                btnRefresh.setFont(new java.awt.Font("Arial", 0, 14));
                btnRefresh.setIcon(new javax.swing.ImageIcon("./src/assets/icon/refresh.png"));
                btnRefresh.setText("Làm mới");
                btnRefresh.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnRefresh.setMaximumSize(new java.awt.Dimension(80, 50));
                btnRefresh.setMinimumSize(new java.awt.Dimension(80, 50));
                btnRefresh.setPreferredSize(new java.awt.Dimension(80, 30));
                btnRefresh.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnRefreshActionPerformed(evt);
                        }
                });

                javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
                jPanel2.setLayout(jPanel2Layout);
                jPanel2Layout.setHorizontalGroup(
                                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel2Layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(GroupButton,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                350,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addComponent(cbSearch,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                168,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(txtInputSearch,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                176,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(btnRefresh,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                141,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap()));
                jPanel2Layout.setVerticalGroup(
                                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(GroupButton, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout
                                                                .createSequentialGroup()
                                                                .addContainerGap(19, Short.MAX_VALUE)
                                                                .addGroup(jPanel2Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(cbSearch,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                51,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(txtInputSearch,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                51,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(btnRefresh,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                51,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addContainerGap(18, Short.MAX_VALUE)));

                pnContentFilter.setBackground(new java.awt.Color(255, 255, 255));
                pnContentFilter.setAlignmentY(0.2F);
                pnContentFilter.setMaximumSize(new java.awt.Dimension(220, 32767));
                pnContentFilter.setMinimumSize(new java.awt.Dimension(220, 600));
                pnContentFilter.setName("");

                pnStatusOrder.setBackground(new java.awt.Color(255, 255, 255));
                pnStatusOrder.setPreferredSize(new java.awt.Dimension(200, 70));

                lblOrderStatus.setFont(new java.awt.Font("Arial", 0, 14));
                lblOrderStatus.setText("Trạng thái đơn hàng");
                lblOrderStatus.setMaximumSize(new java.awt.Dimension(200, 20));
                lblOrderStatus.setMinimumSize(new java.awt.Dimension(200, 20));
                lblOrderStatus.setPreferredSize(new java.awt.Dimension(200, 20));

                cbStatus.setFont(new java.awt.Font("Arial", 0, 14));
                cbStatus.setModel(new javax.swing.DefaultComboBoxModel<>(
                                new String[] { "Tất cả", "cancelled", "pending", "successful" }));
                cbStatus.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
                cbStatus.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                cbStatus.setMinimumSize(new java.awt.Dimension(200, 20));
                cbStatus.setPreferredSize(new java.awt.Dimension(200, 20));

                javax.swing.GroupLayout pnStatusOrderLayout = new javax.swing.GroupLayout(pnStatusOrder);
                pnStatusOrder.setLayout(pnStatusOrderLayout);
                pnStatusOrderLayout.setHorizontalGroup(
                                pnStatusOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                pnStatusOrderLayout.createSequentialGroup()
                                                                                .addGroup(pnStatusOrderLayout
                                                                                                .createParallelGroup(
                                                                                                                javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                                .addComponent(cbStatus,
                                                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                                0,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                Short.MAX_VALUE)
                                                                                                .addComponent(lblOrderStatus,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                Short.MAX_VALUE))
                                                                                .addContainerGap()));
                pnStatusOrderLayout.setVerticalGroup(
                                pnStatusOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(pnStatusOrderLayout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(lblOrderStatus,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(cbStatus,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                31,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap()));

                pnContentFilter.add(pnStatusOrder);

                pnPaymentMethods.setBackground(new java.awt.Color(255, 255, 255));
                pnPaymentMethods.setPreferredSize(new java.awt.Dimension(200, 70));

                lblPaymentMethods.setFont(new java.awt.Font("Arial", 0, 14));
                lblPaymentMethods.setText("Hình thức thanh toán");
                lblPaymentMethods.setMaximumSize(new java.awt.Dimension(200, 20));
                lblPaymentMethods.setMinimumSize(new java.awt.Dimension(200, 20));
                lblPaymentMethods.setPreferredSize(new java.awt.Dimension(200, 20));

                // Set cbbox payment methods model
                cbPaymentMethods.setFont(new java.awt.Font("Arial", 0, 14));
                ArrayList<PaymentMethodDTO> pmMethodlist = new PaymentMethodBUS().getAll();
                String[] pmMethodStrings = new String[pmMethodlist.size() + 1];
                pmMethodStrings[0] = "Tất cả";
                for (int i = 1; i <= pmMethodlist.size(); i++) {
                        pmMethodStrings[i] = pmMethodlist.get(i - 1).getPayment_name();
                }
                cbPaymentMethods.setModel(new javax.swing.DefaultComboBoxModel<>(pmMethodStrings));

                cbPaymentMethods.setBorder(
                                javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
                cbPaymentMethods.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                cbPaymentMethods.setMinimumSize(new java.awt.Dimension(200, 20));
                cbPaymentMethods.setPreferredSize(new java.awt.Dimension(200, 20));

                javax.swing.GroupLayout pnPaymentMethodsLayout = new javax.swing.GroupLayout(pnPaymentMethods);
                pnPaymentMethods.setLayout(pnPaymentMethodsLayout);
                pnPaymentMethodsLayout.setHorizontalGroup(
                                pnPaymentMethodsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                pnPaymentMethodsLayout.createSequentialGroup()
                                                                                .addGroup(pnPaymentMethodsLayout
                                                                                                .createParallelGroup(
                                                                                                                javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                                .addComponent(cbPaymentMethods,
                                                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                                0,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                Short.MAX_VALUE)
                                                                                                .addComponent(lblPaymentMethods,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                Short.MAX_VALUE))
                                                                                .addContainerGap()));
                pnPaymentMethodsLayout.setVerticalGroup(
                                pnPaymentMethodsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(pnPaymentMethodsLayout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(lblPaymentMethods,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(cbPaymentMethods,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                31,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap()));

                pnContentFilter.add(pnPaymentMethods);

                pnPickStartDate.setBackground(new java.awt.Color(255, 255, 255));
                pnPickStartDate.setPreferredSize(new java.awt.Dimension(200, 90));
                pnPickStartDate.setRequestFocusEnabled(false);

                lblStartDate.setFont(new java.awt.Font("Arial", 0, 14));
                lblStartDate.setText("Từ ngày");
                lblStartDate.setMaximumSize(new java.awt.Dimension(200, 20));
                lblStartDate.setMinimumSize(new java.awt.Dimension(200, 20));
                lblStartDate.setPreferredSize(new java.awt.Dimension(200, 20));

                dcStartDate.setBackground(new java.awt.Color(255, 255, 255));
                dcStartDate.setFont(new java.awt.Font("Arial", 0, 14));
                dcStartDate.setNextFocusableComponent(dcStartDate);

                javax.swing.GroupLayout pnPickStartDateLayout = new javax.swing.GroupLayout(pnPickStartDate);
                pnPickStartDate.setLayout(pnPickStartDateLayout);
                pnPickStartDateLayout.setHorizontalGroup(
                                pnPickStartDateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(dcStartDate, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(lblStartDate, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
                pnPickStartDateLayout.setVerticalGroup(
                                pnPickStartDateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(pnPickStartDateLayout.createSequentialGroup()
                                                                .addGap(5, 5, 5)
                                                                .addComponent(lblStartDate,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                24,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(5, 5, 5)
                                                                .addComponent(dcStartDate,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                50,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)));

                pnContentFilter.add(pnPickStartDate);

                pnPickEndDate.setBackground(new java.awt.Color(255, 255, 255));
                pnPickEndDate.setPreferredSize(new java.awt.Dimension(200, 90));

                lblEndDate.setFont(new java.awt.Font("Arial", 0, 14));
                lblEndDate.setText("Đến ngày");
                lblEndDate.setMaximumSize(new java.awt.Dimension(200, 20));
                lblEndDate.setMinimumSize(new java.awt.Dimension(200, 20));
                lblEndDate.setPreferredSize(new java.awt.Dimension(200, 20));

                dcEndDate.setBackground(new java.awt.Color(255, 255, 255));
                dcEndDate.setFont(new java.awt.Font("Arial", 0, 14));
                dcEndDate.setNextFocusableComponent(dcEndDate);

                javax.swing.GroupLayout pnPickEndDateLayout = new javax.swing.GroupLayout(pnPickEndDate);
                pnPickEndDate.setLayout(pnPickEndDateLayout);
                pnPickEndDateLayout.setHorizontalGroup(
                                pnPickEndDateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(lblEndDate, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(dcEndDate, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
                pnPickEndDateLayout.setVerticalGroup(
                                pnPickEndDateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(pnPickEndDateLayout.createSequentialGroup()
                                                                .addGap(5, 5, 5)
                                                                .addComponent(lblEndDate,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(5, 5, 5)
                                                                .addComponent(dcEndDate,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                50,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap()));

                pnContentFilter.add(pnPickEndDate);

                pnMinPrice.setBackground(new java.awt.Color(255, 255, 255));
                pnMinPrice.setPreferredSize(new java.awt.Dimension(200, 90));

                lblMinPrice.setFont(new java.awt.Font("Arial", 0, 14));
                lblMinPrice.setText("Tổng tiền từ");
                lblMinPrice.setMaximumSize(new java.awt.Dimension(200, 20));
                lblMinPrice.setMinimumSize(new java.awt.Dimension(200, 20));
                lblMinPrice.setPreferredSize(new java.awt.Dimension(200, 20));

                txtInputMinPrice.setFont(new java.awt.Font("Arial", 0, 14));
                txtInputMinPrice.setMinimumSize(new java.awt.Dimension(82, 20));
                txtInputMinPrice.setName("");
                txtInputMinPrice.setPreferredSize(new java.awt.Dimension(82, 20));

                javax.swing.GroupLayout pnMinPriceLayout = new javax.swing.GroupLayout(pnMinPrice);
                pnMinPrice.setLayout(pnMinPriceLayout);
                pnMinPriceLayout.setHorizontalGroup(
                                pnMinPriceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(lblMinPrice, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(txtInputMinPrice, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
                pnMinPriceLayout.setVerticalGroup(
                                pnMinPriceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(pnMinPriceLayout.createSequentialGroup()
                                                                .addComponent(lblMinPrice,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(5, 5, 5)
                                                                .addComponent(txtInputMinPrice,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                50,
                                                                                Short.MAX_VALUE)
                                                                .addContainerGap()));

                pnContentFilter.add(pnMinPrice);

                pnMaxPrice.setBackground(new java.awt.Color(255, 255, 255));
                pnMaxPrice.setPreferredSize(new java.awt.Dimension(200, 90));

                lblMaxPrice.setFont(new java.awt.Font("Arial", 0, 14));
                lblMaxPrice.setText("Tổng tiền đến");
                lblMaxPrice.setMaximumSize(new java.awt.Dimension(200, 20));
                lblMaxPrice.setMinimumSize(new java.awt.Dimension(200, 20));
                lblMaxPrice.setPreferredSize(new java.awt.Dimension(200, 20));
                lblMaxPrice.setRequestFocusEnabled(false);

                txtInputMaxPrice.setFont(new java.awt.Font("Arial", 0, 14));
                txtInputMaxPrice.setMinimumSize(new java.awt.Dimension(82, 20));
                txtInputMaxPrice.setName("");
                txtInputMaxPrice.setPreferredSize(new java.awt.Dimension(82, 20));

                javax.swing.GroupLayout pnMaxPriceLayout = new javax.swing.GroupLayout(pnMaxPrice);
                pnMaxPrice.setLayout(pnMaxPriceLayout);
                pnMaxPriceLayout.setHorizontalGroup(
                                pnMaxPriceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(lblMaxPrice, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(txtInputMaxPrice, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
                pnMaxPriceLayout.setVerticalGroup(
                                pnMaxPriceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(pnMaxPriceLayout.createSequentialGroup()
                                                                .addComponent(lblMaxPrice,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(5, 5, 5)
                                                                .addComponent(txtInputMaxPrice,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                50,
                                                                                Short.MAX_VALUE)
                                                                .addContainerGap()));

                pnContentFilter.add(pnMaxPrice);

                jPanel3.setBackground(new java.awt.Color(255, 255, 255));
                jPanel3.setPreferredSize(new java.awt.Dimension(200, 50));

                btnFilter.setFont(new java.awt.Font("Arial", 0, 14));
                btnFilter.setIcon(new javax.swing.ImageIcon("./src/assets/icon/filter-32.png"));
                btnFilter.setText("Lọc");
                btnFilter.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnFilter.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnFilterActionPerformed(evt);
                        }
                });

                javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
                jPanel3.setLayout(jPanel3Layout);
                jPanel3Layout.setHorizontalGroup(
                                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGap(0, 200, Short.MAX_VALUE)
                                                .addGroup(jPanel3Layout.createParallelGroup(
                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addGroup(jPanel3Layout.createSequentialGroup()
                                                                                .addGap(0, 0, Short.MAX_VALUE)
                                                                                .addComponent(btnFilter)
                                                                                .addGap(0, 0, Short.MAX_VALUE))));
                jPanel3Layout.setVerticalGroup(
                                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGap(0, 50, Short.MAX_VALUE)
                                                .addGroup(jPanel3Layout.createParallelGroup(
                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addGroup(jPanel3Layout.createSequentialGroup()
                                                                                .addGap(0, 0, Short.MAX_VALUE)
                                                                                .addComponent(btnFilter)
                                                                                .addGap(0, 0, Short.MAX_VALUE))));

                pnContentFilter.add(jPanel3);

                jSplitPane.setLeftComponent(pnContentFilter);

                jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

                tbTableOrder.setFont(new java.awt.Font("Arial", 0, 14));
                tbTableOrder.setAlignmentX(0.8F);
                tbTableOrder.setAlignmentY(0.8F);
                tbTableOrder.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                tbTableOrder.setGridColor(new java.awt.Color(204, 204, 204));
                tbTableOrder.setMaximumSize(new java.awt.Dimension(2147483647, 32767));
                tbTableOrder.setMinimumSize(new java.awt.Dimension(879, 600));
                tbTableOrder.setPreferredSize(new java.awt.Dimension(879, 600));
                tbTableOrder.getTableHeader().setReorderingAllowed(false);

                tbTableOrder.setShowGrid(true);
                jScrollPane1.setViewportView(tbTableOrder);
                if (tbTableOrder.getColumnModel().getColumnCount() > 0) {
                        tbTableOrder.getColumnModel().getColumn(0).setMinWidth(0);
                        tbTableOrder.getColumnModel().getColumn(0).setPreferredWidth(0);
                }

                jSplitPane.setRightComponent(jScrollPane1);
                jSplitPane.setBorder((javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0))));

                javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jSplitPane, javax.swing.GroupLayout.DEFAULT_SIZE, 1085,
                                                                Short.MAX_VALUE)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout
                                                                .createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(jPanel2,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                1073, Short.MAX_VALUE)
                                                                .addContainerGap()));
                jPanel1Layout.setVerticalGroup(
                                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addGap(15, 15, 15)
                                                                .addComponent(jPanel2,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                88,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(jSplitPane)
                                                                .addContainerGap()));

                javax.swing.GroupLayout pnCenterLayout = new javax.swing.GroupLayout(pnCenter);
                pnCenter.setLayout(pnCenterLayout);
                pnCenterLayout.setHorizontalGroup(
                                pnCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnCenterLayout
                                                                .createSequentialGroup()
                                                                .addComponent(jPanel1,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addGap(0, 0, 0)));
                pnCenterLayout.setVerticalGroup(
                                pnCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, 727,
                                                                Short.MAX_VALUE));

                add(pnCenter);

                DefaultTableModel dtm = new DefaultTableModel() {
                        @Override
                        public boolean isCellEditable(int row, int column) {
                                return false;
                        }
                };
                dtm.addColumn("Mã đơn hàng");
                dtm.addColumn("Email");
                dtm.addColumn("Họ tên nhân viên");
                dtm.addColumn("Tổng tiền");
                dtm.addColumn("Phương thức thanh toán");
                dtm.addColumn("Ngày tạo");
                dtm.addColumn("Trạng thái");

                tbTableOrder.setModel(dtm);
                tbTableOrder.addComponentListener(new ComponentAdapter() {
                        @Override
                        public void componentResized(ComponentEvent e) {
                                double[] columnPercentages = { 0.1, 0.1, 0.2, 0.2, 0.2, 0.1, 0.1 }; // Phần trăm độ rộng
                                                                                                    // cho từng cột
                                setColumnWidths(tbTableOrder, columnPercentages);
                        }
                });
                Formatter.setBoldHeaderTable(tbTableOrder);
        }

        private static void setColumnWidths(JTable table, double[] percentages) {
                if (table == null || percentages == null
                                || table.getColumnModel().getColumnCount() != percentages.length) {
                        return; // Bảng hoặc mảng tỉ lệ không hợp lệ
                }

                TableColumnModel columnModel = table.getColumnModel();
                int totalWidth = table.getWidth();
                for (int i = 0; i < percentages.length; i++) {
                        int width = (int) (totalWidth * percentages[i]); // Tính toán độ rộng dựa trên tỉ lệ phần trăm
                        columnModel.getColumn(i).setPreferredWidth(width);
                }
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
                                        DefaultTableModel model = (DefaultTableModel) tbTableOrder.getModel();
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
                                                                } // Và có thể thêm các kiểu dữ liệu khác tùy theo nhu
                                                                  // cầu
                                                        }
                                                }
                                        }

                                        // Ghi vào tệp Excel
                                        try (FileOutputStream outputStream = new FileOutputStream(
                                                        fileToSave + ".xlsx")) {
                                                workbook.write(outputStream);
                                        }
                                        JOptionPane.showMessageDialog(null, "Xuất file excel thành công.", "Thông báo",
                                                        JOptionPane.INFORMATION_MESSAGE);
                                }
                        } catch (IOException e) {
                                e.printStackTrace();
                        }
                }
        }

        private void btnCreateActionPerformed(java.awt.event.ActionEvent evt) {
                if (BuyProductForm == null) {
                        BuyProductForm = new JFrame("Chọn sản phẩm");
                        BuyProductForm.setSize(1085, 768);
                        BuyProductForm.setPreferredSize(new java.awt.Dimension(1085, 768));
                        BuyProductForm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        BuyProductForm.pack();
                        BuyProductForm.setLocationRelativeTo(null);
                        if (BuyProductPanel == null) {
                                BuyProductPanel = new BuyProduct();
                        }
                        BuyProductPanel = Main.getBuyProduct();
                        // BuyProductForm.add(BuyProductPanel);
                } else {
                        BuyProductForm.setVisible(true);
                }

        }

        private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {
                if (permissionList.hasPermission("UPDATE_BILL")) {
                        int selectedRow = tbTableOrder.getSelectedRow();
                        if (selectedRow != -1) {
                                int orderID = (int) tbTableOrder.getValueAt(selectedRow, 0);
                                String emp_email = (String) tbTableOrder.getValueAt(selectedRow, 1);
                                String emp_name = (String) tbTableOrder.getValueAt(selectedRow, 2);

                                for (OrderDTO order : orders.keySet()) {
                                        if (order.getId() == orderID) {
                                                if (orderDetailForm != null) {
                                                        orderDetailForm.dispose();
                                                }
                                                editOrderForm = new EditOrderForm(order, emp_name, emp_email);
                                                return;
                                        }
                                }
                        } else {
                                JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng để xem chi tiết", "Thông báo",
                                                JOptionPane.INFORMATION_MESSAGE);
                        }

                } else {
                        JOptionPane.showMessageDialog(null, "Bạn không có quyền truy cập");
                        return;
                }
        }

        private void btnFilterActionPerformed(java.awt.event.ActionEvent evt) {
                Date startDate = dcStartDate.getDate();
                Date endDate = dcEndDate.getDate();
                String startTotalText = txtInputMinPrice.getText();
                String endTotalText = txtInputMaxPrice.getText();

                float minTotal = -1;
                float maxTotal = -1;
                Timestamp tsStartDate = null;
                Timestamp tsEndDate = null;

                if (startDate != null || endDate != null) {
                        if (startDate == null || endDate == null) {
                                JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ giá trị ngày", "Thông báo",
                                                JOptionPane.INFORMATION_MESSAGE);
                                return;
                        }
                        if (startDate.compareTo(endDate) > 0) {
                                JOptionPane.showMessageDialog(null, "Ngày bắt đầu phải nhỏ hơn hoặc bằng ngày kết thúc",
                                                "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                                return;
                        }

                        tsStartDate = new Timestamp(startDate.getTime());
                        tsEndDate = new Timestamp(endDate.getTime());
                }

                if (!startTotalText.isEmpty() || !endTotalText.isEmpty()) {
                        if (startTotalText.isEmpty() || endTotalText.isEmpty()) {
                                JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ giá trị tổng hoá đơn",
                                                "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                                return;
                        }
                        try {
                                minTotal = Integer.parseInt(startTotalText);
                                maxTotal = Integer.parseInt(endTotalText);

                                if (minTotal > maxTotal) {
                                        JOptionPane.showMessageDialog(null,
                                                        "Tổng tiền bắt đầu phải nhỏ hơn hoặc bằng tổng tiền kết thúc.",
                                                        "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                                        return;
                                }

                        } catch (Exception e) {

                                JOptionPane.showMessageDialog(null, "Nhập dữ liệu là số", "Thông báo",
                                                JOptionPane.INFORMATION_MESSAGE);
                                return;
                        }
                }

                orders = orderBUS.getAllWithTotal(cbStatus.getSelectedItem().toString(),
                                cbPaymentMethods.getSelectedIndex(), tsStartDate, tsEndDate,
                                minTotal, maxTotal);
                if (orders != null) {

                        loadData(orders);
                } else {
                        JOptionPane.showMessageDialog(this, "Có lỗi xảy ra!", "Lỗi", JOptionPane.ERROR_MESSAGE);

                }

        }

        private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {
                loadData();
                cbPaymentMethods.setSelectedIndex(0);
                cbStatus.setSelectedIndex(0);
                dcStartDate.setDate(null);
                dcEndDate.setDate(null);
                txtInputMinPrice.setText("");
                txtInputMaxPrice.setText("");
        }

        private void handleChangeInputSearch() {
                if (!txtInputSearch.getText().isEmpty()) {
                        DefaultTableModel dtm = (DefaultTableModel) tbTableOrder.getModel();
                        dtm.setRowCount(0);

                        int cbIndex = cbSearch.getSelectedIndex();
                        switch (cbIndex) {
                                case 0:
                                        orders = orderBUS.getAllWithTotalByOrderID(
                                                        Integer.parseInt(txtInputSearch.getText()));
                                        break;

                                case 1:
                                        orders = orderBUS.getAllWithTotalByEmp_Name(txtInputSearch.getText().trim());
                                        break;
                        }
                        loadData(orders);
                } else

                {
                        loadData();
                }
        }

        private void btnExportExcelActionPerformed(java.awt.event.ActionEvent evt) {
                if (permissionList.hasPermission("EXPORT_BILL")) {
                        exportToExcel();

                } else {
                        JOptionPane.showMessageDialog(null, "Bạn không có quyền truy cập");
                        return;
                }
        }

        private void btnViewDetailsActionPerformed(java.awt.event.ActionEvent evt) {
                int selectedRow = tbTableOrder.getSelectedRow();
                if (selectedRow != -1) {
                        int orderID = (int) tbTableOrder.getValueAt(selectedRow, 0);
                        String emp_email = (String) tbTableOrder.getValueAt(selectedRow, 1);
                        String emp_name = (String) tbTableOrder.getValueAt(selectedRow, 2);

                        for (OrderDTO order : orders.keySet()) {
                                if (order.getId() == orderID) {
                                        if (orderDetailForm != null) {
                                                orderDetailForm.dispose();
                                        }
                                        orderDetailForm = new OrderDetail(order, emp_name, emp_email);
                                        return;
                                }
                        }
                } else {
                        JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng để xem chi tiết", "Thông báo",
                                        JOptionPane.INFORMATION_MESSAGE);
                }
        }

        private javax.swing.JPanel GroupButton;
        private javax.swing.JButton btnCreate;
        private javax.swing.JButton btnEdit;
        private javax.swing.JButton btnExportExcel;
        private javax.swing.JButton btnFilter;
        private javax.swing.JButton btnRefresh;
        private javax.swing.JButton btnViewDetails;
        private javax.swing.JComboBox<String> cbSearch;
        private javax.swing.JComboBox<String> cbStatus;
        private javax.swing.JComboBox<String> cbPaymentMethods;
        private com.toedter.calendar.JDateChooser dcEndDate;
        private com.toedter.calendar.JDateChooser dcStartDate;
        private javax.swing.JPanel jPanel1;
        private javax.swing.JPanel jPanel2;
        private javax.swing.JPanel jPanel3;
        private javax.swing.JScrollPane jScrollPane1;
        private javax.swing.JSplitPane jSplitPane;
        private javax.swing.JLabel lblEndDate;
        private javax.swing.JLabel lblMaxPrice;
        private javax.swing.JLabel lblMinPrice;
        private javax.swing.JLabel lblOrderStatus;
        private javax.swing.JLabel lblPaymentMethods;
        private javax.swing.JLabel lblStartDate;
        private javax.swing.JLabel lblTitle;
        private javax.swing.JPanel pnCenter;
        private javax.swing.JPanel pnContentFilter;
        private javax.swing.JPanel pnMaxPrice;
        private javax.swing.JPanel pnMinPrice;
        private javax.swing.JPanel pnPickEndDate;
        private javax.swing.JPanel pnPickStartDate;
        private javax.swing.JPanel pnStatusOrder;
        private javax.swing.JPanel pnPaymentMethods;
        private javax.swing.JPanel pnTitle;
        private javax.swing.JTable tbTableOrder;
        private javax.swing.JTextField txtInputMaxPrice;
        private javax.swing.JTextField txtInputMinPrice;
        private javax.swing.JTextField txtInputSearch;
}
