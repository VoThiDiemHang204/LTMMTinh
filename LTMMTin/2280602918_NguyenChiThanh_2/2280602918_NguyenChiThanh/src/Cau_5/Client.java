/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Cau_5;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

/**
 *
 * @author Asus
 */
public class Client {
    public static void main(String[] args) {
        try (DatagramSocket clientSocket = new DatagramSocket();
             Scanner scanner = new Scanner(System.in)) {

            InetAddress serverAddress = InetAddress.getByName("localhost");
            int serverPort = 9880;

            // Yêu cầu người dùng nhập biểu thức
            System.out.println("Nhập biểu thức hậu tố (các thành phần cách nhau bằng dấu cách).");
            System.out.print("Ví dụ: 3 4 + 2 * --> (3+4)*2 = 14\n> ");
            String dataToSend = scanner.nextLine();

            if (dataToSend.trim().isEmpty()) {
                System.out.println("Bạn chưa nhập dữ liệu. Đang thoát...");
                return;
            }

            // Gửi dữ liệu đến server
            byte[] sendData = dataToSend.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, serverPort);
            clientSocket.send(sendPacket);
            System.out.println("Đã gửi biểu thức đến server...");

            // Nhận kết quả từ server
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);

            // Hiển thị kết quả
            String response = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("\n✅ Kết quả từ Server: " + response);

        } catch (IOException e) {
            System.err.println("Lỗi Client: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
