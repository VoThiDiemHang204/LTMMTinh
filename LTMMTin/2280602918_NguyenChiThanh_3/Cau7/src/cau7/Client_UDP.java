package cau7;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Client_UDP {
    public static void main(String[] args) {
        final String SERVER_HOST = "localhost";
        final int SERVER_PORT = 9999;

        try (DatagramSocket socket = new DatagramSocket();
             Scanner scanner = new Scanner(System.in)) {

            // Nhập số và hệ đếm
            System.out.print("Nhập số nguyên dương: ");
            int number = scanner.nextInt();
            System.out.print("Nhập hệ đếm muốn chuyển (2, 8 hoặc 16): ");
            int base = scanner.nextInt();

            // Tạo thông điệp và gửi đi
            String message = number + "," + base;
            byte[] sendData = message.getBytes();
            InetAddress serverAddress = InetAddress.getByName(SERVER_HOST);
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, SERVER_PORT);
            socket.send(sendPacket);

            // Nhận kết quả từ server
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            socket.receive(receivePacket);

            String result = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Kết quả chuyển đổi: " + result);

        } catch (Exception e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
    }
}
