package BTLamThem2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Lớp này đại diện cho một tác vụ đọc nội dung từ một file.
 * Nó cài đặt (implements) giao diện Runnable để có thể được thực thi bởi một Thread.
 */
public class FileTReader implements Runnable {

    // Thuộc tính để lưu tên file sẽ được đọc
    private final String fileName;

    // Hàm khởi tạo (Constructor) để nhận tên file khi tạo đối tượng
    public FileTReader(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Phương thức run() chứa toàn bộ logic sẽ được thực thi trong một luồng riêng.
     */
    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        System.out.println(" Bat Dau luong " + threadName + " -> Doc file " + this.fileName);

        // Sử dụng try-with-resources để đảm bảo file reader được đóng tự động
        try (BufferedReader reader = new BufferedReader(new FileReader(this.fileName))) {
            String line;
            
            // Đọc file theo từng dòng cho đến khi hết file (readLine() trả về null)
            while ((line = reader.readLine()) != null) {
                // In nội dung của dòng vừa đọc ra console, kèm theo tên luồng
                System.out.println("[" + threadName + "] doc: " + line);
            }
            
            System.out.println(" Ket Thuc luong " + threadName + " -> Da doc xong file " + this.fileName);

        } catch (IOException e) {
            // Xử lý nếu có lỗi trong quá trình đọc file (ví dụ: file không tồn tại)
            System.err.println("Loi khi doc file " + this.fileName + " tren luong " + threadName);
            e.printStackTrace();
        }
    }
}