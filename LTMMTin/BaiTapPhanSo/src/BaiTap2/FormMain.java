package BaiTap2;

public class FormMain extends javax.swing.JFrame {

    public FormMain() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtNhap = new javax.swing.JTextField();
        btnThucHien = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnThucHien.setText("Thực hiện");
        btnThucHien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThucHienActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(txtNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(162, 162, 162)
                        .addComponent(btnThucHien, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnThucHien, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThucHienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThucHienActionPerformed
        // TODO add your handling code here:
        String fileName = txtNhap.getText().trim();
        if (fileName.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Bạn phải nhập tên tập tin!");
            return;
        }
 
        try {
            java.io.BufferedReader br = new java.io.BufferedReader(new java.io.FileReader(fileName));
            java.util.ArrayList<Integer> tempList = new java.util.ArrayList<>();
            java.util.ArrayList<int[]> listOfArrays = new java.util.ArrayList<>();
            String line;

            while ((line = br.readLine()) != null) {
                String[] tokens = line.split("\\s+"); // tách theo khoảng trắng
                for (String t : tokens) {
                    if (!t.isEmpty()) {
                        int num = Integer.parseInt(t);
                        tempList.add(num);
                        if (tempList.size() == 100) {
                            int[] arr = tempList.stream().mapToInt(i -> i).toArray();
                            listOfArrays.add(arr);
                            tempList.clear();
                        }
                    }
                }
            }
            br.close();

            // xử lý phần còn lại nếu < 100 phần tử
            if (!tempList.isEmpty()) {
                int[] arr = tempList.stream().mapToInt(i -> i).toArray();
                listOfArrays.add(arr);
            }

            // ghi vào Mang.txt
            java.io.BufferedWriter bw = new java.io.BufferedWriter(new java.io.FileWriter("Mang.txt"));
            for (int[] arr : listOfArrays) {
                for (int i = 0; i < arr.length; i++) {
                    bw.write(arr[i] + (i < arr.length - 1 ? " " : ""));
                }
                bw.newLine();
            }
            bw.close();

            javax.swing.JOptionPane.showMessageDialog(this, "Xử lý xong! Đã lưu vào Mang.txt");
        } catch (Exception ex) {
            javax.swing.JOptionPane.showMessageDialog(this, "Lỗi: " + ex.getMessage());
        }
    }//GEN-LAST:event_btnThucHienActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormMain().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnThucHien;
    private javax.swing.JTextField txtNhap;
    // End of variables declaration//GEN-END:variables
}
