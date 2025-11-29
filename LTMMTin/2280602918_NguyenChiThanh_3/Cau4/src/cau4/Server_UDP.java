package cau4;
import java.io.*;
import java.net.*;

public class Server_UDP{
    public static void main(String[] args) throws IOException {
        try (DatagramSocket serverSocket = new DatagramSocket(9014)) {
            System.out.println("Server tính tổng dãy số đang chạy...");
            
            byte[] buffer = new byte[512];
            DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);
            serverSocket.receive(receivePacket);
            
            String received = new String(receivePacket.getData(), 0, receivePacket.getLength()).trim();
            System.out.println("Nhận được chuỗi: " + received);
            
            // Xử lý chuỗi để tính tổng
            String[] numbers = received.split(",");
            double sum = 0;
            for (String numStr : numbers) {
                try {
                    sum += Double.parseDouble(numStr.trim());
                } catch (NumberFormatException e) {
                    // Bỏ qua phần tử không hợp lệ
                }
            }
            
            String result = String.valueOf(sum);
            byte[] resultBytes = result.getBytes();
            
            InetAddress clientAddress = receivePacket.getAddress();
            int clientPort = receivePacket.getPort();
            
            DatagramPacket sendPacket = new DatagramPacket(resultBytes, resultBytes.length, clientAddress, clientPort);
            serverSocket.send(sendPacket);
        }
    }
}
