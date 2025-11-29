package cau6;
import java.io.*;
import java.net.*;

public class Client_UDP {
    public static void main(String[] args) throws IOException {
        try (DatagramSocket clientSocket = new DatagramSocket()) {
            InetAddress serverAddress = InetAddress.getByName("localhost");
            int serverPort = 9022;
            
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Nhập số nguyên dương: ");
            String number = reader.readLine().trim();
            
            byte[] sendData = number.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, serverPort);
            clientSocket.send(sendPacket);
            
            // Nhận phản hồi từ server
            byte[] receiveData = new byte[256];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);
            
            String response = new String(receivePacket.getData(), 0, receivePacket.getLength()).trim();
            System.out.println("Kết quả từ server: " + response);
        }
    }
}
