/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Cau_1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 *
 * @author Asus
 */
public class Server {
    public static void main(String[] args) {
        // Tạo socket server lắng nghe ở cổng 9876
        try (DatagramSocket serverSocket = new DatagramSocket(9876)) {
            System.out.println("Server đang chạy và lắng nghe ở cổng 9876...");

            // Vòng lặp vô tận để chờ client kết nối
            while (true) {
                // Tạo buffer để nhận dữ liệu từ client
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

                // Chờ và nhận gói tin từ client (lời gọi blocking)
                serverSocket.receive(receivePacket);
                System.out.println("Đã nhận yêu cầu từ client.");

                // Lấy dữ liệu từ gói tin
                // Chuyển đổi byte sang String, chỉ lấy phần dữ liệu thực tế
                String data = new String(receivePacket.getData(), 0, receivePacket.getLength()).trim();
                System.out.println("Dữ liệu nhận được: \"" + data + "\"");

                // Xử lý dữ liệu: tách chuỗi và tính tổng
                String[] numbers = data.split(" ");
                int sum = 0;
                try {
                    int num1 = Integer.parseInt(numbers[0]);
                    int num2 = Integer.parseInt(numbers[1]);
                    sum = num1 + num2;
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    System.err.println("Dữ liệu nhận được không hợp lệ: " + data);
                    continue; // Bỏ qua và chờ yêu cầu tiếp theo
                }

                // Lấy địa chỉ IP và cổng của client từ gói tin đã nhận
                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();

                // Chuẩn bị dữ liệu để gửi về client
                String result = String.valueOf(sum);
                byte[] sendData = result.getBytes();
                System.out.println("Gửi kết quả '" + result + "' về cho client.");

                // Tạo gói tin để gửi về client
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);

                // Gửi gói tin đi
                serverSocket.send(sendPacket);
            }
        } catch (IOException e) {
            System.err.println("Lỗi Server: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
