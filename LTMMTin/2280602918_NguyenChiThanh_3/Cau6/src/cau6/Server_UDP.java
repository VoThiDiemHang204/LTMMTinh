/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cau6;
import java.io.*;
import java.net.*;

public class Server_UDP{
    public static void main(String[] args) throws IOException {
        try (DatagramSocket serverSocket = new DatagramSocket(9022)) {
            System.out.println("Server kiểm tra số đối xứng đang chạy...");
            
            byte[] receiveData = new byte[256];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            
            // Nhận dữ liệu từ client
            serverSocket.receive(receivePacket);
            String received = new String(receivePacket.getData(), 0, receivePacket.getLength()).trim();
            System.out.println("Nhận được: " + received);
            
            // Kiểm tra đối xứng
            String result = isPalindrome(received) ? "Palindrome" : "Not Palindrome";
            
            // Gửi kết quả về client
            byte[] sendData = result.getBytes();
            InetAddress clientAddress = receivePacket.getAddress();
            int clientPort = receivePacket.getPort();
            
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
            serverSocket.send(sendPacket);
        }
    }

    private static boolean isPalindrome(String str) {
        int len = str.length();
        for (int i = 0; i < len / 2; i++) {
            if (str.charAt(i) != str.charAt(len - 1 - i)) return false;
        }
        return true;
    }
}
