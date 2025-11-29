package BTLamThem3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SynchronizedReader implements Runnable {
    private final String fileName;
    private final Object fileLock; // Đối tượng khóa chung

    // Constructor cũng nhận vào tên file VÀ CÙNG một đối tượng khóa
    public SynchronizedReader(String fileName, Object lock) {
        this.fileName = fileName;
        this.fileLock = lock;
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        
        // Luồng này cũng phải chiếm được khóa "fileLock" thì mới được đọc
        synchronized (fileLock) {
            System.out.println(">>> " + threadName + " da chiem duoc khoa va BAT DAU DOC file " + fileName);
            
            try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                String line;
                System.out.println("--- Noi dung file ---");
                while ((line = reader.readLine()) != null) {
                    System.out.println("   [" + threadName + "] doc: " + line);
                    // Giả lập việc đọc tốn thời gian
                    Thread.sleep(100);
                }
                System.out.println("---------------------");

            } catch (IOException | InterruptedException e) {
                // Lỗi có thể xảy ra nếu file chưa tồn tại khi luồng đọc chạy trước
                System.err.println("Loi khi doc file tren " + threadName + ". Co the file chua ton tai.");
            }
            
            System.out.println("<<< " + threadName + " da DOC XONG va giai phong khoa.");
        } // Khóa được tự động giải phóng
    }
}