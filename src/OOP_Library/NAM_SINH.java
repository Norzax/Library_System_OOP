package OOP_Library;

import java.util.Scanner;

public class NAM_SINH {
	private int Ngay, Thang, Nam;
	private final int MAX_NAM = 2020;
	Scanner sc = new Scanner(System.in);
	KT_NhapXuat kt = new KT_NhapXuat();

	public int getNgay() {
		return Ngay;
	}

	public void setNgay(int ngay) {
		Ngay = ngay;
	}

	public int getThang() {
		return Thang;
	}

	public void setThang(int thang) {
		Thang = thang;
	}

	public int getNam() {
		return Nam;
	}

	public void setNam(int nam) {
		Nam = nam;
	}

	public NAM_SINH() {
		Ngay = Thang = Nam = 0;
	}

	public NAM_SINH(int ngay, int thang, int nam) {
		Ngay = ngay;
		Thang = thang;
		Nam = nam;
	}

	public NAM_SINH(NAM_SINH ns) {
		Ngay = ns.Ngay;
		Thang = ns.Ngay;
		Nam = ns.Nam;
	}

	public void Nhap() {
		System.out.print("- Nhap Nam");
		Nam = kt.KTLuaChon(MAX_NAM);

		System.out.print("- Nhap Thang");
		Thang = kt.KTLuaChon(12);

		System.out.print("- Nhap Ngay");
		while (true) {
			Ngay = kt.KTNhapInt(1);
			if (KTNgay(Ngay))
				break;
			else
				System.out.print("- Nhap lai ngay");
		}
	}

	public void Xuat() {
		System.out.format("%02d/%02d/%04d", Ngay, Thang, Nam);
	}

	public boolean KTNamNhuan() {
		if (Nam % 400 == 0 || (Nam % 4 == 0 && Nam % 100 != 0))
			return true;
		return false;
	}

	public boolean KTNgay(int ngay) {

		switch (Thang) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12: {
			if (Ngay > 31)
				return false;
			break;
		}
		case 4:
		case 6:
		case 9:
		case 11: {
			if (Ngay > 30)
				return false;
			break;
		}
		case 2: {
			if (KTNamNhuan() == true)
				if (Ngay > 29)
					return false;
				else
					return true;
			else if (Ngay > 28)
				return false;
			else
				return true;
		}
		}
		return true;
	}

	public boolean KTNguyenNgay(NAM_SINH ns) {
		if (this.Nam < ns.Nam)
			return false;
		else if (this.Nam > ns.Nam)
			return true;
		if (this.Thang < ns.Thang)
			return false;
		else if (this.Thang > ns.Thang)
			return true;
		if (this.Ngay < ns.Ngay)
			return false;
		else if (this.Ngay > ns.Ngay)
			return true;
		return true;
	}

	public boolean SoSanh(NAM_SINH ns) {
		if (Nam != ns.Nam)
			return false;
		if (Thang != ns.Thang)
			return false;
		if (Ngay != ns.Ngay)
			return false;
		return true;
	}
}
