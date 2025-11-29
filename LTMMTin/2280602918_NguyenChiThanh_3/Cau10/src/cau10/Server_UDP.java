package cau10;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Server_UDP {
    public static boolean isFibonacci(int n) {
        int a = 0, b = 1;
        while (a < n) {
            int temp = a + b;
            a = b;
            b = temp;
        }
        return a == n || n == 0;
    }

    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket(8888);
            byte[] buffer = new byte[1024];

            System.out.println("Server đang chạy...");

            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);

                String received = new String(packet.getData(), 0, packet.getLength());
                int number = Integer.parseInt(received.trim());
                boolean isFibo = isFibonacci(number);

                String result = number + (isFibo ? " là số Fibonacci" : " không phải là số Fibonacci");

                byte[] sendData = result.getBytes();
                InetAddress clientAddress = packet.getAddress();
                int clientPort = packet.getPort();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
                socket.send(sendPacket);
            }
        } catch (IOException | NumberFormatException e) {
        }
    }
}
