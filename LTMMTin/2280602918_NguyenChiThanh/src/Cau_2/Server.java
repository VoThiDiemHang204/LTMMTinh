package Cau_2;

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
                    int n = Integer.parseInt(input.trim());
                    String result = isPrime(n) ? "Prime" : "Not Prime";
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

    private static boolean isPrime(int n) {
        if (n < 2) return false;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
}
