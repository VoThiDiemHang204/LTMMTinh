/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Cau_6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 *
 * @author Asus
 */
public class Client {
    public static void main(String[] args) {
        String hostname = "localhost";
        int port = 6789;

        // Sử dụng try-with-resources để tự động đóng các tài nguyên
        try (
            Socket socket = new Socket(hostname, port);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Scanner stdIn = new Scanner(System.in)
        ) {
            System.out.print("Nhập một số nguyên dương để kiểm tra: ");
            String userInput = stdIn.nextLine();

            // Gửi dữ liệu đến server
            out.println(userInput);
            System.out.println("Đã gửi '" + userInput + "' đến server.");

            // Đọc phản hồi từ server và hiển thị
            String serverResponse = in.readLine();
            System.out.println("✅ Kết quả từ Server: " + serverResponse);

        } catch (UnknownHostException e) {
            System.err.println("Không tìm thấy máy chủ: " + hostname);
        } catch (IOException e) {
            System.err.println("Lỗi I/O khi kết nối tới " + hostname + ". Server có đang chạy không?");
        }
    }
}
