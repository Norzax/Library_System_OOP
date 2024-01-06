package OOP_Library;

import java.util.Scanner;

public class Nha_Xuat_Ban {
	private	String TenTG, DiaChi,DienThoai,MS;

	public String getMS() {
		return MS;
	}

	public void setMS(String mS) {
		MS = mS;
	}

	Scanner sc = new Scanner(System.in);
	KT_NhapXuat kt = new KT_NhapXuat();
	
	public String getTenTG() {
		return TenTG;
	}

	public void setTenTG(String tenTG) {
		TenTG = tenTG;
	}

	public String getDiaChi() {
		return DiaChi;
	}

	public void setDiaChi(String diaChi) {
		DiaChi = diaChi;
	}

	public String getDienThoai() {
		return DienThoai;
	}

	public void setDienThoai(String dienThoai) {
		DienThoai = dienThoai;
	}

	public Nha_Xuat_Ban(String tenTG, String diaChi, String dienThoai) {
		super();
		TenTG = tenTG;
		DiaChi = diaChi;
		DienThoai = dienThoai;
	}
	
	public Nha_Xuat_Ban() {
		// TODO Auto-generated constructor stub
	}
	
	public void Nhap() {
		System.out.print("- Nhập Tên: ");
		TenTG = kt.KTTen();
		System.out.print("- Nhập Địa Chỉ: ");
		DiaChi = sc .nextLine();
		System.out.print("- Nhập Số Điện Thoại: ");
		DienThoai= kt.KtDienThoai();
	}
	
	public void Xuat() {
		System.out.printf("%5s%-5s%-20s%-20s%-15s\n","",MS,TenTG,DiaChi,DienThoai);
	}
	
}
