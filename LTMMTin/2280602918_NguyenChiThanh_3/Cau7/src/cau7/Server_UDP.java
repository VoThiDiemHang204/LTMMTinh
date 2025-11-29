package cau7;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Server_UDP {
    public static void main(String[] args) {
        final int SERVER_PORT = 9999;

        try (DatagramSocket socket = new DatagramSocket(SERVER_PORT)) {
            System.out.println("Server UDP đang chạy ở cổng " + SERVER_PORT + "...");

            while (true) {
                // Nhận dữ liệu từ client
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                socket.receive(receivePacket);

                String message = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Yêu cầu nhận: " + message);

                // Tách dữ liệu: "123,2"
                String[] parts = message.split(",");
                int number = Integer.parseInt(parts[0]);
                int base = Integer.parseInt(parts[1]);

                String result;
                result = switch (base) {
                    case 2 -> "Hệ nhị phân: " + Integer.toBinaryString(number);
                    case 8 -> "Hệ bát phân: " + Integer.toOctalString(number);
                    case 16 -> "Hệ thập lục phân: " + Integer.toHexString(number).toUpperCase();
                    default -> "Hệ đếm không hợp lệ! Chỉ chấp nhận 2, 8 hoặc 16.";
                };

                // Gửi kết quả lại cho client
                byte[] sendData = result.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(
                        sendData, sendData.length,
                        receivePacket.getAddress(), receivePacket.getPort()
                );
                socket.send(sendPacket);
            }

        } catch (Exception e) {
            System.out.println("Lỗi Server: " + e.getMessage());
        }
    }
}
