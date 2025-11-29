package Thuchanh1;

import java.sql.*;
import javax.swing.*;

public class MyConnection {
    private Connection conn;

    public Connection getConnection() {
        try {
            // Dùng driver mới từ MySQL Connector/J 8.0 trở lên
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Kết nối CSDL - thêm useSSL & timezone để tránh cảnh báo
            String URL = "jdbc:mysql://localhost/quanlytaikhoan?useSSL=false&serverTimezone=UTC";
            conn = DriverManager.getConnection(URL, "root", "");
            return conn;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Lỗi kết nối: " + ex.toString(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
}
