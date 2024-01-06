package OOP_Library;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class ArrEmployee implements Arr {
	private int lenArr = 0;
	private int lenFile = 0;
	private Employee[] Arremp = new Employee[0];
	private final String filename = "Employee.txt";

	Scanner sc = new Scanner(System.in);
	KT_NhapXuat kt = new KT_NhapXuat();

	public ArrEmployee(int len, Employee[] Arremp) {
		this.lenArr = len;
		Arremp = Arremp;
	}

	public int getLen() {
		return lenArr;
	}

	public void setLen(int len) {
		this.lenArr = len;
	}

	public Employee[] getArremp() {
		return Arremp;
	}

	public void setArremp(Employee[] Arremp) {
		Arremp = Arremp;
	}

	public ArrEmployee() throws IOException {
		HienDS();
	}

	public void ThemDT() {
		System.out.print("- Nhap so luong Nhân viên trong danh sách");
		lenArr = kt.KTNhapInt(1);
		Arremp = Arrays.copyOf(Arremp, lenArr + lenFile);
		for (int i = lenFile; i < lenArr + lenFile; i++) {
			System.out.printf("\n%60s %d%s\n\n", "------------NV", i + 1, "------------");

			Arremp[i] = new Employee();
			Arremp[i].Nhap();
			Arremp[i].setMS("" + (i + 1));

		}
	}

	// Thêm 1 Tác Giả vô file khi nhập trong Class Sách nếu ko có sẽ thêm vô File
	public void Them1(String ma) throws IOException {
		lenArr += 1;
		Arremp = Arrays.copyOf(Arremp, lenArr + lenFile);

		Arremp[lenArr + lenFile - 1] = new Employee();
		Arremp[lenArr + lenFile - 1].Nhap();
		Arremp[lenArr + lenFile - 1].setMS("" + (lenArr + lenFile));
		GhiFile();
	}

	@Override
	public void HienDS() throws IOException {
		FileReader fr = new FileReader(filename);
		BufferedReader br = new BufferedReader(fr);
		String data;
		int i = 0;
		// boolean check = false;
		lenFile = SoluongTrongFile();

		Arremp = Arrays.copyOf(Arremp, lenArr + lenFile);
		while ((data = br.readLine()) != null) {
			if (data.length() != 1) {
				Arremp[i] = new Employee();
				Scanner scan = new Scanner(data).useDelimiter("-");
				Arremp[i].setMS(scan.next());
				Arremp[i].setTen(scan.next());
				Arremp[i].setGioiTinh(scan.nextInt());
				Arremp[i].setNgaySinh(scan.nextInt(), scan.nextInt(), scan.nextInt());
				Arremp[i].setDiaChi(scan.next());
				Arremp[i].setDienThoai(scan.next());
				Arremp[i++].setEmail(scan.next());
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
			String ns = Arremp[i].getNgaySinh().getNgay() + "-" + Arremp[i].getNgaySinh().getThang() + "-"
					+ Arremp[i].getNgaySinh().getNam();
			String data = (int) (i + 1) + "-" + Arremp[i].getTen() + "-" + Arremp[i].getGioiTinh() + "-" + ns + "-"
					+ Arremp[i].getDiaChi() + "-" + Arremp[i].getDienThoai() + "-" + Arremp[i].getEmail();
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
			if (Arremp[i].getMS().compareTo(ma) == 0) {
				return i;
			}
		}
		return -1;

	}

	public int TimKiem_Ten(String ten) {
		for (int i = 0; i < lenArr + lenFile; i++) {
			if (Arremp[i].getTen().compareTo(ten) == 0) {
				return i;
			}
		}
		return -1;
	}

	public void TimKiem_MS() throws IOException {
		// HienPS();

		System.out.print("-Nhập mã NV bạn muốn tìm :");
		String ma = sc.nextLine();
		int dem = 0;
		for (int i = 0; i < lenArr + lenFile; i++) {
			if (Arremp[i].getMS().compareTo(ma) == 0) {
				Arremp[i].Xuat();
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
		System.out.print("- Nhập tên Nhân viên bạn muốn tìm: ");
		String ma = kt.KTTen();
		int dem = 0;
		for (int i = 0; i < lenArr + lenFile; i++) {
			if (Arremp[i].getTen().compareTo(ma) == 0) {
				Arremp[i].Xuat();
				dem++;
			}
		}
		if (dem == 0) {
			System.out.printf("- Không có Nhân viên nào có ten %s .", ma);
		}

	}

	@Override
	public void Xoa() {
		while (true) {
			System.out.print("- Nhập mã số Nhân viên bạn muốn xóa : ");
			String ma = kt.KTMaSo();
			int vt = TimKiem_MaSo(ma);
			System.out.println(vt);

			if (vt != -1) {
				System.out.print("- Bạn có thực sự muốn xoa Nhân viên này ( y / n ): ");
				while (true) {
					String regex = "y|Y";
					String yn = kt.KTYesNo();
					if (yn.matches(regex)) {
						XoaPS(vt);
						System.out.println("- Bạn đã xoá thành công.");
						break;
					} else {
						System.out.print("- Bạn đã không xóa Nhân viên này.");

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
			Arremp[i] = Arremp[i + 1];
		}
		lenArr--;
	}

	@Override
	public void Sua() {

		System.out.print("- Nhập mã Nhân viên muốn sửa: ");
		String ma = kt.KTMaSo();
		int vt;
		if ((vt = TimKiem_MaSo(ma)) != -1) {
			System.out.print("- Bạn có chắc muốn sửa thông tin của Nhân viên này ( y / n ): ");
			while (true) {
				String regex = "[yY]";
				String yn = kt.KTYesNo();
				if (yn.matches(regex)) {
					for (int i = 0; i < lenArr + lenFile; i++) {
						if (i == vt) {
							Arremp[i] = MenuSua(Arremp[i]);
						}

					}
					System.out.println("- Bạn đã sửa thành công. ");
					break;
				} else {
					System.out.print("- Bạn đã không xóa Nhân viên này.");
					break;
				}
			}

		} else {
			System.out.println("- Mã tg bạn tìm không có trong danh sách. ");
		}

	}

	public Employee MenuSua(Employee tg) {
		int lc;
		String temp;
		do {
			System.out.println("** Bạn muốn sửa thông tin của: ");
			System.out.println("1.Họ Tên.");
			System.out.println("2.Giới Tính.");
			System.out.println("3.Ngày Sinh.");
			System.out.println("4.Địa Chỉ.");
			System.out.println("5.Số Điện Thoại.");
			System.out.println("6.Email.");
			System.out.println("7.Toàn Bộ");
			System.out.println("8.Thoát");
			System.out.print("- Nhập lựa chọn của bạn");
			lc = kt.KTLuaChon(8);
			switch (lc) {
			case 1: {
				System.out.print("- Nhập Tên mới: ");
				temp = kt.KTTen();
				tg.setTen(temp);
				break;
			}
			case 2: {
				System.out.print("- Nhập (1. Nam 2. Nữ ): ");
				int t = kt.KTLuaChon(2);
				tg.setGioiTinh(t);
				break;
			}
			case 3: {
				System.out.print("- Nhập Ngày Sinh: ");
				NAM_SINH ns = new NAM_SINH();
				ns.Nhap();
				tg.setNgaySinh(ns.getNgay(), ns.getThang(), ns.getNam());
				break;
			}
			case 4: {
				System.out.print("- Nhập Địa Chỉ: ");
				temp = sc.nextLine();
				tg.setDiaChi(temp);
				break;
			}
			case 5: {
				System.out.print("- Nhập Sdt: ");
				temp = kt.KtDienThoai();
				tg.setDienThoai(temp);
				break;
			}
			case 6: {
				System.out.print("- Nhập Email: ");
				temp = kt.KtEmail();
				tg.setEmail(temp);
				break;
			}
			case 7: {
				System.out.print("- Nhập All: ");
				Employee n = new Employee();
				n.Nhap();
				tg = n;

				break;
			}

			case 8: {
				System.out.println("- Bạn đã thoát.");
				break;
			}
			}
		} while (lc != 8);
		return tg;
	}

	public void Xuat() throws IOException {
		if (SoluongTrongFile() == 0 && lenArr == 0) {
			String yn;
			System.out.print("- Danh sách chua co nhan vien : Ban co muon them nhan vien ???.(y / n) : ");
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
			Arremp[j].Xuat();
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
