package Cau_4;

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
            System.out.print("Nhap day so (vi du 1,2,3,4): ");
            String numbers = scanner.nextLine();

            out.println(numbers);

            String response = in.readLine();
            System.out.println("Server tra loi: " + response);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
