
package com.project.GUI;

import java.awt.Cursor;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import com.project.BUS.EmployeeBUS;
import com.project.BUS.WareHouseService;
import com.project.DTO.PermissionAccount;
import com.project.DTO.WareHouse;
import com.project.Util.Formatter;

public class WareHouseMenu extends javax.swing.JPanel {
    WareHouseService wareHouseService;
    DefaultTableModel dtm;
    private PermissionAccount permissionList;
    private EmployeeBUS empBUS = new EmployeeBUS();

    public WareHouseMenu() {
        initComponents();
        permissionList = PermissionAccount.getInstance();
        wareHouseService = new WareHouseService();
        dtm = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {

                return false;
            }
        };
        TableReceipt.setModel(dtm);
        dtm.addColumn("Mã");
        dtm.addColumn("Tên nguyên liệu");
        dtm.addColumn("Đơn vị");
        dtm.addColumn("Số lượng");
        dtm.addColumn("Ngày tạo");
        dtm.addColumn("Cập nhật gần nhất");
        List<WareHouse> warehouses = null;
        try {
            warehouses = wareHouseService.getAllWareHouse();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (WareHouse warehouse : warehouses) {
            dtm.addRow(
                    new Object[] { warehouse.getId(), warehouse.getName(), warehouse.getUnit(), warehouse.getQuantity(),
                            warehouse.getCreateDate(), warehouse.getUpdateDate() });

        }
        Formatter.setBoldHeaderTable(TableReceipt);
        Formatter.centerAlignTableCells(TableReceipt);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        ReceiptTop = new javax.swing.JPanel();
        BoxBtn = new javax.swing.JPanel();
        BtnCreate = new javax.swing.JButton();
        BtnImport = new javax.swing.JButton();
        BtnExport = new javax.swing.JButton();
        BtnDetail = new javax.swing.JButton();
        BtnDelete = new javax.swing.JButton();
        BtnExportExcel = new javax.swing.JButton();
        BoxSearch = new javax.swing.JPanel();
        Filter = new javax.swing.JComboBox<>();
        BtnRefresh = new javax.swing.JButton();
        InputSearch = new javax.swing.JTextField();
        BoxTitle = new javax.swing.JPanel();
        TitleReceipt = new javax.swing.JLabel();
        ReceiptCenter = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableReceipt = new javax.swing.JTable();

        BtnCreate.setCursor(new java.awt.Cursor(Cursor.HAND_CURSOR));
        BtnDelete.setCursor(new java.awt.Cursor(Cursor.HAND_CURSOR));
        BtnDetail.setCursor(new java.awt.Cursor(Cursor.HAND_CURSOR));
        BtnExport.setCursor(new java.awt.Cursor(Cursor.HAND_CURSOR));
        BtnImport.setCursor(new java.awt.Cursor(Cursor.HAND_CURSOR));
        BtnRefresh.setCursor(new java.awt.Cursor(Cursor.HAND_CURSOR));
        BtnExportExcel.setCursor(new java.awt.Cursor(Cursor.HAND_CURSOR));

        setMinimumSize(new java.awt.Dimension(1085, 768));
        setName(""); // NOI18N
        setPreferredSize(new java.awt.Dimension(1085, 768));
        setLayout(new java.awt.BorderLayout(0, 5));

        ReceiptTop.setBackground(new java.awt.Color(255, 255, 255));
        ReceiptTop.setPreferredSize(new java.awt.Dimension(608, 160));

        BoxBtn.setBackground(new java.awt.Color(255, 255, 255));
        BoxBtn.setPreferredSize(new java.awt.Dimension(480, 40));
        BoxBtn.setLayout(new java.awt.GridLayout(1, 6, 10, 20));

        BtnCreate.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        BtnCreate.setIcon(new javax.swing.ImageIcon("./src/assets/icon/plus.png")); // NOI18N
        BtnCreate.setText("Thêm");
        BtnCreate.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnCreate.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BtnCreate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnCreateMouseClicked(evt);
            }
        });
        BtnCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCreateActionPerformed(evt);
            }
        });
        BoxBtn.add(BtnCreate);

        BtnImport.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        BtnImport.setIcon(new javax.swing.ImageIcon("./src/assets/icon/import.png")); // NOI18N
        BtnImport.setText("Nhập");
        BtnImport.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnImport.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BtnImport.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnImportMouseClicked(evt);
            }
        });
        BoxBtn.add(BtnImport);

        BtnExport.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        BtnExport.setIcon(new javax.swing.ImageIcon("./src/assets/icon/export.png")); // NOI18N
        BtnExport.setText("Xuất");
        BtnExport.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnExport.setMargin(new java.awt.Insets(2, 1, 2, 1));
        BtnExport.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BtnExport.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnExportMouseClicked(evt);
            }
        });
        // BoxBtn.add(BtnExport);

        BtnDetail.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        BtnDetail.setIcon(new javax.swing.ImageIcon("./src/assets/icon/info-rgb.png")); // NOI18N
        BtnDetail.setText("Chi tiết");
        BtnDetail.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnDetail.setMargin(new java.awt.Insets(2, 1, 2, 1));
        BtnDetail.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BtnDetail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnDetailMouseClicked(evt);
            }
        });
        BoxBtn.add(BtnDetail);

        BtnDelete.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        BtnDelete.setIcon(new javax.swing.ImageIcon("./src/assets/icon/cancel.png")); // NOI18N
        BtnDelete.setText("Xoá");
        BtnDelete.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnDelete.setMargin(new java.awt.Insets(2, 1, 2, 1));
        BtnDelete.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BtnDelete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnDeleteMouseClicked(evt);
            }
        });
        BoxBtn.add(BtnDelete);

        BtnExportExcel.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        BtnExportExcel.setIcon(new javax.swing.ImageIcon("./src/assets/icon/xls.png")); // NOI18N
        BtnExportExcel.setText("export");
        BtnExportExcel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnExportExcel.setMargin(new java.awt.Insets(2, 1, 2, 1));
        BtnExportExcel.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BtnExportExcel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnExportExcelMouseClicked(evt);
            }
        });
        BoxBtn.add(BtnExportExcel);

        BoxSearch.setBackground(new java.awt.Color(255, 255, 255));

        Filter.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        Filter.setModel(
                new javax.swing.DefaultComboBoxModel<>(new String[] { "Tìm kiếm theo mã", "Tìm kiếm theo tên" }));
        Filter.setCursor(new Cursor(Cursor.HAND_CURSOR));
        Filter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FilterActionPerformed(evt);
            }
        });

        BtnRefresh.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        BtnRefresh.setIcon(new javax.swing.ImageIcon("./src/assets/icon/refresh.png")); // NOI18N
        BtnRefresh.setText("Làm mới");
        BtnRefresh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnRefreshMouseClicked(evt);
            }
        });
        BtnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRefreshActionPerformed(evt);
            }
        });

        InputSearch.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        // InputSearch.setText("Tìm kiếm....");
        Formatter.setPlaceHolder(InputSearch, "Nhập từ khóa tìm kiếm");
        InputSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InputSearchActionPerformed(evt);
            }
        });
        InputSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfSearchKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout BoxSearchLayout = new javax.swing.GroupLayout(BoxSearch);
        BoxSearch.setLayout(BoxSearchLayout);
        BoxSearchLayout.setHorizontalGroup(
                BoxSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BoxSearchLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Filter, javax.swing.GroupLayout.PREFERRED_SIZE, 130,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(InputSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 221,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addComponent(BtnRefresh)));
        BoxSearchLayout.setVerticalGroup(
                BoxSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(BoxSearchLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(BoxSearchLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(InputSearch, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(Filter, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(BtnRefresh, javax.swing.GroupLayout.DEFAULT_SIZE, 47,
                                                Short.MAX_VALUE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        BoxTitle.setBackground(new java.awt.Color(255, 255, 255));
        BoxTitle.setLayout(new java.awt.CardLayout());

        TitleReceipt.setBackground(new java.awt.Color(255, 255, 255));
        TitleReceipt.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        TitleReceipt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TitleReceipt.setIcon(new javax.swing.ImageIcon("./src/assets/icon/warehouse-rgb.png")); // NOI18N
        TitleReceipt.setText("Quản lý kho");
        TitleReceipt.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        TitleReceipt.setIconTextGap(10);
        BoxTitle.add(TitleReceipt, "card2");

        javax.swing.GroupLayout ReceiptTopLayout = new javax.swing.GroupLayout(ReceiptTop);
        ReceiptTop.setLayout(ReceiptTopLayout);
        ReceiptTopLayout.setHorizontalGroup(
                ReceiptTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(ReceiptTopLayout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addComponent(BoxBtn, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72,
                                        Short.MAX_VALUE)
                                .addComponent(BoxSearch, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14))
                        .addComponent(BoxTitle, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        ReceiptTopLayout.setVerticalGroup(
                ReceiptTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ReceiptTopLayout.createSequentialGroup()
                                .addComponent(BoxTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 72,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(ReceiptTopLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(BoxBtn, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(BoxSearch, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap(13, Short.MAX_VALUE)));

        add(ReceiptTop, java.awt.BorderLayout.PAGE_START);

        ReceiptCenter.setLayout(new javax.swing.BoxLayout(ReceiptCenter, javax.swing.BoxLayout.LINE_AXIS));

        TableReceipt.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        TableReceipt.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {
                        { "test", "test", "test", "test", "test", null },
                        { null, null, null, null, null, null },
                        { null, null, null, null, null, null },
                        { null, null, null, null, null, null }
                },
                new String[] {
                        "ID", "Tên nguyên liệu", "Số lượng", "Đơn vị", "Ngày tạo", "Cập nhật cuối"
                }));
        TableReceipt.getTableHeader().setReorderingAllowed(false);
        TableReceipt.setShowGrid(true);
        jScrollPane1.setViewportView(TableReceipt);
        TableReceipt.setShowGrid(true);

        ReceiptCenter.add(jScrollPane1);

        add(ReceiptCenter, java.awt.BorderLayout.CENTER);
    }// </editor-fold>

    private void BtnImportMouseClicked(java.awt.event.MouseEvent evt) {
        if (empBUS.getEmpByAccountID(permissionList.getAccountId()) == null) {
            JOptionPane.showMessageDialog(null, "Tài khoản của bạn chưa cập nhật thông tin nhân viên!",
                    "Thông báo",
                    JOptionPane.INFORMATION_MESSAGE);
            return;

        }
        if (permissionList.hasPermission("CREATE_WAREHOUSE_RECEIPT")) {
            new FormCreateEnterCoupon().setVisible(true);

        } else {
            JOptionPane.showMessageDialog(null, "Bạn không có quyền truy cập");
            return;
        }
    }

    private void BtnRefreshMouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
    }

    private void BtnExportMouseClicked(java.awt.event.MouseEvent evt) {
        if (empBUS.getEmpByAccountID(permissionList.getAccountId()) == null) {
            JOptionPane.showMessageDialog(null, "Tài khoản của bạn chưa cập nhật thông tin nhân viên!",
                    "Thông báo",
                    JOptionPane.INFORMATION_MESSAGE);
            return;

        }
        if (permissionList.hasPermission("CREATE_WAREHOUSE_DISPATCH_NOTE")) {
            new FormCreateDeliveryBill().setVisible(true);

        } else {
            JOptionPane.showMessageDialog(null, "Bạn không có quyền truy cập");
            return;
        }
    }

    private void BtnDetailMouseClicked(java.awt.event.MouseEvent evt) {

        int selectedRow = TableReceipt.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn nguyên liệu muốn cập nhật");
        } else {
            int idWareHouse = Integer.parseInt(String.valueOf(TableReceipt.getValueAt(selectedRow, 0)));
            try {
                new DetailWareHouse(idWareHouse).setVisible(true);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private void BtnCreateMouseClicked(java.awt.event.MouseEvent evt) {
        if (permissionList.hasPermission("CREATE_WAREHOUSE")) {
            new CreateWareHouse().setVisible(true);

        } else {
            JOptionPane.showMessageDialog(null, "Bạn không có quyền truy cập");
            return;
        }
    }

    private void InputSearchActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void BtnRefreshActionPerformed(java.awt.event.ActionEvent evt) {
        InputSearch.setText("");
        dtm.setRowCount(0);
        try {
            List<WareHouse> wareHouses = wareHouseService.getAllWareHouse();
            for (WareHouse warehouse : wareHouses) {
                dtm.addRow(new Object[] { warehouse.getId(), warehouse.getName(), warehouse.getUnit(),
                        warehouse.getQuantity(),
                        warehouse.getCreateDate(), warehouse.getUpdateDate() });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void BtnDeleteMouseClicked(java.awt.event.MouseEvent evt) {
        if (permissionList.hasPermission("DELETE_WAREHOUSE")) {
            int selectedRow = TableReceipt.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn nguyên liệu muốn xóa");
            } else {

                int choice = JOptionPane.showConfirmDialog(this, "Bạn có thực sự muốn xóa nguyên liệu này ?");
                if (choice == JOptionPane.YES_OPTION) {
                    int idWareHouse = Integer.parseInt(String.valueOf(TableReceipt.getValueAt(selectedRow, 0)));
                    try {
                        Map<Boolean, String> respond = wareHouseService.deleteWareHouse(idWareHouse);
                        if (respond.containsKey(true)) {
                            JOptionPane.showMessageDialog(this, respond.get(true));
                            return;
                        } else {
                            JOptionPane.showMessageDialog(this, respond.get(false));
                            return;

                        }
                    } catch (Exception ex) {
                        Logger.getLogger(WareHouseMenu.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    dtm.setRowCount(0);
                    List<WareHouse> warehouses = null;
                    try {
                        warehouses = wareHouseService.getAllWareHouse();
                    } catch (Exception ex) {
                        Logger.getLogger(WareHouseMenu.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    for (WareHouse warehouse : warehouses) {
                        dtm.addRow(new Object[] { warehouse.getId(), warehouse.getName(), warehouse.getUnit(),
                                warehouse.getQuantity(),
                                warehouse.getCreateDate(),
                                warehouse.getUpdateDate() });
                    }
                }
            }

        } else {
            JOptionPane.showMessageDialog(null, "Bạn không có quyền truy cập");
            return;
        }

    }

    private void BtnExportExcelMouseClicked(java.awt.event.MouseEvent evt) {
        if (permissionList.hasPermission("EXPORT_WAREHOUSE")) {
            new ExportWareHouse().setVisible(true);

        } else {
            JOptionPane.showMessageDialog(null, "Bạn không có quyền truy cập");
            return;
        }
    }

    private void BtnCreateActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void FilterActionPerformed(java.awt.event.ActionEvent evt) {
        InputSearch.setText("");
        InputSearch.requestFocus();
        dtm.setRowCount(0);
        try {
            List<WareHouse> warehouses = wareHouseService.getAllWareHouse();
            for (WareHouse warehouse : warehouses) {
                dtm.addRow(new Object[] { warehouse.getId(), warehouse.getName(), warehouse.getUnit(),
                        warehouse.getQuantity(),
                        warehouse.getCreateDate(), warehouse.getUpdateDate() });

            }
        } catch (Exception ex) {
            Logger.getLogger(EmployeeMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void jtfSearchKeyReleased(java.awt.event.KeyEvent evt) {
        String keyword = InputSearch.getText().trim();
        try {
            List<WareHouse> warehouses = null;
            if (Filter.getSelectedItem().equals("Tìm kiếm theo mã")) {
                if (keyword.isEmpty()) {
                    warehouses = wareHouseService.getAllWareHouse();
                } else {
                    String integerPattern = "^\\d+$";
                    if (!keyword.matches(integerPattern)) {
                        JOptionPane.showMessageDialog(null, "Mã kho phải là một số");
                        InputSearch.requestFocus();
                        return;
                    }
                    int id = Integer.parseInt(keyword);
                    warehouses = wareHouseService.searchAllWareHouseById(id);
                }
            }

            else if (Filter.getSelectedItem().equals("Tìm kiếm theo tên")) {
                warehouses = wareHouseService.searchAllWareHouseByName(keyword);
            }
            dtm.setRowCount(0);
            for (WareHouse warehouse : warehouses) {
                dtm.addRow(new Object[] { warehouse.getId(), warehouse.getName(), warehouse.getUnit(),
                        warehouse.getQuantity(),
                        warehouse.getCreateDate(), warehouse.getUpdateDate() });

            }
        } catch (Exception ex) {
            Logger.getLogger(EmployeeMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Variables declaration - do not modify
    private javax.swing.JPanel BoxBtn;
    private javax.swing.JPanel BoxSearch;
    private javax.swing.JPanel BoxTitle;
    private javax.swing.JButton BtnCreate;
    private javax.swing.JButton BtnDelete;
    private javax.swing.JButton BtnDetail;
    private javax.swing.JButton BtnExport;
    private javax.swing.JButton BtnExportExcel;
    private javax.swing.JButton BtnImport;
    private javax.swing.JButton BtnRefresh;
    private javax.swing.JComboBox<String> Filter;
    private javax.swing.JTextField InputSearch;
    private javax.swing.JPanel ReceiptCenter;
    private javax.swing.JPanel ReceiptTop;
    private javax.swing.JTable TableReceipt;
    private javax.swing.JLabel TitleReceipt;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration
}
