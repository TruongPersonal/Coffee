
package com.project.GUI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.itextpdf.text.Font;
import com.project.BUS.AccountBUS;
import com.project.BUS.UserService;
import com.project.DTO.AccountDTO;
import com.project.DTO.PermissionAccount;
import com.project.DTO.User;
import com.project.Util.Formatter;

public class EmployeeMenu extends javax.swing.JPanel {
    UserService userservice;
    DefaultTableModel dtm;
    private PermissionAccount permissionList;
    AccountBUS accountBUS;
    AccountDTO accountDTO;

    public EmployeeMenu() {
        initComponents();
        permissionList = PermissionAccount.getInstance();
        accountDTO = new AccountDTO();
        accountBUS = new AccountBUS();
        userservice = new UserService();
        userservice = new UserService();
        dtm = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        dtm.addColumn("Mã");
        dtm.addColumn("Họ và tên");
        dtm.addColumn("Ngày sinh");
        dtm.addColumn("Địa chỉ");
        dtm.addColumn("Chức vụ");
        dtm.addColumn("Số điện thoại");
        dtm.addColumn("Lương");
        dtm.addColumn("Ngày tạo");
        dtm.addColumn("Tài khoản");
        jTable1.setModel(dtm);

        loadDataTable(userservice.getAllUser());
    }

    public void loadDataTable(List<User> users) {
        dtm.setRowCount(0);
        for (User user : users) {
            accountDTO = accountBUS.getIdAccountUser(user.getId());
            String email = accountDTO.getEmail();
            dtm.addRow(new Object[] { user.getId(), user.getName(), user.getDate(), user.getAddress(),
                    user.getPosition(), user.getPhone(), com.project.Util.Formatter.getFormatedPrice(user.getSalary()),
                    user.getDateCreate(),
                    email });
        }
        Formatter.setBoldHeaderTable(jTable1);
        Formatter.setBoldHeaderTable(jTable1);
        Formatter.centerAlignTableCells(jTable1);
    }

    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jLabel5 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnAdd = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnImport = new javax.swing.JButton();
        btnExport = new javax.swing.JButton();
        btnPrint = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        cbSelect = new javax.swing.JComboBox<>();
        inputSearch = new javax.swing.JTextField();
        btnRefresh = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setMinimumSize(new java.awt.Dimension(1085, 768));
        setPreferredSize(new java.awt.Dimension(1085, 768));
        setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("Arial", Font.BOLD, 24));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon("./src/assets/icon/employees.png"));
        jLabel5.setText("Quản lí nhân viên");
        jLabel5.setToolTipText("");
        // jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel5.setIconTextGap(10);
        // jLabel5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel5.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));

        jPanel1.setLayout(new java.awt.GridBagLayout());

        btnAdd.setFont(new java.awt.Font("Arial", 0, 14));
        btnAdd.setIcon(new javax.swing.ImageIcon("./src/assets/icon/plus.png"));
        btnAdd.setText("Thêm");
        btnAdd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdd.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAdd.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnEdit.setFont(new java.awt.Font("Arial", 0, 14));
        btnEdit.setIcon(new javax.swing.ImageIcon("./src/assets/icon/edit_user.png"));
        btnEdit.setText("Sửa");
        btnEdit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEdit.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEdit.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnDelete.setFont(new java.awt.Font("Arial", 0, 14));
        btnDelete.setIcon(new javax.swing.ImageIcon("./src/assets/icon/cancel.png"));
        btnDelete.setText("Xóa");
        btnDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDelete.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDelete.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnImport.setFont(new java.awt.Font("Arial", 0, 14));
        btnImport.setIcon(new javax.swing.ImageIcon("./src/assets/icon/import_user.png"));
        btnImport.setText("Nhập");
        btnImport.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnImport.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnImport.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnImport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImportActionPerformed(evt);
            }
        });

        btnExport.setFont(new java.awt.Font("Arial", 0, 14));
        btnExport.setIcon(new javax.swing.ImageIcon("./src/assets/icon/export_user.png"));
        btnExport.setText("Xuất");
        btnExport.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnExport.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnExport.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportActionPerformed(evt);
            }
        });

        btnPrint.setFont(new java.awt.Font("Arial", 0, 14));
        btnPrint.setIcon(new javax.swing.ImageIcon("./src/assets/icon/pdf_user.png"));
        btnPrint.setText("In");
        btnPrint.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPrint.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnPrint.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(btnAdd)
                                .addGap(18, 18, 18)
                                .addComponent(btnEdit)
                                .addGap(18, 18, 18)
                                .addComponent(btnDelete)
                                .addGap(18, 18, 18)
                                .addComponent(btnImport)
                                .addGap(18, 18, 18)
                                .addComponent(btnExport)
                                .addGap(18, 18, 18)
                                .addComponent(btnPrint)
                                .addContainerGap(26, Short.MAX_VALUE)));
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btnAdd, javax.swing.GroupLayout.Alignment.TRAILING,
                                                javax.swing.GroupLayout.PREFERRED_SIZE, 72,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnEdit, javax.swing.GroupLayout.Alignment.TRAILING,
                                                javax.swing.GroupLayout.PREFERRED_SIZE, 72,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnDelete, javax.swing.GroupLayout.Alignment.TRAILING,
                                                javax.swing.GroupLayout.PREFERRED_SIZE, 72,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnImport, javax.swing.GroupLayout.Alignment.TRAILING,
                                                javax.swing.GroupLayout.PREFERRED_SIZE, 72,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnExport, javax.swing.GroupLayout.Alignment.TRAILING,
                                                javax.swing.GroupLayout.PREFERRED_SIZE, 72,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnPrint, javax.swing.GroupLayout.Alignment.TRAILING,
                                                javax.swing.GroupLayout.PREFERRED_SIZE, 72,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))));
        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 20;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel1.add(jPanel2, gridBagConstraints);
        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        cbSelect.setFont(new java.awt.Font("Arial", 0, 14));
        cbSelect.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cbSelect.setModel(new javax.swing.DefaultComboBoxModel<>(
                new String[] { "Tìm kiếm theo mã", "Tìm kiếm theo tên", "Tìm kiếm theo số điện thoại" }));
        cbSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbSelectActionPerformed(evt);
            }
        });

        inputSearch.setFont(new java.awt.Font("Arial", 0, 14));
        Formatter.setPlaceHolder(inputSearch, "Nhập từ khóa tìm kiếm");
        inputSearch.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                handleChangeInputSearch();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                handleChangeInputSearch();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                handleChangeInputSearch();
            }

        });

        btnRefresh.setFont(new java.awt.Font("Arial", 0, 14));
        btnRefresh.setIcon(new javax.swing.ImageIcon("./src/assets/icon/refresh.png"));
        btnRefresh.setText("Làm mới");
        btnRefresh.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnRefresh.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });
        btnRefresh.setCursor(new java.awt.Cursor(Cursor.HAND_CURSOR));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(cbSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 202,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(inputSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 160,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnRefresh)
                                .addContainerGap(21, Short.MAX_VALUE)));
        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addGroup(jPanel3Layout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(cbSelect)
                                        .addComponent(inputSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 38,
                                                Short.MAX_VALUE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 72,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)));

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 6, 0, 0);
        jPanel1.add(jPanel3, gridBagConstraints);

        jTable1.setFont(new java.awt.Font("Arial", 0, 13));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {
                        { null, null, null, null },
                        { null, null, null, null },
                        { null, null, null, null },
                        { null, null, null, null }
                },
                new String[] {
                        "Title 1", "Title 2", "Title 3", "Title 4"
                }));
        jTable1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTable1.setRowHeight(40);
        jTable1.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                double[] columnPercentages = { 0.05, 0.1, 0.1, 0.15, 0.125, 0.1, 0.1, 0.15, 0.125 }; // Phần trăm độ
                                                                                                     // rộng
                                                                                                     // cho từng cột
                setColumnWidths(jTable1, columnPercentages);
            }
        });
        jTable1.setShowGrid(true);
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING,
                                javax.swing.GroupLayout.DEFAULT_SIZE, 1085, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1)
                                .addContainerGap()));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 70,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 622,
                                        Short.MAX_VALUE)));
    }

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {
        if (permissionList.hasPermission("CREATE_EMPLOYEE")) {
            new AddUser().setVisible(true);

        } else {
            JOptionPane.showMessageDialog(null, "Bạn không có quyền truy cập");
            return;
        }
    }

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {
        if (permissionList.hasPermission("UPDATE_EMPLOYEE")) {
            int selectedRow = jTable1.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn nhân viên muốn cập nhật");
            } else {

                String position = String.valueOf(jTable1.getValueAt(selectedRow, 4));
                if (PermissionAccount.getInstance().getRoleId() == 2 && position.equals("Admin")) {
                    JOptionPane.showMessageDialog(null, "Bạn không thể chỉnh sửa nhân viên có quyền cao hơn.");
                    return;
                }

                int userId = Integer.parseInt(String.valueOf(jTable1.getValueAt(selectedRow, 0)));
                System.out.println(userId);
                try {
                    new EditUser(userId).setVisible(true);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }

        } else {
            JOptionPane.showMessageDialog(null, "Bạn không có quyền truy cập");
            return;
        }

    }

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {
        if (permissionList.hasPermission("DELETE_EMPLOYEE")) {
            int selectedRow = jTable1.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn nhân viên muốn xóa");
            } else {
                int choice = JOptionPane.showConfirmDialog(this, "Bạn có thực sự muốn xóa nhân viên này ?");
                if (choice == JOptionPane.YES_OPTION) {
                    int userId = Integer.parseInt(String.valueOf(jTable1.getValueAt(selectedRow, 0)));
                    if (userservice.deleteUser(userId)) {
                        JOptionPane.showMessageDialog(null, "Xóa nhân viên thành công!", "Thông báo",
                                JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Có lỗi xảy ra!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                    loadDataTable(userservice.getAllUser());
                }
            }

        } else {
            JOptionPane.showMessageDialog(null, "Bạn không có quyền truy cập");
            return;
        }

    }

    private void btnImportActionPerformed(java.awt.event.ActionEvent evt) {
        if (permissionList.hasPermission("IMPORT_EMPLOYEE")) {

        } else {
            JOptionPane.showMessageDialog(null, "Bạn không có quyền truy cập");
            return;
        }
        File excelFile;
        FileInputStream excelFIS = null;
        BufferedInputStream excelBIS = null;
        XSSFWorkbook excelJTableImport = null;
        String defaultCurrentDirectoryPath = "";
        JFileChooser excelFileChooser = new JFileChooser(defaultCurrentDirectoryPath);
        FileNameExtensionFilter fnef = new FileNameExtensionFilter("EXCEL FILES",
                "xls", "xlsx", "xlsm");
        excelFileChooser.setFileFilter(fnef);
        excelFileChooser.setDialogTitle("Select Excel File");
        int excelChooser = excelFileChooser.showOpenDialog(null);
        if (excelChooser == JFileChooser.APPROVE_OPTION) {
            try {
                excelFile = excelFileChooser.getSelectedFile();
                excelFIS = new FileInputStream(excelFile);
                excelBIS = new BufferedInputStream(excelFIS);
                excelJTableImport = new XSSFWorkbook(excelBIS);
                XSSFSheet excelSheet = excelJTableImport.getSheetAt(0);
                for (int row = 1; row <= excelSheet.getLastRowNum(); row++) {
                    XSSFRow excelRow = excelSheet.getRow(row);

                    if (excelRow == null) {
                        JOptionPane.showMessageDialog(null, "Import file excel thành công!", "Thông báo",
                                JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }

                    XSSFCell excelName = excelRow.getCell(1);
                    XSSFCell excelDate = excelRow.getCell(2);
                    System.out.println(excelDate);
                    XSSFCell excelAddress = excelRow.getCell(3);
                    XSSFCell excelPosition = excelRow.getCell(4);
                    XSSFCell excelPhone = excelRow.getCell(5);
                    XSSFCell excelSalary = excelRow.getCell(6);
                    XSSFCell excelEmailAccount = excelRow.getCell(7);
                    System.out.println(excelEmailAccount);
                    XSSFCell excelPasswordAccount = excelRow.getCell(8);
                    String dateRegex = "\\d{1,2}/\\d{1,2}/\\d{4}";

                    if (excelDate.getCellType() == CellType.STRING) {
                        String dateString = excelDate.getStringCellValue();
                        if (!dateString.matches(dateRegex)) {
                            JOptionPane.showMessageDialog(this, "Ngày sinh không hợp lệ! Vui lòng kiểm tra lại.");
                            return;
                        }
                    }
                    System.out.println(excelPasswordAccount);

                    SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
                    String dateFormat = sdfDate.format(excelDate.getDateCellValue());
                    User user = new User();
                    user.setName(excelName.getStringCellValue());
                    user.setDate(dateFormat);
                    System.out.println(dateFormat);
                    user.setAddress(excelAddress.getStringCellValue());
                    user.setPosition(excelPosition.getStringCellValue());
                    String phone = "";
                    String PHONE_REGEX = "^0\\d{9,10}$";
                    if (excelPhone != null) {
                        if (excelPhone.getCellType() == CellType.STRING) {
                            // Nếu ô là kiểu chuỗi
                            phone = excelPhone.getStringCellValue();
                            // Nếu ô là kiểu số, chuyển đổi thành chuỗi trước khi gán cho user
                        } else if (excelPhone.getCellType() == CellType.NUMERIC) {
                            phone = String.valueOf((int) excelPhone.getNumericCellValue());
                        }

                    }
                    if (!phone.matches(PHONE_REGEX)) {
                        JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ! Vui lòng kiểm tra lại.");
                        return;
                    }

                    String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,}$";
                    String checkEmail = excelEmailAccount.getStringCellValue();
                    if (!checkEmail.matches(emailRegex)) {
                        JOptionPane.showMessageDialog(this, "Email không hợp lệ! Vui lòng kiểm tra lại.");
                        return;
                    }
                    user.setPhone(phone);
                    System.out.println(phone);
                    user.setSalary((float) (excelSalary.getNumericCellValue()));
                    boolean check = AccountBUS.createAccountBUS(new AccountDTO(excelEmailAccount.getStringCellValue(),
                            excelPasswordAccount.getStringCellValue(), 3));
                    if (check) {
                        System.out.println(check);
                    } else {
                        System.out.println("fail");
                        JOptionPane.showMessageDialog(this, "Tạo tài khoản thất bại");
                        return;
                    }
                    int idAccount = accountBUS.getLastAccountId();
                    user.setAccountId(idAccount);

                    System.out.println(excelSalary.getNumericCellValue());
                    userservice.addUser(user);

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private void btnExportActionPerformed(java.awt.event.ActionEvent evt) {
        if (permissionList.hasPermission("EXPORT_EMPLOYEE")) {

            new ExportUser().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Bạn không có quyền truy cập");
            return;
        }
    }

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {
        if (permissionList.hasPermission("EXPORT_EMPLOYEE")) {

            new PrintUser().setVisible(true);

        } else {
            JOptionPane.showMessageDialog(null, "Bạn không có quyền truy cập");
            return;
        }
    }

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {
        List<User> users = null;
        inputSearch.setText("");
        users = userservice.getAllUser();
        if (users != null && !users.isEmpty()) {
            loadDataTable(users);
        } else {
            JOptionPane.showMessageDialog(null, "Có lỗi xảy ra!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cbSelectActionPerformed(java.awt.event.ActionEvent evt) {
        inputSearch.setText("");
        inputSearch.requestFocus();
        List<User> users = userservice.getAllUser();
        loadDataTable(users);
    }

    private void handleChangeInputSearch() {
        String keyword = inputSearch.getText().trim();
        List<User> users = null;
        if (cbSelect.getSelectedItem().equals("Tìm kiếm theo mã")) {
            if (keyword.isEmpty()) {
                users = userservice.getAllUser();
            } else {
                String integerPattern = "^\\d+$";
                if (!keyword.matches(integerPattern)) {
                    JOptionPane.showMessageDialog(null, "Mã nhân viên phải là một số");
                    inputSearch.requestFocus();
                    return;
                }

                int id = Integer.parseInt(keyword);
                users = userservice.searchAllUserById(id);
            }
        } else if (cbSelect.getSelectedItem().equals("Tìm kiếm theo tên")) {
            users = userservice.searchAllUserByName(keyword);
        } else if (cbSelect.getSelectedItem().equals("Tìm kiếm theo số điện thoại")) {
            users = userservice.searchAllUserByPhone(keyword);
        }

        loadDataTable(users);
    }

    private void setColumnWidths(JTable table, double[] percentages) {
        if (table == null || percentages == null || table.getColumnModel().getColumnCount() != percentages.length) {
            return; // Bảng hoặc mảng tỉ lệ không hợp lệ
        }

        TableColumnModel columnModel = table.getColumnModel();
        int totalWidth = table.getWidth();
        for (int i = 0; i < percentages.length; i++) {
            int width = (int) (totalWidth * percentages[i]); // Tính toán độ rộng dựa trên tỉ lệ phần trăm
            columnModel.getColumn(i).setPreferredWidth(width);
        }

    }

    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnExport;
    private javax.swing.JButton btnImport;
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JComboBox<String> cbSelect;
    private javax.swing.JTextField inputSearch;
}
