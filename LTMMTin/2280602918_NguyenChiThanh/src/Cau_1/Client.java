package Cau_1;

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
            System.out.print("Nhap 2 so nguyen (vi du 5,7): ");
            String input = scanner.nextLine();

            out.println(input);

            String response = in.readLine();
            System.out.println("Server tra loi: " + response);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
