package BTLamThem2;

public class MainReader {
    public static void main(String[] args) {
        System.out.println("Chuong trinh chinh (main thread) bat dau.");

        // Các tên file đã được tạo từ bài tập trước
        String file1 = "file_output_1.txt";
        String file2 = "file_output_2.txt";
        String file3 = "file_output_3.txt";

        // 1. Tạo 3 đối tượng TÁC VỤ (Runnable) FileTReader
        Runnable task1 = new FileTReader(file1);
        Runnable task2 = new FileTReader(file2);
        Runnable task3 = new FileTReader(file3);
        
        // 2. Tạo 3 đối tượng LUỒNG (Thread) và gắn mỗi luồng với một tác vụ
        Thread thread1 = new Thread(task1, "Luong-Doc-A");
        Thread thread2 = new Thread(task2, "Luong-Doc-B");
        Thread thread3 = new Thread(task3, "Luong-Doc-C");
        
        // 3. Bắt đầu thực thi cả 3 luồng CÙNG MỘT LÚC
        thread1.start();
        thread2.start();
        thread3.start();

        System.out.println("Chuong trinh chinh (main thread) da khoi dong xong 3 luong va ket thuc.");
        System.out.println("---------------------------------------------------------");
    }
}