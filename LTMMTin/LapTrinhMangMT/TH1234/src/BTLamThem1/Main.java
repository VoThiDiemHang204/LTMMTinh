package BTLamThem1;

public class Main {
    public static void main(String[] args) {
        System.out.println("Chuong trinh chinh (main thread) bat dau.");

        // 1. Tạo 3 đối tượng TÁC VỤ (Runnable) FileTWriter
        // Mỗi tác vụ sẽ ghi vào một file khác nhau.
        Runnable task1 = new FileTWriter("file_output_1.txt");
        Runnable task2 = new FileTWriter("file_output_2.txt");
        Runnable task3 = new FileTWriter("file_output_3.txt");

        // 2. Tạo 3 đối tượng LUỒNG (Thread) và gắn mỗi luồng với một tác vụ
        Thread thread1 = new Thread(task1, "Luong-so-1"); // Đặt tên cho luồng
        Thread thread2 = new Thread(task2, "Luong-So-2");
        Thread thread3 = new Thread(task3, "Luong-So-3");
        
        // 3. Bắt đầu thực thi cả 3 luồng CÙNG MỘT LÚC
        // Lưu ý: Phải gọi .start(), không được gọi .run()
        thread1.start();
        thread2.start();
        thread3.start();

        System.out.println("Chuong trinh chinh (main thread) da khoi dong xong 3 luong va ket thuc.");
        System.out.println("---------------------------------------------------------");
    }
}