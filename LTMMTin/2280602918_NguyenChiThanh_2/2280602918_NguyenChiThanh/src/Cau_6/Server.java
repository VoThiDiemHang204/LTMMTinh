/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Cau_6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Asus
 */
public class Server {
    /*
     * Hàm kiểm tra một số có phải là số đối xứng hay không.
     * @param number Số nguyên dương cần kiểm tra.
     * @return true nếu là số đối xứng, ngược lại là false.
     */
    private static boolean isPalindrome(int number) {
        if (number < 0) {
            return false; // Số âm không được coi là đối xứng trong bài toán này
        }
        String originalStr = String.valueOf(number);
        String reversedStr = new StringBuilder(originalStr).reverse().toString();
        return originalStr.equals(reversedStr);
    }

    public static void main(String[] args) {
        int port = 6789; // Cổng để server lắng nghe
        
        // Sử dụng try-with-resources để tự động đóng ServerSocket
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server đang chạy và lắng nghe ở cổng " + port + "...");

            // Vòng lặp vô tận để chấp nhận nhiều client (tuần tự)
            while (true) {
                // Chấp nhận một kết nối từ client, đây là một lời gọi blocking
                // Sử dụng try-with-resources để tự động đóng clientSocket và các luồng
                try (Socket clientSocket = serverSocket.accept()) {
                    System.out.println("Đã kết nối với client: " + clientSocket.getInetAddress().getHostAddress());

                    // Tạo luồng đọc và ghi cho client này
                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true); // true để tự động flush

                    // Đọc dữ liệu từ client
                    String inputLine = in.readLine();
                    System.out.println("Nhận từ client: " + inputLine);
                    String response;

                    try {
                        int number = Integer.parseInt(inputLine);
                        if (isPalindrome(number)) {
                            response = number + " là số đối xứng.";
                        } else {
                            response = number + " không phải là số đối xứng.";
                        }
                    } catch (NumberFormatException e) {
                        response = "Lỗi: Dữ liệu gửi lên không phải là một số nguyên hợp lệ.";
                    }
                    
                    // Gửi phản hồi về cho client
                    out.println(response);
                    System.out.println("Đã gửi phản hồi: \"" + response + "\"");
                } catch (IOException e) {
                    System.err.println("Lỗi khi giao tiếp với client: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Không thể khởi động server ở cổng " + port);
            e.printStackTrace();
        }
    }
}
