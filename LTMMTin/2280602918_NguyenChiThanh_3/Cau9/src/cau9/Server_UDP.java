package cau9;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Server_UDP {
    public static void main(String[] args) throws Exception {
        DatagramSocket serverSocket = new DatagramSocket(9876);
        byte[] receiveData = new byte[1024];
        byte[] sendData;

        System.out.println("Server is running...");

        while (true) {
            // Nhận dữ liệu từ client
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);
            String input = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Received: " + input);

            // Tách các số thực từ chuỗi
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

            String result = "Average: " + average + ", Max: " + max + ", Min: " + min;

            // Gửi kết quả lại cho client
            InetAddress clientAddress = receivePacket.getAddress();
            int clientPort = receivePacket.getPort();
            sendData = result.getBytes();

            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
            serverSocket.send(sendPacket);
        }
    }
}
