package Cau_9;

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
            System.out.print("Nhap danh sach so (vi du 2.5,3.7,5.0): ");
            String numbers = scanner.nextLine();

            out.println(numbers);

            String response;
            while ((response = in.readLine()) != null) {
                System.out.println(response);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
