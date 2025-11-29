package BTLamThem3;

public class MainSynchronized {
    public static void main(String[] args) {
        
        final String sharedFileName = "synchronized_data.txt";
        
        // 1. TẠO RA MỘT ĐỐI TƯỢNG KHÓA DUY NHẤT
        // Đây là điểm mấu chốt: Cả hai luồng phải chia sẻ CÙNG MỘT khóa.
        final Object fileLock = new Object();

        // 2. Tạo tác vụ Ghi và Đọc, truyền vào CÙNG MỘT TÊN FILE và CÙNG MỘT KHÓA
        Runnable writerTask = new SynchronizedWriter(sharedFileName, fileLock);
        Runnable readerTask = new SynchronizedReader(sharedFileName, fileLock);
        
        // 3. Tạo 2 luồng từ 2 tác vụ
        Thread writerThread = new Thread(writerTask, "Luong-GHI");
        Thread readerThread = new Thread(readerTask, "Luong-DOC");
        
        System.out.println("Bat dau chay 2 luong GHI va DOC dong thoi...");
        
        // 4. Bắt đầu cả 2 luồng. Thứ tự chạy của chúng là không thể đoán trước!
        writerThread.start();
        readerThread.start();
    }
}