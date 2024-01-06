package OOP_Library;

import java.util.Scanner;

public class Vi_Tri {
	private String MaVT, MaKe, MaKhu, MaNgan;

	Scanner sc = new Scanner(System.in);
	KT_NhapXuat kt = new KT_NhapXuat();

	public String getMaVT() {
		return MaVT;
	}

	public void setMaVT(String maVT) {
		MaVT = maVT;
	}

	public String getMaKe() {
		return MaKe;
	}

	public void setMaKe(String maKe) {
		MaKe = maKe;
	}

	public String getMaKhu() {
		return MaKhu;
	}

	public void setMaKhu(String maKhu) {
		MaKhu = maKhu;
	}

	public String getMaNgan() {
		return MaNgan;
	}

	public void setMaNgan(String maNgan) {
		MaNgan = maNgan;
	}

	public Vi_Tri(String maVT, String maKe, String maKhu, String maNgan) {
		super();
		MaVT = maVT;
		MaKe = maKe;
		MaKhu = maKhu;
		MaNgan = maNgan;
	}

	public Vi_Tri() {
		// TODO Auto-generated constructor stub
	}

	public void Nhap() {
		System.out.print("- Nhập Mã Vị Trí: ");
		MaVT = sc.nextLine();
		System.out.print("- Nhập Mã Khu: ");
		MaKhu = sc.nextLine();
		System.out.print("- Nhập Mã Ngăn: ");
		MaNgan = sc.nextLine();
		System.out.print("- Nhập Mã Kệ: ");
		MaKe = sc.nextLine();

	}

	public void Xuat() {
		System.out.printf("%5s%-5s%-5s%-5s%-5s", "", MaVT, MaKhu, MaNgan, MaKe);
	}
}
