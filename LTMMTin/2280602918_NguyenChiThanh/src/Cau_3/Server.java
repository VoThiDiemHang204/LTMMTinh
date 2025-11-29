package Cau_3;

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

                String result = giaiPhuongTrinh(input);

                out.println(result);
                System.out.println("Server gui: " + result);

                socket.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static String giaiPhuongTrinh(String input) {
        try {
            String[] parts = input.split(",");
            double a = Double.parseDouble(parts[0].trim());
            double b = Double.parseDouble(parts[1].trim());
            double c = Double.parseDouble(parts[2].trim());

            if (a == 0) {
                if (b == 0) {
                    return (c == 0) ? "Phuong trinh vo so nghiem" : "Phuong trinh vo nghiem";
                } else {
                    double x = -c / b;
                    return "Phuong trinh bac nhat, nghiem x = " + x;
                }
            }

            double delta = b * b - 4 * a * c;

            if (delta > 0) {
                double x1 = (-b + Math.sqrt(delta)) / (2 * a);
                double x2 = (-b - Math.sqrt(delta)) / (2 * a);
                return "Phuong trinh co 2 nghiem thuc phan biet:\nx1 = " + x1 + "\nx2 = " + x2;
            } else if (delta == 0) {
                double x = -b / (2 * a);
                return "Phuong trinh co nghiem kep:\nx = " + x;
            } else {
                double realPart = -b / (2 * a);
                double imagPart = Math.sqrt(-delta) / (2 * a);
                return "Phuong trinh co 2 nghiem phuc:\n" +
                       "x1 = " + realPart + " + " + imagPart + "i\n" +
                       "x2 = " + realPart + " - " + imagPart + "i";
            }
        } catch (Exception e) {
            return "Loi xu ly du lieu dau vao";
        }
    }
}
