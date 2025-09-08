
package com.project.GUI;

import com.project.BUS.*;
import com.project.DTO.*;
import com.project.DAO.*;
import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.border.Border;

public class CreateWareHouse extends javax.swing.JFrame {
        WareHouseService wareHouseService;

        public CreateWareHouse() {
                initComponents();
                wareHouseService = new WareHouseService();

                jtfUInit.addItem("gram");
                jtfUInit.addItem("ml");

                jtfQuantity.setEnabled(false);
        }

        @SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated
        // Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                jPanel4 = new javax.swing.JPanel();
                jLabel1 = new javax.swing.JLabel();
                jtfName = new javax.swing.JTextField();
                jLabel4 = new javax.swing.JLabel();
                jLabel5 = new javax.swing.JLabel();
                jtfQuantity = new javax.swing.JTextField();
                jtfUInit = new javax.swing.JComboBox<>();
                jbAdd = new javax.swing.JButton();

                setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
                setBackground(new java.awt.Color(153, 153, 255));

                jPanel4.setBackground(new java.awt.Color(204, 204, 255));
                jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tạo mới nguyên liệu",
                                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                                javax.swing.border.TitledBorder.DEFAULT_POSITION,
                                new java.awt.Font("Segoe UI Semibold", 2, 28))); // NOI18N

                jLabel1.setFont(new java.awt.Font("Microsoft Sans Serif", 2, 18)); // NOI18N
                jLabel1.setText("Tên nguyên liệu");

                jtfName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jtfName.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                jtfNameActionPerformed(evt);
                        }
                });

                jLabel4.setFont(new java.awt.Font("Microsoft Sans Serif", 2, 18)); // NOI18N
                jLabel4.setText("Đơn vị");

                jLabel5.setFont(new java.awt.Font("Microsoft Sans Serif", 2, 18)); // NOI18N
                jLabel5.setText("Số lượng");

                jtfQuantity.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jtfQuantity.setText("0");
                jtfQuantity.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                jtfQuantityActionPerformed(evt);
                        }
                });

                jtfUInit.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                jtfUInit.setPreferredSize(new java.awt.Dimension(60, 20));
                // jtfUInit.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                // jtfUInit.setText("gram");
                // jtfUInit.setEnabled(false);

                jbAdd.setFont(new java.awt.Font("Segoe UI Emoji", 1, 18)); // NOI18N
                jbAdd.setText("Thêm");
                jbAdd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                jbAdd.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                jbAddActionPerformed(evt);
                        }
                });

                javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
                jPanel4.setLayout(jPanel4Layout);
                jPanel4Layout.setHorizontalGroup(
                                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel4Layout.createSequentialGroup()
                                                                .addGap(22, 22, 22)
                                                                .addGroup(jPanel4Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(jPanel4Layout
                                                                                                .createSequentialGroup()
                                                                                                .addComponent(jLabel4,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                62,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                .addComponent(jtfUInit,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                110,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(45, 45, 45)
                                                                                                .addComponent(jLabel5,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                77,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(18, 18, 18)
                                                                                                .addComponent(jtfQuantity,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                110,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addContainerGap(
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                Short.MAX_VALUE))
                                                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                jPanel4Layout
                                                                                                                .createSequentialGroup()
                                                                                                                .addComponent(jLabel1,
                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                Short.MAX_VALUE)
                                                                                                                .addGap(18, 18, 18)
                                                                                                                .addGroup(jPanel4Layout
                                                                                                                                .createParallelGroup(
                                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                                .addComponent(jtfName,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                265,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                .addComponent(jbAdd,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                97,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                                .addGap(22, 22, 22)))));
                jPanel4Layout.setVerticalGroup(
                                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel4Layout.createSequentialGroup()
                                                                .addGap(55, 55, 55)
                                                                .addGroup(jPanel4Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jLabel1,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                35,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(jtfName,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                35,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(52, 52, 52)
                                                                .addGroup(jPanel4Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(jLabel4,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                35,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addGroup(jPanel4Layout
                                                                                                .createParallelGroup(
                                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                .addComponent(jtfUInit,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                38,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addComponent(jLabel5,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                Short.MAX_VALUE)
                                                                                                .addComponent(jtfQuantity,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                35,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                39,
                                                                                Short.MAX_VALUE)
                                                                .addComponent(jbAdd,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                46,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(37, 37, 37)));

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                                .addGap(33, 33, 33)
                                                                .addComponent(jPanel4,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap(39, Short.MAX_VALUE)));
                layout.setVerticalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                                .addGap(26, 26, 26)
                                                                .addComponent(jPanel4,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap(39, Short.MAX_VALUE)));

                pack();
        }// </editor-fold>//GEN-END:initComponents

        private void jtfQuantityActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jtfQuantityActionPerformed
                // TODO add your handling code here:
        }// GEN-LAST:event_jtfQuantityActionPerformed

        public boolean isNumeric(String str) {
                return str.matches("-?[0-9]+");
        }

        private void jbAddActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jbAddActionPerformed
                try {
                        Border redBorder = BorderFactory.createLineBorder(Color.RED);
                        WareHouse warehouse = new WareHouse();
                        warehouse.setName(jtfName.getText());
                        warehouse.setUnit((String) jtfUInit.getSelectedItem());
                        if (wareHouseService.WareHouseExist(warehouse.getName()) || jtfName.getText().isEmpty()
                                        || jtfQuantity.getText().isEmpty() ||
                                        !isNumeric(jtfQuantity.getText())
                                        || Integer.parseInt(jtfQuantity.getText()) < 0) {
                                if (jtfName.getText().isEmpty()) {
                                        JOptionPane.showMessageDialog(null,
                                                        "Tên nguyên liệu không được để trống. Không thể tạo nguyên liệu mới.");
                                        jtfName.requestFocus();
                                        jtfName.setBorder(redBorder);
                                        if (!jtfQuantity.getText().isEmpty() || isNumeric(jtfQuantity.getText())
                                                        || Integer.parseInt(jtfQuantity.getText()) >= 0) {
                                                jtfQuantity.setBorder(null);
                                        }
                                        return;
                                } else {
                                        jtfName.setBorder(null);

                                }
                                if (wareHouseService.WareHouseExist(warehouse.getName())) {
                                        JOptionPane.showMessageDialog(null,
                                                        "Tên nguyên liệu đã tồn tại trong cơ sở dữ liệu. Không thể tạo nguyên liệu mới.");
                                        jtfName.requestFocus();
                                        jtfName.setBorder(redBorder);
                                        if (!jtfQuantity.getText().isEmpty() || isNumeric(jtfQuantity.getText())
                                                        || Integer.parseInt(jtfQuantity.getText()) >= 0) {
                                                jtfQuantity.setBorder(null);
                                        }
                                        return;
                                } else {
                                        jtfName.setBorder(null);
                                }
                                if (jtfQuantity.getText().isEmpty()) {
                                        JOptionPane.showMessageDialog(null,
                                                        "Số lượng nguyên liệu không được rỗng. Không thể tạo nguyên liệu mới.");
                                        jtfQuantity.requestFocus();
                                        jtfQuantity.setBorder(redBorder);
                                        return;
                                } else {
                                        jtfQuantity.setBorder(null);
                                }
                                if (!isNumeric(jtfQuantity.getText())) {
                                        JOptionPane.showMessageDialog(null,
                                                        "Số lượng nguyên liệu phải là một số. Không thể tạo nguyên liệu mới.");
                                        jtfQuantity.requestFocus();
                                        jtfQuantity.setBorder(redBorder);
                                        return;
                                } else {
                                        jtfQuantity.setBorder(null);
                                }
                                if (Integer.parseInt(jtfQuantity.getText()) < 0) {
                                        JOptionPane.showMessageDialog(null,
                                                        "Số lượng nguyên liệu không được nhỏ hơn 0. Không thể tạo nguyên liệu mới.");
                                        jtfQuantity.requestFocus();
                                        jtfQuantity.setBorder(redBorder);
                                        return;
                                } else {
                                        jtfQuantity.setBorder(null);
                                }
                        } else {
                                warehouse.setQuantity(Integer.parseInt(jtfQuantity.getText()));
                                wareHouseService.AddWareHouse(warehouse);
                                JOptionPane.showMessageDialog(null, "Tạo nguyên liệu thành công.");
                                this.setVisible(false);
                                return;
                        }
                } catch (Exception ex) {
                        Logger.getLogger(CreateWareHouse.class.getName()).log(Level.SEVERE, null, ex);
                }

        }// GEN-LAST:event_jbAddActionPerformed

        private void jtfNameActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jtfNameActionPerformed
                // TODO add your handling code here:
        }// GEN-LAST:event_jtfNameActionPerformed

        /**
         * @param args the command line arguments
         */
        public static void main(String args[]) {
                /* Set the Nimbus look and feel */
                // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
                // (optional) ">
                /*
                 * If Nimbus (introduced in Java SE 6) is not available, stay with the default
                 * look and feel.
                 * For details see
                 * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
                 */
                try {
                        for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
                                        .getInstalledLookAndFeels()) {
                                if ("Nimbus".equals(info.getName())) {
                                        javax.swing.UIManager.setLookAndFeel(info.getClassName());
                                        break;
                                }
                        }
                } catch (ClassNotFoundException ex) {
                        java.util.logging.Logger.getLogger(CreateWareHouse.class.getName()).log(
                                        java.util.logging.Level.SEVERE,
                                        null, ex);
                } catch (InstantiationException ex) {
                        java.util.logging.Logger.getLogger(CreateWareHouse.class.getName()).log(
                                        java.util.logging.Level.SEVERE,
                                        null, ex);
                } catch (IllegalAccessException ex) {
                        java.util.logging.Logger.getLogger(CreateWareHouse.class.getName()).log(
                                        java.util.logging.Level.SEVERE,
                                        null, ex);
                } catch (javax.swing.UnsupportedLookAndFeelException ex) {
                        java.util.logging.Logger.getLogger(CreateWareHouse.class.getName()).log(
                                        java.util.logging.Level.SEVERE,
                                        null, ex);
                }
                // </editor-fold>
                // </editor-fold>

                /* Create and display the form */
                java.awt.EventQueue.invokeLater(new Runnable() {
                        public void run() {
                                new CreateWareHouse().setVisible(true);
                        }
                });
        }

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JLabel jLabel1;
        private javax.swing.JLabel jLabel4;
        private javax.swing.JLabel jLabel5;
        private javax.swing.JPanel jPanel4;
        private javax.swing.JButton jbAdd;
        private javax.swing.JTextField jtfName;
        private javax.swing.JTextField jtfQuantity;
        private javax.swing.JComboBox<String> jtfUInit;
        // End of variables declaration//GEN-END:variables
}
