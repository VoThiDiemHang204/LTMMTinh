package Cau_1;

import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        int port = 12345;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server dang lang nghe tren cong " + port);

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client da ket noi: " + socket.getInetAddress());

                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

                String input = in.readLine();
                System.out.println("Server nhan: " + input);

                try {
                    String[] parts = input.split(",");
                    int a = Integer.parseInt(parts[0].trim());
                    int b = Integer.parseInt(parts[1].trim());
                    int sum = a + b;
                    String result = "Tong la: " + sum;
                    out.println(result);
                    System.out.println("Server gui: " + result);
                } catch (Exception e) {
                    out.println("Loi dau vao khong hop le");
                    System.out.println("Server gui: Loi dau vao khong hop le");
                }

                socket.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
