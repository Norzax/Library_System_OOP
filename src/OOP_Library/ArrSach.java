package OOP_Library;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class ArrSach implements Arr {

	private int lenArr = 0;
	private int lenFile = 0;
	private Sach[] ArrSach = new Sach[1];

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

	public Sach[] getArrSach() {
		return ArrSach;
	}

	public void setArrSach(Sach[] arrSach) {
		ArrSach = arrSach;
	}

	private static final String filename = "Sach.txt";

	Scanner sc = new Scanner(System.in);
	KT_NhapXuat kt = new KT_NhapXuat();

	public ArrSach() throws IOException {
		HienDS();
	}

	// KHông cần hàm này vì khi nhập 1 cuốn sách trong kho thì ta phải
	// nhập luôn thông tin của cuốn sách này vào trong danh sách sách
	@Override
	public void ThemDT() throws IOException {
//		ArrKho arrK = new ArrKho();
//		//System.out.print("- Nhập số lượng Sách trong danh sách");
//		lenArr = arrK.getLenArr() + arrK.getLenFile();
//		String ma;
//		int vt ;
//		ArrSach = Arrays.copyOf(ArrSach, lenArr + lenFile);
//		for (int i = lenFile; i < lenArr + lenFile; i++) {
//			System.out.printf("\n%60s %d%s\n\n", "------------S", i + 1, "------------");
//			System.out.print("- Nhập Mã Sách: ");
//			while (true) {
//				ma = kt.KTMaSo();
//				if((vt = arrK.TimKiem_MaSo(ma)) == -1)
//					System.out.print("- Mã bạn nhập ko có trong danh sách Kho . Nhập lại: ");
//				else
//					break;
//			}
//			ArrSach[i] = new Sach();
//			ArrSach[i].Nhap();
//			ArrSach[i].setSl(arrK.TruyXuatPT(vt).getSLuong());
//	}
//		for(int i = 0 ; i< arrK.getLenArr() + arrK.getLenFile() ; i++)
//		{
//			if(arrK.TruyXuatPT(i).getSLuong() != 0 )
//				
//		}
	}

	// Thêm 1 Sách vô file khi nhập trong Class Sách nếu ko có sẽ thêm vô File
	public void Them1(String mas, String maNXB) throws IOException {
		lenArr += 1;
		ArrSach = Arrays.copyOf(ArrSach, lenArr + lenFile);
		ArrSach[lenArr + lenFile - 1] = new Sach();
		ArrSach[lenArr + lenFile - 1].Nhap(maNXB);
		ArrSach[lenFile + lenArr - 1].setMaSach(mas);
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

		ArrSach = Arrays.copyOf(ArrSach, lenArr + lenFile);
		while ((data = br.readLine()) != null) {
			if (data.length() != 1) {
				ArrSach[i] = new Sach();
				Scanner scan = new Scanner(data).useDelimiter("-");
				ArrSach[i].setMaSach(scan.next());
				ArrSach[i].setTenSach(scan.next());
				ArrSach[i].setMaTl(scan.next());
				ArrSach[i].setMaTG(scan.next());
				ArrSach[i].setMaNXB(scan.next());
				ArrSach[i++].setMaVT(scan.next());
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
			String data = ArrSach[i].getMaSach() + "-" + ArrSach[i].getTenSach() + "-" + ArrSach[i].getMaTl() + "-"
					+ ArrSach[i].getMaTG() + "-" + ArrSach[i].getMaNXB() + "-" + ArrSach[i].getMaVT();
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
			if (ArrSach[i].getMaSach().equalsIgnoreCase(ma)) {
				return i;
			}
		}
		return -1;

	}

	public void TimKiem_MS() throws IOException {
		// HienPS();

		System.out.print("-Nhập mã Sách bạn muốn tìm :");
		String ma = sc.nextLine();
		int dem = 0;
		for (int i = 0; i < lenArr + lenFile; i++) {
			if (ArrSach[i].getMaSach().compareTo(ma) == 0) {
				ArrSach[i].Xuat();
				dem++;
			}
		}
		if (dem == 0) {
			System.out.printf("- Không có mã %s .", ma);
		}
	}

	public int TimKiem_Ten(String ten) {

		for (int i = 0; i < lenArr + lenFile; i++) {
			if (ArrSach[i].getTenSach().contains(ten)) {
				return i;
			}
		}
		return -1;

	}

	@Override
	public void TimKiem_Ten() throws IOException {

		System.out.print("- Nhập tên sách bạn muốn tìm: ");
		String ten = kt.KT_TenSach();

		// biến dem là dùng để đếm xem trong danh sách có bao nhiêu sách có tên cần tìm
		// biến demsl là dùng để đếm xem cuốn sách đó trong kho có còn hay là hết
		int dem = 0, demsl = 0;
		ArrKho ak = new ArrKho();
		ArrVT av = new ArrVT();
		ArrTG at = new ArrTG();
		ArrTheLoai atl = new ArrTheLoai();

		for (int j = 0; j < lenArr + lenFile; j++) {
			if (ArrSach[j].getTenSach().contains(ten)) {

				// Lấy mã Sách
				String mas = ArrSach[j].getMaSach();

				// Lấy vị trí của mã Tác Giả để lấy dc Tên tác giả
				int vttg = at.TimKiem_MaSo(ArrSach[j].getMaTG());
				String tenTg = at.getArrTG()[vttg].getTenTG();

				// Lấy vị trí của Vị trí của cuốn sách đó nằm ở đâu
				int vtViTri = av.TimKiem_MaSo(ArrSach[j].getMaVT());

				// Lấy vị trí của mã TL để lấy tên Thể loại
				int vtTl = atl.TimKiem_MaSo(ArrSach[j].getMaTl());
				String tenTl = atl.getArrTL()[vtTl].getTenTheLoai();

				// Lấy vị trí cuốn sách đó nằm trong Kho
				int vtk = ak.TimKiem_MaSach(mas);

				// Lấy số lượng của cuốn sách đó
				int sl = ak.TruyXuatPT(vtk).getSLuong();
				if (sl != 0) {
					System.out.printf("%5s%-5s%-30s%-30s%-30s%-5s%-5s%-5s\n", "", ArrSach[j].getMaSach(),
							ArrSach[j].getTenSach(), tenTg, tenTl, av.getArrVT()[vtViTri].getMaKe(),
							av.getArrVT()[vtViTri].getMaKhu(), av.getArrVT()[vtViTri].getMaNgan());
					demsl++;
				}

				dem++;
			}
		}
		if (dem == 0) {
			System.out.printf("- Không có sách nào có ten %s .", ten);
		}

		else if (demsl == 0)
			System.out.printf(" -Hiện tại sách này trong kho đã hết .");
	}

	public void TimKiem_TenTG() throws IOException {
		System.out.print("- Nhập tên Tác giả bạn muốn tìm: ");
		String tenTG = kt.KTTenTG();

		// biến dem là dùng để đếm xem trong danh sách có bao nhiêu sách có tên cần tìm
		// biến demsl là dùng để đếm xem cuốn sách đó trong kho có còn hay là hết
		int dem = 0, demsl = 0;
		ArrKho ak = new ArrKho();
		ArrVT av = new ArrVT();
		ArrTG at = new ArrTG();
		ArrTheLoai atl = new ArrTheLoai();

		for (int j = 0; j < lenArr + lenFile; j++) {

			// Lấy vị trí của mã Tác Giả để lấy dc Tên tác giả
			int vttg = at.TimKiem_MaSo(ArrSach[j].getMaTG());
			String tenTg = at.getArrTG()[vttg].getTenTG();
			if (tenTg.contains(tenTG)) {

				// Lấy mã Sách
				String mas = ArrSach[j].getMaSach();

				// Lấy vị trí của Vị trí của cuốn sách đó nằm ở đâu
				int vtViTri = av.TimKiem_MaSo(ArrSach[j].getMaVT());

				// Lấy vị trí của mã TL để lấy tên Thể loại
				int vtTl = atl.TimKiem_MaSo(ArrSach[j].getMaTl());
				String tenTl = atl.getArrTL()[vtTl].getTenTheLoai();

				// Lấy vị trí cuốn sách đó nằm trong Kho
				int vtk = ak.TimKiem_MaSach(mas);

				// Lấy số lượng của cuốn sách đó
				int sl = ak.TruyXuatPT(vtk).getSLuong();
				if (sl != 0) {
					System.out.printf("%5s%-5s%-30s%-30s%-30s%-5s%-5s%-5s\n", "", ArrSach[j].getMaSach(),
							ArrSach[j].getTenSach(), tenTg, tenTl, av.getArrVT()[vtViTri].getMaKe(),
							av.getArrVT()[vtViTri].getMaKhu(), av.getArrVT()[vtViTri].getMaNgan());
					demsl++;
				}

				dem++;
			}
		}
		if (dem == 0) {
			System.out.printf("- Không có sách nào có ten %s .", tenTG);
		}

		else if (demsl == 0)
			System.out.printf(" -Hiện tại sách này trong kho đã hết .");

	}

	@Override
	public void Xoa() {
		while (true) {
			System.out.print("- Nhập mã số sách bạn muốn xóa : ");
			String ma = kt.KTMaSo();
			int vt = TimKiem_MaSo(ma);
			System.out.println(vt);

			if (vt != -1) {
				System.out.print("- Bạn có thực sự muốn xoa sách này ( y / n ): ");
				while (true) {
					String regex = "y|Y";
					String yn = kt.KTYesNo();
					if (yn.matches(regex)) {
						XoaPS(vt);
						System.out.println("- Bạn đã xoá thành công.");
						break;
					} else {
						System.out.print("- Bạn đã không xóa sách này.");

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
			ArrSach[i] = ArrSach[i + 1];
		}
		lenArr--;
	}

	@Override
	public void Sua() {

		System.out.print("- Nhập mã sách muốn sửa: ");
		String ma = kt.KTMaSo();
		int vt;
		if ((vt = TimKiem_MaSo(ma)) != -1) {
			System.out.print("- Bạn có chắc muốn sửa thông tin của sách này ( y / n ): ");
			while (true) {
				String regex = "[yY]";
				String yn = kt.KTYesNo();
				if (yn.matches(regex)) {
					for (int i = 0; i < lenArr + lenFile; i++) {
						if (i == vt) {
							ArrSach[i] = MenuSua(ArrSach[i]);
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
			System.out.println("- Mã sách bạn tìm không có trong danh sách. ");
		}

	}

	public Sach MenuSua(Sach tg) {
		int lc;
		String temp;
		do {
			System.out.println("** Bạn muốn sửa thông tin nào: ");
			System.out.println("0.Mã Sách.");
			System.out.println("1.Tên Sách.");
			System.out.println("2.Mã TG.");
			System.out.println("3.Mã TL");
			System.out.println("4.Mã NXB");
			System.out.println("5.Mã VT");
			System.out.println("6.Thoát");

			System.out.print("- Nhập lựa chọn của bạn");
			lc = kt.KTLuaChon(6);
			switch (lc) {
			case 1: {
				System.out.print("- Nhập Tên Sách : ");
				temp = kt.KTTen();
				tg.setTenSach(temp);
				break;
			}
			case 2: {
				System.out.print("- Nhập Mã TG: ");
				temp = kt.KTMaSo();
				tg.setMaTG(temp);
				break;
			}
			case 3: {
				System.out.print("- Nhập Mã TL: ");
				temp = sc.nextLine();
				tg.setMaTl(temp);
				break;
			}

			case 4: {
				System.out.print("- Nhập Mã NXB: ");
				temp = kt.KTMaSo();
				tg.setMaNXB(temp);
				break;
			}
			case 5: {
				System.out.print("- Nhập Mã VT: ");
				temp = sc.nextLine();
				tg.setMaVT(temp);
				break;
			}
			case 6: {
				System.out.println("- Bạn đã thoát.");
				break;
			}
			}
		} while (lc != 6);
		return tg;
	}

	public void Xuat() throws IOException {
		if (SoluongTrongFile() == 0 && lenArr == 0) {
			String yn;
			System.out.print("- Danh sach chua co Sách : Ban co muon them nhan vien ???.(y / n) : ");
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
		ArrKho ak = new ArrKho();
		for (int j = 0; j < lenArr + lenFile; j++) {
			int vt = ak.TimKiem_MaSach(ArrSach[j].getMaSach());
			int sl = ak.TruyXuatPT(vt).getSLuong();
			if (sl != 0)
				ArrSach[j].Xuat();
		}
	}
        public void menuphu()throws IOException{
            int lcp = 0;
		boolean check = false;
		do {
			System.out.println("\n=============================MENU============================");
			System.out.println("1.Xuất.");
			System.out.println("2.Thoát.");
			System.out.print("- Nhập Lựa Chọn của bạn");
			lcp = kt.KTLuaChon(5);
			switch (lcp) {
			
			case 1: {
				Xuat();
				kt.Phim();
				break;
			}
			case 2: {
				System.out.print("- Bạn có muốn ghi vào cơ sở dữ liệu ko( y / n ) ??? : ");
				String s = kt.KTYesNo();
				String regex = "y|Y";
				if (s.matches(regex))
					GhiFile();
				System.out.println("- Bạn đã thoát.");
				
				break;
			}

			}

		} while (lcp != 5);
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
