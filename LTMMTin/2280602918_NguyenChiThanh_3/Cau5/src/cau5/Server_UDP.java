package cau5;
import java.io.*;
import java.net.*;
import java.util.Stack;

public class Server_UDP {
    public static void main(String[] args) throws IOException {
        try (DatagramSocket serverSocket = new DatagramSocket(9016)) {
            System.out.println("Server đang chạy để xử lý biểu thức hậu tố...");
            byte[] buffer = new byte[512];
            DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);
            serverSocket.receive(receivePacket);
            String expression = new String(receivePacket.getData(), 0, receivePacket.getLength()).trim();
            System.out.println("Biểu thức nhận được: " + expression);
            String result;
            try {
                double value = evaluatePostfix(expression);
                result = String.valueOf(value);
            } catch (Exception e) {
                result = "Lỗi: Biểu thức không hợp lệ!";
            }
            byte[] resultBytes = result.getBytes();
            InetAddress clientAddress = receivePacket.getAddress();
            int clientPort = receivePacket.getPort();
            DatagramPacket sendPacket = new DatagramPacket(resultBytes, resultBytes.length, clientAddress, clientPort);
            serverSocket.send(sendPacket);
            }
    }
    // Hàm tính biểu thức hậu tố sử dụng stack
    private static double evaluatePostfix(String expr) {
        Stack<Double> stack = new Stack<>();
        String[] tokens = expr.split(" ");

        for (String token : tokens) {
            if (isNumeric(token)) {
                stack.push(Double.valueOf(token));
            } else if ("+-*/".contains(token) && stack.size() >= 2) {
                double b = stack.pop();
                double a = stack.pop();
                switch (token) {
                    case "+" -> stack.push(a + b);
                    case "-" -> stack.push(a - b);
                    case "*" -> stack.push(a * b);
                    case "/" -> stack.push(a / b);
                }
            } else {
                throw new IllegalArgumentException("Biểu thức sai");
            }
        }

        if (stack.size() != 1) throw new IllegalArgumentException("Biểu thức sai");
        return stack.pop();
    }

    private static boolean isNumeric(String str) {
        try {
            Double.valueOf(str); return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}

