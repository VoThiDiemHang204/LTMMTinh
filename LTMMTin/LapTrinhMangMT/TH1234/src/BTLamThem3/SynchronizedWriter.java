package BTLamThem3;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class SynchronizedWriter implements Runnable {
    private final String fileName;
    private final Object fileLock; // Đối tượng khóa chung

    // Constructor nhận vào tên file VÀ đối tượng khóa
    public SynchronizedWriter(String fileName, Object lock) {
        this.fileName = fileName;
        this.fileLock = lock;
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        
        // Sử dụng khối synchronized, luồng phải chiếm được khóa "fileLock"
        // thì mới được chạy code bên trong.
        synchronized (fileLock) {
            System.out.println(">>> " + threadName + " da chiem duoc khoa va BAT DAU GHI file " + fileName);
            
            // Dùng try-with-resources để đảm bảo file được đóng
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) { // true để ghi nối tiếp
                for (int i = 1; i <= 5; i++) {
                    String content = "Dong " + i + " duoc ghi boi " + threadName + " luc " + LocalDateTime.now();
                    writer.write(content);
                    writer.newLine();
                    System.out.println("   " + threadName + " dang ghi... (" + i + "/5)");
                    
                    // Giả lập công việc ghi file tốn thời gian
                    Thread.sleep(200); 
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
            
            System.out.println("<<< " + threadName + " da GHI XONG va giai phong khoa.");
        } // Khóa được tự động giải phóng khi ra khỏi khối này
    }
}