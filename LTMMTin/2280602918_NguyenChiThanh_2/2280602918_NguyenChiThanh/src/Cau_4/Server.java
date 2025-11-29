/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Cau_4;

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
        // Lắng nghe ở cổng 9879
        try (DatagramSocket serverSocket = new DatagramSocket(9879)) {
            System.out.println("Sum Array Server đang chạy ở cổng 9879...");

            while (true) {
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket); // Chờ nhận dữ liệu từ client
                System.out.println("\nĐã nhận yêu cầu từ client.");

                // Lấy chuỗi dữ liệu từ gói tin
                String data = new String(receivePacket.getData(), 0, receivePacket.getLength()).trim();
                System.out.println("Dữ liệu nhận được: \"" + data + "\"");
                String response;

                try {
                    // Tách chuỗi bằng dấu phẩy
                    String[] numberStrings = data.split(",");
                    double sum = 0;

                    // Lặp qua mảng chuỗi số và tính tổng
                    for (String numStr : numberStrings) {
                        // trim() để loại bỏ khoảng trắng thừa (ví dụ: "5, 10, 15")
                        sum += Double.parseDouble(numStr.trim());
                    }
                    
                    // Chuyển kết quả thành chuỗi
                    response = String.valueOf(sum);

                } catch (NumberFormatException e) {
                    response = "Lỗi: Dữ liệu chứa phần tử không phải là số.";
                    System.err.println(response);
                }

                // Gửi phản hồi về client
                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();
                byte[] sendData = response.getBytes();
                System.out.println("Gửi kết quả tổng: \"" + response + "\"");

                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
                serverSocket.send(sendPacket);
            }
        } catch (IOException e) {
            System.err.println("Lỗi Server: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
