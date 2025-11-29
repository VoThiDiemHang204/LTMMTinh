import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 12345;

        try (Socket socket = new Socket(host, port)) {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            Scanner scanner = new Scanner(System.in);
            System.out.print("Nhap bieu thuc hau to (vi du 3 4 + 2 *): ");
            String expression = scanner.nextLine();

            out.println(expression);

            String response = in.readLine();
            System.out.println("Server tra loi: " + response);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
