package Cau_7;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 12345;

        try (Socket socket = new Socket(host, port)) {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            Scanner scanner = new Scanner(System.in);
            System.out.print("Nhập so nguyen duong: ");
            String number = scanner.nextLine();

            System.out.print("Nhap he dem muon chuyen (2, 8 hoac 16): ");
            String base = scanner.nextLine();

            // Gửi đến server
            out.println(number);
            out.println(base);

            // Nhận và in kết quả
            String response = in.readLine();
            System.out.println("Server tra loi: " + response);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
