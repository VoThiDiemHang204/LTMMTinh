package Cau_5;

import java.io.*;
import java.net.*;
import java.util.Stack;

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
                    double result = evaluatePostfix(input);
                    out.println("Ket qua: " + result);
                    System.out.println("Server gui: Ket qua: " + result);
                } catch (Exception e) {
                    out.println("Loi tinh toan: " + e.getMessage());
                    System.out.println("Server gui: Loi tinh toan");
                }

                socket.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static double evaluatePostfix(String expr) throws Exception {
        String[] tokens = expr.trim().split("\\s+");
        Stack<Double> stack = new Stack<>();

        for (String token : tokens) {
            if (isNumber(token)) {
                stack.push(Double.parseDouble(token));
            } else if (isOperator(token)) {
                if (stack.size() < 2) throw new Exception("Bieu thuc hau to sai");
                double b = stack.pop();
                double a = stack.pop();
                double res = applyOperator(a, b, token);
                stack.push(res);
            } else {
                throw new Exception("Ky tu khong hop le: " + token);
            }
        }

        if (stack.size() != 1) throw new Exception("Bieu thuc hau to sai");
        return stack.pop();
    }

    private static boolean isNumber(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean isOperator(String s) {
        return s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/");
    }

    private static double applyOperator(double a, double b, String op) {
        switch (op) {
            case "+": return a + b;
            case "-": return a - b;
            case "*": return a * b;
            case "/": return a / b;
            default: throw new IllegalArgumentException("Toan tu khong hop le");
        }
    }
}
