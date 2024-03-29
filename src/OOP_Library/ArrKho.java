package OOP_Library;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class ArrKho implements Arr {

	private int lenArr = 0;
	private int lenFile = 0;

	public Kho[] getArrKho() {
		return ArrKho;
	}

	public void setArrKho(Kho[] arrKho) {
		ArrKho = arrKho;
	}

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

	private Kho[] ArrKho = new Kho[1];
	private static final String filename = "Kho.txt";

	Scanner sc = new Scanner(System.in);
	KT_NhapXuat kt = new KT_NhapXuat();

	public ArrKho() throws IOException {
		HienDS();
	}

	// KHông cần hàm này vì khi nhập 1 cuốn sách trong kho thì ta chỉ cần
	// gọi hàm THEM1 trong vòng for để thêm 1 cuốn sách
	@Override
	public void ThemDT() throws IOException {
//		ArrSach arrS = new ArrSach();
//		System.out.print("-Bạn muôn nhập thêm bao nhiêu sách vào trong kho");
//		lenArr = kt.KTNhapInt(1);
//		int vt ;
//		ArrKho = Arrays.copyOf(ArrKho, lenArr + lenFile);
//		for (int i = lenFile; i < lenArr + lenFile; i++) {
//			System.out.printf("\n%60s %d%s\n\n", "------------SACH ", i + 1, "_TRONG-KHO------------");
//
//			ArrKho[i] = new Kho();
//                        String	ma;
//			System.out.print("- Nhập Mã Sách: ");
//			while (true) {
//			ma = kt.KTMaSo();
//				if((vt = TimKiem_MaSo(ma,i)) != -1)
//					System.out.print("- Mã bạn nhập da có trong danh sách Kho . Nhập lại: ");
//				else
//					break;
//			}
//                        ArrKho[i].setMaSach(ma);
//			ArrKho[i].Nhap();
//			arrS.Them1(ArrKho[i].getMaSach(), ArrKho[i].getSLuong());
//		}
//		
	}

	// Thêm 1 Tác Giả vô file khi nhập trong Class Sách nếu ko có sẽ thêm vô File
	public void Them1(String mas, String maNXB, int gia) throws IOException {
		ArrSach arrS = new ArrSach();
		lenArr += 1;
		ArrKho = Arrays.copyOf(ArrKho, lenArr + lenFile);
		int vt = lenArr + lenFile - 1;
		ArrKho[vt] = new Kho();
		ArrKho[vt].Nhap(gia);
		ArrKho[vt].setMaSach(mas);
		System.out.println("- Bạn cũng phải thêm thông tin của cuốn sách này nữa .");
		arrS.Them1(ArrKho[vt].getMaSach(), maNXB);
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
		
		lenFile = SoluongTrongFile();

		ArrKho = Arrays.copyOf(ArrKho, lenArr + lenFile);
		while ((data = br.readLine()) != null) {
			if (data.length() != 1) {
				ArrKho[i] = new Kho();
				Scanner scan = new Scanner(data).useDelimiter("-");
				ArrKho[i].setMaKhu(scan.next());
				ArrKho[i].setMaSach(scan.next());
				ArrKho[i].setSLuong(scan.nextInt());
				ArrKho[i++].setGIA(scan.nextInt());
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

			String data = ArrKho[i].getMaKhu() + "-" + ArrKho[i].getMaSach() + "-" + ArrKho[i].getSLuong() + "-"
					+ ArrKho[i].getGIA();
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
	public int TimKiem_MaSo(String makhu) {

		for (int i = 0; i < lenArr + lenFile; i++) {
			if (ArrKho[i].getMaKhu().equalsIgnoreCase(makhu)) {
				return i;
			}
		}
		return -1;

	}

	public int TimKiem_MaSach(String masach) {

		for (int i = 0; i < lenArr + lenFile; i++) {
			if (ArrKho[i].getMaSach().compareTo(masach) == 0) {
				return i;
			}
		}
		return -1;

	}

	public int TimKiem_MaSo(String makhu, String mas) {

		for (int i = 0; i < lenArr + lenFile; i++) {
			if (ArrKho[i].getMaKhu().equalsIgnoreCase(makhu)) {
				return i;
			}
		}
		return -1;

	}

	public void TimKiem_MS() throws IOException {
		// HienPS();

		System.out.print("-Nhập mã Kho bạn muốn tìm :");
		String ma = sc.nextLine();
		int dem = 0;
		ArrSach as = new ArrSach();
		for (int i = 0; i < lenArr + lenFile; i++) {
			String mas = ArrKho[i].getMaSach();
			int vts = as.TimKiem_MaSo(mas);
			String tens = as.getArrSach()[vts].getTenSach();
			if (ArrKho[i].getMaKhu().compareTo(ma) == 0) {
				ArrKho[i].Xuat(tens);
				dem++;
			}
		}
		if (dem == 0) {
			System.out.printf("- Không có mã %s .", ma);
		}
	}

	@Override
	public void TimKiem_Ten() {
//		for (int i = 0; i < lenArr + lenFile; i++) {
//			if (ArrKho[i].getMaKhu().equalsIgnoreCase(makhu )) {
//				return i;
//			}
//		}
//		return -1;

	}

	public int TimKiem_MaSo(String ma, int j) {
		if (j == 0)
			return -1;
		for (int i = 0; i < j; i++) {
			if (ArrKho[i].getMaKhu().equalsIgnoreCase(ma)) {
				return i;
			}
		}
		return -1;
	}

	// Dùng để thay đổi số lượng sách trong Kho khi nhập hay xuất hàng
	public void ThayDoiSLuongSach(int tt, int vt, int sl) throws IOException {
		
		// Lấy số lượng của sản phẩm trong kho theo đối số vt
		int slcu = TruyXuatPT(vt).getSLuong();
		
		// Xét theo trạng thái của Hoá đơn .
		// Trạng thái nhập hàng
		if (tt == 1) {
			
			// Số lượng trong Kho sẽ tăng lên theo số lượng trong chi tiết hoa đơn
			ArrKho[vt].setSLuong(slcu + sl);
		} 
		
		// Trạng thái xuất hàng
		else
			
			// Số lượng trong Kho sẽ giảm theo số lượng trong chi tiết hoa đơn
			ArrKho[vt].setSLuong(slcu - sl);
		GhiFile();
	}

	//Dùng để lấy đối tượng trong mảng Kho
	public Kho TruyXuatPT(int vt) {
		if (vt != -1)
			return ArrKho[vt];
		return null;
	}

	@Override
	public void Xoa() {
		while (true) {
			System.out.print("- Nhập mã số Kho bạn muốn xóa : ");
			String ma = kt.KTMaSo();
			int vt = TimKiem_MaSo(ma);
			System.out.println(vt);

			if (vt != -1) {
				System.out.print("- Bạn có thực sự muốn xoa Kho này ( y / n ): ");
				while (true) {
					String regex = "y|Y";
					String yn = kt.KTYesNo();
					if (yn.matches(regex)) {
						XoaPS(vt);
						System.out.println("- Bạn đã xoá thành công.");
						break;
					} else {
						System.out.print("- Bạn đã không xóa Kho này.");

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
			ArrKho[i] = ArrKho[i + 1];
		}
		lenArr--;
	}

	@Override
	public void Sua() {

		System.out.print("- Nhập mã Kho muốn sửa: ");
		String ma = kt.KTMaSo();
		int vt;
		if ((vt = TimKiem_MaSo(ma)) != -1) {
			System.out.print("- Bạn có chắc muốn sửa thông tin của Kho này ( y / n ): ");
			while (true) {
				String regex = "[yY]";
				String yn = kt.KTYesNo();
				if (yn.matches(regex)) {
					for (int i = 0; i < lenArr + lenFile; i++) {
						if (i == vt) {
							ArrKho[i] = MenuSua(ArrKho[i]);
						}

					}
					System.out.println("- Bạn đã sửa thành công. ");
					break;
				} else {
					System.out.print("- Bạn đã không xóa Kho này.");
					break;
				}
			}

		} else {
			System.out.println("- Mã Kho bạn tìm không có trong danh sách. ");
		}

	}

	public Kho MenuSua(Kho tg) {
		int lc;
		String temp;
		do {
			System.out.println("** Bạn muốn sửa thông tin nào: ");
			System.out.println("1.Mã Khu.");
			System.out.println("2.Mã Sách.");
			System.out.println("3.Số Lượng.");
			System.out.println("4.Thoát");
			System.out.print("- Nhập lựa chọn của bạn");
			lc = kt.KTLuaChon(4);
			switch (lc) {
			case 1: {
				System.out.print("- Mã Khu : ");
				temp = sc.nextLine();
				tg.setMaKhu(temp);
				break;
			}
			case 2: {
				System.out.print("- Mã Sách: ");
				temp = sc.nextLine();
				tg.setMaKhu(temp);
				break;
			}
			case 3: {
				System.out.print("- Số Lượng: ");
				int sl = kt.KTNhapInt(0);
				tg.setSLuong(sl);
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
			System.out.print("- Danh sách chua co kho nào hết : Ban co muon them vao không ???.(y / n) : ");
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

		ArrSach as = new ArrSach();
		for (int j = 0; j < lenArr + lenFile; j++) {
			String mas = ArrKho[j].getMaSach();
			int vts = as.TimKiem_MaSo(mas);
			String tens = as.getArrSach()[vts].getTenSach();
			ArrKho[j].Xuat(tens);
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
