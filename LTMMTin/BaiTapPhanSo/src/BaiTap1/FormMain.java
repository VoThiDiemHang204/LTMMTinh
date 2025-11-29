package BaiTap1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FormMain extends javax.swing.JFrame {

    private final String fileName = "phanso.dat";

    public FormMain() {
        initComponents();
    }
    
     // ham kiem tra so nguyen to
    public static boolean laNguyenTo(int n) {
        if (n < 2) return false;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    // ham tao danh sach phan so ngau nhien
    public static List<PhanSo> taoDanhSachPhanSo(int soLuong) {
        List<PhanSo> list = new ArrayList<>();
        Random rd = new Random();
        for (int i = 0; i < soLuong; i++) {
            int tu = rd.nextInt(20) + 1;
            int mau = rd.nextInt(20) + 1;
            list.add(new PhanSo(tu, mau));
        }
        return list;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnTao = new javax.swing.JButton();
        btnDoc = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtKetQua = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(51, 51, 255));

        btnTao.setText("Tạo");
        btnTao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoActionPerformed(evt);
            }
        });

        btnDoc.setText("Đọc");
        btnDoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDocActionPerformed(evt);
            }
        });

        txtKetQua.setColumns(20);
        txtKetQua.setRows(5);
        jScrollPane1.setViewportView(txtKetQua);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(btnTao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDoc)
                .addGap(23, 23, 23))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnDoc)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnTao)))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoActionPerformed
        // TODO add your handling code here:
        List<PhanSo> danhSach = taoDanhSachPhanSo(10);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(danhSach);
            txtKetQua.setText("Da tao va ghi danh sach phan so xuong file!\n");
            for (PhanSo ps : danhSach) {
                txtKetQua.append(ps.toString() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnTaoActionPerformed

    private void btnDocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDocActionPerformed
        // TODO add your handling code here:
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            List<PhanSo> danhSach = (List<PhanSo>) ois.readObject();
            txtKetQua.setText("Cac phan so co mau so la so nguyen to:\n");
            for (PhanSo ps : danhSach) {
                if (laNguyenTo(ps.getMauSo())) {
                    txtKetQua.append(ps.toString() + "\n");
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnDocActionPerformed


    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormMain().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDoc;
    private javax.swing.JButton btnTao;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtKetQua;
    // End of variables declaration//GEN-END:variables
}
