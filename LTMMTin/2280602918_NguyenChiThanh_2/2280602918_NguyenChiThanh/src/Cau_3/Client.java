/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Cau_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Asus
 */
public class Client {
    public static void main(String[] args) throws IOException {
        DatagramSocket clientSocket = new DatagramSocket();
        InetAddress serverAddress = InetAddress.getByName("localhost");
        int serverPort = 9012;

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Nhập hệ số a: ");
        float a = Float.parseFloat(reader.readLine());

        System.out.print("Nhập hệ số b: ");
        float b = Float.parseFloat(reader.readLine());

        System.out.print("Nhập hệ số c: ");
        float c = Float.parseFloat(reader.readLine());

        // Gộp các hệ số thành một chuỗi, cách nhau bởi dấu phẩy
        String message = a + "," + b + "," + c;
        byte[] dataToSend = message.getBytes();

        // Gửi dữ liệu đến server
        DatagramPacket sendPacket = new DatagramPacket(dataToSend, dataToSend.length, serverAddress, serverPort);
        clientSocket.send(sendPacket);

        // Nhận kết quả
        byte[] buffer = new byte[512];
        DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);
        clientSocket.receive(receivePacket);

        String result = new String(receivePacket.getData(), 0, receivePacket.getLength()).trim();
        System.out.println("Kết quả từ server: " + result);

        clientSocket.close();
    }
}
