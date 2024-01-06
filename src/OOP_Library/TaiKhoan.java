package OOP_Library;

import java.io.IOException;
import java.util.Scanner;

public class TaiKhoan {
	private String username, password,MANV;
	private int Quyen;
	public int getQuyen() {
		return Quyen;
	}

	public void setQuyen(int quyen) {
		Quyen = quyen;
	}

	public String getMANV() {
		return MANV;
	}

	public void setMANV(String mANV) {
		MANV = mANV;
	}

	Scanner sc = new Scanner(System.in);
	KT_NhapXuat kt = new KT_NhapXuat();
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public TaiKhoan(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	public TaiKhoan(String user) {
		username = user;
	}
	
	public TaiKhoan() {
	
	}
	
	public void Nhap() throws IOException {//Nhân viên
		
		System.out.print("- Password: ");
		password = sc.nextLine();
		System.out.print("- Mã NHÂN VIÊN của bạn là: ");
		MANV = kt.KT_MaNV();
		Quyen= 2;
	}
        public void Nhap2() throws IOException {//Khách hàng
		
		System.out.print("- Password: ");
		password = sc.nextLine();
		System.out.print("- Mã KHÁCH HÀNG của bạn là: ");
		MANV = kt.KT_MaKH();
		Quyen= 3;
	}
	
	public String KTQuyen() {
		if(Quyen == 1)
			return "admin";
                else if(Quyen==2) return "user";
                else return "Khách";
	}
	
	public void Xuat() {
		System.out.printf("%5s%-10s%-20s%-5s%-10s\n", "", username, password,MANV,KTQuyen());
		
	}
}
