package Cau_6;

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

                // Nhận dữ liệu từ client
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

                String input = in.readLine();
                System.out.println("Server nhan: " + input);

                // Kiểm tra đối xứng
                boolean isPalindrome = checkPalindrome(input);

                String result = isPalindrome ? "Đoi xung" : "Khong doi xung";
                out.println(result);
                System.out.println("Server gui: " + result);

                socket.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static boolean checkPalindrome(String number) {
        int len = number.length();
        for (int i = 0; i < len / 2; i++) {
            if (number.charAt(i) != number.charAt(len - 1 - i)) {
                return false;
            }
        }
        return true;
    }
}
