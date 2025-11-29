package cau9;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Client_UDP {
    public static void main(String[] args) throws Exception {
        try (DatagramSocket clientSocket = new DatagramSocket()) {
            InetAddress serverAddress = InetAddress.getByName("localhost");
            byte[] sendData;
            byte[] receiveData = new byte[1024];
            
            Scanner scanner = new Scanner(System.in);
            System.out.print("Nhập danh sách số thực (ngăn cách bằng dấu phẩy): ");
            String input = scanner.nextLine();
            
            sendData = input.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, 9876);
            clientSocket.send(sendPacket);
            
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);
            String response = new String(receivePacket.getData(), 0, receivePacket.getLength());
            
            System.out.println("Phản hồi từ server: " + response);
        }
    }
}
