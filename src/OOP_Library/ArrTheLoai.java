package OOP_Library;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class ArrTheLoai implements Arr {

	private int lenArr = 0;
	private int lenFile = 0;
	private TheLoai[] ArrTL = new TheLoai[1];
	private static final String filename = "Category.txt";

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

	public TheLoai[] getArrTL() {
		return ArrTL;
	}

	public void setArrTL(TheLoai[] arrTL) {
		ArrTL = arrTL;
	}

	public ArrTheLoai() throws IOException {
		HienDS();
	}

	// Thêm danh sách Tác Giả vô File.
	@Override
	public void ThemDT() {
		System.out.print("- Nhập số lượng Thể loại trong danh sách");
		lenArr = kt.KTNhapInt(1);
		ArrTL = Arrays.copyOf(ArrTL, lenArr + lenFile);
		for (int i = lenFile; i < lenArr + lenFile; i++) {
			System.out.printf("\n%60s %d%s\n\n", "------------TheLoai", i + 1, "------------");

			ArrTL[i] = new TheLoai();
			// ArrTL[i].setMaTheLoai("" + (i + 1));
			ArrTL[i].Nhap();
			while (true) {
				if (TimKiem_MaSoPT(ArrTL[i].getMaTheLoai(), i) != -1) {
					System.out.print("** Mã Thể loại bạn nhập đã tồn tại **");
					System.out.print(". Xin hãy nhập lại");
					ArrTL[i].setMaTheLoai(sc.nextLine());
				} else
					break;
			}

		}
	}

	// Thêm 1 Thể loại vô file khi nhập trong Class Sách nếu ko có sẽ thêm vô File
	public void Them1(String ma) throws IOException {
		lenArr += 1;
		ArrTL = Arrays.copyOf(ArrTL, lenArr + lenFile);

		ArrTL[lenArr + lenFile - 1] = new TheLoai();
		System.out.print("- Nhập tên Thể loại: ");
		String ten = kt.KTTen();
		ArrTL[lenArr + lenFile - 1].setTenTheLoai(ten);
		ArrTL[lenArr + lenFile - 1].setMaTheLoai(ma);
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

		ArrTL = Arrays.copyOf(ArrTL, lenArr + lenFile);
		while ((data = br.readLine()) != null) {
			if (data.length() != 1) {
				ArrTL[i] = new TheLoai();
				Scanner scan = new Scanner(data).useDelimiter("-");
				ArrTL[i].setMaTheLoai(scan.next());
				ArrTL[i++].setTenTheLoai(scan.next());
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
			// ArrTL[i].setMaTheLoai("" + (i + 1));

			String data = ArrTL[i].getMaTheLoai() + "-" + ArrTL[i].getTenTheLoai();
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
			if (ArrTL[i].getMaTheLoai().equalsIgnoreCase(ma)) {
				return i;
			}
		}
		return -1;

	}

	public int TimKiem_MaSoPT(String ma, int j) {

		for (int i = 0; i < j; i++) {
			if (ArrTL[i].getMaTheLoai().equalsIgnoreCase(ma)) {
				return i;
			}
		}
		return -1;

	}

	public void TimKiem_MS() throws IOException {
		// HienPS();

		System.out.print("-Nhập mã Thể loại bạn muốn tìm :");
		String ma = sc.nextLine();
		int dem = 0;
		for (int i = 0; i < lenArr + lenFile; i++) {
			if (ArrTL[i].getMaTheLoai().compareTo(ma) == 0) {
				ArrTL[i].Xuat();
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
		System.out.print("- Nhập tên Thể loại bạn muốn tìm: ");
		String ma = kt.KTTen();
		int dem = 0;
		for (int i = 0; i < lenArr + lenFile; i++) {
			if (ArrTL[i].getTenTheLoai().compareTo(ma) == 0) {
				ArrTL[i].Xuat();
				dem++;
			}
		}
		if (dem == 0) {
			System.out.printf("- Không có Thể loại nào có ten %s .", ma);
		}

	}

	@Override
	public void Xoa() {
		while (true) {
			System.out.print("- Nhập mã số Thể loại bạn muốn xóa : ");
			String ma = sc.nextLine();
			int vt = TimKiem_MaSo(ma);
			System.out.println(vt);

			if (vt != -1) {
				System.out.print("- Bạn có thực sự muốn xoa Thể loại này ( y / n ): ");
				while (true) {
					String regex = "y|Y";
					String yn = kt.KTYesNo();
					if (yn.matches(regex)) {
						XoaPS(vt);
						System.out.println("- Bạn đã xoá thành công.");
						break;
					} else {
						System.out.print("- Bạn đã không xóa Thể loại này.");

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
			ArrTL[i] = ArrTL[i + 1];
		}
		lenArr--;
	}

	@Override
	public void Sua() {

		System.out.print("- Nhập mã Thể loại muốn sửa: ");
		String ma = sc.nextLine();
		int vt;
		if ((vt = TimKiem_MaSo(ma)) != -1) {
			System.out.print("- Bạn có chắc muốn sửa thông tin của Thể loại này ( y / n ): ");
			while (true) {
				String regex = "[yY]";
				String yn = kt.KTYesNo();
				if (yn.matches(regex)) {
					for (int i = 0; i < lenArr + lenFile; i++) {
						if (i == vt) {
							System.out.print("- Nhập tên thể loại mới: ");
							ArrTL[i].setTenTheLoai(kt.KTTen());
						}

					}
					System.out.println("- Bạn đã sửa thành công. ");
					break;
				} else {
					System.out.print("- Bạn đã không xóa Thể loại này.");
					break;
				}
			}

		} else {
			System.out.println("- Mã Thể loại bạn tìm không có trong danh sách. ");
		}

	}

	public void Xuat() throws IOException {
		if (SoluongTrongFile() == 0 && lenArr == 0) {
			String yn;
			System.out.print("- Danh sách chua co Thể loại nào hết : Ban co muon them vao không ???.(y / n) : ");
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
			ArrTL[j].Xuat();
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
