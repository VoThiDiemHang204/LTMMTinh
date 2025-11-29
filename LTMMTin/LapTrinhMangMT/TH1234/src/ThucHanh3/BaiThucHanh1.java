package ThucHanh3;

import java.net.Inet4Address;
import java.net.InetAddress;
import javax.swing.JOptionPane;

public class BaiThucHanh1 extends javax.swing.JFrame {
    public BaiThucHanh1() {
        initComponents();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtIP = new javax.swing.JTextField();
        btnKiemTra = new javax.swing.JButton();
        btnThoat = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Chương trình kiểm tra IP");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Nhập địa chỉ IP cần kiểm tra");

        btnKiemTra.setText("Kiểm tra");
        btnKiemTra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKiemTraActionPerformed(evt);
            }
        });

        btnThoat.setText("Thoát");
        btnThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnKiemTra)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnThoat))
                    .addComponent(txtIP, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(60, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtIP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnKiemTra)
                    .addComponent(btnThoat))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnKiemTraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKiemTraActionPerformed
        // TODO add your handling code here:
        // 1. Lấy dữ liệu từ ô text
        String IP = txtIP.getText();

        // 2. Kiểm tra dữ liệu đầu vào có rỗng không
        if(IP.isEmpty()){
            JOptionPane.showMessageDialog(null, "Hãy nhập địa chỉ IP cần kiểm tra");
            return; // Dừng thực thi nếu rỗng
        }

        // 3. Khối try-catch để xử lý lỗi
        try{
            // 4. Phân giải địa chỉ IP
            InetAddress host = Inet4Address.getByName(IP);

            // 5. Kiểm tra và phân loại IP
            if(host != null){
                if(IP.contains(".")){//ipv4 là dấu chấm, ipv6 là dấu ":"
                    JOptionPane.showMessageDialog(null, "Đây là IPv4");
                } else {
                    JOptionPane.showMessageDialog(null, "Đây là IPv6");
                }
            } else {
                // Dòng này thực tế khó xảy ra
                JOptionPane.showMessageDialog(null, "Địa chỉ IP không hợp lệ");
            }
        }catch(Exception e){
            // 6. Bắt và thông báo lỗi
            JOptionPane.showMessageDialog(null, "Địa chỉ IP không hợp lệ \n" + e.toString());
        }
    }//GEN-LAST:event_btnKiemTraActionPerformed

    private void btnThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatActionPerformed
        // TODO add your handling code here:
        dispose();//dispose(): Phương thức này chỉ đóng và giải phóng tài nguyên của cửa sổ hiện tại (BaiThucHanh1). 
                    //Chương trình chính (cửa sổ MyFrame) vẫn tiếp tục chạy. 
                    //Đây là cách đúng để đóng một cửa sổ con mà không ảnh hưởng đến toàn bộ ứng dụng.
                    //System.exit(0): Lệnh này sẽ chấm dứt hoàn toàn bộ máy ảo Java (JVM), 
                    //tức là đóng tất cả các cửa sổ và kết thúc toàn bộ ứng dụng.
    }//GEN-LAST:event_btnThoatActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BaiThucHanh1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnKiemTra;
    private javax.swing.JButton btnThoat;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField txtIP;
    // End of variables declaration//GEN-END:variables
}
