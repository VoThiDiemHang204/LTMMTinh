package cau8;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Client_UDP{
    public static void main(String[] args) {
        final int SERVER_PORT = 9999;
        final String SERVER_HOST = "localhost";

        try (DatagramSocket socket = new DatagramSocket();
             Scanner scanner = new Scanner(System.in)) {

            System.out.print("Nhập số nguyên n: ");
            int n = scanner.nextInt();
            String msg = String.valueOf(n);

            byte[] sendData = msg.getBytes();
            InetAddress serverAddr = InetAddress.getByName(SERVER_HOST);

            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddr, SERVER_PORT);
            socket.send(sendPacket);

            // Nhận kết quả
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            socket.receive(receivePacket);

            String result = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Kết quả từ server: " + result);

        } catch (Exception e) {
        }
    }
}
