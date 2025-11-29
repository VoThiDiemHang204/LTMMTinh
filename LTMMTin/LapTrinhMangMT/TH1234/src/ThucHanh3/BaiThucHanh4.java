package ThucHanh3;

import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.swing.JOptionPane;

public class BaiThucHanh4 extends javax.swing.JFrame {
    public BaiThucHanh4() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtIp = new javax.swing.JLabel();
        btnSubmit = new javax.swing.JButton();
        btnSubmit1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Chương trình tìm IP của máy cục bộ");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("IP máy cục bộ");

        txtIp.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        btnSubmit.setText("Tìm");
        btnSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubmitActionPerformed(evt);
            }
        });

        btnSubmit1.setText("Thoát");
        btnSubmit1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubmit1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSubmit1)
                            .addComponent(btnSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtIp, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(86, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtIp))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSubmit)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(btnSubmit1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitActionPerformed
        // TODO add your handling code here:
        try {
            // 1. Lấy đối tượng InetAddress của máy cục bộ
            InetAddress addr = InetAddress.getLocalHost();

            // 2. Lấy địa chỉ IP dạng mảng byte
            byte[] ipAddr = addr.getAddress();

            // 3. Chuyển đổi mảng byte sang chuỗi String (cách thủ công)
            String ipAddrStr = "";
            for (int j = 0; j < ipAddr.length; j++) {
                if (j > 0) {
                    ipAddrStr += ".";
                }
                ipAddrStr += ipAddr[j] & 0xFF;
            }

            // 4. Hiển thị kết quả
            txtIp.setText(ipAddrStr);

        } catch (UnknownHostException e) {
            // 5. Khối catch bị bỏ trống
        }
    }//GEN-LAST:event_btnSubmitActionPerformed

    private void btnSubmit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmit1ActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btnSubmit1ActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BaiThucHanh4().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSubmit;
    private javax.swing.JButton btnSubmit1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel txtIp;
    // End of variables declaration//GEN-END:variables
}
