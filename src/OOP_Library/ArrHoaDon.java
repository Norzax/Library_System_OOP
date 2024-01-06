package OOP_Library;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Scanner;

public class ArrHoaDon implements Arr {

	private int lenArr = 0;
	private int lenFile = 0;
	private HoaDon[] ArrHoaDon = new HoaDon[1];
	private static final String filename = "HoaDon.txt";

	private ArrBillDetail arrBd = new ArrBillDetail();

	// Khai báo hàm Calendar để lấy ngày hiện tại của hệ thống.
	Calendar c = Calendar.getInstance();
	private NAM_SINH Now = new NAM_SINH(c.get(Calendar.DATE), c.get(Calendar.MONTH) + 1, c.get(Calendar.YEAR));

	Scanner sc = new Scanner(System.in);
	KT_NhapXuat kt = new KT_NhapXuat();

	public ArrHoaDon() throws IOException {
		HienDS();
	}

	// Không cần vì ta chỉ cần nhập 1 lần 1 hoá đơn vào trong danh sách
	@Override
	public void ThemDT() throws IOException {

	}

	// Thêm 1 hóa đơn vô file khi nhập trong Class Sách nếu ko có sẽ thêm vô File
	public void Them1(String maNV) throws IOException{
                lenArr += 1;
		ArrHoaDon = Arrays.copyOf(ArrHoaDon, lenArr + lenFile);
		int vt = lenArr + lenFile - 1, tt;
		String mahd;
		ArrHoaDon[vt] = new HoaDon();
		System.out.print("- Nhập Trạng Thái ( 1. Nhập Hàng\t2.Xuất Hàng )");
		tt = kt.KTLuaChon(2);
		ArrHoaDon[vt].setTRANGTHAI(tt);
		System.out.print("- Nhap mã hoá đơn: ");

		// Kiểm tra MAHD trong cả đơn Nhập hàng và Xuất Hàng
		while (true) {
			mahd = kt.KTMaSo();
			ArrHoaDon[vt].setMAHD(mahd);
			if (tt == 1) {
				if (TimKiem_MaSoNH("NH" + mahd, vt) != -1) {
					System.out.print("- Ma nhap da co. Nhap lai: ");

				} else {
					break;
				}
			} else if (TimKiem_MaSoXH("XH" + mahd, vt) != -1) {
				System.out.print("- Ma nhap da co. Nhap lai: ");

			} else {
				break;
			}
		}

		// Nhập hàng
		if (tt == 1) {
			ArrHoaDon[vt].NhapNH(mahd, maNV);
			arrBd.Them1(mahd, tt, ArrHoaDon[vt].getMANV());
		}
		// Xuất hàng
		else {
			ArrHoaDon[vt].NhapXH(mahd, maNV);
			arrBd.Them1(mahd, tt, ArrHoaDon[vt].getMANV());

			// Trong hoá đơn xuất hàng thì Tổng tiền của hoá đơn sẽ lấy từ giá của sản phẩm
			// trong Kho * với số lượng của sản phẩm đó trong CTHD
			ArrHoaDon[vt].setTONG_TIEN(arrBd.TinhTongTienChoHD(mahd));
		}

		// Cập nhật lại ngày xuất hoá đơn trùng vs ngày hiện tại
		ArrHoaDon[vt].setNgay_xuat(Now);
		GhiFile();
	}

	@Override
	public void HienDS() throws IOException {
		File file = new File(filename);
		if (!file.exists()) {
			file.createNewFile();
		}
		FileReader fr = new FileReader(file.getAbsoluteFile());
		BufferedReader br = new BufferedReader(fr);
		String data;
		int i = 0;
		// boolean check = false;
		lenFile = SoluongTrongFile();

		ArrHoaDon = Arrays.copyOf(ArrHoaDon, lenArr + lenFile);
		while ((data = br.readLine()) != null) {
			if (data.length() != 1) {
				ArrHoaDon[i] = new HoaDon();
				Scanner scan = new Scanner(data).useDelimiter("-");
				ArrHoaDon[i].setMAHD(scan.next());
				ArrHoaDon[i].setNgay_xuat(scan.nextInt(), scan.nextInt(), scan.nextInt());
				ArrHoaDon[i].setTRANGTHAI(scan.nextInt());
				ArrHoaDon[i].setMAKH(scan.next());
				ArrHoaDon[i].setMANV(scan.next());
				ArrHoaDon[i++].setTONG_TIEN(scan.nextDouble());
			}

		}

		if (br != null) {
			br.close();
		}
		if (fr != null) {
			fr.close();
		}

	}

	public void GhiFile() throws IOException {
		File file = new File(filename);
		if (!file.exists()) {
			file.createNewFile();
		}
		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		for (int i = 0; i < lenArr + lenFile; i++) {

			String ns = ArrHoaDon[i].getNgay_xuat().getNgay() + "-" + ArrHoaDon[i].getNgay_xuat().getThang() + "-"
					+ ArrHoaDon[i].getNgay_xuat().getNam();
			String data = ArrHoaDon[i].getMAHD() + "-" + ns + "-" + ArrHoaDon[i].getTRANGTHAI() + "-"
					+ ArrHoaDon[i].getMAKH() + "-" + ArrHoaDon[i].getMANV() + "-" + ArrHoaDon[i].getTONG_TIEN();

			bw.write(data);
			bw.newLine();
		}

		if (bw != null) {
			bw.close();
		}
		if (fw != null) {
			fw.close();
		}
	}

	public void GhiDeFile() throws IOException {
		File file = new File(filename);
		if (!file.exists()) {
			file.createNewFile();
		}
		FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
		BufferedWriter bw = new BufferedWriter(fw);
		for (int i = 0; i < lenArr + lenFile; i++) {

			String ns = ArrHoaDon[i].getNgay_xuat().getNgay() + "-" + ArrHoaDon[i].getNgay_xuat().getThang() + "-"
					+ ArrHoaDon[i].getNgay_xuat().getNam();
			String data = ArrHoaDon[i].getMAHD() + "-" + ns + "-" + ArrHoaDon[i].getTRANGTHAI() + "-"
					+ ArrHoaDon[i].getMAKH() + "-" + ArrHoaDon[i].getMANV() + "-" + ArrHoaDon[i].getTONG_TIEN();

			bw.write(data);
			bw.newLine();
		}

		if (bw != null) {
			bw.close();
		}
		if (fw != null) {
			fw.close();
		}
	}

	@Override
	public int SoluongTrongFile() throws IOException {

		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader(filename);
			br = new BufferedReader(fr);
			String data;
			lenFile = 0;
			while ((data = br.readLine()) != null) {
				if (data.length() != 1) {
					lenFile++;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (fr != null) {
				try {
					fr.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return lenFile;
	}

	public int TimKiem_MaSoNH(String ma, int j) {
		if (j == 0) {
			return -1;
		}

		for (int i = 0; i < j; i++) {
			if (ArrHoaDon[i].getTRANGTHAI() == 1 && ArrHoaDon[i].getMAHD().compareTo(ma) == 0) {
				return i;
			}
		}
		return -1;

	}

	public int TimKiem_MaSoXH(String ma, int j) {
		if (j == 0) {
			return -1;
		}

		for (int i = 0; i < j; i++) {
			if (ArrHoaDon[i].getTRANGTHAI() == 2 && ArrHoaDon[i].getMAHD().compareTo(ma) == 0) {
				return i;
			}
		}
		return -1;

	}

	public void TimKiem_MS() throws IOException {
		// HienPS();

		System.out.print("-Nhập mã Hoa don bạn muốn tìm :");
		String ma = sc.nextLine();
		int dem = 0;
		for (int i = 0; i < lenArr + lenFile; i++) {

			// Kiem tra MAHD co bang vs NH ( so ) hoac XH ( so ) hoac ma.
			if (ArrHoaDon[i].getMAHD().compareTo("NH" + ma) == 0 || ArrHoaDon[i].getMAHD().compareTo("XH" + ma) == 0
					|| ArrHoaDon[i].getMAHD().compareTo(ma) == 0) {
				System.out.println("\n============HD " + (i + 1) + "=========");
				ArrHoaDon[i].Xuat();
				dem++;
			}
		}
		if (dem == 0) {
			System.out.printf("- Không có mã %s .", ma);
		}
	}

	@Override
	public void TimKiem_Ten() {

	}

	// Tìm Kiếm hoá đơn từ ngày A -> B
	public void TimKiem_MinMaxNgay() throws IOException {
		NAM_SINH minns = new NAM_SINH();
		System.out.println("- Bạn muốn tìm hoá đơn từ : ");
		minns.Nhap();
		NAM_SINH maxns = new NAM_SINH();
		System.out.println(" đến : ");
		maxns.Nhap();
		for (int i = 0; i < lenArr + lenFile; i++) {
			if (ArrHoaDon[i].getNgay_xuat().KTNguyenNgay(minns) && maxns.KTNguyenNgay(ArrHoaDon[i].getNgay_xuat())) {
				System.out.println("\n============HD " + (i + 1) + "=========");
				ArrHoaDon[i].Xuat();
			}
		}
	}

	// TÌm Kiếm hoá đơn từ ngày A
	public void TimKiem_MinNgay() throws IOException {
		NAM_SINH minns = new NAM_SINH();
		System.out.println("- Bạn muốn tìm hoá đơn từ : ");
		minns.Nhap();
		for (int i = 0; i < lenArr + lenFile; i++) {
			if (ArrHoaDon[i].getNgay_xuat().KTNguyenNgay(minns)) {
				System.out.println("\n============HD " + (i + 1) + "=========");
				ArrHoaDon[i].Xuat();
			}
		}
	}

	// Tìm Kiếm hoá đơn từ đầu đến ngày B
	public void TimKiem_MaxNgay() throws IOException {
		NAM_SINH maxns = new NAM_SINH();
		System.out.println("- Bạn muốn tìm hoá đơn đến ngày : ");
		maxns.Nhap();
		for (int i = 0; i < lenArr + lenFile; i++) {
			if (maxns.KTNguyenNgay(ArrHoaDon[i].getNgay_xuat())) {
				System.out.println("\n============HD " + (i + 1) + "=========");
				ArrHoaDon[i].Xuat();
			}
		}
	}

	// Tìm kiếm hoá đơn trong ngày C nào đó
	public void TimKiem_Ngay(int lc, NAM_SINH ns) throws IOException {
		if (lc == 3)
			for (int i = 0; i < lenArr + lenFile; i++) {
				if (ArrHoaDon[i].getNgay_xuat().SoSanh(ns)) {
					System.out.println("\n============HD " + (i + 1) + "=========");
					ArrHoaDon[i].Xuat();
				}
			}
		else {
			for (int i = 0; i < lenArr + lenFile; i++) {
				if (ArrHoaDon[i].getTRANGTHAI() == lc && ArrHoaDon[i].getNgay_xuat().SoSanh(ns)) {
					System.out.println("\n============HD " + (i + 1) + "=========");
					ArrHoaDon[i].Xuat();
				}
			}
		}
	}

	public void TimKiem_MANV() throws IOException {
		System.out.print("- Bạn muốn tìm hoá đơn của NV (1. Nhập mã    2. Nhập Tên)");
		int lc = kt.KTLuaChon(2);
		String maNV = "", tenNV = "";

		// Đặt cờ hiệu
		boolean check = true;
		if (lc == 1) {
			System.out.print("- Nhập mã: ");
			maNV = kt.KT_MaNV();
		} else {
			ArrEmployee ae = new ArrEmployee();
			System.out.print("- Nhập tên: ");
			tenNV = kt.KTTen();

			// Tìm vị trí của tên NV trong danh sách
			int vtnv = ae.TimKiem_Ten(tenNV);
			if (vtnv == -1) {
				System.out.println("- Không có nhân viên nào tên : " + tenNV);

				// Set giá trị của cờ hiệu là false vì lúc này tên NV ko tồn tại
				check = false;
			} else
				// Lấy mã NV
				maNV = ae.getArremp()[vtnv].getMS();
		}

		for (int i = 0; i < lenArr + lenFile; i++) {
			if (check && ArrHoaDon[i].getMANV().compareTo(maNV) == 0) {
				System.out.println("\n============HD " + (i + 1) + "=========");
				ArrHoaDon[i].Xuat();
			}
		}
	}

	public void TimKiem_MAKH() throws IOException {
		System.out.print("- Bạn muốn tìm hoá đơn của KH (1. Nhập mã    2. Nhập Tên)");
		int lc = kt.KTLuaChon(2);
		String maKH = "", tenKH = "";
		boolean check = true;
		if (lc == 1) {
			System.out.print("- Nhập mã: ");
			maKH = kt.KT_MaKH();
		} else {
			ArrCustomer ac = new ArrCustomer();
			System.out.print("- Nhập tên: ");
			tenKH = kt.KTTen();

			// Tìm vị trí của tên NV trong danh sách
			int vtnv = ac.TimKiem_Ten(tenKH);
			if (vtnv == -1) {
				System.out.println("- Không có khách hàng thành viên nào tên : " + tenKH);

				// Set giá trị của cờ hiệu là false vì lúc này tên NV ko tồn tại
				check = false;
			} else
				// Lấy mã NV
				maKH = ac.getArrcus()[vtnv].getMS();
		}

		for (int i = 0; i < lenArr + lenFile; i++) {
			if (check && ArrHoaDon[i].getMAKH().compareTo(maKH) == 0) {
				System.out.println("\n============HD " + (i + 1) + "=========");
				ArrHoaDon[i].Xuat();
			}
		}
	}

	public void TimKiem_MANXB() throws IOException {
		System.out.print("- Bạn muốn tìm hoá đơn của NXB ( Nhập mã ) : ");
		String maNXB = kt.KTNXB();
		for (int i = 0; i < lenArr + lenFile; i++) {
			if (ArrHoaDon[i].getTRANGTHAI() == 2 && ArrHoaDon[i].getMAKH().compareTo(maNXB) == 0)
				System.out.println("\n============HD " + (i + 1) + "=========");
			ArrHoaDon[i].Xuat();
		}
	}

	public HoaDon TruyXuatPhanTu(int vt) {
		return ArrHoaDon[vt];
	}

	public void TongDoanhThuTrongNgay(int lc) throws IOException {
		int tong = 0;
		if (lc != 3) {
			for (int i = 0; i < lenArr + lenFile; i++) {

				if (ArrHoaDon[i].getTRANGTHAI() == lc && ArrHoaDon[i].getNgay_xuat().SoSanh(Now)) {
					System.out.println("\n============HD " + (i + 1) + "=========");
					ArrHoaDon[i].Xuat();
					tong += ArrHoaDon[i].getTONG_TIEN();
				}
			}
			if (lc == 1)
				System.out.println("- Tổng doanh thu hôm nay cửa hàng nhập vô là : " + tong);
			else
				System.out.println("- Tổng doanh thu hôm nay cửa hàng xuất đi là : " + tong);
		} else {
			for (int i = 0; i < lenArr + lenFile; i++) {

				if (ArrHoaDon[i].getTRANGTHAI() == lc && ArrHoaDon[i].getNgay_xuat().SoSanh(Now)) {
					System.out.println("\n============HD " + (i + 1) + "=========");
					ArrHoaDon[i].Xuat();
					tong += ArrHoaDon[i].getTONG_TIEN();
				}
			}
		}
	}

	@Override
	public void Xoa() {
		while (true) {
			System.out.print("- Nhập mã số hóa đơn bạn muốn xóa : ");
			String ma = kt.KTMaSo();
			int vt = TimKiem_MaSo(ma);
			System.out.println(vt);

			if (vt != -1) {
				System.out.print("- Bạn có thực sự muốn xoa hóa đơn này ( y / n ): ");
				while (true) {
					String regex = "y|Y";
					String yn = kt.KTYesNo();
					if (yn.matches(regex)) {
						XoaPS(vt);
						System.out.println("- Bạn đã xoá thành công.");
						break;
					} else {
						System.out.print("- Bạn đã không xóa hóa đơn này.");

						break;
					}
				}

			} else {
				System.out.println("- Không có mã số trong danh sách. ");
			}
			System.out.print("- Bạn có muốn tiếp tục ( y / n ): ");

			String regex = "[yY]";
			String yn = kt.KTYesNo();
			if (yn.matches(regex) == false) {
				break;
			}
		}

	}

	public void XoaPS(int vt) {
		for (int i = vt; i < lenArr - 1 + lenFile; i++) {
			ArrHoaDon[i] = ArrHoaDon[i + 1];
		}
		lenArr--;
	}

	@Override
	public void Sua() throws IOException {

		System.out.print("- Nhập mã hóa đơn sửa: ");
		String ma = kt.KTMaSo();
                
		int vt;
		if ((vt = TimKiem_MaSo(ma)) != -1) {
			System.out.print("- Bạn có chắc muốn sửa thông tin của hóa đơn này ( y / n ): ");
			while (true) {
				String regex = "[yY]";
				String yn = kt.KTYesNo();
				if (yn.matches(regex)) {
					for (int i = 0; i < lenArr + lenFile; i++) {
						if (i == vt) {
                                                    ArrHoaDon[i].Xuat();
							ArrHoaDon[i] = MenuSua(ArrHoaDon[i]);
						}

					}
					System.out.println("- Bạn đã sửa thành công. ");
					break;
				} else {
					System.out.print("- Bạn đã không xóa sách này.");
					break;
				}
			}

		} else {
			System.out.println("- Mã hóa đơn bạn tìm không có trong danh sách. ");
		}

	}

	public HoaDon MenuSua(HoaDon tg) {
		int lc;
		String temp;
		do {
			System.out.println("** Bạn muốn sửa thông tin nào: ");
			System.out.println("1.Mã HD.");
			System.out.println("2.Mã KH.");
			System.out.println("3.Mã NV.");
			System.out.println("4.Trạng Thái");
			System.out.println("5.Tổng Tiền");
			System.out.println("6.Ngày xuất");
			System.out.println("7.Thoát");

			System.out.print("- Nhập lựa chọn của bạn");
			lc = kt.KTLuaChon(4);
			switch (lc) {
			case 1: {
				System.out.print("- Nhập Mã HD : ");
				temp = sc.nextLine();
				tg.setMAHD(temp);
				break;
			}
			case 2: {
				System.out.print("- Nhập Mã KH: ");
				temp = kt.KTMaSo();
				tg.setMAKH(temp);
				break;
			}
			case 3: {
				System.out.print("- Nhập Mã NV: ");
				temp = sc.nextLine();
				tg.setMANV(temp);
				break;
			}

			case 4: {
				System.out.print("- Nhập Trạng Thái: ");
				int tt = kt.KTLuaChon(2);
				tg.setTRANGTHAI(tt);
				break;
			}
			case 5: {
				System.out.print("- Nhập Tổng Tiền: ");
				int tt = kt.KTNhapInt(0);
				tg.setTONG_TIEN(tt);
				break;
			}
			case 6: {
				System.out.println("- Nhập Ngày Xuất: ");
				NAM_SINH ns = new NAM_SINH();
				ns.Nhap();
				tg.setNgay_xuat(ns.getNgay(), ns.getThang(), ns.getNam());
				break;
			}
			case 7: {
				System.out.println("- Bạn đã thoát. ");
				break;
			}
			}
		} while (lc != 8);
		return tg;
	}

	public void Xuat(int lc) throws IOException {
		if (SoluongTrongFile() == 0 && lenArr == 0) {
			String yn;
			System.out.print(
					"- Danh sách hóa đơn rỗng : Ban co muon them ???.(y / n) : ");
			while (true) {
				String regex = "[yY]";
				yn = kt.KTYesNo();
				if (yn.matches(regex)) {
					ThemDT();
					break;
				} else {
					break;
				}
			}
		}

		if (lc == 3) {

			for (int j = 0; j < lenArr + lenFile; j++) {
				System.out.println("\n============HD " + (j + 1) + "=========");
				ArrHoaDon[j].Xuat();
			}
		} else {
			for (int j = 0; j < lenArr + lenFile; j++) {
				if (ArrHoaDon[j].getTRANGTHAI() == lc) {
					System.out.println("\n============HD " + (j + 1) + "=========");
					ArrHoaDon[j].Xuat();
				}
			}
		}
	}

//	public void Xuat2(int tt) throws IOException {
//		if (SoluongTrongFile() == 0 && lenArr == 0) {
//			String yn;
//			System.out.print("- Danh HoaDon chua co nhan vien : Ban co muon them nhan vien ???.(y / n) : ");
//			while (true) {
//				String regex = "[yY]";
//				yn = kt.KTYesNo();
//				if (yn.matches(regex)) {
//					ThemDT();
//					break;
//				} else {
//					break;
//				}
//			}
//		}
//
//		if (tt != 3) {
//
//			for (int j = 0; j < lenArr + lenFile; j++) {
//				System.out.println("\n============HD " + (j + 1) + "=========");
//				ArrHoaDon[j].Xuat();
//				arrBd.TimKiem_MS(ArrHoaDon[j].getMAHD());
//			}
//		} else {
//			for (int j = 0; j < lenArr + lenFile; j++) {
//				if (ArrHoaDon[j].getTRANGTHAI() == tt) {
//					ArrHoaDon[j].Xuat();
//					arrBd.TimKiem_MS(ArrHoaDon[j].getMAHD());
//				}
//			}
//		}
//	}

	public void MenuChinh() throws IOException {
		int lc = 0;
		boolean check = false;
		do {
			System.out.println("=============================MENU============================");
			System.out.println("1.Tìm kiếm hoá đơn.");
			System.out.println("2.Xuất.");
			System.out.println("3.Xoá.");
			System.out.println("4.Sửa.");
			System.out.println("5.Thoát.");
			System.out.print("- Nhập Lựa Chọn của bạn");
			lc = kt.KTLuaChon(5);
			switch (lc) {
			case 1: {
				int lctk = 0;
				do {
					System.out.println("=====MENU=====");
					System.out.println("1. Tìm kiếm theo Mã NV.");
					System.out.println("2. Tìm kiếm theo Mã KH.");
					System.out.println("3. Tìm kiếm theo ngày.");
					System.out.println("4. Thoát.");
					System.out.print("- Nhập lựa chọn của bạn");
					lctk = kt.KTLuaChon(4);
					switch (lctk) {
					case 1: {
						TimKiem_MANV();
						kt.Phim();
						break;
					}

					case 2: {
						TimKiem_MAKH();
						kt.Phim();
						break;
					}
					case 3: {
						int lcn = 0;
						String thoat;
						System.out.println("=====MENU=====");
						System.out.println("** Chọn định dạng **");
						System.out.println("1. Từ ngày A -> B.");
						System.out.println("2. Từ ngày A .");
						System.out.println("3. Từ đầu đến ngày B.");
						System.out.println("4. Trong ngày C nào đó.");
						System.out.println("5. Thoát.");
						System.out.print("- Nhập lựa chọn của bạn");
						lcn = kt.KTLuaChon(5);
						switch (lcn) {
						case 1: {
							TimKiem_MinMaxNgay();
							kt.Phim();
							break;
						}
						case 2: {
							TimKiem_MinNgay();
							kt.Phim();
							break;
						}
						case 3: {
							TimKiem_MaxNgay();
							kt.Phim();
							break;
						}
						case 4: {
							NAM_SINH ns = new NAM_SINH();
							System.out.println("- Bạn muốn tìm hoá đơn ngày : ");
							ns.Nhap();
							TimKiem_Ngay(3, ns);
							kt.Phim();
							break;
						}
						case 5: {
							System.out.println("- Bạn đã thoát . ");
							thoat = kt.Out();
							kt.Phim();
							break;
						}
						}
						break;
					}

					}
				} while (lctk != 4);
				break;
			}
			case 2: {
				System.out.print("- Ban muon xuat hoa don 1.Nhap  2.Xuat  3.Tat ca");
				int tt = kt.KTLuaChon(3);
				Xuat(tt);
				kt.Phim();
				break;
			}
			case 3: {
				Xoa();
				kt.Phim();
				break;
			}
			case 4: {
				Sua();
				kt.Phim();
				break;
			}
			case 5: {
				System.out.print("- Bạn có muốn ghi vào cơ sở dữ liệu ko( y / n ) ??? : ");
				String s = kt.KTYesNo();
				String regex = "y|Y";
				if (s.matches(regex)) {
					GhiFile();
				}
				System.out.println("- Bạn đã thoát.");
		
				break;
			}

			}
		} while (lc != 5);
	}

	@Override
	public int TimKiem_MaSo(String ma) {
            int temp=Integer.parseInt(ma);
		for (int i = 0; i < lenArr + lenFile; i++) {
			if (i==temp) {
				return i;
			}
		}
		return -1;
	}
}
