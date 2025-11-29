package cau5;
import java.io.*;
import java.net.*;

public class Client_UDP{
    public static void main(String[] args) throws IOException {
        try (DatagramSocket clientSocket = new DatagramSocket()) {
            InetAddress serverAddress = InetAddress.getByName("localhost");
            int serverPort = 9016;
            
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Nhập biểu thức hậu tố (VD: 3 4 + 2 *): ");
            String expression = reader.readLine().trim();
            
            byte[] dataToSend = expression.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(dataToSend, dataToSend.length, serverAddress, serverPort);
            clientSocket.send(sendPacket);
            
            // Nhận kết quả
            byte[] buffer = new byte[256];
            DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);
            clientSocket.receive(receivePacket);
            
            String result = new String(receivePacket.getData(), 0, receivePacket.getLength()).trim();
            System.out.println("Kết quả tính toán: " + result);
        }
    }
}
