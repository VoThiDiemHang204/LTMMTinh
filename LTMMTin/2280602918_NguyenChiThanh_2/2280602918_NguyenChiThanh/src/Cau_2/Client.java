/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Cau_2;

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

            // Địa chỉ server
            InetAddress serverAddress = InetAddress.getByName("localhost");
            int serverPort = 9877;

            // Nhập một số nguyên từ bàn phím
            System.out.print("Nhập một số nguyên để kiểm tra: ");
            int number = scanner.nextInt();

            // Chuẩn bị dữ liệu gửi đi (chuyển số thành chuỗi)
            String dataToSend = String.valueOf(number);
            byte[] sendData = dataToSend.getBytes();

            // Tạo và gửi gói tin đến server
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, serverPort);
            clientSocket.send(sendPacket);
            System.out.println("Đã gửi số " + number + " đến server.");

            // Buffer để nhận kết quả
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

            // Chờ nhận phản hồi từ server
            clientSocket.receive(receivePacket);

            // Hiển thị kết quả
            String response = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Kết quả từ Server: " + response);
            if(response.equalsIgnoreCase("Prime")){
                System.out.println("=> Vậy " + number + " là số nguyên tố.");
            } else {
                System.out.println("=> Vậy " + number + " không phải là số nguyên tố.");
            }

        } catch (IOException e) {
            System.err.println("Lỗi Client: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
