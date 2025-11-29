using System;
using System.Data;
using System.Windows.Forms;
using System.Data.SqlClient;
using System.Security.Cryptography;
using System.Text;

namespace KiemTra2
{
    public partial class FormPhanQuyen : Form
    {
        // Sử dụng Windows Authentication
        string connectionString = "Data Source=MSI\\SQLEXPRESS;Initial Catalog=VANPHONGPHAM;Integrated Security=True";

        DataTable dtQuanLy;
        DataTable dtNhanVien;

        private string adminUsername;
        private string adminPassword;

        public FormPhanQuyen(string adminUsername, string adminPassword)
        {
            InitializeComponent();
            this.adminUsername = adminUsername;
            this.adminPassword = adminPassword;
            this.Load += FormPhanQuyen_Load;
            dgvQuanLy.CellContentClick += dgvQuanLy_CellContentClick;
            dgvNhanVienBanHang.CellContentClick += dgvNhanVienBanHang_CellContentClick;
        }

        private void FormPhanQuyen_Load(object sender, EventArgs e)
        {
            LoadData();
            AddChangeButtons();
        }

        private string HashPassword(string password)
        {
            using (SHA256 sha256 = SHA256.Create())
            {
                byte[] bytes = sha256.ComputeHash(Encoding.UTF8.GetBytes(password));
                StringBuilder builder = new StringBuilder();
                foreach (byte b in bytes)
                    builder.Append(b.ToString("x2"));
                return builder.ToString();
            }
        }

        private bool VerifyAdminPassword()
        {
            using (var form = new Form())
            {
                form.Text = "Xác thực Admin";
                var lbl = new Label() { Left = 10, Top = 20, Text = "Nhập lại mật khẩu admin:", AutoSize = true };
                var txt = new TextBox() { Left = 10, Top = 50, Width = 200, UseSystemPasswordChar = true };
                var btnOK = new Button() { Text = "OK", Left = 120, Width = 90, Top = 80, DialogResult = DialogResult.OK };
                form.Controls.Add(lbl);
                form.Controls.Add(txt);
                form.Controls.Add(btnOK);
                form.AcceptButton = btnOK;
                form.StartPosition = FormStartPosition.CenterParent;
                form.ClientSize = new System.Drawing.Size(230, 120);

                if (form.ShowDialog() == DialogResult.OK)
                {
                    string hash = HashPassword(txt.Text);
                    using (SqlConnection conn = new SqlConnection(connectionString))
                    {
                        conn.Open();
                        string sql = "SELECT COUNT(*) FROM NHAN_VIEN WHERE TENDANGNHAP = @user AND MATKHAU = @pass AND LAQUANLY = 1 AND VOHIEUHOA = 0";
                        SqlCommand cmd = new SqlCommand(sql, conn);
                        cmd.Parameters.AddWithValue("@user", adminUsername);
                        cmd.Parameters.AddWithValue("@pass", hash);
                        int count = (int)cmd.ExecuteScalar();
                        return count > 0;
                    }
                }
                return false;
            }
        }

        void LoadData()
        {
            try
            {
                using (SqlConnection conn = new SqlConnection(connectionString))
                {
                    conn.Open();
                    string query = "SELECT MANHANVIEN, TENDANGNHAP, TENNHANVIEN, SDT, GIOITINH, NGAYSINH, LAQUANLY FROM NHAN_VIEN WHERE VOHIEUHOA = 0";
                    SqlDataAdapter adapter = new SqlDataAdapter(query, conn);
                    DataTable dtAllNhanVien = new DataTable();
                    adapter.Fill(dtAllNhanVien);

                    // Luôn clone schema mới để tránh lỗi Clear khi chưa có schema
                    dtQuanLy = dtAllNhanVien.Clone();
                    dtNhanVien = dtAllNhanVien.Clone();

                    foreach (DataRow row in dtAllNhanVien.Rows)
                    {
                        if (row["LAQUANLY"] != DBNull.Value && Convert.ToBoolean(row["LAQUANLY"]))
                            dtQuanLy.ImportRow(row);
                        else
                            dtNhanVien.ImportRow(row);
                    }
                }

                dgvQuanLy.DataSource = null;
                dgvQuanLy.DataSource = dtQuanLy;
                dgvNhanVienBanHang.DataSource = null;
                dgvNhanVienBanHang.DataSource = dtNhanVien;

                SetupDataGridViewHeaders();
            }
            catch (Exception ex)
            {
                MessageBox.Show("Lỗi khi tải dữ liệu: " + ex.Message);
            }
        }

        void SetupDataGridViewHeaders()
        {
            // Cấu hình cho bảng Quản lý
            if (dgvQuanLy.Columns.Count > 0)
            {
                if (dgvQuanLy.Columns.Contains("MANHANVIEN"))
                    dgvQuanLy.Columns["MANHANVIEN"].HeaderText = "Mã NV";
                if (dgvQuanLy.Columns.Contains("TENDANGNHAP"))
                    dgvQuanLy.Columns["TENDANGNHAP"].HeaderText = "Tên Đăng Nhập";
                if (dgvQuanLy.Columns.Contains("TENNHANVIEN"))
                    dgvQuanLy.Columns["TENNHANVIEN"].HeaderText = "Tên Nhân Viên";
                if (dgvQuanLy.Columns.Contains("SDT"))
                    dgvQuanLy.Columns["SDT"].HeaderText = "Số Điện Thoại";
                if (dgvQuanLy.Columns.Contains("GIOITINH"))
                    dgvQuanLy.Columns["GIOITINH"].HeaderText = "Giới Tính";
                if (dgvQuanLy.Columns.Contains("NGAYSINH"))
                    dgvQuanLy.Columns["NGAYSINH"].HeaderText = "Ngày Sinh";
                if (dgvQuanLy.Columns.Contains("LAQUANLY"))
                    dgvQuanLy.Columns["LAQUANLY"].Visible = false;
            }

            // Cấu hình cho bảng Nhân viên bán hàng
            if (dgvNhanVienBanHang.Columns.Count > 0)
            {
                if (dgvNhanVienBanHang.Columns.Contains("MANHANVIEN"))
                    dgvNhanVienBanHang.Columns["MANHANVIEN"].HeaderText = "Mã NV";
                if (dgvNhanVienBanHang.Columns.Contains("TENDANGNHAP"))
                    dgvNhanVienBanHang.Columns["TENDANGNHAP"].HeaderText = "Tên Đăng Nhập";
                if (dgvNhanVienBanHang.Columns.Contains("TENNHANVIEN"))
                    dgvNhanVienBanHang.Columns["TENNHANVIEN"].HeaderText = "Tên Nhân Viên";
                if (dgvNhanVienBanHang.Columns.Contains("SDT"))
                    dgvNhanVienBanHang.Columns["SDT"].HeaderText = "Số Điện Thoại";
                if (dgvNhanVienBanHang.Columns.Contains("GIOITINH"))
                    dgvNhanVienBanHang.Columns["GIOITINH"].HeaderText = "Giới Tính";
                if (dgvNhanVienBanHang.Columns.Contains("NGAYSINH"))
                    dgvNhanVienBanHang.Columns["NGAYSINH"].HeaderText = "Ngày Sinh";
                if (dgvNhanVienBanHang.Columns.Contains("LAQUANLY"))
                    dgvNhanVienBanHang.Columns["LAQUANLY"].Visible = false;
            }
        }

        void AddChangeButtons()
        {
            // Xóa cột button cũ nếu có (tránh lỗi thêm nhiều lần)
            if (dgvQuanLy.Columns.Contains("colChange"))
                dgvQuanLy.Columns.Remove("colChange");
            if (dgvNhanVienBanHang.Columns.Contains("colChange"))
                dgvNhanVienBanHang.Columns.Remove("colChange");

            // Thêm nút cho bảng Quản lý (để hạ quyền)
            DataGridViewButtonColumn btnCol1 = new DataGridViewButtonColumn();
            btnCol1.Name = "colChange";
            btnCol1.HeaderText = "Thay Đổi";
            btnCol1.Text = "Hạ Quyền";
            btnCol1.UseColumnTextForButtonValue = true;
            dgvQuanLy.Columns.Add(btnCol1);

            // Thêm nút cho bảng Nhân viên (để thăng quyền)
            DataGridViewButtonColumn btnCol2 = new DataGridViewButtonColumn();
            btnCol2.Name = "colChange";
            btnCol2.HeaderText = "Thay Đổi";
            btnCol2.Text = "Thăng Quyền";
            btnCol2.UseColumnTextForButtonValue = true;
            dgvNhanVienBanHang.Columns.Add(btnCol2);
        }

        private void dgvQuanLy_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {
            if (e.RowIndex >= 0 && dgvQuanLy.Columns[e.ColumnIndex].Name == "colChange")
            {
                var row = dgvQuanLy.Rows[e.RowIndex];
                var cellMaNV = row.Cells["MANHANVIEN"];
                var cellTenNV = row.Cells["TENNHANVIEN"];

                if (cellMaNV.Value == null || cellTenNV.Value == null)
                {
                    MessageBox.Show("Không thể thực hiện thao tác với dòng này!", "Lỗi dữ liệu", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                    return;
                }

                string maNV = cellMaNV.Value.ToString();
                string tenNV = cellTenNV.Value.ToString();
                ChangePermission(maNV, tenNV, false);
            }
        }

        private void dgvNhanVienBanHang_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {
            if (e.RowIndex >= 0 && dgvNhanVienBanHang.Columns[e.ColumnIndex].Name == "colChange")
            {
                var row = dgvNhanVienBanHang.Rows[e.RowIndex];
                var cellMaNV = row.Cells["MANHANVIEN"];
                var cellTenNV = row.Cells["TENNHANVIEN"];

                if (cellMaNV.Value == null || cellTenNV.Value == null)
                {
                    MessageBox.Show("Không thể thực hiện thao tác với dòng này!", "Lỗi dữ liệu", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                    return;
                }

                string maNV = cellMaNV.Value.ToString();
                string tenNV = cellTenNV.Value.ToString();
                ChangePermission(maNV, tenNV, true);
            }
        }

        void ChangePermission(string maNV, string tenNV, bool isPromoting)
        {
            // Không cho phép admin tự hạ quyền chính mình
            if (!isPromoting && maNV == adminUsername)
            {
                MessageBox.Show("Bạn không thể tự hạ quyền quản lý của chính mình!", "Cảnh báo", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                return;
            }

            if (!VerifyAdminPassword())
            {
                MessageBox.Show("Mật khẩu admin không đúng!", "Xác thực thất bại", MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }
            string actionText = isPromoting ? "cấp quyền Quản lý cho" : "hủy quyền Quản lý của";
            int newPermission = isPromoting ? 1 : 0;

            DialogResult result = MessageBox.Show(
                $"Bạn có chắc chắn muốn {actionText} nhân viên: {tenNV} không?",
                "Thông báo",
                MessageBoxButtons.YesNo,
                MessageBoxIcon.Question);

            if (result == DialogResult.Yes)
            {
                try
                {
                    using (SqlConnection conn = new SqlConnection(connectionString))
                    {
                        conn.Open();
                        string updateQuery = "UPDATE NHAN_VIEN SET LAQUANLY = @NewPermission WHERE MANHANVIEN = @MaNV";
                        SqlCommand cmd = new SqlCommand(updateQuery, conn);
                        cmd.Parameters.AddWithValue("@NewPermission", newPermission);
                        cmd.Parameters.AddWithValue("@MaNV", maNV);
                        cmd.ExecuteNonQuery();
                    }
                    MessageBox.Show("Cập nhật quyền thành công!", "Thông báo", MessageBoxButtons.OK, MessageBoxIcon.Information);
                    LoadData();
                    AddChangeButtons();
                }
                catch (Exception ex)
                {
                    MessageBox.Show("Lỗi khi cập nhật quyền: " + ex.Message);
                }
            }
        }
    }
}