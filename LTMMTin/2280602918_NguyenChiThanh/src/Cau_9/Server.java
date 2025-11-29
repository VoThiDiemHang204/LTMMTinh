package Cau_9;

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

                String result = xuLyDanhSach(input);

                out.println(result);
                System.out.println("Server gui: " + result);

                socket.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static String xuLyDanhSach(String input) {
        String[] parts = input.split(",");
        double sum = 0;
        double max = Double.MIN_VALUE;
        double min = Double.MAX_VALUE;

        for (String part : parts) {
            double num = Double.parseDouble(part.trim());
            sum += num;
            if (num > max) max = num;
            if (num < min) min = num;
        }

        double average = sum / parts.length;
        return "Trung binh: " + String.format("%.2f", average) +
               "\nLon nhat: " + max +
               "\nNho nhat: " + min;
    }
}
