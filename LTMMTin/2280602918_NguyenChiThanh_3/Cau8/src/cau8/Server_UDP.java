package cau8;
import java.math.BigInteger;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Server_UDP{
    public static void main(String[] args) {
        final int SERVER_PORT = 9999;

        try (DatagramSocket socket = new DatagramSocket(SERVER_PORT)) {
            System.out.println("Server UDP tính giai thừa đang chạy...");

            while (true) {
                // Nhận dữ liệu
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                socket.receive(receivePacket);

                String msg = new String(receivePacket.getData(), 0, receivePacket.getLength());
                int n = Integer.parseInt(msg);
                System.out.println("Yêu cầu tính " + n + "!");

                // Tính giai thừa song song
                BigInteger result = calculateFactorialParallel(n);

                // Gửi kết quả về client
                byte[] sendData = result.toString().getBytes();
                DatagramPacket sendPacket = new DatagramPacket(
                        sendData, sendData.length,
                        receivePacket.getAddress(), receivePacket.getPort()
                );
                socket.send(sendPacket);
            }

        } catch (Exception e) {
        }
    }

    // Tính giai thừa song song (chia thành nhiều đoạn)
    public static BigInteger calculateFactorialParallel(int n) throws InterruptedException {
        final int THREAD_COUNT = 4;
        FactorialThread[] threads = new FactorialThread[THREAD_COUNT];
        int chunkSize = n / THREAD_COUNT;
        int remainder = n % THREAD_COUNT;

        int start = 1;
        for (int i = 0; i < THREAD_COUNT; i++) {
            int end = start + chunkSize - 1;
            if (i == THREAD_COUNT - 1) end += remainder; // gộp dư vào thread cuối
            threads[i] = new FactorialThread(start, end);
            threads[i].start();
            start = end + 1;
        }

        BigInteger result = BigInteger.ONE;
        for (FactorialThread thread : threads) {
            thread.join();
            result = result.multiply(thread.getPartialResult());
        }
        return result;
    }

    // Thread tính giai thừa đoạn [start, end]
    static class FactorialThread extends Thread {
        private int start, end;
        private BigInteger partialResult = BigInteger.ONE;

        public FactorialThread(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public void run() {
            for (int i = start; i <= end; i++) {
                partialResult = partialResult.multiply(BigInteger.valueOf(i));
            }
        }

        public BigInteger getPartialResult() {
            return partialResult;
        }
    }
}
