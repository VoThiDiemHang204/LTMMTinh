package Cau_10;

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

                int n = Integer.parseInt(input.trim());
                boolean isFibo = checkFibonacci(n);

                String result;
                if (isFibo) {
                    result = "So " + n + " thuoc day Fibonacci";
                } else {
                    result = "So " + n + " KHONG thuoc day Fibonacci";
                }

                out.println(result);
                System.out.println("Server gui: " + result);

                socket.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static boolean checkFibonacci(int n) {
        return isPerfectSquare(5 * n * n + 4) || isPerfectSquare(5 * n * n - 4);
    }

    private static boolean isPerfectSquare(int x) {
        int s = (int)Math.sqrt(x);
        return s * s == x;
    }
}
