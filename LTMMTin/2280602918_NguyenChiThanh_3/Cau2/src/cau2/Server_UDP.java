package cau2;
import java.io.*;
import java.net.*;

public class Server_UDP{
    public static void main(String[] args) throws IOException {
        try (DatagramSocket serverSocket = new DatagramSocket(9010)) {
            System.out.println("Server kiểm tra số nguyên tố đang chạy...");
            
            byte[] buffer = new byte[256];
            DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);
            serverSocket.receive(receivePacket);
            
            String receivedData = new String(receivePacket.getData(), 0, receivePacket.getLength()).trim();
            int number = Integer.parseInt(receivedData);
            System.out.println("Nhận được số: " + number);
            
            // Kiểm tra số nguyên tố
            String result = isPrime(number) ? "Prime" : "Not Prime";
            
            // Gửi kết quả về client
            byte[] resultBytes = result.getBytes();
            InetAddress clientAddress = receivePacket.getAddress();
            int clientPort = receivePacket.getPort();
            
            DatagramPacket sendPacket = new DatagramPacket(resultBytes, resultBytes.length, clientAddress, clientPort);
            serverSocket.send(sendPacket);
        }
    }

    // Hàm kiểm tra số nguyên tố
    private static boolean isPrime(int n) {
        if (n < 2) return false;
        if (n == 2) return true;
        if (n % 2 == 0) return false;
        for (int i = 3; i <= Math.sqrt(n); i += 2) {
            if (n % i == 0) return false;
        }
        return true;
    }
}
