namespace KiemTra2
{
    partial class FormLogin
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
            this.components = new System.ComponentModel.Container();
            this.txtUsernamee = new System.Windows.Forms.TextBox();
            this.contextMenuStrip1 = new System.Windows.Forms.ContextMenuStrip(this.components);
            this.txtUsername = new System.Windows.Forms.Label();
            this.txtPassword = new System.Windows.Forms.Label();
            this.txtPasswordd = new System.Windows.Forms.TextBox();
            this.btnLogin = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // txtUsernamee
            // 
            this.txtUsernamee.Location = new System.Drawing.Point(49, 61);
            this.txtUsernamee.Multiline = true;
            this.txtUsernamee.Name = "txtUsernamee";
            this.txtUsernamee.Size = new System.Drawing.Size(389, 38);
            this.txtUsernamee.TabIndex = 0;
            // 
            // contextMenuStrip1
            // 
            this.contextMenuStrip1.ImageScalingSize = new System.Drawing.Size(20, 20);
            this.contextMenuStrip1.Name = "contextMenuStrip1";
            this.contextMenuStrip1.Size = new System.Drawing.Size(61, 4);
            // 
            // txtUsername
            // 
            this.txtUsername.AutoSize = true;
            this.txtUsername.Location = new System.Drawing.Point(47, 31);
            this.txtUsername.Name = "txtUsername";
            this.txtUsername.Size = new System.Drawing.Size(98, 16);
            this.txtUsername.TabIndex = 3;
            this.txtUsername.Text = "Tên đăng nhập";
            // 
            // txtPassword
            // 
            this.txtPassword.AutoSize = true;
            this.txtPassword.Location = new System.Drawing.Point(47, 118);
            this.txtPassword.Name = "txtPassword";
            this.txtPassword.Size = new System.Drawing.Size(61, 16);
            this.txtPassword.TabIndex = 4;
            this.txtPassword.Text = "Mật khẩu";
            // 
            // txtPasswordd
            // 
            this.txtPasswordd.Location = new System.Drawing.Point(49, 151);
            this.txtPasswordd.Multiline = true;
            this.txtPasswordd.Name = "txtPasswordd";
            this.txtPasswordd.Size = new System.Drawing.Size(389, 38);
            this.txtPasswordd.TabIndex = 5;
            // 
            // btnLogin
            // 
            this.btnLogin.Location = new System.Drawing.Point(168, 210);
            this.btnLogin.Name = "btnLogin";
            this.btnLogin.Size = new System.Drawing.Size(125, 33);
            this.btnLogin.TabIndex = 6;
            this.btnLogin.Text = "Đăng nhập";
            this.btnLogin.UseVisualStyleBackColor = true;
            this.btnLogin.Click += new System.EventHandler(this.btnLogin_Click);
            // 
            // FormLogin
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(483, 255);
            this.Controls.Add(this.btnLogin);
            this.Controls.Add(this.txtPasswordd);
            this.Controls.Add(this.txtPassword);
            this.Controls.Add(this.txtUsername);
            this.Controls.Add(this.txtUsernamee);
            this.Name = "FormLogin";
            this.Text = "FormLogin";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.TextBox txtUsernamee;
        private System.Windows.Forms.ContextMenuStrip contextMenuStrip1;
        private System.Windows.Forms.Label txtUsername;
        private System.Windows.Forms.Label txtPassword;
        private System.Windows.Forms.TextBox txtPasswordd;
        private System.Windows.Forms.Button btnLogin;
    }
}