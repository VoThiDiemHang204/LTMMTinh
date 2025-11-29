package ThucHanh1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import javax.swing.JOptionPane;

public class frmClient extends javax.swing.JFrame {
    public frmClient() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtDomain = new javax.swing.JTextField();
        txtChuoi = new javax.swing.JTextField();
        txtKetQua = new javax.swing.JTextField();
        btnTruyenChuoi = new javax.swing.JButton();
        btnThoat = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Nhập địa chỉ:");

        jLabel2.setText("Nhập chuỗi:");

        jLabel3.setText("Kết quả:");

        txtKetQua.setEditable(false);

        btnTruyenChuoi.setText("Truyền chuỗi");
        btnTruyenChuoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTruyenChuoiActionPerformed(evt);
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
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnTruyenChuoi)
                        .addGap(138, 138, 138)
                        .addComponent(btnThoat))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtDomain, javax.swing.GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE)
                        .addComponent(txtChuoi)
                        .addComponent(txtKetQua)))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtDomain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtChuoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(54, 54, 54)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtKetQua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTruyenChuoi)
                    .addComponent(btnThoat))
                .addGap(36, 36, 36))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Hàm xử lý sự kiện khi nhấn nút "Truyền chuỗi".
    private void btnTruyenChuoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTruyenChuoiActionPerformed
        byte []sendData; // Mảng byte để chứa dữ liệu gửi đi.
        DatagramSocket socket = null; // Khai báo socket UDP cho client. Khởi tạo là null.
        
        try{
            // Tạo DatagramSocket cho client. Không cần chỉ định cổng, 
            // hệ điều hành sẽ tự chọn một cổng trống (ephemeral port).
            socket = new DatagramSocket(); 
            
            // Lấy địa chỉ server (có thể là IP hoặc domain name) từ ô text.
            String domain = this.txtDomain.getText(); 
            
            // Phân giải địa chỉ server (IP/domain) thành đối tượng InetAddress.
            // Nếu nhập domain, nó sẽ truy vấn DNS. Nếu nhập IP, nó sẽ parse IP.
            InetAddress ipServer = InetAddress.getByName(domain); 
            
            // Cổng của server mà client muốn gửi đến (phải khớp với cổng server đang lắng nghe).
            int port = 1234; 
            
            // Lấy chuỗi dữ liệu mà người dùng nhập vào.
            String stSend = this.txtChuoi.getText(); 
            
            // Chuyển chuỗi cần gửi thành mảng byte.
            sendData = stSend.getBytes(); 
            
            // Tạo gói tin DatagramPacket để gửi đi.
            // Gói tin này chứa: dữ liệu (sendData), độ dài dữ liệu, địa chỉ IP server, cổng server.
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipServer, port);
            
            // Gửi gói tin đi qua socket. Dữ liệu sẽ được chuyển đến địa chỉ ipServer:port.
            socket.send(sendPacket); 
            System.out.println("Sent to " + ipServer + ":" + port + " - Data: " + stSend);
            
            // --- Phần nhận phản hồi từ Server ---
            
            // Tạo buffer để nhận dữ liệu phản hồi từ server.
            byte[] buffer = new byte[65507]; 
            
            // Tạo DatagramPacket rỗng để chứa dữ liệu nhận về.
            DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);
            
            // Chờ nhận gói tin phản hồi từ server. Lệnh này sẽ block cho đến khi có gói tin đến.
            socket.receive(receivePacket); 
            
            // Chuyển dữ liệu byte nhận được thành String và hiển thị lên ô kết quả.
            // .trim() rất quan trọng để bỏ các byte thừa trong buffer.
            String result = new String(receivePacket.getData()).trim();
            txtKetQua.setText(result); 
            System.out.println("Received from server: " + result);

        } catch(IOException ex){
            // Hiển thị hộp thoại thông báo nếu có lỗi xảy ra (vd: sai địa chỉ server, server không chạy...).
            JOptionPane.showMessageDialog(this, ex.toString());
        } finally {
            // Đóng socket sau khi hoàn thành việc gửi và nhận.
            // Rất quan trọng để giải phóng tài nguyên cổng mạng.
            if (socket != null && !socket.isClosed()) {
                socket.close();
                System.out.println("Client socket closed.");
            }
        }
    }//GEN-LAST:event_btnTruyenChuoiActionPerformed
 
    private void btnThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnThoatActionPerformed

    public static void main(String args[]) {
        /* Tạo và hiển thị cửa sổ giao diện Client */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmClient().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnThoat;
    private javax.swing.JButton btnTruyenChuoi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField txtChuoi;
    private javax.swing.JTextField txtDomain;
    private javax.swing.JTextField txtKetQua;
    // End of variables declaration//GEN-END:variables
}
