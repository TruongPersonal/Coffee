
package com.project.GUI;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.project.BUS.CategoryBUS;
import com.project.BUS.EmployeeBUS;
import com.project.BUS.EnterCouponBUS;
import com.project.BUS.PaymentMethodBUS;
import com.project.BUS.ProductBUS;
import com.project.DAO.OrderDAO;
import com.project.DTO.CategoryDTO;
import com.project.DTO.OrderDTO;
import com.project.DTO.OrderDetailDTO;
import com.project.DTO.PaymentMethodDTO;
import com.project.DTO.PermissionAccount;
import com.project.DTO.ProductDTO;
import com.project.Util.Formatter;

/**
 *
 * @author LuanLe
 */
public class BuyProduct extends JPanel implements AddProductListener {
        private ProductBUS productBUS = new ProductBUS();
        private OrderDAO orderDAO = new OrderDAO();
        private EmployeeBUS empBUS = new EmployeeBUS();
        private PermissionAccount permissionList;

        public BuyProduct() {
                initComponents();
                permissionList = PermissionAccount.getInstance();
        }

        private void initComponents() {
                jSplitPane = new javax.swing.JSplitPane();
                tpProducts = new TabbedPaneProducts();
                pnCarts = new PanelCarts();

                jSplitPane.setBackground(new java.awt.Color(255, 255, 255));
                jSplitPane.setResizeWeight(1);
                jSplitPane.setLeftComponent(tpProducts);
                jSplitPane.setRightComponent(pnCarts);
                add(jSplitPane);
                setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

                tpProducts.setListener(this);

                pnCarts.PanelBottom.btnOrder.addActionListener(e -> {
                        if (pnCarts.cartItems.size() == 0) {
                                JOptionPane.showMessageDialog(this, "Chưa chọn sản phẩm nào", "Thông báo",
                                                JOptionPane.INFORMATION_MESSAGE);
                        } else if (empBUS.getEmpByAccountID(permissionList.getAccountId()) == null) {
                                JOptionPane.showMessageDialog(null, "Tài khoản này không được cấp quyền bán hàng!",
                                                "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                                int choice = JOptionPane.showConfirmDialog(this, "Xác nhận đơn hàng", "Xác nhận",
                                                JOptionPane.OK_CANCEL_OPTION);
                                if (choice == JOptionPane.YES_OPTION) {
                                        int paymentMethod_id = pnCarts.PanelBottom.getSelectedPaymentMethodID();

                                        int account_id = PermissionAccount.getInstance().getAccountId();

                                        OrderDTO orderDTO = new OrderDTO("pending", paymentMethod_id, account_id);
                                        ArrayList<OrderDetailDTO> orderDetails = new ArrayList<>();
                                        for (ProductDTO product : pnCarts.cartItems.keySet()) {
                                                CartItem cartItem = pnCarts.cartItems.get(product);
                                                int quantity = (int) cartItem.SpinnerQuantity.getValue();

                                                orderDetails.add(new OrderDetailDTO(quantity, product.getId()));
                                        }

                                        if (orderDAO.addOrder(orderDTO, orderDetails)) {

                                                for (ProductDTO product : pnCarts.cartItems.keySet()) {
                                                        CartItem cartItem = pnCarts.cartItems.get(product);
                                                        int quantity = (int) cartItem.SpinnerQuantity.getValue();

                                                        productBUS.decreaseProductQuantity(product.getId(), quantity);

                                                        EnterCouponBUS.updateQuantityIngredient(product.getId(),
                                                                        quantity);

                                                }

                                                // Reset
                                                pnCarts.PanelBottom.cbPaymentMethod.setSelectedIndex(0);
                                                tpProducts.reloadProductList();
                                                tpProducts.setListener(this);
                                                pnCarts.removeAllCartItem();
                                                setTextTotal();

                                                JOptionPane.showMessageDialog(this, "Đặt hàng thành công",
                                                                "Thông báo",
                                                                JOptionPane.INFORMATION_MESSAGE);
                                        }
                                }
                        }
                });

        }

        private void exportPDF() {
                // TODO
        }

        private float getTotalPrice() {
                float total = 0;

                for (ProductDTO p : pnCarts.cartItems.keySet()) {
                        CartItem cartItem = pnCarts.cartItems.get(p);
                        total += p.getPrice() * (int) cartItem.SpinnerQuantity.getValue();
                }

                return total;
        }

        private void setTextTotal() {
                pnCarts.PanelBottom.Total.setText(Formatter.getFormatedPrice(getTotalPrice()) + " VNĐ");
        }

        @Override
        public void btnAddOnClicked(ProductDTO product) {
                pnCarts.addCartItem(product);

                pnCarts.cartItems.get(product).btnRemove.addActionListener(e -> {
                        pnCarts.removeCartItem(product);
                        setTextTotal();
                });

                pnCarts.cartItems.get(product).SpinnerQuantity.addChangeListener(new ChangeListener() {
                        @Override
                        public void stateChanged(ChangeEvent e) {
                                setTextTotal();
                        }

                });

                setTextTotal();
        }

        private javax.swing.JSplitPane jSplitPane;
        private TabbedPaneProducts tpProducts;
        private PanelCarts pnCarts;

}

class TabbedPaneProducts extends javax.swing.JTabbedPane {
        private ProductBUS productBUS = new ProductBUS();

        public TabbedPaneProducts() {
                initComponents();
        }

        private void initComponents() {
                setMinimumSize(new Dimension(750, 768));
                setMaximumSize(new Dimension(32767, 32767));
                setPreferredSize(new Dimension(750, 768));

                loadProductList();
        }

        public void loadProductList() {
                ArrayList<CategoryDTO> categoryDTOs = new CategoryBUS().getAll();

                for (CategoryDTO categoryDTO : categoryDTOs) {
                        ScrollPaneProductList productList = new ScrollPaneProductList(
                                        productBUS.getProductByCategory(categoryDTO.getId()));
                        this.addTab(categoryDTO.getCategory_name(), productList);
                }
        }

        public void reloadProductList() {
                this.removeAll();
                loadProductList();
                this.revalidate();
                this.repaint();
        }

        public void setListener(AddProductListener listener) {
                for (int i = 0; i < this.getTabCount(); i++) {
                        ScrollPaneProductList productList = (ScrollPaneProductList) this.getComponentAt(i);
                        productList.setListener(listener);
                }
        }
}

class PanelCarts extends JPanel {

        public HashMap<ProductDTO, CartItem> cartItems;

        public PanelCarts() {
                initComponents();
                cartItems = new HashMap<>();
        }

        private void initComponents() {
                setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.Y_AXIS));
                setMinimumSize(new Dimension(380, 768));
                setMaximumSize(new Dimension(380, 32767));
                setPreferredSize(new Dimension(380, 768));

                ScrollPaneCart = new javax.swing.JScrollPane();
                CartList = new javax.swing.JPanel();
                PanelBottom = new PanelBottom();

                CartList.setMaximumSize(new java.awt.Dimension(380, 120));
                CartList.setLayout(new javax.swing.BoxLayout(CartList, javax.swing.BoxLayout.Y_AXIS));

                ScrollPaneCart.setViewportView(CartList);

                add(ScrollPaneCart);
                add(PanelBottom);
        }

        public void addCartItem(ProductDTO product) {

                CartItem cartItem = cartItems.get(product);
                if (cartItem == null) {
                        cartItem = new CartItem(product);
                        cartItems.put(product, cartItem);

                        CartList.add(cartItem);
                        CartList.revalidate();
                        CartList.repaint();
                } else {

                        int currentQuty = (int) cartItem.SpinnerQuantity.getValue();
                        if (currentQuty < product.getQuantity()) {
                                cartItem.SpinnerQuantity.setValue(currentQuty + 1);
                        } else {
                                JOptionPane.showMessageDialog(null, "Số lượng sản phẩm đạt tới giới hạn",
                                                "Thông báo", JOptionPane.INFORMATION_MESSAGE);

                        }
                }
                cartItem.SpinnerQuantity.requestFocus();
        }

        public void removeCartItem(ProductDTO product) {
                CartItem cartItem = cartItems.get(product);
                cartItems.remove(product);
                CartList.remove(cartItem);
                CartList.revalidate();
                CartList.repaint();
        }

        public void removeAllCartItem() {
                for (CartItem cartItem : cartItems.values()) {
                        CartList.remove(cartItem);
                }
                cartItems.clear();
                CartList.revalidate();
                CartList.repaint();
        }

        private javax.swing.JPanel CartList;
        public PanelBottom PanelBottom;
        private javax.swing.JScrollPane ScrollPaneCart;
}

class ScrollPaneProductList extends javax.swing.JScrollPane {
        private AddProductListener listener;

        public ScrollPaneProductList(ArrayList<ProductDTO> products) {
                initComponents(products);
        }

        private void initComponents(ArrayList<ProductDTO> products) {
                JPanel ProducList = new JPanel();
                ProducList.setBackground(new java.awt.Color(255, 255, 255));
                ProducList.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10, 10));
                ProducList.setMaximumSize(new Dimension(380, 32767));
                ProducList.setMinimumSize(new Dimension(380, 768));
                ProducList.setPreferredSize(new Dimension(380, 768));

                for (ProductDTO product : products) {
                        ProductCard ProductCard = new ProductCard(product);

                        ProductCard.btnAdd.addActionListener(e -> {
                                if (listener != null) {
                                        listener.btnAddOnClicked(ProductCard.getProduct());
                                }
                        });

                        ProducList.add(ProductCard);
                }

                this.setViewportView(ProducList);

        }

        public void setListener(AddProductListener listener) {
                this.listener = listener;
        }

}

class ProductCard extends JPanel {
        private ProductDTO product;

        public ProductCard(ProductDTO product) {
                initComponents(product);
        }

        public ProductDTO getProduct() {
                return product;
        }

        private void initComponents(ProductDTO product) {
                this.product = product;

                setMinimumSize(new Dimension(200, 310));
                setMaximumSize(new Dimension(32767, 32767));
                setPreferredSize(new Dimension(200, 310));

                ProductCard8 = new javax.swing.JPanel();
                btnImage = new javax.swing.JButton();
                Name = new javax.swing.JLabel();
                Price = new javax.swing.JLabel();
                jPanel1 = new javax.swing.JPanel();
                lblQuantity = new javax.swing.JLabel();
                Remaining = new javax.swing.JLabel();
                btnAdd = new javax.swing.JButton();

                // Nếu số lượng sản phẩm = 0 thì disable button
                if (product.getQuantity() == 0)
                        btnAdd.setEnabled(false);

                ProductCard8.setBackground(new java.awt.Color(255, 255, 255));
                ProductCard8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
                ProductCard8.setMinimumSize(new java.awt.Dimension(200, 310));
                ProductCard8.setPreferredSize(new java.awt.Dimension(200, 310));

                btnImage.setIcon(new javax.swing.ImageIcon(
                                product.getUrl_image()));
                btnImage.setMaximumSize(new java.awt.Dimension(185, 185));
                btnImage.setPreferredSize(new java.awt.Dimension(165, 165));
                ProductCard8.add(btnImage);

                Name.setBackground(new java.awt.Color(255, 255, 255));
                Name.setFont(new java.awt.Font("Arial", 1, 18));
                Name.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                Name.setText(product.getProduct_name());
                Name.setToolTipText("");
                Name.setFocusable(false);
                Name.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                Name.setMaximumSize(new java.awt.Dimension(185, 20));
                Name.setMinimumSize(new java.awt.Dimension(185, 20));
                Name.setPreferredSize(new java.awt.Dimension(185, 20));
                ProductCard8.add(Name);

                Price.setBackground(new java.awt.Color(255, 255, 255));
                Price.setFont(new java.awt.Font("Arial", 1, 18));
                Price.setForeground(new java.awt.Color(255, 51, 51));
                Price.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

                Price.setText(Formatter.getFormatedPrice(product.getPrice()) + " VNĐ");
                Price.setToolTipText("");
                Price.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                Price.setMaximumSize(new java.awt.Dimension(185, 20));
                Price.setMinimumSize(new java.awt.Dimension(185, 20));
                Price.setPreferredSize(new java.awt.Dimension(185, 20));
                ProductCard8.add(Price);

                jPanel1.setBackground(new java.awt.Color(255, 255, 255));
                jPanel1.setMaximumSize(new java.awt.Dimension(185, 25));
                jPanel1.setMinimumSize(new java.awt.Dimension(185, 25));
                jPanel1.setPreferredSize(new java.awt.Dimension(185, 25));
                jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 0));

                lblQuantity.setBackground(new java.awt.Color(255, 255, 255));
                lblQuantity.setFont(new java.awt.Font("Arial", 0, 18));
                lblQuantity.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lblQuantity.setText("Số lượng:");
                lblQuantity.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                lblQuantity.setMaximumSize(new java.awt.Dimension(90, 25));
                lblQuantity.setMinimumSize(new java.awt.Dimension(90, 25));
                jPanel1.add(lblQuantity);

                Remaining.setBackground(new java.awt.Color(255, 255, 255));

                Remaining.setFont(new java.awt.Font("Arial", 1, 18));
                Remaining.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                Remaining.setText(String.valueOf(product.getQuantity()));
                Remaining.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                Remaining.setMaximumSize(new java.awt.Dimension(90, 25));
                Remaining.setMinimumSize(new java.awt.Dimension(90, 25));
                jPanel1.add(Remaining);

                ProductCard8.add(jPanel1);

                btnAdd.setFont(new java.awt.Font("Arial", 1, 20));
                btnAdd.setBackground(new java.awt.Color(255, 153, 102));
                btnAdd.setForeground(new java.awt.Color(255, 255, 255));
                btnAdd.setBorderPainted(false);
                btnAdd.setText("Chọn");
                btnAdd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnAdd.setMaximumSize(new java.awt.Dimension(220, 40));
                btnAdd.setMinimumSize(new java.awt.Dimension(150, 40));
                btnAdd.setPreferredSize(new java.awt.Dimension(150, 40));
                ProductCard8.add(btnAdd);

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
                this.setLayout(layout);
                layout.setHorizontalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGap(0, 400, Short.MAX_VALUE)
                                                .addGroup(layout.createParallelGroup(
                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addGroup(layout.createSequentialGroup()
                                                                                .addGap(0, 0, Short.MAX_VALUE)
                                                                                .addComponent(ProductCard8,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(0, 0, Short.MAX_VALUE))));
                layout.setVerticalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGap(0, 420, Short.MAX_VALUE)
                                                .addGroup(layout.createParallelGroup(
                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addGroup(layout.createSequentialGroup()
                                                                                .addGap(0, 0, Short.MAX_VALUE)
                                                                                .addComponent(ProductCard8,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(0, 0, Short.MAX_VALUE))));
        }

        public javax.swing.JLabel Price;
        public javax.swing.JPanel ProductCard8;
        public javax.swing.JButton btnAdd;
        public javax.swing.JButton btnImage;
        public javax.swing.JLabel lblQuantity;
        public javax.swing.JPanel jPanel1;
        public javax.swing.JLabel Name;
        public javax.swing.JLabel Remaining;
}

class PanelBottom extends javax.swing.JPanel {
        private ArrayList<PaymentMethodDTO> paymentMethodDTOs;

        public PanelBottom() {
                initComponents();
        }

        private void initComponents() {
                this.paymentMethodDTOs = new PaymentMethodBUS().getAll();

                setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.Y_AXIS));
                setMinimumSize(new Dimension(32767, 200));
                setMaximumSize(new Dimension(32767, 200));
                setPreferredSize(new Dimension(32767, 200));

                BoxPaymentMethods = new javax.swing.JPanel();
                PaymentMethodsTitle = new javax.swing.JLabel();
                cbPaymentMethod = new javax.swing.JComboBox<>();
                BoxTotal = new javax.swing.JPanel();
                TotalTitle = new javax.swing.JLabel();
                Total = new javax.swing.JLabel();
                GroupButtons = new javax.swing.JPanel();

                btnOrder = new javax.swing.JButton();

                BoxPaymentMethods.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 0, 3, 0,
                                new java.awt.Color(255, 255, 255)));
                BoxPaymentMethods.setMaximumSize(new java.awt.Dimension(32964, 50));
                BoxPaymentMethods.setMinimumSize(new java.awt.Dimension(380, 50));
                BoxPaymentMethods.setPreferredSize(new java.awt.Dimension(380, 50));

                PaymentMethodsTitle.setFont(new java.awt.Font("Arial", 0, 16));
                PaymentMethodsTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

                PaymentMethodsTitle.setText("Hình thức thanh toán:");
                PaymentMethodsTitle.setToolTipText("");
                PaymentMethodsTitle.setMaximumSize(new java.awt.Dimension(32767, 40));
                PaymentMethodsTitle.setMinimumSize(new java.awt.Dimension(200, 40));
                PaymentMethodsTitle.setOpaque(true);
                PaymentMethodsTitle.setPreferredSize(new java.awt.Dimension(200, 40));
                BoxPaymentMethods.add(PaymentMethodsTitle);

                cbPaymentMethod.setFont(new java.awt.Font("Arial", 0, 14));

                setPaymentMethodModel();
                cbPaymentMethod.setMaximumSize(new java.awt.Dimension(32767, 35));
                cbPaymentMethod.setMinimumSize(new java.awt.Dimension(100, 35));
                cbPaymentMethod.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                BoxPaymentMethods.add(cbPaymentMethod);

                add(BoxPaymentMethods);

                BoxTotal.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0,
                                new java.awt.Color(255, 255, 255)));
                BoxTotal.setMaximumSize(new java.awt.Dimension(32767, 50));
                BoxTotal.setMinimumSize(new java.awt.Dimension(380, 50));

                TotalTitle.setFont(new java.awt.Font("Arial", 1, 18));
                TotalTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                TotalTitle.setText("Tổng tiền:");
                TotalTitle.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                TotalTitle.setMaximumSize(new java.awt.Dimension(32767, 40));
                TotalTitle.setMinimumSize(new java.awt.Dimension(110, 40));
                TotalTitle.setPreferredSize(new java.awt.Dimension(110, 40));
                BoxTotal.add(TotalTitle);

                Total.setFont(new java.awt.Font("Arial", 0, 18));
                Total.setForeground(java.awt.Color.red);
                Total.setText("0 VNĐ");
                BoxTotal.add(Total);

                add(BoxTotal);

                GroupButtons.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0,
                                new java.awt.Color(255, 255, 255)));
                GroupButtons.setMaximumSize(new java.awt.Dimension(32767, 100));
                GroupButtons.setMinimumSize(new java.awt.Dimension(380, 100));
                GroupButtons.setPreferredSize(new java.awt.Dimension(380, 100));

                btnOrder.setBackground(new java.awt.Color(255, 153, 102));
                btnOrder.setFont(new java.awt.Font("Arial", 1, 18));
                btnOrder.setForeground(new java.awt.Color(255, 255, 255));
                btnOrder.setText("Đặt hàng");
                btnOrder.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnOrder.setMaximumSize(new java.awt.Dimension(150, 50));
                btnOrder.setMinimumSize(new java.awt.Dimension(120, 50));
                btnOrder.setPreferredSize(new java.awt.Dimension(120, 50));
                GroupButtons.add(btnOrder);

                add(GroupButtons);

        }

        private void setPaymentMethodModel() {
                String[] paymentMethods = new String[paymentMethodDTOs.size()];
                for (int i = 0; i < paymentMethodDTOs.size(); i++) {
                        paymentMethods[i] = paymentMethodDTOs.get(i).getPayment_name();
                }

                cbPaymentMethod
                                .setModel(new javax.swing.DefaultComboBoxModel<>(
                                                paymentMethods));
        }

        public int getSelectedPaymentMethodID() {
                return paymentMethodDTOs.get(cbPaymentMethod.getSelectedIndex()).getId();
        }

        public javax.swing.JLabel Total;
        public javax.swing.JComboBox<String> cbPaymentMethod;
        public javax.swing.JButton btnOrder;
        // public javax.swing.JButton btnPrint;
        public javax.swing.JLabel TotalTitle;
        public javax.swing.JLabel PaymentMethodsTitle;
        public javax.swing.JPanel BoxPaymentMethods;
        public javax.swing.JPanel BoxTotal;
        public javax.swing.JPanel GroupButtons;
}

class CartItem extends JPanel {

        public CartItem(ProductDTO product) {
                initComponents(product);
        }

        public void initComponents(ProductDTO product) {

                setBackground(new java.awt.Color(255, 255, 255));
                setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0,
                                new java.awt.Color(0, 0, 0)));
                setMaximumSize(new java.awt.Dimension(32767, 80));
                setMinimumSize(new java.awt.Dimension(350, 80));
                setPreferredSize(new java.awt.Dimension(350, 80));
                setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.X_AXIS));

                Top = new javax.swing.JPanel();
                Name = new javax.swing.JLabel();
                Bottom = new javax.swing.JPanel();
                BoxDonGia = new javax.swing.JPanel();
                lblDonGia = new javax.swing.JLabel();
                Price = new javax.swing.JLabel();
                BottomRight = new javax.swing.JPanel();
                btnRemove = new javax.swing.JButton();

                // Thiết lập giới hạn cho spinner
                javax.swing.SpinnerModel sm = new javax.swing.SpinnerNumberModel(1, 0, product.getQuantity() + 1, 1);
                SpinnerQuantity = new javax.swing.JSpinner(sm);
                SpinnerQuantity.addChangeListener(new javax.swing.event.ChangeListener() {
                        public void stateChanged(javax.swing.event.ChangeEvent evt) {
                                int currentValue = (int) SpinnerQuantity.getValue();
                                int maxValue = product.getQuantity();

                                if (currentValue > maxValue) {
                                        SpinnerQuantity.setValue(maxValue);
                                        JOptionPane.showMessageDialog(null, "Số lượng sản phẩm đạt tới giới hạn",
                                                        "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                                }

                                if (currentValue <= 0) {

                                        SpinnerQuantity.setValue(1);
                                        JOptionPane.showMessageDialog(null, "Số lượng sản phẩm tối thiểu là 1",
                                                        "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                                }
                        }
                });

                Top.setBackground(new java.awt.Color(255, 255, 255));
                Top.setMaximumSize(new java.awt.Dimension(32767, 80));
                Top.setMinimumSize(new java.awt.Dimension(325, 80));
                Top.setPreferredSize(new java.awt.Dimension(325, 80));
                Top.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

                Name.setFont(new java.awt.Font("Arial", 0, 18));
                Name.setText(product.getProduct_name());
                Name.setMaximumSize(new java.awt.Dimension(32767, 25));
                Name.setMinimumSize(new java.awt.Dimension(390, 25));
                Name.setName("");
                Name.setPreferredSize(new java.awt.Dimension(390, 25));
                Top.add(Name);

                Bottom.setBackground(new java.awt.Color(255, 255, 255));
                Bottom.setMaximumSize(new java.awt.Dimension(32767, 50));
                Bottom.setMinimumSize(new java.awt.Dimension(390, 40));
                Bottom.setPreferredSize(new java.awt.Dimension(390, 40));
                Bottom.setLayout(new java.awt.BorderLayout());

                BoxDonGia.setBackground(new java.awt.Color(255, 255, 255));
                BoxDonGia.setMaximumSize(new java.awt.Dimension(32767, 25));
                BoxDonGia.setMinimumSize(new java.awt.Dimension(150, 25));
                BoxDonGia.setPreferredSize(new java.awt.Dimension(150, 25));
                BoxDonGia.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING));

                lblDonGia.setFont(new java.awt.Font("Arial", 0, 16));
                lblDonGia.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                lblDonGia.setText("Đơn giá:");
                lblDonGia.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
                lblDonGia.setMaximumSize(new java.awt.Dimension(70, 20));
                lblDonGia.setMinimumSize(new java.awt.Dimension(70, 20));
                lblDonGia.setName("");
                lblDonGia.setPreferredSize(new java.awt.Dimension(70, 20));
                BoxDonGia.add(lblDonGia);

                Price.setFont(new java.awt.Font("Arial", 0, 14));
                Price.setForeground(java.awt.Color.red);
                Price.setText(Formatter.getFormatedPrice(product.getPrice()));
                Price.setMaximumSize(new java.awt.Dimension(32767, 20));
                Price.setMinimumSize(new java.awt.Dimension(60, 20));
                Price.setPreferredSize(new java.awt.Dimension(60, 20));
                BoxDonGia.add(Price);

                Bottom.add(BoxDonGia, java.awt.BorderLayout.WEST);

                BottomRight.setBackground(new java.awt.Color(255, 255, 255));
                BottomRight.setPreferredSize(new Dimension(200, 70));
                BottomRight.setPreferredSize(new Dimension(200, 70));
                BottomRight.setMinimumSize(new Dimension(200, 70));

                SpinnerQuantity.setMinimumSize(new java.awt.Dimension(70, 30));
                SpinnerQuantity.setPreferredSize(new java.awt.Dimension(70, 30));

                btnRemove.setBackground(java.awt.Color.red);
                btnRemove.setFont(new java.awt.Font("Arial", 1, 16));
                btnRemove.setForeground(new java.awt.Color(255, 255, 255));
                btnRemove.setText("Xóa");
                btnRemove.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnRemove.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                btnRemove.setMaximumSize(new java.awt.Dimension(85, 30));
                btnRemove.setMinimumSize(new java.awt.Dimension(85, 30));
                btnRemove.setPreferredSize(new java.awt.Dimension(85, 30));

                javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(BottomRight);
                BottomRight.setLayout(jPanel14Layout);
                jPanel14Layout.setHorizontalGroup(
                                jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel14Layout.createSequentialGroup()
                                                                .addComponent(SpinnerQuantity,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(24, 24, 24)
                                                                .addComponent(btnRemove,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)));
                jPanel14Layout.setVerticalGroup(
                                jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel14Layout.createSequentialGroup()
                                                                .addGroup(jPanel14Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(btnRemove,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(SpinnerQuantity,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(10, 10, 10)));

                Bottom.add(BottomRight, java.awt.BorderLayout.EAST);
                Top.add(Bottom);
                add(Top);
        }

        public javax.swing.JLabel Name;
        public javax.swing.JLabel Price;
        public javax.swing.JSpinner SpinnerQuantity;
        public javax.swing.JButton btnRemove;
        public javax.swing.JPanel Top;
        public javax.swing.JLabel lblDonGia;
        public javax.swing.JPanel Bottom;
        public javax.swing.JPanel BoxDonGia;
        public javax.swing.JPanel BottomRight;

}

interface AddProductListener {
        void btnAddOnClicked(ProductDTO product);
}