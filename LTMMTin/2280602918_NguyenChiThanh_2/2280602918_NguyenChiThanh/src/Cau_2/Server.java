/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Cau_2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 *
 * @author Asus
 */
public class Server {
    /*
     * Hàm kiểm tra một số có phải là số nguyên tố hay không.
     * @param n Số cần kiểm tra.
     * @return true nếu n là số nguyên tố, ngược lại là false.
     */
    private static boolean isPrime(int n) {
        // Số nguyên tố phải lớn hơn 1
        if (n <= 1) {
            return false;
        }
        // Kiểm tra các ước số từ 2 đến căn bậc hai của n
        // Đây là cách tối ưu để kiểm tra số nguyên tố
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false; // Nếu có ước số, không phải là số nguyên tố
            }
        }
        return true; // Nếu không tìm thấy ước số nào, là số nguyên tố
    }

    public static void main(String[] args) {
        // Tạo socket server lắng nghe ở cổng 9877 (sử dụng cổng khác để tránh xung đột)
        try (DatagramSocket serverSocket = new DatagramSocket(9877)) {
            System.out.println("Prime Server đang chạy và lắng nghe ở cổng 9877...");

            while (true) {
                // Buffer để nhận dữ liệu
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

                // Chờ và nhận gói tin từ client
                serverSocket.receive(receivePacket);
                System.out.println("Đã nhận yêu cầu từ client.");

                // Lấy dữ liệu và chuyển thành số nguyên
                String data = new String(receivePacket.getData(), 0, receivePacket.getLength()).trim();
                String response;
                try {
                    int number = Integer.parseInt(data);
                    System.out.println("Số nhận được: " + number);
                    
                    // Kiểm tra số nguyên tố và chuẩn bị phản hồi
                    if (isPrime(number)) {
                        response = "Prime";
                    } else {
                        response = "Not Prime";
                    }
                } catch (NumberFormatException e) {
                    response = "Invalid Input";
                    System.err.println("Dữ liệu nhận được không phải là số: " + data);
                }

                // Lấy địa chỉ IP và cổng của client
                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();

                // Chuẩn bị dữ liệu để gửi về
                byte[] sendData = response.getBytes();
                System.out.println("Gửi kết quả \"" + response + "\" về cho client.");

                // Tạo và gửi gói tin phản hồi
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
                serverSocket.send(sendPacket);
            }
        } catch (IOException e) {
            System.err.println("Lỗi Server: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
