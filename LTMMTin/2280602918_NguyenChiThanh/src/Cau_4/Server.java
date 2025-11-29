package Cau_4;

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
                    int tong = tinhTong(input);
                    String result = "Tong day so la: " + tong;
                    out.println(result);
                    System.out.println("Server gui: " + result);
                } catch (Exception e) {
                    out.println("Loi xu ly du lieu");
                    System.out.println("Server gui: Loi xu ly du lieu");
                }

                socket.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static int tinhTong(String input) throws Exception {
        String[] parts = input.split(",");
        int sum = 0;
        for (String part : parts) {
            sum += Integer.parseInt(part.trim());
        }
        return sum;
    }
}
