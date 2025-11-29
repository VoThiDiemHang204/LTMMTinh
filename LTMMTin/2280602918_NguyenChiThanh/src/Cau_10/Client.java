package Cau_10;

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
            System.out.print("Nhap so nguyen duong n: ");
            String number = scanner.nextLine();

            out.println(number);

            String response = in.readLine();
            System.out.println("Server tra loi: " + response);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
