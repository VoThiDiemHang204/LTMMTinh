package Cau_7;

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

                // Nhận số và hệ đếm
                String numberStr = in.readLine();
                String baseStr = in.readLine();

                System.out.println("Server nhan so: " + numberStr);
                System.out.println("Server nhan he dem: " + baseStr);

                int number = Integer.parseInt(numberStr);
                int base = Integer.parseInt(baseStr);

                String result = convertNumber(number, base);
                out.println(result);

                System.out.println("Server gui: " + result);

                socket.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static String convertNumber(int number, int base) {
        String converted;
        switch (base) {
            case 2:
                converted = Integer.toBinaryString(number);
                break;
            case 8:
                converted = Integer.toOctalString(number);
                break;
            case 16:
                converted = Integer.toHexString(number).toUpperCase();
                break;
            default:
                converted = "He dem khong hop le (chi ho tro 2, 8, 16)";
        }
        return "Ket qua trong he " + base + ": " + converted;
    }
}
