package OOP_Library;

import java.io.IOException;
import java.util.Scanner;

public class HoaDon {
	private String MAHD, MAKH, MANV;
	private NAM_SINH Ngay_xuat = new NAM_SINH();
	private int TRANG_THAI;
	private double TONG_TIEN = 1000;
        private int songaymuon;
	Scanner sc = new Scanner(System.in);
	KT_NhapXuat kt = new KT_NhapXuat();

        public void setsongaymuon(int songaymuon){
            songaymuon=songaymuon;
        }
        
        public int getsongaymuon(){
            return songaymuon;
        }
        
	public String getMAHD() {
		return MAHD;
	}

	public void setMAHD(String mAHD) {
		MAHD = mAHD;
	}

	public String getMAKH() {
		return MAKH;
	}

	public void setMAKH(String mAKH) {
		MAKH = mAKH;
	}

	public String getMANV() {
		return MANV;
	}

	public void setMANV(String mANV) {
		MANV = mANV;
	}

	public int getTRANGTHAI() {
		return TRANG_THAI;
	}

	public void setTRANGTHAI(int tRANGTHAI) {
		TRANG_THAI = tRANGTHAI;
	}

	public NAM_SINH getNgay_xuat() {
		return Ngay_xuat;
	}

	public void setNgay_xuat(int ngay, int thang, int nam) {
		Ngay_xuat.setNgay(ngay);
		Ngay_xuat.setThang(thang);
		Ngay_xuat.setNam(nam);
	}

	public void setNgay_xuat(NAM_SINH ns) {
		Ngay_xuat.setNgay(ns.getNgay());
		Ngay_xuat.setThang(ns.getThang());
		Ngay_xuat.setNam(ns.getNam());
	}

	public double getTONG_TIEN() {
		return TONG_TIEN;
	}

	public void setTONG_TIEN(double tONG_TIEN) {
		TONG_TIEN = tONG_TIEN;
	}

	public HoaDon() {

	}

	public HoaDon(String mAHD, String mAKH, String mANV, int tRANGTHAI, NAM_SINH ngay_xuat, double tONG_TIEN) {
		super();
		MAHD = mAHD;
		MAKH = mAKH;
		MANV = mANV;
		TRANG_THAI = tRANGTHAI;
		Ngay_xuat = ngay_xuat;
		TONG_TIEN = tONG_TIEN;
	}

	public void NhapNH(String maHD, String maNV) throws IOException {

		MAHD = "NH" + maHD;
		System.out.print("- Nhập Mã Nhà Sản Xuất: ");
		MAKH = kt.KTNXB();
		MANV = maNV;
		System.out.print("- Nhập số ngày mượn sach: p");
		TONG_TIEN = (kt.KTNhapInt(0))*songaymuon;
                
	}

	public void NhapXH(String maHD, String maNV) throws IOException {

		ArrKho ak = new ArrKho();
		MAHD = "XH" + maHD;
		System.out.print("- Bạn có là thành viên của shop không ( y / n ) ??? ");
		String yn = kt.KTYesNo();
		String regex = "y|Y";
		if(yn.matches(regex)) {
			
			System.out.print("- Nhập Mã Khách Hàng: ");
			MAKH = kt.KT_MaKH();
		}
		else
			MAKH = null;
		MANV = maNV;
	}

	public void Xuat() throws IOException {
		ArrBillDetail ab = new ArrBillDetail();
		ArrEmployee ae = new ArrEmployee();
		ArrCustomer ac = new ArrCustomer();
		int vte = ae.TimKiem_MaSo(MANV);
		int vtc = ac.TimKiem_MaSo(MAKH);
		String tenNV = ae.getArremp()[vte].getTen();
		String tenKH="";
		if(vtc != -1)
		tenKH = ac.getArrcus()[vtc].getTen();
		System.out.printf("%5s%-10s", "", MAHD);
		Ngay_xuat.Xuat();
		System.out.printf("%-11s%-20s%-20s%-20s%-10.2f\n","", KT_TT(), tenKH, tenNV, TONG_TIEN);
		ab.TimKiem_MS(MAHD);
	}

	public String KT_TT() {
		if (TRANG_THAI == 1)
			return "Nhập Hàng";
		return "Xuất Hàng";
	}
}
