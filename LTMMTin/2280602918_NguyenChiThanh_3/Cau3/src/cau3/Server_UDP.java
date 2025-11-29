package cau3;
import java.io.*;
import java.net.*;

public class Server_UDP{
    public static void main(String[] args) throws IOException {
        try (DatagramSocket serverSocket = new DatagramSocket(9012)) {
            System.out.println("Server giải phương trình bậc hai đang chạy...");
            
            byte[] buffer = new byte[512];
            DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);
            serverSocket.receive(receivePacket);
            
            String received = new String(receivePacket.getData(), 0, receivePacket.getLength()).trim();
            String[] parts = received.split(",");
            
            float a = Float.parseFloat(parts[0]);
            float b = Float.parseFloat(parts[1]);
            float c = Float.parseFloat(parts[2]);
            
            String result = solveQuadratic(a, b, c);
            
            byte[] resultBytes = result.getBytes();
            InetAddress clientAddress = receivePacket.getAddress();
            int clientPort = receivePacket.getPort();
            
            DatagramPacket sendPacket = new DatagramPacket(resultBytes, resultBytes.length, clientAddress, clientPort);
            serverSocket.send(sendPacket);
        }
    }

    private static String solveQuadratic(float a, float b, float c) {
        if (a == 0) {
            if (b == 0) {
                return (c == 0) ? "Phương trình vô số nghiệm" : "Phương trình vô nghiệm";
            } else {
                float x = -c / b;
                return "Phương trình bậc nhất. Nghiệm x = " + x;
            }
        }

        float delta = b * b - 4 * a * c;
        if (delta > 0) {
            double x1 = (-b + Math.sqrt(delta)) / (2 * a);
            double x2 = (-b - Math.sqrt(delta)) / (2 * a);
            return "Hai nghiệm phân biệt: x1 = " + x1 + ", x2 = " + x2;
        } else if (delta == 0) {
            double x = -b / (2 * a);
            return "Nghiệm kép: x = " + x;
        } else {
            double real = -b / (2 * a);
            double imag = Math.sqrt(-delta) / (2 * a);
            return String.format("Hai nghiệm phức: x1 = %.2f + %.2fi, x2 = %.2f - %.2fi",
                    real, imag, real, imag);
        }
    }
}
