/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ThucHanh1; // Đảm bảo đúng package của bạn

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Lớp UDPServer: Lắng nghe và xử lý dữ liệu từ UDP Client.
 * @author Administrator
 */
public class udpserver { // Tên lớp nên viết hoa chữ cái đầu: UdpServer

    // Định nghĩa cổng (port) mà server sẽ lắng nghe.
    // Client cần biết cổng này để gửi dữ liệu đến đúng server.
    static final int PORT = 1234; 
    
    // Khai báo đối tượng DatagramSocket để gửi và nhận gói tin UDP.
    private DatagramSocket socket = null; 

    // Hàm khởi tạo (Constructor) của server.
    public udpserver(){ // Tên constructor nên trùng tên lớp: UdpServer
        try{
            // Tạo DatagramSocket và ràng buộc (bind) nó vào cổng PORT đã định nghĩa.
            // Server sẽ bắt đầu lắng nghe các gói tin UDP đến cổng này.
            socket = new DatagramSocket(PORT);  
        } catch(Exception e){
            // In lỗi ra console nếu không thể tạo hoặc bind socket (ví dụ: cổng đã bị dùng).
            e.printStackTrace();
        }
    }

    // Phương thức chính xử lý logic của server.
    public void action(){
        InetAddress host = null; // Biến lưu địa chỉ IP của client gửi đến.
        int port; // Biến lưu cổng (port) của client gửi đến.
        String chuoi=""; // Biến lưu chuỗi dữ liệu nhận được từ client.
        
        try{
            System.out.println("Server is listening on port " + PORT);
            
            // Vòng lặp vô tận để server luôn hoạt động và chờ nhận dữ liệu.
            while(true){ 
                // Gọi hàm receive() để chờ và nhận một gói tin DatagramPacket từ client.
                DatagramPacket packet = receive(); 
                
                // Lấy địa chỉ IP của client từ gói tin vừa nhận.
                host = packet.getAddress(); 
                
                // Lấy số hiệu cổng (port) của client từ gói tin vừa nhận.
                port = packet.getPort(); 
                
                // Chuyển đổi dữ liệu byte trong gói tin thành chuỗi String.
                // .trim() để loại bỏ các khoảng trắng thừa hoặc ký tự null ở cuối buffer.
                chuoi = new String(packet.getData()).trim(); 
                
                System.out.println("Received from " + host + ":" + port + " - Data: " + chuoi);
                
                // Chuyển chuỗi nhận được thành chữ in hoa.
                chuoi = chuoi.toUpperCase(); 
                
                // Kiểm tra xem chuỗi có rỗng không (phòng trường hợp nhận gói tin trống).
                if(!chuoi.equals(""))
                    // Gọi hàm send() để gửi chuỗi đã xử lý (in hoa) trở lại đúng client.
                    send(chuoi, host, port); 
            }
        } catch(Exception e){
            // In lỗi ra console nếu có vấn đề trong quá trình nhận/gửi.
            e.printStackTrace();
        } finally{
            // Khối finally đảm bảo socket luôn được đóng khi vòng lặp kết thúc (mặc dù ở đây là vòng lặp vô tận).
            // Trong thực tế, cần có cơ chế để dừng server một cách an toàn và đóng socket.
            if (socket != null && !socket.isClosed()) {
                socket.close();
                System.out.println("Server socket closed.");
            }
        }       
    }
    
    /**
     * Gửi một chuỗi dữ liệu đến một địa chỉ host và cổng cụ thể qua UDP.
     * @param chuoi Chuỗi cần gửi.
     * @param host Địa chỉ IP của máy nhận.
     * @param port Cổng của máy nhận.
     * @throws IOException Nếu có lỗi khi gửi.
     */
    private void send(String chuoi, InetAddress host, int port) throws IOException{
        // Chuyển chuỗi String thành mảng byte để gửi đi.
        byte[] buffer = chuoi.getBytes(); 
        
        // Tạo một DatagramPacket mới chứa dữ liệu (buffer), độ dài dữ liệu, 
        // địa chỉ IP đích (host) và cổng đích (port).
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, host, port);
        
        // Gửi gói tin đi qua socket.
        socket.send(packet); 
        System.out.println("Sent response to " + host + ":" + port + " - Data: " + chuoi);
    }
    
    
    /**
     * Chờ và nhận một gói tin UDP đến.
     * @return DatagramPacket chứa dữ liệu nhận được.
     * @throws IOException Nếu có lỗi khi nhận.
     */
    private DatagramPacket receive() throws IOException{
        // Tạo một buffer (mảng byte) đủ lớn để chứa dữ liệu nhận được. 
        // Kích thước tối đa lý thuyết của UDP payload là 65507 bytes.
        byte[] buffer = new byte[65507]; 
        
        // Tạo một DatagramPacket rỗng, trỏ vào buffer này để lưu dữ liệu nhận được.
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        
        // Lệnh này sẽ block (dừng) chương trình cho đến khi có một gói tin UDP đến cổng của socket.
        // Dữ liệu nhận được sẽ được ghi vào buffer của packet.
        socket.receive(packet); 
        
        // Trả về gói tin đã chứa dữ liệu.
        return packet; 
    }

    // Hàm main để khởi động server.
    public static void main(String[] args) {
        // Tạo một đối tượng udpserver (server).
        udpserver server = new udpserver(); // Tên nên là UdpServer
        // Gọi phương thức action() để bắt đầu lắng nghe và xử lý.
        server.action(); 
    }
}