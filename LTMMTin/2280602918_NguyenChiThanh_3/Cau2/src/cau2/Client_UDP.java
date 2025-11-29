package cau2;
import java.io.*;
import java.net.*;

public class Client_UDP {
    public static void main(String[] args) throws IOException {
        try (DatagramSocket clientSocket = new DatagramSocket()) {
            InetAddress serverAddress = InetAddress.getByName("localhost");
            int serverPort = 9010;
            
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Nhập số nguyên cần kiểm tra: ");
            int number = Integer.parseInt(reader.readLine());
            
            // Chuyển số sang dạng byte để gửi
            byte[] dataToSend = String.valueOf(number).getBytes();
            DatagramPacket sendPacket = new DatagramPacket(dataToSend, dataToSend.length, serverAddress, serverPort);
            clientSocket.send(sendPacket);
            
            // Chuẩn bị nhận kết quả trả về
            byte[] buffer = new byte[256];
            DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);
            clientSocket.receive(receivePacket);
            
            String result = new String(receivePacket.getData(), 0, receivePacket.getLength()).trim();
            System.out.println("Kết quả từ Server: " + result);
        }
    }
}
