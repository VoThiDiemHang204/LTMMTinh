
package cau10;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Client_UDP {
    public static void main(String[] args) {
        try {
            try (DatagramSocket socket = new DatagramSocket()) {
                Scanner scanner = new Scanner(System.in);
                
                System.out.print("Nhập số nguyên dương cần kiểm tra Fibonacci: ");
                String input = scanner.nextLine();
                
                byte[] sendData = input.getBytes();
                InetAddress serverAddress = InetAddress.getByName("localhost");
                int serverPort = 8888;
                
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, serverPort);
                socket.send(sendPacket);
                
                byte[] buffer = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);
                socket.receive(receivePacket);
                
                String result = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Kết quả từ server: " + result);
            }
        } catch (IOException e) {
        }
    }
}


