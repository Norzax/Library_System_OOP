package OOP_Library;

import java.util.Scanner;

public class TheLoai {
	private String MaTheLoai, TenTheLoai;

	Scanner sc = new Scanner(System.in);
	
	KT_NhapXuat kt = new KT_NhapXuat();
	public String getMaTheLoai() {
		return MaTheLoai;
	}

	public void setMaTheLoai(String maTheLoai) {
		MaTheLoai = maTheLoai;
	}

	public String getTenTheLoai() {
		return TenTheLoai;
	}

	public void setTenTheLoai(String tenTheLoai) {
		TenTheLoai = tenTheLoai;
	}

	public TheLoai(String maTheLoai, String tenTheLoai) {
		super();
		MaTheLoai = maTheLoai;
		TenTheLoai = tenTheLoai;
	}
	
	public TheLoai() {
		// TODO Auto-generated constructor stub
	}
	
	public void Nhap() {
		System.out.print("- Nhap Ma The Loai: ");
		MaTheLoai = sc.nextLine();
		System.out.print("- Nhap Ten The Loai: ");
		TenTheLoai = kt.KTTen();
	}
	
	public void Xuat() {
		System.out.printf("%5s%-10s%-20s\n","",MaTheLoai,TenTheLoai);
	}
}
