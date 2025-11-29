/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ThucHanh3; // Hoặc package của bạn, ví dụ: ThucHanh4

import java.net.DatagramPacket;
import java.net.DatagramSocket;
// import javax.swing.JOptionPane; // Nên import nếu muốn dùng

/**
 * Lớp Main: Khởi chạy giao diện Chat và xử lý nhận tin nhắn UDP.
 * @author ADMIN
 */
public class Main {
    
    // Khai báo cổng cố định mà chương trình này sẽ lắng nghe
    public static final int PORT = 1234; 

    public static void main(String[] args) {
        // 1. Tạo đối tượng giao diện Chat
        Chat app = new Chat(); 
        // 2. Hiển thị cửa sổ giao diện lên màn hình
        app.setVisible(true); 
        
        DatagramSocket socket = null; // Socket để nhận tin nhắn
        String strContent = "";     // Biến tạm lưu nội dung chat (có vẻ không cần thiết)
        
        try {
            // 3. Tạo buffer (bộ đệm) để chứa dữ liệu nhận được
            byte []buffer = new byte[1024]; // Kích thước buffer 1KB
            
            // 4. Tạo DatagramSocket và bind (gắn) nó vào cổng PORT (1234)
            // Chương trình sẽ lắng nghe tất cả các gói tin UDP gửi đến cổng này.
            socket = new DatagramSocket(PORT); 
            System.out.println("UDP Chat Receiver listening on port " + PORT);
            
            // Biến cờ để dừng vòng lặp (logic hiện tại có vấn đề)
            boolean ktFinish = false; 
            DatagramPacket receivePacket; // Đối tượng để chứa gói tin nhận được
            String stReceive;             // Chuỗi chứa tin nhắn nhận được
            
            // 5. Vòng lặp vô tận để liên tục chờ nhận tin nhắn
            while(ktFinish != true){ // Logic này sẽ chạy mãi trừ khi nhận "end"
                // Tạo DatagramPacket rỗng, trỏ vào buffer để chuẩn bị nhận
                receivePacket = new DatagramPacket(buffer, buffer.length);
                
                // 6. Chờ nhận gói tin UDP (Lệnh này sẽ block/dừng chương trình tại đây)
                socket.receive(receivePacket); 
                
                // 7. Chuyển đổi dữ liệu byte nhận được thành chuỗi String (sử dụng encoding mặc định)
                // Lấy đúng độ dài dữ liệu thực tế nhận được (getLength()).
                stReceive = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Received: " + stReceive);
                
                // --- Cập nhật giao diện ---
                // 8. Lấy nội dung chat hiện tại từ JTextArea trên giao diện
                strContent = app.getContentChat(); 
                // 9. Nối thêm tin nhắn vừa nhận vào nội dung cũ
                strContent += "Nhan: " + stReceive + "\n"; 
                // 10. Cập nhật lại toàn bộ nội dung cho JTextArea trên giao diện
                app.setContentChat(strContent); 
                
                // 11. Kiểm tra điều kiện dừng (có vẻ chưa hoàn chỉnh)
                // Nếu tin nhắn nhận được là "end" thì dừng vòng lặp.
                // So sánh strContent cũng không hợp lý ở đây.
                if(stReceive.equalsIgnoreCase("end")){ // Nên dùng equalsIgnoreCase
                    ktFinish = true;
                    System.out.println("Termination message received. Stopping receiver.");
                }
            }
        } catch (Exception e) {
            // Nên có xử lý lỗi cụ thể hơn hoặc thông báo cho người dùng
             System.err.println("Error receiving UDP packet: " + e.getMessage());
             e.printStackTrace();
        } finally {
            // 12. Đóng socket khi vòng lặp kết thúc (do nhận "end" hoặc do lỗi)
            if (socket != null && !socket.isClosed()) {
                socket.close();
                System.out.println("Receiver socket closed.");
            }
        }
    }
}