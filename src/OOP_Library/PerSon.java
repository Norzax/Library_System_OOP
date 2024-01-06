package OOP_Library;

import java.util.Scanner;

public abstract class PerSon {
	private String Ten, DienThoai, Email, DiaChi;
	private int GioiTinh;
	private NAM_SINH NgaySinh;

	Scanner sc = new Scanner(System.in);
	KT_NhapXuat kt = new KT_NhapXuat();

	public String getTen() {
		return Ten;
	}

	public void setTen(String ten) {
		Ten = ten;
	}

	public String getDienThoai() {
		return DienThoai;
	}

	public void setDienThoai(String dienThoai) {
		DienThoai = dienThoai;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getDiaChi() {
		return DiaChi;
	}

	public void setDiaChi(String diaChi) {
		DiaChi = diaChi;
	}

	public int getGioiTinh() {
		return GioiTinh;
	}

	public void setGioiTinh(int gioiTinh) {
		GioiTinh = gioiTinh;
	}

	public NAM_SINH getNgaySinh() {
		return NgaySinh;
	}

	public void setNgaySinh(int ngay,int thang,int nam) {
		NgaySinh.setNgay(ngay); 
		NgaySinh.setThang(thang);
		NgaySinh.setNam(nam);
	}

	public PerSon() {
		NgaySinh = new NAM_SINH();
	}

	public PerSon(String ten, String dienThoai, String email, String diaChi, int gioiTinh, NAM_SINH ngaySinh) {

		Ten = ten;
		DienThoai = dienThoai;
		Email = email;
		DiaChi = diaChi;
		GioiTinh = gioiTinh;
		NgaySinh = ngaySinh;
	}

	public void Nhap() {
		System.out.print("- Nhap Ho Ten: ");
		Ten = kt.KTTen();
		System.out.println("** Nhap Ngay Sinh: ");
		NgaySinh.Nhap();
		System.out.print("- Nhap Gioi Tinh ( 1. Nam   2. Nu )");
		GioiTinh = kt.KTLuaChon(2);
		// String x =sc.nextLine();

		System.out.print("- Nhap Dia Chi: ");
		DiaChi = sc.nextLine();
		while (true) {

			System.out.print("- Nhap so dien thoai: ");
			DienThoai = sc.nextLine();

			try {

				int dt = Integer.parseInt(DienThoai);
				if (DienThoai.length() != 10)
					System.out.println("** So Dien Thoai = 10 so ** ");
				else if (dt < 0)
					System.out.println("** Loi dinh dang **");
				else
					break;

			} catch (Exception e) {
				System.out.println("** Loi dinh dang **");

			}

		}
		System.out.print("- Nhap Email: ");
		Email =kt.KtEmail();
	}

	public void Xuat() {
		
		System.out.printf("%-30s%-20s",Ten, KTGioiTinh(GioiTinh));
		NgaySinh.Xuat();
		System.out.printf("%-11s%-30s%-15s%-30s\n", "", DiaChi, DienThoai, Email);
	}

	public String KTGioiTinh( int gt) {
		if (gt == 1)
			return "Nam";
		return "Nu";
	}

}
