/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Cau_1;

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
        // Sử dụng try-with-resources để tự động đóng socket và scanner
        try (DatagramSocket clientSocket = new DatagramSocket();
             Scanner scanner = new Scanner(System.in)) {

            // Lấy địa chỉ IP của server (ở đây là localhost)
            InetAddress serverAddress = InetAddress.getByName("localhost");
            int serverPort = 9876;

            // Nhập hai số nguyên từ bàn phím
            System.out.print("Nhập số nguyên thứ nhất: ");
            int num1 = scanner.nextInt();
            System.out.print("Nhập số nguyên thứ hai: ");
            int num2 = scanner.nextInt();

            // Chuẩn bị dữ liệu để gửi đi
            String dataToSend = num1 + " " + num2;
            byte[] sendData = dataToSend.getBytes();

            // Tạo gói tin để gửi đến server
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, serverPort);

            // Gửi gói tin đi
            clientSocket.send(sendPacket);
            System.out.println("Đã gửi hai số tới server.");

            // Tạo buffer để nhận kết quả từ server
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

            // Chờ và nhận gói tin kết quả từ server
            clientSocket.receive(receivePacket);

            // Lấy dữ liệu từ gói tin nhận được và hiển thị
            String result = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Kết quả tổng từ Server: " + result);

        } catch (IOException e) {
            System.err.println("Lỗi Client: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
