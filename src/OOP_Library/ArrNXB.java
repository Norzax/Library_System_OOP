package OOP_Library;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class ArrNXB implements Arr {

	private int lenArr = 0;
	private int lenFile = 0;
	private Nha_Xuat_Ban[] ArrNXB = new Nha_Xuat_Ban[1];
	private static final String filename = "Nha_Xuat_Ban.txt";

	Scanner sc = new Scanner(System.in);
	KT_NhapXuat kt = new KT_NhapXuat();

	public ArrNXB() throws IOException {
		HienDS();
	}

	// Thêm danh sách Tác Giả vô File.
	@Override
	public void ThemDT() {
		System.out.print("- Nhập số lượng NXB trong danh sách");
		lenArr = kt.KTNhapInt(1);
		ArrNXB = Arrays.copyOf(ArrNXB, lenArr + lenFile);
		for (int i = lenFile; i < lenArr + lenFile; i++) {
			System.out.printf("\n%60s %d%s\n\n", "------------Nha_Xuat_Ban", i + 1, "------------");

			ArrNXB[i] = new Nha_Xuat_Ban();
			ArrNXB[i].Nhap();
			ArrNXB[i].setMS("" + (i + 1));

		}
	}

	// Thêm 1 Tác Giả vô file khi nhập trong Class Sách nếu ko có sẽ thêm vô File
	public void Them1(String ma) throws IOException {
		lenArr += 1;
		ArrNXB = Arrays.copyOf(ArrNXB, lenArr + lenFile);
		ArrNXB[lenArr + lenFile - 1] = new Nha_Xuat_Ban();
		ArrNXB[lenArr + lenFile - 1].Nhap();
		ArrNXB[lenFile + lenArr - 1].setMS("" + (int) (lenArr + lenFile));
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

		ArrNXB = Arrays.copyOf(ArrNXB, lenArr + lenFile);
		while ((data = br.readLine()) != null) {
			if (data.length() != 1) {
				ArrNXB[i] = new Nha_Xuat_Ban();
				Scanner scan = new Scanner(data).useDelimiter("-");
				ArrNXB[i].setMS(scan.next());
				ArrNXB[i].setTenTG(scan.next());
				ArrNXB[i].setDiaChi(scan.next());
				ArrNXB[i++].setDienThoai(scan.next());
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
			ArrNXB[i].setMS("" + (i + 1));

			String data = ArrNXB[i].getMS() + "-" + ArrNXB[i].getTenTG() + "-" + ArrNXB[i].getDiaChi() + "-"
					+ ArrNXB[i].getDienThoai();

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
			if (ArrNXB[i].getMS().equalsIgnoreCase(ma)) {
				return i;
			}
		}
		return -1;

	}

	public void TimKiem_MS() throws IOException {
		// HienPS();

		System.out.print("-Nhập mã NXB bạn muốn tìm :");
		String ma = sc.nextLine();
		int dem = 0;
		for (int i = 0; i < lenArr + lenFile; i++) {
			if (ArrNXB[i].getMS().compareTo(ma) == 0) {
				ArrNXB[i].Xuat();
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
//			if (ArrNXB[i].getTenNha_Xuat_Ban().compareTo(ma) == 0) {
//				ArrNXB[i].Xuat();
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
			System.out.print("- Nhập mã số NXB bạn muốn xóa : ");
			String ma = kt.KTMaSo();
			int vt = TimKiem_MaSo(ma);
			System.out.println(vt);

			if (vt != -1) {
				System.out.print("- Bạn có thực sự muốn xoa NXB này ( y / n ): ");
				while (true) {
					String regex = "y|Y";
					String yn = kt.KTYesNo();
					if (yn.matches(regex)) {
						XoaPS(vt);
						System.out.println("- Bạn đã xoá thành công.");
						break;
					} else {
						System.out.print("- Bạn đã không xóa NXB này.");

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
			ArrNXB[i] = ArrNXB[i + 1];
		}
		lenArr--;
	}

	@Override
	public void Sua() {

		System.out.print("- Nhập mã sách muốn sửa: ");
		String ma = kt.KTMaSo();
		int vt;
		if ((vt = TimKiem_MaSo(ma)) != -1) {
			System.out.print("- Bạn có chắc muốn sửa thông tin của NXB này ( y / n ): ");
			while (true) {
				String regex = "[yY]";
				String yn = kt.KTYesNo();
				if (yn.matches(regex)) {
					for (int i = 0; i < lenArr + lenFile; i++) {
						if (i == vt) {
							ArrNXB[i] = MenuSua(ArrNXB[i]);
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
			System.out.println("- Mã NXB bạn tìm không có trong danh sách. ");
		}

	}

	public Nha_Xuat_Ban MenuSua(Nha_Xuat_Ban tg) {
		int lc;
		String temp;
		do {
			System.out.println("** Bạn muốn sửa thông tin nào: ");
			System.out.println("1.Họ Tên.");
			System.out.println("2.Địa Chỉ.");
			System.out.println("3.Điện Thoại.");
			System.out.println("4.Thoát");
			System.out.print("- Nhập lựa chọn của bạn");
			lc = kt.KTLuaChon(4);
			switch (lc) {
			case 1: {
				System.out.print("- Nhập tên mới : ");
				temp = kt.KTTen();
				tg.setTenTG(temp);
				break;
			}
			case 2: {
				System.out.print("- Nhập Địa Chỉ mới: ");
				temp = sc.nextLine();
				tg.setDiaChi(temp);
				break;
			}
			case 3: {
				System.out.print("- Nhập Điện Thoại: ");
				temp = kt.KtDienThoai();
				tg.setDienThoai(temp);
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
			System.out.print("- Danh Sách chưa có NXB nào hết : Bạn có muốn thêm vào không ???.(y / n) : ");
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
			ArrNXB[j].Xuat();
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
