//package DOAN;
//
//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.Arrays;
//import java.util.Scanner;
//
//public class ArrVT implements Arr {
//	private int len;
//	private Vi_Tri[] ArrVT = new Vi_Tri[0];
//	private static final String filename = "ViTri.txt";
//
//	Scanner sc = new Scanner(System.in);
//	KT_NhapXuat kt = new KT_NhapXuat();
//
//	// Thêm danh sách Tác Giả vô File.
//	@Override
//	public void ThemPS() throws IOException {
//		System.out.print("- Nhap so luong Vị Trí");
//		int n = kt.KTNhapInt();
//		File file = new File(filename);
//		if (!file.exists()) {
//			file.createNewFile();
//		}
//		FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
//		BufferedWriter bw = new BufferedWriter(fw);
//		len = SoluongTrongFile();
//		HienPS();
//
//		ArrVT = Arrays.copyOf(ArrVT, len + n);
//		for (int i = len; i < len + n; i++) {
//			System.out.printf("\n%60s %d%s\n\n", "------------VT", i + 1, "------------");
//
//			ArrVT[i] = new Vi_Tri();
//			ArrVT[i].setMaVT("" + (i + 1));
//			ArrVT[i].Nhap();
//
//			String data = ArrVT[len + n].getMaVT() + "-" + ArrVT[len + n].getMaKhu() + "-" + ArrVT[len + n].getMaNgan()
//					+ "-" + ArrVT[len + n].getMaKe();
//			bw.write(data);
//			bw.newLine();
//		}
//		len += n; // cÃ³ Ä‘á»ƒ khi gá»�i hÃ m HienNV() mÃ  náº¿u trong file ko cÃ³ nv nÃ o háº¿t
//					// thÃ¬ sáº½ gá»�i hÃ m
//		// thÃªm vÃ  luu láº¡i biáº¿n len khi thÃªm n nv
//		if (bw != null)
//			bw.close();
//		if (fw != null)
//			fw.close();
//
//	}
//
//	// Thêm 1 Tác Giả vô file khi nhập trong Class Sách nếu ko có sẽ thêm vô File
//	public void Them1(String ma) throws IOException {
//		int n = 1;
//
//		File file = new File(filename);
//		if (!file.exists()) {
//			file.createNewFile();
//		}
//		FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
//		BufferedWriter bw = new BufferedWriter(fw);
//		len = SoluongTrongFile();
//		HienPS();
//
//		ArrVT = Arrays.copyOf(ArrVT, len + n);
//		ArrVT[len + n].setMaVT("" + (int) (len + n));
//		ArrVT[len + n].Nhap();
//		String data = ArrVT[len + n].getMaVT() + "-" + ArrVT[len + n].getMaKhu() + "-" + ArrVT[len + n].getMaNgan()
//				+ "-" + ArrVT[len + n].getMaKe();
//		bw.write(data);
//		bw.newLine();
//		len += n; // cÃ³ Ä‘á»ƒ khi gá»�i hÃ m HienNV() mÃ  náº¿u trong file ko cÃ³ nv nÃ o háº¿t
//					// thÃ¬ sáº½ gá»�i hÃ m
//		// thÃªm vÃ  luu láº¡i biáº¿n len khi thÃªm n nv
//		if (bw != null)
//			bw.close();
//		if (fw != null)
//			fw.close();
//	}
//
//	@Override
//	public void HienPS() throws IOException {
//		FileReader fr = new FileReader(filename);
//		BufferedReader br = new BufferedReader(fr);
//		String data;
//		int i = 0;
//		// boolean check = false;
//		len = SoluongTrongFile();
//
//		ArrVT = Arrays.copyOf(ArrVT, len);
//		while ((data = br.readLine()) != null) {
//			if (len != 0) {
//				ArrVT[i] = new Vi_Tri();
//				Scanner scan = new Scanner(data).useDelimiter("-");
//				ArrVT[i].setMaVT(scan.next());
//				ArrVT[i].setMaKhu(scan.next());
//				ArrVT[i].setMaNgan(scan.next());
//				ArrVT[i++].setMaKe(scan.next());
//
//			}
//
//		}
//
//		if (br != null)
//			br.close();
//		if (fr != null)
//			fr.close();
//
//	}
//
//	@Override
//	public int SoluongTrongFile() throws IOException {
//
//		FileReader fr = null;
//		BufferedReader br = null;
//		try {
//			fr = new FileReader(filename);
//			br = new BufferedReader(fr);
//			String data;
//			len = 0;
//			while ((data = br.readLine()) != null) {
//				if (data.length() != 1) {
//					len++;
//				}
//			}
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			if (br != null)
//				try {
//					br.close();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			if (fr != null)
//				try {
//					fr.close();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//		}
//
//		return len;
//	}
//
//	@Override
//	public int TimKiem_MaSo(String ma) throws IOException {
//
//		for (int i = 0; i < len; i++) {
//			if (ArrVT[i].getMaVT().equalsIgnoreCase(ma))
//				return i;
//		}
//		return -1;
//
//	}
//
//	public void TimKiem_MS() throws IOException {
//		// HienPS();
//
//		System.out.print("-Nhập mã TG bạn muốn tìm :");
//		String ma = sc.nextLine();
//		int dem = 0;
//		for (int i = 0; i < len; i++) {
//			if (ArrVT[i].getMaVT().compareTo(ma) == 0) {
//				ArrVT[i].Xuat();
//				dem++;
//			}
//		}
//		if (dem == 0)
//			System.out.printf("- Không có mã %s .", ma);
//	}
//
//	@Override
//	public void TimKiem_Ten() throws IOException {
//		// HienPS();
//		System.out.print("-Nháº­p tÃªn NV báº¡n muá»‘n tÃ¬m: ");
//		String ma = sc.nextLine();
//		int dem = 0;
//		for (int i = 0; i < len; i++) {
//			if (ArrVT[i].getMaKhu().compareTo(ma) == 0) {
//				ArrVT[i].Xuat();
//				dem++;
//			}
//		}
//		if (dem == 0)
//			System.out.printf("- KhÃ´ng cÃ³ nv nÃ o tÃªn %s .", ma);
//
//	}
//
//	@Override
//	public void Xoa() throws IOException {
//
//		FileWriter fw = new FileWriter(filename);
//		BufferedWriter bw = new BufferedWriter(fw);
//		while (true) {
//			System.out.print("- Nhập mã số tg bạn muốn xóa : ");
//			String ma = kt.KTMaSo();
//			int vt = TimKiem_MaSo(ma);
//			System.out.println(vt);
//			if (vt != -1) {
//				System.out.print("- Bạn có thực sự muốn xoa tg này ( y / n ): ");
//				while (true) {
//					String regex = "[yY]";
//					String yn = kt.KTYesNo();
//					if (yn.matches(regex)) {
//						// HienPS();
//						// len = SoluongTrongFile();
//						for (int i = 0; i < len; i++) {
//							if (i == vt)
//								continue;
//							else {
//								int j = 0;
//								String data = ArrVT[i].getMaVT() + "-" + ArrVT[i].getMaKhu() + "-"
//										+ ArrVT[i].getMaNgan() + "-" + ArrVT[i].getMaKe();
//								bw.write(data);
//								bw.newLine();
//							}
//						}
//						break;
//					} else {
//						System.out.print("- Bạn đã không xóa tg này.");
//						break;
//					}
//				}
//
//			} else {
//				System.out.println("- Không có mã số trong danh sách. ");
//			}
//			System.out.print("- Bạn có muốn tiếp tục ( y / n ): ");
//
//			String regex = "[yY]";
//			String yn = kt.KTYesNo();
//			if (yn.matches(regex) == false) {
//				break;
//			}
//		}
//		// len--;
//		bw.close();
//		fw.close();
//
//	}
//
//	@Override
//	public void Sua() throws IOException {
//
//		FileWriter fw = new FileWriter(filename);
//		BufferedWriter bw = new BufferedWriter(fw);
//
//		System.out.print("- Nhập mã Tác Giả muốn sửa: ");
//		String ma = kt.KTMaSo();
//		int vt;
//		if ((vt = TimKiem_MaSo(ma)) != -1) {
//			System.out.print("- Bạn có chắc muốn sửa thông tin của tg này ( y / n ): ");
//			while (true) {
//				String regex = "[yY]";
//				String yn = kt.KTYesNo();
//				if (yn.matches(regex)) {
////                                    HienPS();
////                len = SoluongTrongFile();
//					for (int i = 0; i < len; i++) {
//						if (i == vt) {
//							ArrVT[i] = MenuSua(ArrVT[i]);
//						}
//
//						String data = ArrVT[i].getMaVT() + "-" + ArrVT[i].getMaKhu() + "-" + ArrVT[i].getMaNgan() + "-"
//								+ ArrVT[i].getMaKe();
//						bw.write(data);
//						bw.newLine();
//
//					}
//					break;
//				} else {
//					System.out.print("- Bạn đã không xóa tg này.");
//					break;
//				}
//			}
//
//		} else {
//			System.out.println("- Mã tg bạn tìm không có trong danh sách. ");
//		}
//
//		bw.close();
//		fw.close();
//	}
//
//	public Vi_Tri MenuSua(Vi_Tri tg) {
//		int lc;
//		String temp;
//		do {
//			System.out.println("** Bạn muốn sửa thông tin nào: ");
//			System.out.println("1.Mã Khu.");
//			System.out.println("2.Mã Ngăn.");
//			System.out.println("3.Mã Kệ.");
//			System.out.println("4.Thoát");
//			System.out.print("- Nhập lựa chọn của bạn");
//			lc = kt.KTLuaChon(4);
//			switch (lc) {
//			case 1: {
//				System.out.print("- Nhập mã khu mới : ");
//				temp = kt.KTTen();
//				tg.setMaKhu(temp);
//				break;
//			}
//			case 2: {
//				System.out.print("- Nhập mã ngăn mới: ");
//				temp = sc.nextLine();
//				tg.setMaNgan(temp);
//				break;
//			}
//			case 3: {
//				System.out.print("- Nhập mã kệ mới: ");
//				temp = kt.KtDienThoai();
//				tg.setMaKe(temp);
//				break;
//			}
//
//			case 4: {
//				System.out.println("- Bạn đã thoát.");
//				break;
//			}
//			}
//		} while (lc != 4);
//		return tg;
//	}
//
//	public void Xuat() throws IOException {
//		if (SoluongTrongFile() == 0) {
//			String yn;
//			System.out.print("- Danh sach chua co nhan vien : Ban co muon them nhan vien ???.(y / n) : ");
//			while (true) {
//				String regex = "[yY]";
//				yn = kt.KTYesNo();
//				if (yn.matches(regex)) {
//					ThemPS();
//					break;
//				} else
//					break;
//			}
//		}
//		HienPS();
//		for (int j = 0; j < len; j++) {
//			ArrVT[j].Xuat();
//		}
//	}
//}

package OOP_Library;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class ArrVT implements Arr {

	private int lenArr = 0;
	private int lenFile = 0;
	private Vi_Tri[] ArrVT = new Vi_Tri[1];
	private static final String filename = "Vi_Tri.txt";

	Scanner sc = new Scanner(System.in);
	KT_NhapXuat kt = new KT_NhapXuat();

	public int getLenArr() {
		return lenArr;
	}

	public void setLenArr(int lenArr) {
		this.lenArr = lenArr;
	}

	public int getLenFile() {
		return lenFile;
	}

	public void setLenFile(int lenFile) {
		this.lenFile = lenFile;
	}

	public Vi_Tri[] getArrVT() {
		return ArrVT;
	}

	public void setArrVT(Vi_Tri[] arrVT) {
		ArrVT = arrVT;
	}

	public ArrVT() throws IOException {
		HienDS();
	}

	// Thêm danh sách Tác Giả vô File.
	@Override
	public void ThemDT() {
		System.out.print("- Nhập số lượng Vị trí trong danh sách");
		lenArr = kt.KTNhapInt(1);
		ArrVT = Arrays.copyOf(ArrVT, lenArr + lenFile);
		for (int i = lenFile; i < lenArr + lenFile; i++) {
			System.out.printf("\n%60s %d%s\n\n", "------------Vi_Tri", i + 1, "------------");

			ArrVT[i] = new Vi_Tri();
			ArrVT[i].Nhap();
			ArrVT[i].setMaVT("" + (i + 1));

		}
	}

	// Thêm 1 Tác Giả vô file khi nhập trong Class Sách nếu ko có sẽ thêm vô File
	public void Them1(String ma) throws IOException {
		lenArr += 1;
		ArrVT = Arrays.copyOf(ArrVT, lenArr + lenFile);
		ArrVT[lenArr + lenFile - 1] = new Vi_Tri();
		ArrVT[lenArr + lenFile - 1].Nhap();
		ArrVT[lenFile + lenArr - 1].setMaVT("" + (int) (lenArr + lenFile));
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

		ArrVT = Arrays.copyOf(ArrVT, lenArr + lenFile);
		while ((data = br.readLine()) != null) {
			if (data.length() != 1) {
				ArrVT[i] = new Vi_Tri();
				Scanner scan = new Scanner(data).useDelimiter("-");
				ArrVT[i].setMaVT(scan.next());
				ArrVT[i].setMaKhu(scan.next());
				ArrVT[i].setMaNgan(scan.next());
				ArrVT[i++].setMaKe(scan.next());
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
			ArrVT[i].setMaKhu("" + (i + 1));

			String data = ArrVT[i].getMaVT() + "-" + ArrVT[i].getMaKhu() + "-" + ArrVT[i].getMaNgan() + "-"
					+ ArrVT[i].getMaKe();
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

	@Override
	public int TimKiem_MaSo(String ma) {

		for (int i = 0; i < lenArr + lenFile; i++) {
			if (ArrVT[i].getMaKhu().equalsIgnoreCase(ma)) {
				return i;
			}
		}
		return -1;

	}

	public void TimKiem_MS() throws IOException {
		// HienPS();

		System.out.print("-Nhập mã Vị trí bạn muốn tìm :");
		String ma = sc.nextLine();
		int dem = 0;
		for (int i = 0; i < lenArr + lenFile; i++) {
			if (ArrVT[i].getMaKhu().compareTo(ma) == 0) {
				ArrVT[i].Xuat();
				dem++;
			}
		}
		if (dem == 0) {
			System.out.printf("- Không có mã %s .", ma);
		}
	}

	@Override
	public void TimKiem_Ten() {
		// HienPS();
//		System.out.print("- Nhập tên sách bạn muốn tìm: ");
//		String ma = kt.KTTen();
//		int dem = 0;
//		for (int i = 0; i < lenArr + lenFile; i++) {
//			if (ArrVT[i].getTenVi_Tri().compareTo(ma) == 0) {
//				ArrVT[i].Xuat();
//				dem++;
//			}
//		}
//		if (dem == 0) {
//			System.out.printf("- Không có sách nào có ten %s .", ma);
//		}

	}

	@Override
	public void Xoa() {
		while (true) {
			System.out.print("- Nhập mã số Vị trí bạn muốn xóa : ");
			String ma = kt.KTMaSo();
			int vt = TimKiem_MaSo(ma);
			System.out.println(vt);

			if (vt != -1) {
				System.out.print("- Bạn có thực sự muốn xoa Vị trí này ( y / n ): ");
				while (true) {
					String regex = "y|Y";
					String yn = kt.KTYesNo();
					if (yn.matches(regex)) {
						XoaPS(vt);
						System.out.println("- Bạn đã xoá thành công.");
						break;
					} else {
						System.out.print("- Bạn đã không xóa Vị trí này.");

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
			ArrVT[i] = ArrVT[i + 1];
		}
		lenArr--;
	}

	@Override
	public void Sua() {

		System.out.print("- Nhập mã Vị trí muốn sửa: ");
		String ma = kt.KTMaSo();
		int vt;
		if ((vt = TimKiem_MaSo(ma)) != -1) {
			System.out.print("- Bạn có chắc muốn sửa thông tin của Vị trí này ( y / n ): ");
			while (true) {
				String regex = "[yY]";
				String yn = kt.KTYesNo();
				if (yn.matches(regex)) {
					for (int i = 0; i < lenArr + lenFile; i++) {
						if (i == vt) {
							ArrVT[i] = MenuSua(ArrVT[i]);
						}

					}
					System.out.println("- Bạn đã sửa thành công. ");
					break;
				} else {
					System.out.print("- Bạn đã không xóa Vị trí này.");
					break;
				}
			}

		} else {
			System.out.println("- Mã Vị trí bạn tìm không có trong danh sách. ");
		}

	}

	public Vi_Tri MenuSua(Vi_Tri tg) {
		int lc;
		String temp;
		do {
			System.out.println("** Bạn muốn sửa thông tin nào: ");
			System.out.println("1.Mã Khu.");
			System.out.println("2.Mã Ngăn.");
			System.out.println("3.Mã Kệ.");
			System.out.println("4.Thoát");
			System.out.print("- Nhập lựa chọn của bạn");
			lc = kt.KTLuaChon(4);
			switch (lc) {
			case 1: {
				System.out.print("- Nhập mã khu mới : ");
				temp = kt.KTTen();
				tg.setMaKhu(temp);
				break;
			}
			case 2: {
				System.out.print("- Nhập mã ngăn mới: ");
				temp = sc.nextLine();
				tg.setMaNgan(temp);
				break;
			}
			case 3: {
				System.out.print("- Nhập mã kệ mới: ");
				temp = kt.KtDienThoai();
				tg.setMaKe(temp);
				break;
			}

			case 4: {
				System.out.println("- Bạn đã thoát.");
				break;
			}
			}
		} while (lc != 4);
		return tg;
	}

	public void Xuat() throws IOException {
		if (SoluongTrongFile() == 0 && lenArr == 0) {
			String yn;
			System.out.print("- Danh sách chua co Vị trí nào hết : Ban co muon them vao không ???.(y / n) : ");
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

		for (int j = 0; j < lenArr + lenFile; j++) {
			ArrVT[j].Xuat();
		}
	}

	public void MenuChinh() throws IOException {
		int lc = 0, loadnum = 0;
		boolean check = false;
		do {
			System.out.println("\n=============================MENU============================");
			System.out.println("1.Nhập.");
			System.out.println("2.Xuất.");
			System.out.println("3.Xoá.");
			System.out.println("4.Sửa.");
			System.out.println("5.Thoát.");
			System.out.print("- Nhập Lựa Chọn của bạn");
			lc = kt.KTLuaChon(5);
			switch (lc) {
			case 1: {
				ThemDT();
				kt.Phim();
				break;
			}
			case 2: {
				Xuat();
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
				if (s.matches(regex))
					GhiFile();
				System.out.println("- Bạn đã thoát.");
			
				break;
			}

			}

		} while (lc != 5);
	}
}
