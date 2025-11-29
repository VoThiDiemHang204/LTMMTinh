package ThucHanh3;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class BaiThucHanh5 extends javax.swing.JFrame {
    public BaiThucHanh5() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtIp = new javax.swing.JTextField();
        btnKiemTra = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtKetqua = new javax.swing.JLabel();
        btnKiemTra1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Chương trình kiểm tra IP");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Nhập IP");

        txtIp.setText("127.0.0.1");

        btnKiemTra.setText("Kiểm tra");
        btnKiemTra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKiemTraActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Kết quả");

        txtKetqua.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        btnKiemTra1.setText("Thoát");
        btnKiemTra1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKiemTra1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtKetqua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnKiemTra)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnKiemTra1))
                            .addComponent(txtIp, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(78, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtIp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnKiemTra)
                    .addComponent(btnKiemTra1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtKetqua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(77, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnKiemTraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKiemTraActionPerformed
        // TODO add your handling code here:
        try {
            // 1. Lấy InetAddress của IP người dùng nhập
            InetAddress addr = InetAddress.getByName(txtIp.getText());
            // 2. Lấy InetAddress của máy cục bộ
            InetAddress localhost = InetAddress.getLocalHost();

            // 3. So sánh IP nhập vào với IP máy cục bộ
            if (addr.equals(localhost)) {
                txtKetqua.setText("Đây là địa chỉ Localhost");
            } else {
                // 4. Nếu không phải, kiểm tra xem có phải là địa chỉ Multicast không
                if (addr.isMulticastAddress()) {
                    txtKetqua.setText("Đây là địa chỉ Multicast");
                // 5. Nếu không phải, kiểm tra xem có phải là địa chỉ Loopback không
                } else if (addr.isLoopbackAddress()) {
                    txtKetqua.setText("Đây là địa chỉ Loopback");
                } else {
                    txtKetqua.setText("Không thấy gì đặc biệt!");
                }
            }
        } catch (UnknownHostException e) {
            // Lại là một khối catch rỗng (bad practice)
        }
    }//GEN-LAST:event_btnKiemTraActionPerformed

    private void btnKiemTra1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKiemTra1ActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btnKiemTra1ActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BaiThucHanh5().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnKiemTra;
    private javax.swing.JButton btnKiemTra1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField txtIp;
    private javax.swing.JLabel txtKetqua;
    // End of variables declaration//GEN-END:variables
}
