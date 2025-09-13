
package com.project.GUI;

import java.awt.Cursor;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.project.BUS.AccountBUS;
import com.project.BUS.ActionBUS;
import com.project.BUS.UserService;
import com.project.Common.Common;
import com.project.DTO.AccountDTO;
import com.project.DTO.ActionDTO;
import com.project.DTO.PermissionAccount;
import com.project.DTO.User;

public class AddUser extends javax.swing.JFrame {
        UserService userService;
        AccountBUS accountBUS;
        ActionBUS actionBUS;
        ArrayList<AccountDTO> accountDTOs;

        public AddUser() {
                initComponents();
                setLocationRelativeTo(null);
                userService = new UserService();
                accountBUS = new AccountBUS();
                actionBUS = new ActionBUS();
                displayPosition();
        }

        private void initComponents() {
                setTitle("Thêm nhân viên");
                setResizable(false);
                jPanel1 = new javax.swing.JPanel();
                jLabel2 = new javax.swing.JLabel();
                jtfName = new javax.swing.JTextField();
                jLabel1 = new javax.swing.JLabel();
                jdcDate = new com.toedter.calendar.JDateChooser();
                jLabel3 = new javax.swing.JLabel();
                jtfAddress = new javax.swing.JTextField();
                jLabel4 = new javax.swing.JLabel();
                jtfPhone = new javax.swing.JTextField();
                jLabel5 = new javax.swing.JLabel();
                jtfSalary = new javax.swing.JTextField();
                jLabel6 = new javax.swing.JLabel();
                cbPosition = new javax.swing.JComboBox<>();
                jLabel7 = new javax.swing.JLabel();
                btnAdd = new javax.swing.JButton();
                cbEmail = new javax.swing.JComboBox<>();

                setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

                jPanel1.setBackground(new java.awt.Color(204, 204, 255));
                jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin nhân viên",
                                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                                javax.swing.border.TitledBorder.DEFAULT_POSITION,
                                new java.awt.Font("Arial", 0, 24)));

                jLabel2.setFont(new java.awt.Font("Arial", 2, 18));
                jLabel2.setText("Họ tên");

                jtfName.setFont(new java.awt.Font("Arial", 0, 14));

                jLabel1.setFont(new java.awt.Font("Arial", 2, 18));
                jLabel1.setText("Ngày sinh");

                jdcDate.setFont(new java.awt.Font("Arial", 0, 14));

                jLabel3.setFont(new java.awt.Font("Arial", 2, 18));
                jLabel3.setText("Địa chỉ ");

                jtfAddress.setFont(new java.awt.Font("Arial", 0, 14));

                jLabel4.setFont(new java.awt.Font("Arial", 0, 18));
                jLabel4.setText("Số điện thoại");

                jtfPhone.setFont(new java.awt.Font("Arial", 0, 14));

                jLabel5.setFont(new java.awt.Font("Arial", 2, 18));
                jLabel5.setText("Lương");

                jtfSalary.setFont(new java.awt.Font("Arial", 0, 14));

                jLabel6.setFont(new java.awt.Font("Arial", 2, 18));
                jLabel6.setText("Chức vụ");

                cbPosition.setFont(new java.awt.Font("Arial", 0, 16));

                if (PermissionAccount.getInstance().getRoleId() == 1) {
                        cbPosition.setModel(
                                        new javax.swing.DefaultComboBoxModel<>(
                                                        new String[] { "Admin", "Quản lý", "Nhân viên bán hàng" }));
                } else {

                        cbPosition.setModel(
                                        new javax.swing.DefaultComboBoxModel<>(
                                                        new String[] { "Quản lý", "Nhân viên bán hàng" }));

                }
                cbPosition.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                cbPositionActionPerformed(evt);
                        }
                });

                jLabel7.setFont(new java.awt.Font("Arial", 2, 18));
                jLabel7.setText("Email");

                btnAdd.setFont(new java.awt.Font("Arial Semibold", 2, 24));
                btnAdd.setText("Thêm");
                btnAdd.setCursor(new Cursor(Cursor.HAND_CURSOR));
                btnAdd.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnAddActionPerformed(evt);
                        }
                });

                cbEmail.setFont(new java.awt.Font("Arial", 0, 14));

                javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addGap(30, 30, 30)
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(jPanel1Layout
                                                                                                .createParallelGroup(
                                                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                                false)
                                                                                                .addComponent(jLabel4,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                Short.MAX_VALUE)
                                                                                                .addComponent(jLabel3,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                Short.MAX_VALUE)
                                                                                                .addComponent(jLabel2,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                Short.MAX_VALUE))
                                                                                .addComponent(jLabel6,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                87,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(18, 18, 18)
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(jPanel1Layout
                                                                                                .createSequentialGroup()
                                                                                                .addComponent(jtfName,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                173,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                                                52,
                                                                                                                Short.MAX_VALUE)
                                                                                                .addComponent(jLabel1,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                84,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addComponent(jdcDate,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                161,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addComponent(jtfAddress)
                                                                                .addGroup(jPanel1Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGroup(jPanel1Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                                                false)
                                                                                                                .addComponent(jtfPhone)
                                                                                                                .addComponent(cbPosition,
                                                                                                                                0,
                                                                                                                                171,
                                                                                                                                Short.MAX_VALUE))
                                                                                                .addGap(57, 57, 57)
                                                                                                .addGroup(jPanel1Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                                                .addComponent(jLabel5,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                66,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(jLabel7,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                66,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                .addGap(18, 18, 18)
                                                                                                .addGroup(jPanel1Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                .addComponent(jtfSalary)
                                                                                                                .addComponent(cbEmail,
                                                                                                                                0,
                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                Short.MAX_VALUE))))
                                                                .addGap(38, 38, 38))
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout
                                                                .createSequentialGroup()
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addComponent(btnAdd,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                126,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(245, 245, 245)));
                jPanel1Layout.setVerticalGroup(
                                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addGap(28, 28, 28)
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                jPanel1Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                                .addComponent(jLabel2,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                35,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(jtfName,
                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                35,
                                                                                                                                Short.MAX_VALUE)
                                                                                                                .addComponent(jLabel1,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                35,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                jPanel1Layout.createSequentialGroup()
                                                                                                                .addGap(0, 0, Short.MAX_VALUE)
                                                                                                                .addComponent(jdcDate,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                35,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                .addGap(43, 43, 43)
                                                                .addGroup(jPanel1Layout
                                                                                .createParallelGroup(
                                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                false)
                                                                                .addComponent(jLabel3,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                35,
                                                                                                Short.MAX_VALUE)
                                                                                .addComponent(jtfAddress))
                                                                .addGap(41, 41, 41)
                                                                .addGroup(jPanel1Layout
                                                                                .createParallelGroup(
                                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                false)
                                                                                .addComponent(jLabel4,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                35,
                                                                                                Short.MAX_VALUE)
                                                                                .addComponent(jLabel5)
                                                                                .addComponent(jtfPhone,
                                                                                                javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                .addComponent(jtfSalary,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                35,
                                                                                                Short.MAX_VALUE))
                                                                .addGap(40, 40, 40)
                                                                .addGroup(jPanel1Layout
                                                                                .createParallelGroup(
                                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                false)
                                                                                .addComponent(jLabel6,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                35,
                                                                                                Short.MAX_VALUE)
                                                                                .addComponent(cbPosition,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                35,
                                                                                                Short.MAX_VALUE)
                                                                                .addComponent(jLabel7)
                                                                                .addComponent(cbEmail))
                                                                .addGap(50, 50, 50)
                                                                .addComponent(btnAdd,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                48,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap(33, Short.MAX_VALUE)));

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                                .addGap(23, 23, 23)
                                                                .addComponent(jPanel1,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap(31, Short.MAX_VALUE)));
                layout.setVerticalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                                .addGap(27, 27, 27)
                                                                .addComponent(jPanel1,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap(33, Short.MAX_VALUE)));

                pack();
        }

        private void displayPosition() {
                int roleID = PermissionAccount.getInstance().getRoleId();
                accountDTOs = accountBUS.getAll_unused(roleID + 1);
                cbEmail.removeAllItems();
                for (AccountDTO acc : accountDTOs) {
                        cbEmail.addItem(acc.getEmail());
                }
                // cbEmail.addItem(accountDTO.getEmail());
        }

        private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {
                if (jtfName.getText().trim().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Họ tên không được để trống!", "Thông báo",
                                        JOptionPane.INFORMATION_MESSAGE);
                        jtfName.requestFocus();
                        return;
                }
                if (jdcDate.getDate() == null) {
                        JOptionPane.showMessageDialog(null, "Ngày sinh không được để trống!", "Thông báo",
                                        JOptionPane.INFORMATION_MESSAGE);
                        return;
                }
                if (jtfAddress.getText().trim().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Địa chỉ không được để trống!", "Thông báo",
                                        JOptionPane.INFORMATION_MESSAGE);
                        jtfAddress.requestFocus();
                        return;
                }
                if (!Common.validateVietnamesePhoneNumber(jtfPhone.getText())) {
                        JOptionPane.showMessageDialog(null, "Số điện thoại không hợp lệ!", "Thông báo",
                                        JOptionPane.INFORMATION_MESSAGE);
                        jtfPhone.requestFocus();
                        return;
                }
                try {
                        Double salary = Double.parseDouble(jtfSalary.getText());
                        if (salary <= 0) {
                                JOptionPane.showMessageDialog(null, "Số tiền lương không hợp lệ!", "Thông báo",
                                                JOptionPane.INFORMATION_MESSAGE);
                                jtfSalary.requestFocus();
                                return;
                        }
                } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Số tiền lương không hợp lệ!", "Thông báo",
                                        JOptionPane.INFORMATION_MESSAGE);
                        jtfSalary.requestFocus();
                        return;
                }
                if (cbEmail.getSelectedItem() == null) {
                        JOptionPane.showMessageDialog(null, "Vui lòng chọn email!", "Thông báo",
                                        JOptionPane.INFORMATION_MESSAGE);
                        return;
                }

                int choice = JOptionPane.showConfirmDialog(null, "Xác nhận thêm nhân viên", "Xác nhận",
                                JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                        User user = new User();
                        user.setName(jtfName.getText());
                        user.setDate(Common.formateDate(jdcDate.getDate()));
                        user.setAddress(jtfAddress.getText());
                        user.setPosition((String) cbPosition.getSelectedItem());
                        user.setPhone(jtfPhone.getText());
                        user.setSalary(Float.parseFloat(jtfSalary.getText()));
                        System.out.println("hello");
                        int roleID = cbPosition.getSelectedIndex() == 0 ? 1 : 3;
                        accountDTOs = accountBUS.getAll_unused_1(roleID);
                        for (AccountDTO acc : accountDTOs) {
                                System.out.println(acc.getId());

                                if (acc.getEmail().equals(cbEmail.getSelectedItem())) {
                                        user.setAccountId(acc.getId());
                                        break;
                                }
                        }
                        boolean rs = userService.addUser(user);
                        if (rs) {
                                JOptionPane.showMessageDialog(null, "Thêm nhân viên thành công", "Thông báo",
                                                JOptionPane.INFORMATION_MESSAGE);
                                new EmployeeMenu().setVisible(true);
                                this.dispose();
                        } else {
                                JOptionPane.showMessageDialog(null, "Thêm thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        }
                        System.out.println("fail");

                }
        }

        private void cbPositionActionPerformed(java.awt.event.ActionEvent evt) {
                try {

                        ArrayList<AccountDTO> accountDTOs = new ArrayList<>();
                        int roleID = cbPosition.getSelectedIndex();
                        if (PermissionAccount.getInstance().getRoleId() == 1) {
                                accountDTOs = accountBUS.getAll_unused(roleID + 1);

                        } else {

                                accountDTOs = accountBUS.getAll_unused(roleID + 2);
                        }
                        cbEmail.removeAllItems();
                        for (AccountDTO acc : accountDTOs) {
                                cbEmail.addItem(acc.getEmail());
                        }
                } catch (Exception e) {
                        e.printStackTrace();
                }
        }

        private javax.swing.JLabel jLabel1;
        private javax.swing.JLabel jLabel2;
        private javax.swing.JLabel jLabel3;
        private javax.swing.JLabel jLabel4;
        private javax.swing.JLabel jLabel5;
        private javax.swing.JLabel jLabel6;
        private javax.swing.JLabel jLabel7;
        private javax.swing.JPanel jPanel1;
        private javax.swing.JButton btnAdd;
        private javax.swing.JComboBox<String> cbEmail;
        private javax.swing.JComboBox<String> cbPosition;
        private com.toedter.calendar.JDateChooser jdcDate;
        private javax.swing.JTextField jtfAddress;
        private javax.swing.JTextField jtfName;
        private javax.swing.JTextField jtfPhone;
        private javax.swing.JTextField jtfSalary;
}
