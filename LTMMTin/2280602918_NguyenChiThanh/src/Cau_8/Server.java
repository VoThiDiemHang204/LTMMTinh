package Cau_8;

import java.io.*;
import java.net.*;
import java.math.BigInteger;

public class Server {
    public static void main(String[] args) {
        int port = 12345;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server listening on port " + port);

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected: " + socket.getInetAddress());

                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

                String numberStr = in.readLine();
                System.out.println("Server received n: " + numberStr);

                int n = Integer.parseInt(numberStr);
                BigInteger result = computeFactorialParallel(n);

                out.println(result.toString());
                System.out.println("Server sent: " + result);

                socket.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static BigInteger computeFactorialParallel(int n) {
        if (n == 0 || n == 1) return BigInteger.ONE;

        int mid = n / 2;
        FactorialTask t1 = new FactorialTask(1, mid);
        FactorialTask t2 = new FactorialTask(mid + 1, n);

        Thread thread1 = new Thread(t1);
        Thread thread2 = new Thread(t2);

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return t1.getResult().multiply(t2.getResult());
    }
}

class FactorialTask implements Runnable {
    private int start;
    private int end;
    private BigInteger result = BigInteger.ONE;

    public FactorialTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public void run() {
        for (int i = start; i <= end; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
    }

    public BigInteger getResult() {
        return result;
    }
}
