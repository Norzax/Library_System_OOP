package OOP_Library;

import java.io.IOException;
import java.util.Scanner;

public class Sach {
	private String MaSach, MaTG,MaNXB,MaVT,TenSach,MaTl;

	Scanner sc = new Scanner(System.in);
	KT_NhapXuat kt = new KT_NhapXuat();
	
	public String getMaSach() {
		return MaSach;
	}

	public void setMaSach(String maSach) {
		MaSach = maSach;
	}

	public String getMaTG() {
		return MaTG;
	}

	public void setMaTG(String maTG) {
		MaTG = maTG;
	}

	public String getMaNXB() {
		return MaNXB;
	}

	public void setMaNXB(String maNXB) {
		MaNXB = maNXB;
	}

	public String getMaVT() {
		return MaVT;
	}

	public void setMaVT(String maVT) {
		MaVT = maVT;
	}

	public String getTenSach() {
		return TenSach;
	}

	public void setTenSach(String tenSach) {
		TenSach = tenSach;
	}

	public String getMaTl() {
		return MaTl;
	}

	public void setMaTl(String maTl) {
		MaTl = maTl;
	}

	public Sach(String maSach, String maTG, String maNXB, String maVT, String tenSach, String maTl) {
		super();
		MaSach = maSach;
		MaTG = maTG;
		MaNXB = maNXB;
		MaVT = maVT;
		TenSach = tenSach;
		MaTl = maTl;
	}
	
	public Sach() {
		// TODO Auto-generated constructor stub
	}
	
	public void Nhap(String maNXB) throws IOException {
//		System.out.print("- Nhập Mã Sách: ");
//		MaSach = kt.KTTen();
		System.out.print("* Nhập Mã Tác Giả: ");
		ArrTG arrTG= new ArrTG();
		MaTG = kt.KTTacGia();
		System.out.print("* Nhập Mã Thể Loại: ");
		MaTl = kt.KTTheLoai();					// Kiểm tra 
//		System.out.print("* Nhập Mã Nhà Xuất Bản: ");
//		MaNXB = kt.KTNXB();					// Kiểm tra
		MaNXB = maNXB;
		System.out.print("* Nhập Mã Vị Trí: ");
		MaVT = kt.KTVitri();					// Kiểm tra 
		System.out.print("* Nhập Tên Sách: ");
		TenSach = kt.KTTen();
	}
	
	public void Xuat() {
		System.out.printf("%5s%-5s%-20s%-5s%-5s%-5s%-5s\n","",MaSach,TenSach,MaTl,MaTG,MaNXB,MaVT);
	}
}
