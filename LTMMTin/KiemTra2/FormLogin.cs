using System;
using System.Data.SqlClient;
using System.Windows.Forms;
using System.Security.Cryptography;
using System.Text;

namespace KiemTra2
{
    public partial class FormLogin : Form
    {
        string connectionString = "Data Source=MSI\\SQLEXPRESS;Initial Catalog=VANPHONGPHAM;Integrated Security=True";

        public FormLogin()
        {
            InitializeComponent();
        }

        private void btnLogin_Click(object sender, EventArgs e)
        {
            string username = txtUsernamee.Text.Trim();
            string password = txtPasswordd.Text.Trim();

            if (CheckAdminLogin(username, password))
            {
                this.Hide();
                FormPhanQuyen f = new FormPhanQuyen(username, password); // Truyền thông tin admin sang
                f.ShowDialog();
                this.Close();
            }
            else
            {
                MessageBox.Show("Sai tài khoản hoặc mật khẩu admin!", "Đăng nhập thất bại", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }

        private bool CheckAdminLogin(string username, string password)
        {
            string hash = HashPassword(password);
            using (SqlConnection conn = new SqlConnection(connectionString))
            {
                conn.Open();
                string sql = "SELECT COUNT(*) FROM NHAN_VIEN WHERE TENDANGNHAP = @user AND MATKHAU = @pass AND LAQUANLY = 1 AND VOHIEUHOA = 0";
                SqlCommand cmd = new SqlCommand(sql, conn);
                cmd.Parameters.AddWithValue("@user", username);
                cmd.Parameters.AddWithValue("@pass", hash);
                int count = (int)cmd.ExecuteScalar();
                return count > 0;
            }
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
    }
}