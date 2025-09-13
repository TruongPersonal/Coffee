package com.project.GUI;

import java.awt.Color;
import java.awt.Cursor;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.project.BUS.ProductBUS;
import com.project.BUS.RecipeBUS;
import com.project.Common.Common;
import com.project.DTO.DetailRecipeDTO;
import com.project.DTO.PermissionAccount;
import com.project.DTO.ProductDTO;

public class DetailProduct extends javax.swing.JFrame {
    private PermissionAccount permissionList;

    /**
     * Creates new form DetailProduct
     */
    public DetailProduct(ProductDTO data) {
        initComponents();
        permissionList = PermissionAccount.getInstance();
        ArrayList<DetailRecipeDTO> detail_recipe = RecipeBUS.getDetailRecipeByProductId(data.getId());
        loadDataDetailProduct(data, detail_recipe);
    }

    private void loadDataDetailProduct(ProductDTO data, ArrayList<DetailRecipeDTO> detail_recipe) {
        jTextField1.setText(String.valueOf(data.getId()));
        jTextField3.setText(String.valueOf(data.getPrice()));
        jTextField2.setText(String.valueOf(data.getProduct_name()));
        jTextField4.setText(String.valueOf(data.getQuantity()));
        jTextField5.setText(String.valueOf(Common.formatedDateTime(data.getCreatedAt())));
        jTextField7.setText(String.valueOf(Common.formatedDateTime(data.getUpdatedAt())));
        jComboBox1.setSelectedItem(String.valueOf(data.getCategory_name()));
        jTextField6.setText(String.valueOf(data.getUrl_image()));
        jToggleButton1.setIcon(new javax.swing.ImageIcon((data.getUrl_image()))); // NOI18N

        StringBuilder recipeText = new StringBuilder();

        for (DetailRecipeDTO detailRecipeDTO : detail_recipe) {
            String temp = detailRecipeDTO.getName_ingredient() + "-" + detailRecipeDTO.getQuantity() + "-"
                    + detailRecipeDTO.getUnit();
            recipeText.append(temp).append(" | ");
        }

        // Remove the trailing comma and space
        if (recipeText.length() > 0) {
            recipeText.setLength(recipeText.length() - 2);
        }

        input_recipe.setText(recipeText.toString());

        jTextField1.setEditable(false);
        jTextField3.setEditable(false);
        jTextField4.setEditable(false);
        jTextField5.setEditable(false);
        jTextField7.setEditable(false);
        input_recipe.setEditable(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {
        setTitle("Chi tiết sản phẩm");
        setResizable(false);
        jPanel1 = new javax.swing.JPanel();
        titleForm = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jPanel9 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        input_recipe = new javax.swing.JTextField();
        jToggleButton1 = new javax.swing.JToggleButton();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setMaximumSize(new java.awt.Dimension(730, 528));
        setMinimumSize(new java.awt.Dimension(730, 528));
        setPreferredSize(new java.awt.Dimension(730, 528));
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        jPanel1.setLayout(new java.awt.BorderLayout());

        titleForm.setBackground(new java.awt.Color(255, 153, 102));
        titleForm.setPreferredSize(new java.awt.Dimension(602, 35));
        titleForm.setLayout(new java.awt.CardLayout());
        // titleForm.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0,
        // java.awt.Color.BLACK));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setBackground(new java.awt.Color(255, 153, 102));
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Chi tiết sản phẩm");
        titleForm.add(jLabel1, "card2");

        jPanel1.add(titleForm, java.awt.BorderLayout.PAGE_START);

        jPanel3.setMinimumSize(new java.awt.Dimension(730, 408));
        jPanel3.setPreferredSize(new java.awt.Dimension(730, 408));
        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 30, 10));

        jPanel5.setPreferredSize(new java.awt.Dimension(250, 40));
        jPanel5.setRequestFocusEnabled(false);
        jPanel5.setLayout(new java.awt.BorderLayout(20, 0));

        jLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel2.setText("Mã sản phẩm");
        jPanel5.add(jLabel2, java.awt.BorderLayout.LINE_START);
        jPanel5.add(jTextField1, java.awt.BorderLayout.CENTER);

        jPanel3.add(jPanel5);

        jPanel6.setPreferredSize(new java.awt.Dimension(320, 40));
        jPanel6.setRequestFocusEnabled(false);
        jPanel6.setLayout(new java.awt.BorderLayout(20, 0));

        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel3.setText("Tên sản phẩm");
        jPanel6.add(jLabel3, java.awt.BorderLayout.LINE_START);
        jPanel6.add(jTextField2, java.awt.BorderLayout.CENTER);

        jPanel3.add(jPanel6);

        jPanel7.setPreferredSize(new java.awt.Dimension(250, 40));
        jPanel7.setRequestFocusEnabled(false);
        jPanel7.setLayout(new java.awt.BorderLayout(20, 0));

        jLabel4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel4.setText("Giá");
        jLabel4.setPreferredSize(new java.awt.Dimension(85, 17));
        jPanel7.add(jLabel4, java.awt.BorderLayout.LINE_START);
        jPanel7.add(jTextField3, java.awt.BorderLayout.CENTER);

        jPanel3.add(jPanel7);

        jPanel8.setPreferredSize(new java.awt.Dimension(320, 40));
        jPanel8.setRequestFocusEnabled(false);
        jPanel8.setLayout(new java.awt.BorderLayout(20, 0));

        jLabel5.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel5.setText("Loại sản phẩm");
        jPanel8.add(jLabel5, java.awt.BorderLayout.LINE_START);

        jComboBox1.setModel(
                new javax.swing.DefaultComboBoxModel<>(new String[] { "Cà phê", "Trà sữa", "Soda" }));
        jPanel8.add(jComboBox1, java.awt.BorderLayout.CENTER);

        jPanel3.add(jPanel8);

        jPanel9.setPreferredSize(new java.awt.Dimension(250, 40));
        jPanel9.setRequestFocusEnabled(false);
        jPanel9.setLayout(new java.awt.BorderLayout(20, 0));

        jLabel6.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel6.setText("Số lượng");
        jLabel6.setPreferredSize(new java.awt.Dimension(85, 17));
        jPanel9.add(jLabel6, java.awt.BorderLayout.LINE_START);
        jPanel9.add(jTextField4, java.awt.BorderLayout.CENTER);

        jPanel3.add(jPanel9);

        jPanel10.setPreferredSize(new java.awt.Dimension(320, 40));
        jPanel10.setRequestFocusEnabled(false);
        jPanel10.setLayout(new java.awt.BorderLayout(20, 0));

        jLabel7.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel7.setText("Ngày tạo");
        jLabel7.setPreferredSize(new java.awt.Dimension(92, 17));
        jPanel10.add(jLabel7, java.awt.BorderLayout.LINE_START);
        jPanel10.add(jTextField5, java.awt.BorderLayout.CENTER);

        jPanel3.add(jPanel10);

        jPanel11.setPreferredSize(new java.awt.Dimension(250, 40));
        jPanel11.setRequestFocusEnabled(false);
        jPanel11.setLayout(new java.awt.BorderLayout(20, 0));

        jLabel8.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel8.setText("Hình ảnh");
        jTextField6.setText("Chọn hình ảnh");
        jLabel8.setPreferredSize(new java.awt.Dimension(85, 17));
        jPanel11.add(jLabel8, java.awt.BorderLayout.LINE_START);
        jPanel11.add(jTextField6, java.awt.BorderLayout.CENTER);
        jTextField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField6ActionPerformed(evt);
            }
        });
        jPanel3.add(jPanel11);

        jPanel12.setPreferredSize(new java.awt.Dimension(320, 40));
        jPanel12.setRequestFocusEnabled(false);
        jPanel12.setLayout(new java.awt.BorderLayout(20, 0));

        jLabel9.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel9.setText("Cấp nhật cuối");
        jLabel9.setPreferredSize(new java.awt.Dimension(92, 17));
        jPanel12.add(jLabel9, java.awt.BorderLayout.LINE_START);
        jPanel12.add(jTextField7, java.awt.BorderLayout.CENTER);

        jPanel3.add(jPanel12);

        jPanel13.setPreferredSize(new java.awt.Dimension(390, 40));
        jPanel13.setRequestFocusEnabled(false);
        jPanel13.setLayout(new java.awt.BorderLayout(20, 0));

        jLabel10.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel10.setText("Công thức");
        jLabel10.setPreferredSize(new java.awt.Dimension(92, 17));
        jPanel13.add(jLabel10, java.awt.BorderLayout.LINE_START);
        jPanel13.add(input_recipe, java.awt.BorderLayout.CENTER);

        jPanel3.add(jPanel13);

        jToggleButton1.setPreferredSize(new java.awt.Dimension(180, 180));
        jPanel3.add(jToggleButton1);

        jPanel1.add(jPanel3, java.awt.BorderLayout.CENTER);

        jPanel2.setPreferredSize(new java.awt.Dimension(728, 50));
        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 30, 0));

        jButton1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jButton1.setText("Cập nhật");

        jButton1.setPreferredSize(new java.awt.Dimension(100, 50));
        jButton1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setBackground(new java.awt.Color(0, 191, 255));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1);

        jButton2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jButton2.setText("Đóng");
        jButton2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setBackground(java.awt.Color.RED);
        jButton2.setPreferredSize(new java.awt.Dimension(100, 50));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2);
        jPanel2.setBackground(java.awt.Color.WHITE);

        jPanel1.add(jPanel2, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(jPanel1);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {

        if (permissionList.hasPermission("UPDATE_PRODUCT")) {

            int id = Integer.parseInt(jTextField1.getText());
            String product_name = jTextField2.getText().trim();
            String url_image = jTextField6.getText().trim();
            String priceString = jTextField3.getText().trim();
            String quantityString = jTextField4.getText().trim();
            int category_id = jComboBox1.getSelectedIndex();

            if (product_name.isEmpty() || priceString.isEmpty() || quantityString.isEmpty() || url_image.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ giá trị");
                return;
            }
            float price = 0;
            int quantity = 0;
            try {
                price = Float.parseFloat(priceString);
                if (price < 0) {
                    JOptionPane.showMessageDialog(null, "Giá phải là số phải là số lớn hơn hoặc bằng 0.");
                    return;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Giá trị không hợp lệ");
                return;
            }
            try {
                quantity = Integer.parseInt(quantityString);
                if (quantity < 0) {
                    JOptionPane.showMessageDialog(null, "Số lượng phải là số lớn hơn hoặc bằng 0.");
                    return;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Giá trị không hợp lệ");
                return;
            }

            System.out.println(category_id + 1);

            ProductDTO productDTO = new ProductDTO(id, product_name, url_image, price, quantity, category_id + 1);
            try {
                boolean check = ProductBUS.updatedProductBUS(productDTO);
                if (!check) {
                    JOptionPane.showMessageDialog(null, "Cập nhật thất bại");
                    return;
                }
                JOptionPane.showMessageDialog(null, "Cập nhật thành công");
                this.setVisible(false);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Cập nhật thất bại");
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Bạn không có quyền truy cập");
            return;
        }

    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        this.setVisible(false);
    }

    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {

        if (permissionList.hasPermission("UPDATE_PRODUCT")) {
            JFileChooser fileChooser = new JFileChooser();

            // Thiết lập filter chỉ cho phép chọn các tệp ảnh
            FileNameExtensionFilter imageFilter = new FileNameExtensionFilter(
                    "Image files", ImageIO.getReaderFileSuffixes());
            fileChooser.setFileFilter(imageFilter);

            // Hiển thị hộp thoại chọn tệp và kiểm tra xem người dùng đã chọn tệp nào chưa
            int result = fileChooser.showOpenDialog(this); // 'this' ở đây là JFrame hoặc JDialog chứa JTextField

            // Nếu người dùng chọn một tệp ảnh
            if (result == JFileChooser.APPROVE_OPTION) {
                // Lấy đường dẫn của tệp được chọn
                File selectedFile = fileChooser.getSelectedFile();
                String filePath = selectedFile.getAbsolutePath();

                // Lưu tệp vào thư mục trong dự án của bạn
                String destinationPath = "./src/assets/icon/" + selectedFile.getName();
                // Kiểm tra xem tệp đã tồn tại trong dự án hay không
                File destinationFile = new File(destinationPath);

                if (destinationFile.exists()) {
                    // Hiển thị cảnh báo cho người dùng
                    int option = JOptionPane.showConfirmDialog(this, "Hình ảnh đã tồn tại. Bạn có muốn ghi đè?",
                            "Xác nhận ghi đè", JOptionPane.YES_NO_OPTION);
                    if (option == JOptionPane.YES_OPTION) {
                        // Ghi đè nếu người dùng chọn YES
                        try {
                            Files.copy(Paths.get(filePath), Paths.get(destinationPath),
                                    StandardCopyOption.REPLACE_EXISTING);
                            jTextField6.setText(destinationPath); // Hiển thị đường dẫn mới trong JTextField
                            jToggleButton1.setIcon(new javax.swing.ImageIcon((destinationPath))); // Đặt hình ảnh mới
                                                                                                  // cho
                                                                                                  // JToggleButton
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    // Copy tệp từ đường dẫn nguồn (đã chọn) vào đường dẫn đích trong dự án của bạn
                    try {
                        Files.copy(Paths.get(filePath), Paths.get(destinationPath),
                                StandardCopyOption.REPLACE_EXISTING);
                        jTextField6.setText(destinationPath); // Hiển thị đường dẫn mới trong JTextField
                        jToggleButton1.setIcon(new javax.swing.ImageIcon((destinationPath))); // Đặt hình ảnh mới cho
                                                                                              // JToggleButton
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Bạn không có quyền truy cập");
            return;
        }

    }

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
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DetailProduct.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DetailProduct.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DetailProduct.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DetailProduct.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        }
        // </editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                // new DetailProduct().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JButton jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField input_recipe;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JPanel titleForm;
    // End of variables declaration
}
