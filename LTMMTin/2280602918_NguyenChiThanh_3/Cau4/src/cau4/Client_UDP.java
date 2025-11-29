package cau4;
import java.io.*;
import java.net.*;

public class Client_UDP {
    public static void main(String[] args) throws IOException {
        try (DatagramSocket clientSocket = new DatagramSocket()) {
            InetAddress serverAddress = InetAddress.getByName("localhost");
            int serverPort = 9014;
            
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Nhập dãy số cách nhau bằng dấu phẩy (ví dụ: 1,2,3.5,4): ");
            String input = reader.readLine().trim();
            
            byte[] dataToSend = input.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(dataToSend, dataToSend.length, serverAddress, serverPort);
            clientSocket.send(sendPacket);
            
            // Nhận kết quả từ server
            byte[] buffer = new byte[256];
            DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);
            clientSocket.receive(receivePacket);
            
            String result = new String(receivePacket.getData(), 0, receivePacket.getLength()).trim();
            System.out.println("Tổng dãy số là: " + result);
        }
    }
}
