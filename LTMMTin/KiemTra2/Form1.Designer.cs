namespace KiemTra2
{
    partial class FormPhanQuyen
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.dgvQuanLy = new System.Windows.Forms.DataGridView();
            this.grbQuanLy = new System.Windows.Forms.GroupBox();
            this.grbNVBH = new System.Windows.Forms.GroupBox();
            this.dgvNhanVienBanHang = new System.Windows.Forms.DataGridView();
            ((System.ComponentModel.ISupportInitialize)(this.dgvQuanLy)).BeginInit();
            this.grbQuanLy.SuspendLayout();
            this.grbNVBH.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.dgvNhanVienBanHang)).BeginInit();
            this.SuspendLayout();
            // 
            // dgvQuanLy
            // 
            this.dgvQuanLy.AutoSizeColumnsMode = System.Windows.Forms.DataGridViewAutoSizeColumnsMode.Fill;
            this.dgvQuanLy.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dgvQuanLy.Dock = System.Windows.Forms.DockStyle.Fill;
            this.dgvQuanLy.Location = new System.Drawing.Point(3, 18);
            this.dgvQuanLy.Name = "dgvQuanLy";
            this.dgvQuanLy.RowHeadersWidth = 51;
            this.dgvQuanLy.RowTemplate.Height = 24;
            this.dgvQuanLy.Size = new System.Drawing.Size(937, 200);
            this.dgvQuanLy.TabIndex = 1;
            this.dgvQuanLy.CellContentClick += new System.Windows.Forms.DataGridViewCellEventHandler(this.dgvQuanLy_CellContentClick);
            // 
            // grbQuanLy
            // 
            this.grbQuanLy.Controls.Add(this.dgvQuanLy);
            this.grbQuanLy.Dock = System.Windows.Forms.DockStyle.Top;
            this.grbQuanLy.Font = new System.Drawing.Font("Microsoft Sans Serif", 7.8F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.grbQuanLy.Location = new System.Drawing.Point(0, 0);
            this.grbQuanLy.Name = "grbQuanLy";
            this.grbQuanLy.Size = new System.Drawing.Size(943, 221);
            this.grbQuanLy.TabIndex = 2;
            this.grbQuanLy.TabStop = false;
            this.grbQuanLy.Text = "Nhân Viên Quản Lý";
            // 
            // grbNVBH
            // 
            this.grbNVBH.Controls.Add(this.dgvNhanVienBanHang);
            this.grbNVBH.Dock = System.Windows.Forms.DockStyle.Bottom;
            this.grbNVBH.Location = new System.Drawing.Point(0, 227);
            this.grbNVBH.Name = "grbNVBH";
            this.grbNVBH.Size = new System.Drawing.Size(943, 229);
            this.grbNVBH.TabIndex = 3;
            this.grbNVBH.TabStop = false;
            this.grbNVBH.Text = "Nhân Viên Bán Hàng";
            // 
            // dgvNhanVienBanHang
            // 
            this.dgvNhanVienBanHang.AutoSizeColumnsMode = System.Windows.Forms.DataGridViewAutoSizeColumnsMode.Fill;
            this.dgvNhanVienBanHang.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dgvNhanVienBanHang.Dock = System.Windows.Forms.DockStyle.Fill;
            this.dgvNhanVienBanHang.Location = new System.Drawing.Point(3, 18);
            this.dgvNhanVienBanHang.Name = "dgvNhanVienBanHang";
            this.dgvNhanVienBanHang.RowHeadersWidth = 51;
            this.dgvNhanVienBanHang.RowTemplate.Height = 24;
            this.dgvNhanVienBanHang.Size = new System.Drawing.Size(937, 208);
            this.dgvNhanVienBanHang.TabIndex = 1;
            this.dgvNhanVienBanHang.CellContentClick += new System.Windows.Forms.DataGridViewCellEventHandler(this.dgvNhanVienBanHang_CellContentClick);
            // 
            // FormPhanQuyen
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(943, 456);
            this.Controls.Add(this.grbNVBH);
            this.Controls.Add(this.grbQuanLy);
            this.Name = "FormPhanQuyen";
            this.Text = "PHÂN QUYỀN NGƯỜI DÙNG";
            ((System.ComponentModel.ISupportInitialize)(this.dgvQuanLy)).EndInit();
            this.grbQuanLy.ResumeLayout(false);
            this.grbNVBH.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.dgvNhanVienBanHang)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion
        private System.Windows.Forms.DataGridView dgvQuanLy;
        private System.Windows.Forms.GroupBox grbQuanLy;
        private System.Windows.Forms.GroupBox grbNVBH;
        private System.Windows.Forms.DataGridView dgvNhanVienBanHang;
    }
}

