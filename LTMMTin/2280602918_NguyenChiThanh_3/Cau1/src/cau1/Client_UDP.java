package cau1;
import java.io.*;
import java.net.*;

public class Client_UDP {
    public static void main(String[] args) throws IOException {
        try ( // Tạo socket client
                DatagramSocket clientSocket = new DatagramSocket()) {
            InetAddress serverAddress = InetAddress.getByName("localhost");
            int serverPort = 9008;
            
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            
            System.out.print("Nhập số nguyên a: ");
            int a = Integer.parseInt(reader.readLine());
            
            System.out.print("Nhập số nguyên b: ");
            int b = Integer.parseInt(reader.readLine());
            
            // Chuyển số a và b thành byte rồi gửi
            byte[] dataA = String.valueOf(a).getBytes();
            byte[] dataB = String.valueOf(b).getBytes();
            
            DatagramPacket packetA = new DatagramPacket(dataA, dataA.length, serverAddress, serverPort);
            DatagramPacket packetB = new DatagramPacket(dataB, dataB.length, serverAddress, serverPort);
            
            clientSocket.send(packetA);
            clientSocket.send(packetB);
            
            // Nhận kết quả trả về từ Server
            byte[] buffer = new byte[256];
            DatagramPacket response = new DatagramPacket(buffer, buffer.length);
            clientSocket.receive(response);
            
            String result = new String(response.getData(), 0, response.getLength()).trim();
            System.out.println("Tổng nhận từ server là: " + result);
        }
    }
}

