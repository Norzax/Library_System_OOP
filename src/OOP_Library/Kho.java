package OOP_Library;

import java.util.Scanner;

public class Kho {
	private String MaKhu, MaSach;
	private int SLuong, GIA;

	public int getGIA() {
		return GIA;
	}

	public void setGIA(int gIA) {
		GIA = gIA;
	}

	Scanner sc = new Scanner(System.in);
	KT_NhapXuat kt = new KT_NhapXuat();

	public String getMaKhu() {
		return MaKhu;
	}

	public void setMaKhu(String maKhu) {
		MaKhu = maKhu;
	}

	public String getMaSach() {
		return MaSach;
	}

	public void setMaSach(String maSach) {
		MaSach = maSach;
	}

	public int getSLuong() {
		return SLuong;
	}

	public void setSLuong(int sLuong) {
		SLuong = sLuong;
	}

	public Kho(String maKhu, String maSach, int sLuong) {
		super();
		MaKhu = maKhu;
		MaSach = maSach;
		SLuong = sLuong;
	}

	public Kho() {
		// TODO Auto-generated constructor stub
	}

	

	public void Nhap(int g) {
		System.out.print("- Nhập mã Khu trong Kho ( từ 1 -> 8 ) : ");
		MaKhu = kt.KTMaKhu();
//		System.out.print("- Nhập Số Lượng của loại sách có trong kho");
//		SLuong = kt.KTNhapInt(0);
		SLuong = 0;
//		System.out.print("- Nhập giá của cuốn sản phẩm");
		GIA = g;
	}

	public void Xuat(String tens) {
		System.out.printf("%5s%-5s%-5s%-20s%-10d\n", "", MaKhu, MaSach,tens, SLuong);
	}
}
