package OOP_Library;

import java.util.Scanner;

public class Sach_NXB {
	private	String MaSach, MaNXB;
	private int NamXB;
	private static final int thisYear =2019; 
	Scanner sc = new Scanner(System.in);
	KT_NhapXuat kt = new KT_NhapXuat();
	
	public String getMaSach() {
		return MaSach;
	}

	public void setMaSach(String maSach) {
		MaSach = maSach;
	}

	public String getMaNXB() {
		return MaNXB;
	}

	public void setMaNXB(String maNXB) {
		MaNXB = maNXB;
	}

	public int getNamXB() {
		return NamXB;
	}

	public void setNamXB(int namXB) {
		NamXB = namXB;
	}

	public Sach_NXB(String maSach, String maNXB, int namXB) {
		super();
		MaSach = maSach;
		MaNXB = maNXB;
		NamXB = namXB;
	}
	
	public Sach_NXB() {
		// TODO Auto-generated constructor stub
	}
	
	public void Nhap() {
		System.out.print("- Nhập Mã NXB: ");
		MaNXB = sc.nextLine();
		System.out.print("- Nhập Mã Sách: ");
		MaSach = sc.nextLine();
		System.out.print("- Nhập Năm XB: ");
		NamXB = kt.KTLuaChon(thisYear);
	}
	
	public void Xuat() {
		System.out.printf("%5d%-5s%-5s%-5d","",MaSach,MaNXB,NamXB);
	}
}
