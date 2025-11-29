/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Cau_4;

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
            int serverPort = 9879;

            // Yêu cầu người dùng nhập dãy số
            System.out.print("Nhập một dãy số, cách nhau bằng dấu phẩy (VD: 5,10.5,15,20): ");
            String dataToSend = scanner.nextLine(); // Đọc cả dòng

            // Kiểm tra nếu người dùng không nhập gì
            if (dataToSend.trim().isEmpty()) {
                System.out.println("Bạn chưa nhập dữ liệu. Đang thoát...");
                return;
            }

            // Gửi dữ liệu đến server
            byte[] sendData = dataToSend.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, serverPort);
            clientSocket.send(sendPacket);
            System.out.println("Đã gửi dãy số đến server...");

            // Nhận kết quả từ server
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);

            // Hiển thị kết quả
            String response = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("\n✅ Kết quả từ Server:");
            System.out.println("Tổng của dãy số là: " + response);

        } catch (IOException e) {
            System.err.println("Lỗi Client: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
