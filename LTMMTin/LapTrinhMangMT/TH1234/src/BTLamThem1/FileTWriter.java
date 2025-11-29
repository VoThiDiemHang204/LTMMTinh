package BTLamThem1;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class FileTWriter implements Runnable {

    // Thuộc tính để lưu tên file sẽ được ghi
    private final String fileName;

    // Hàm khởi tạo (Constructor) để nhận tên file khi tạo đối tượng
    public FileTWriter(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Phương thức run() chứa toàn bộ logic sẽ được thực thi trong một luồng riêng.
     */
    @Override
    public void run() {
        // Lấy tên của luồng hiện tại để tiện theo dõi
        String threadName = Thread.currentThread().getName();
        System.out.println(" Bat Dau luong " + threadName + " -> Ghi file " + this.fileName);

        // Sử dụng try-with-resources để đảm bảo file writer được đóng tự động
        // dù có lỗi xảy ra hay không.
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.fileName))) {
            Random random = new Random();

            // Vòng lặp để ghi 100 số ngẫu nhiên (bạn có thể thay đổi số lượng)
            for (int i = 0; i < 100; i++) {
                int randomNumber = random.nextInt(1000); // Tạo số ngẫu nhiên từ 0 đến 999
                writer.write(Integer.toString(randomNumber)); // Ghi số vào file
                writer.newLine(); // Ghi một dòng mới
            }
            
            System.out.println(" Ket Thuc luong " + threadName + " -> Da ghi xong file " + this.fileName);

        } catch (IOException e) {
            // Xử lý nếu có lỗi trong quá trình ghi file
            System.err.println("Loi khi ghi file " + this.fileName + " tren luong " + threadName);
            e.printStackTrace();
        }
    }
}