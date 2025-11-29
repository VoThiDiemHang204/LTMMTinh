/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ThucHanh2; // Đảm bảo đúng package của bạn

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner; // Import lớp Scanner để phân tích chuỗi

/**
 * Lớp UDPServer: Lắng nghe yêu cầu tính toán từ UDP Client.
 * @author Administrator
 */
public class UDPServer {

    // Cổng lắng nghe của server
    static final int PORT = 1234; 
    // Socket UDP của server
    private DatagramSocket socket = null; 

    // Constructor: Tạo và bind socket vào cổng PORT
    public UDPServer(){ // Tên lớp và constructor nên viết hoa: UDPServer
        try{
            socket = new DatagramSocket(PORT);  
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    // Phương thức xử lý chính của server
    public void action(){
        InetAddress host = null; // Địa chỉ IP client
        int port; // Cổng client
        String chuoi = ""; // Chuỗi nhận được từ client (vd: "10@+@5")
        
        try{
            System.out.println("Calculation Server is listening on port " + PORT);
            
            // Vòng lặp vô tận chờ yêu cầu
            while(true){
                // Nhận gói tin từ client
                DatagramPacket packet = receive(); 
                host = packet.getAddress(); // Lấy IP client
                port = packet.getPort(); // Lấy cổng client
                
                // Chuyển dữ liệu byte thành chuỗi và loại bỏ khoảng trắng thừa
                chuoi = new String(packet.getData()).trim();       
                System.out.println("Received from " + host + ":" + port + " - Data: " + chuoi);
                
                // Chỉ xử lý nếu chuỗi không rỗng
                if(!chuoi.equals("")){
                    try {
                        // Tạo đối tượng Scanner để phân tích chuỗi nhận được
                        Scanner sc = new Scanner(chuoi); 
                        // Đặt ký tự '@' làm dấu phân cách (delimiter)
                        sc.useDelimiter("@"); 
                        
                        // Đọc số thứ nhất từ chuỗi
                        int so1 = sc.nextInt(); 
                        // Đọc phép toán từ chuỗi
                        String pheptoan = sc.next(); 
                        // Đọc số thứ hai từ chuỗi
                        int so2 = sc.nextInt(); 
                        
                        // Thực hiện phép tính dựa vào phép toán nhận được
                        if(pheptoan.equals("+"))
                            chuoi = (so1 + so2) + ""; // Cộng và chuyển kết quả thành String
                        else if(pheptoan.equals("-"))
                            chuoi = (so1 - so2) + ""; // Trừ và chuyển kết quả thành String
                        else if(pheptoan.equals("*")) 
                            chuoi = (so1 * so2) + ""; // Nhân và chuyển kết quả thành String
                        else if(pheptoan.equals("/")) {
                            // Kiểm tra chia cho 0
                            if (so2 == 0) {
                                chuoi = "Lỗi: Chia cho 0!";
                            } else {
                                // Thực hiện phép chia số thực và chuyển kết quả thành String
                                chuoi = ((float)so1 / so2) + ""; 
                            }
                        } else {
                            chuoi = "Lỗi: Phép toán không hợp lệ!";
                        }
                        
                        // Gửi kết quả (hoặc thông báo lỗi) về cho client
                        send(chuoi, host, port); 
                        
                    } catch (Exception parseEx) {
                        // Xử lý lỗi nếu client gửi sai định dạng (vd: "abc@+@5")
                        System.err.println("Error parsing input from " + host + ":" + port + " - Input: " + chuoi);
                        send("Lỗi: Dữ liệu gửi không đúng định dạng (số1@phéptoán@số2)", host, port);
                    }
                }
            }
        } catch(Exception e){
            e.printStackTrace();
        } finally{
            // Đóng socket khi server dừng (cần cơ chế dừng an toàn)
            if (socket != null && !socket.isClosed()) {
                socket.close();
                System.out.println("Server socket closed.");
            }
        }       
    }

    /**
     * Gửi chuỗi dữ liệu đến client cụ thể.
     * @param chuoi Chuỗi kết quả cần gửi.
     * @param host Địa chỉ IP client.
     * @param port Cổng client.
     * @throws IOException Lỗi khi gửi.
     */
    private void send(String chuoi, InetAddress host, int port) throws IOException{
        byte[] buffer = chuoi.getBytes(); // Chuyển chuỗi thành byte
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, host, port); // Tạo gói tin gửi
        socket.send(packet); // Gửi gói tin
        System.out.println("Sent response to " + host + ":" + port + " - Data: " + chuoi);
    }
    
    
    /**
     * Nhận một gói tin UDP.
     * @return Gói tin nhận được.
     * @throws IOException Lỗi khi nhận.
     */
    private DatagramPacket receive() throws IOException{
        byte[] buffer = new byte[65507]; // Buffer nhận dữ liệu
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length); // Tạo gói tin nhận
        socket.receive(packet); // Chờ nhận gói tin (blocking)
        return packet; // Trả về gói tin
    }

    // Hàm main khởi động server
    public static void main(String[] args) {
        new UDPServer().action(); // Tạo đối tượng server và chạy
    }
}