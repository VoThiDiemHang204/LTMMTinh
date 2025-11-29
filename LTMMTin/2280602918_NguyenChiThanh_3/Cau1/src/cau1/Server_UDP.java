package cau1;
import java.io.*;
import java.net.*;

public class Server_UDP {
    public static void main(String[] args) throws IOException {
        try (DatagramSocket serverSocket = new DatagramSocket(9008)) {
            System.out.println("Server đã sẵn sàng...");
            
            // Nhận số a
            byte[] bufferA = new byte[256];
            DatagramPacket packetA = new DatagramPacket(bufferA, bufferA.length);
            serverSocket.receive(packetA);
            String strA = new String(packetA.getData(), 0, packetA.getLength()).trim();
            int a = Integer.parseInt(strA);
            
            // Nhận số b
            byte[] bufferB = new byte[256];
            DatagramPacket packetB = new DatagramPacket(bufferB, bufferB.length);
            serverSocket.receive(packetB);
            String strB = new String(packetB.getData(), 0, packetB.getLength()).trim();
            int b = Integer.parseInt(strB);
            
            // Tính tổng
            int tong = a + b;
            System.out.println("Tính tổng: " + a + " + " + b + " = " + tong);
            
            // Gửi kết quả về client 
            String result = String.valueOf(tong);
            byte[] resultBytes = result.getBytes();
            
            InetAddress clientAddress = packetA.getAddress();
            int clientPort = packetA.getPort();
            
            DatagramPacket response = new DatagramPacket(resultBytes, resultBytes.length, clientAddress, clientPort);
            serverSocket.send(response);
        }
    }
}
