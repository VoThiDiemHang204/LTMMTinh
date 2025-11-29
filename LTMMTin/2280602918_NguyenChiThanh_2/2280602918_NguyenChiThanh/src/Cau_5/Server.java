/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Cau_5;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 *
 * @author Asus
 */
public class Server {
    /*
     * Phân tích và tính giá trị của một biểu thức hậu tố.
     * @param expression Chuỗi biểu thức hậu tố, ví dụ "3 4 + 2 *"
     * @return Kết quả của biểu thức.
     * @throws Exception nếu biểu thức không hợp lệ hoặc có lỗi tính toán.
     */
    private static double evaluatePostfix(String expression) throws Exception {
        // Sử dụng Deque như một Stack. Đây là cách làm được khuyến nghị.
        Deque<Double> stack = new ArrayDeque<>();
        
        // Tách biểu thức thành các thành phần (số và toán tử)
        // \\s+ để xử lý trường hợp có nhiều dấu cách giữa các thành phần
        String[] tokens = expression.trim().split("\\s+");

        for (String token : tokens) {
            try {
                // Nếu token là một số, đẩy vào stack
                double number = Double.parseDouble(token);
                stack.push(number);
            } catch (NumberFormatException e) {
                // Nếu không phải là số, nó phải là một toán tử
                if (stack.size() < 2) {
                    throw new IllegalArgumentException("Biểu thức không hợp lệ: Thiếu toán hạng cho toán tử '" + token + "'");
                }
                
                double operand2 = stack.pop();
                double operand1 = stack.pop();
                double result = 0;

                switch (token) {
                    case "+":
                        result = operand1 + operand2;
                        break;
                    case "-":
                        result = operand1 - operand2;
                        break;
                    case "*":
                        result = operand1 * operand2;
                        break;
                    case "/":
                        if (operand2 == 0) {
                            throw new ArithmeticException("Lỗi chia cho 0.");
                        }
                        result = operand1 / operand2;
                        break;
                    default:
                        throw new IllegalArgumentException("Toán tử không hợp lệ: '" + token + "'");
                }
                stack.push(result);
            }
        }

        // Sau khi duyệt hết, nếu stack còn lại đúng 1 phần tử thì đó là kết quả
        if (stack.size() != 1) {
            throw new IllegalArgumentException("Biểu thức không hợp lệ: Thừa toán hạng.");
        }

        return stack.pop();
    }


    public static void main(String[] args) {
        // Lắng nghe ở cổng 9880
        try (DatagramSocket serverSocket = new DatagramSocket(9880)) {
            System.out.println("Postfix Evaluation Server đang chạy ở cổng 9880...");

            while (true) {
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);
                System.out.println("\nĐã nhận yêu cầu từ client.");

                String expression = new String(receivePacket.getData(), 0, receivePacket.getLength()).trim();
                System.out.println("Biểu thức nhận được: \"" + expression + "\"");
                String response;

                try {
                    // Gọi hàm tính toán
                    double result = evaluatePostfix(expression);
                    response = String.valueOf(result);
                } catch (Exception e) {
                    // Bắt tất cả các lỗi có thể xảy ra và gửi thông báo về client
                    response = "Lỗi: " + e.getMessage();
                    System.err.println(response);
                }

                // Gửi phản hồi về client
                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();
                byte[] sendData = response.getBytes();
                System.out.println("Gửi kết quả: \"" + response + "\"");

                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
                serverSocket.send(sendPacket);
            }
        } catch (IOException e) {
            System.err.println("Lỗi Server: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
