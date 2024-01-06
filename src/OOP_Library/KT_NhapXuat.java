package OOP_Library;

import java.io.IOException;
import java.util.Scanner;

public class KT_NhapXuat {
	Scanner sc = new Scanner(System.in);

	public float KTNhap(String s) {
		float so = 0;
		while (true) {
			System.out.print(" : ");
			try {
				so = Float.parseFloat(s);
				if (so > 0)
					break;
				else {
					System.out.print("* So nhap vao phai lon hon 0 * . Nhap lai");
					so = Float.parseFloat(sc.nextLine());
				}
			} catch (Exception e) {
				System.out.print("* Loi du lieu!!! * . Nhap lai");
			}

		}
		return so;
	}

	public String KTYesNo() {

		String s;
		while (true) {
			String regex = "[yn]";
			s = sc.nextLine();
			boolean check;
			if ((check = s.matches(regex)) == true)
				break;
			else
				System.out.print("** Kí tự nhập không hợp lệ. ** Nhap lai: ");
		}
		return s;
	}

	public int KTNhapInt(int x) {
		int so = 0;
		while (true) {
			System.out.print(" : ");
			try {
				so = Integer.parseInt(sc.nextLine());
				if (so >= x)
					break;
				else
					System.out.print("* So nhap vao ko dc am * . Nhap lai");
			} catch (Exception e) {
				System.out.print("* Loi du lieu!!! * . Nhap lai");
			}

		}
		return so;
	}

	public int KTLuaChon(int x) {
		int LuaChon;
		while (true) {
			LuaChon = KTNhapInt(0);
			if (LuaChon > x)
				System.out.print("** Lua chon cua ban da sai ** . Nhap lai");
			else
				break;
		}
		return LuaChon;
	}

	public boolean KTFile(String s) {
		String regex = "([[\\\\w\\\\s*]+-[\\\\w[@.]*\\\\s*]+]+)+";
		boolean test = s.matches(regex);
		return test;

	}

	public String KTTen() {
//		String regex = "[A-Z]\\pL+[\\s+[A-Z]\\pL+]*";
		String regex = "[\\pL\\pMn*\\s*]+";		// \pL (\p{L})là 1 ký tự Unicode ( â , ă , ơ ,....) 
		String s;								// \pMn (\p{Mn} là một kí tự dự định kết hợp với một ký tự khác mà không chiếm thêm không gian ( các thanh ngang )
		while (true) {
			s = sc.nextLine();
			boolean test = s.matches(regex);
			if (test == true)
				break;
			else {
				System.out.print("** Tên nhập không hợp lệ ** . Nhập lai : ");

			}
		}
		return s;
	}

	public String KtEmail() {
		String regex = "[a-zA-Z0-9_]+@\\w+([.[a-zA-Z]]+)$";
		String s;
		while (true) {
			s = sc.nextLine();
			boolean test = s.matches(regex);
			if (test)
				break;
			else {
				System.out.print("** Email nhập không hợp lệ ** . Nhập lai : ");

			}
		}
		return s;
	}

	public String KtDienThoai() {
		String regex = "0[1-9]{9}";
		String s;
		while (true) {
			s = sc.nextLine();
			boolean test = s.matches(regex);
			if (test)
				break;
			else {
				System.out.print("** Điện Thoại nhập không hợp lệ ( 10 số )** . Nhập lai : ");

			}
		}
		return s;
	}

	public String KTMaSo() {
		String regex;
		String s;
		boolean test;
		while (true) {
			s = sc.nextLine();
			regex = "[1-9][0-9]*";
			if ((test = s.matches(regex)))
				break;
			else
				System.out.print("- Nhập sai mã số . Nhập lại : ");
		}
		return s;
	}

	public String KTMaKhu() {
		String regex;
		String s;
		boolean test;
		while (true) {
			s = sc.nextLine();
			regex = "[1-8]";
			if ((test = s.matches(regex)))
				break;
			else
				System.out.print("- Nhập sai mã số . Nhập lại : ");
		}
		return s;
	}

	public String KTTheLoai() throws IOException {
		String ma;
		while (true) {
			ma = sc.nextLine();
			ArrTheLoai arrTG = new ArrTheLoai();
			if (arrTG.TimKiem_MaSo(ma) == -1) {
				System.out.print(
						"- Mã Thể loại bạn nhập chưa có trong danh sách bạn có muốn thêm không ( y / n ) ??? : ");
				String yn = KTYesNo();
				String regex = "y|Y";
				if (yn.matches(regex)) {
					arrTG.Them1(ma);
					break;
				} else {
					System.out.print("- Mã bạn nhập không có . Hãy nhập lai : ");
				}
			} else
				break;

		}
		return ma;
	}

	public String KTNXB() throws IOException {
		String ma;
		while (true) {
			ma = KTMaSo();
			ArrNXB arrTG = new ArrNXB();
			if (arrTG.TimKiem_MaSo(ma) == -1) {
				System.out.print("- Mã NXB bạn nhập chưa có trong danh sách bạn có muốn thêm không ( y / n ) ??? : ");
				String yn = KTYesNo();
				String regex = "y|Y";
				if (yn.matches(regex)) {
					arrTG.Them1(ma);
					break;
				} else {
					System.out.print("- Mã bạn nhập không có . Hãy nhập lai : ");
				}
			} else
				break;

		}
		return ma;
	}

	public String KTVitri() throws IOException {
		String ma;
		while (true) {
			ma = KTMaSo();
			ArrVT arrTG = new ArrVT();
			if (arrTG.TimKiem_MaSo(ma) == -1) {
				System.out
						.print("- Mã Vị trí bạn nhập chưa có trong danh sách bạn có muốn thêm không ( y / n ) ??? : ");
				String yn = KTYesNo();
				String regex = "y|Y";
				if (yn.matches(regex)) {
					arrTG.Them1(ma);
					break;
				} else {
					System.out.print("- Mã bạn nhập không có . Hãy nhập lai : ");
				}
			} else
				break;

		}
		return ma;
	}

	public String KTTacGia() throws IOException {
		String ma;
		while (true) {
			ma = KTMaSo();
			ArrTG arrTG = new ArrTG();
			if (arrTG.TimKiem_MaSo(ma) == -1) {
				System.out.print("- Mã Tác giả bạn nhập chưa có trong danh sách bạn có muốn thêm không ( y / n ) ??? : ");
				String yn = KTYesNo();
				String regex = "y|Y";
				if (yn.matches(regex)) {
					arrTG.Them1(ma);
					break;
				} else {
					System.out.print("- Mã bạn nhập không có . Hãy nhập lai : ");
				}
			} else
				break;

		}
		return ma;
	}

	public String KTTenTG() throws IOException {
		String ten;
        while (true) {
		ten = KTTen();
		ArrTG as = new ArrTG();

		if (as.TimKiem_Ten(ten) == -1) {
			System.out.print("- Mã bạn nhập không có . Hãy nhập lai : ");
		} else {
			break;
		}
	}
        return ten;
	}
	public String KT_MaNV() throws IOException {
		String ma;
		while (true) {
			ma = KTMaSo();
			ArrEmployee arrTG = new ArrEmployee();
			if (arrTG.TimKiem_MaSo(ma) == -1) {
				System.out.print(
						"- Mã Nhân viên bạn nhập chưa có trong danh sách bạn có muốn thêm không ( y / n ) ??? : ");
				String yn = KTYesNo();
				String regex = "y|Y";
				if (yn.matches(regex)) {
					arrTG.Them1(ma);
					break;
				} else {
					System.out.print("- Mã bạn nhập không có . Hãy nhập lai : ");
				}
			} else
				break;

		}
		return ma;
	}

	public String KT_MaKH() throws IOException {
		String ma;
		while (true) {
			ma = KTMaSo();
			ArrCustomer arrTG = new ArrCustomer();
			if (arrTG.TimKiem_MaSo(ma) == -1) {
				System.out.print(
						"- Mã Khách hàng bạn nhập chưa có trong danh sách bạn có muốn thêm không ( y / n ) ??? : ");
				String yn = KTYesNo();
				String regex = "y|Y";
				if (yn.matches(regex)) {
					arrTG.Them1(ma);
					break;
				} else {
					System.out.print("- Mã bạn nhập không có . Hãy nhập lai : ");
				}
			} else
				break;

		}
		return ma;
	}

	public String KT_MaHD() throws IOException {
		String ma;
		while (true) {
			ma = KTMaSo();
			ArrHoaDon arrTG = new ArrHoaDon();
			if (arrTG.TimKiem_MaSo(ma) != -1) {
				System.out.println("- Mã bạn nhập ko có . Hãy nhập lại: ");
			} else {
				break;
			}

		}
		return ma;
	}

	public String KT_MaSach() throws IOException {
		String ma;
		while (true) {
			ma = KTMaSo();
			ArrKho arrTG = new ArrKho();

			if (arrTG.TimKiem_MaSo(ma) == -1) {
				System.out.print("- Mã bạn nhập không có . Hãy nhập lai : ");
			} else {
				break;
			}
		}
		return ma;
	}

        public String KT_TenSach() throws IOException
        {
            String ten;
            while (true) {
			ten = KTTen();
			ArrSach as = new ArrSach();

			if (as.TimKiem_Ten(ten) == -1) {
				System.out.print("- Mã bạn nhập không có . Hãy nhập lai : ");
			} else {
				break;
			}
		}
            return ten;
        }
        
	public String KT_MaSach_KhiXH() throws IOException {
		String mas;
		ArrKho arrTG = new ArrKho();
		while (true) {
			mas = KTMaSo();
			int vt = arrTG.TimKiem_MaSach(mas);
			if (vt == -1) {
				System.out.print("- Mã bạn nhập không có . Hãy nhập lai : ");
			} else {
				break;
			}

		}
		return mas;
	}

	public void ThayDoiSlSach(String mas, int tt, int sl) throws IOException {
		ArrKho arrK = new ArrKho();
		int vtk = arrK.TimKiem_MaSach(mas);
		if (vtk != -1)
			arrK.ThayDoiSLuongSach(tt, vtk, sl);
	}

	public String KT_MaSach_KhiNH(String maNXB, int gia) throws IOException {
		String mas;
		mas = KTMaSo();
		ArrKho arrTG = new ArrKho();
		int vt = arrTG.TimKiem_MaSach(mas);
		if (vt == -1) {
			System.out.println(
					"- Do Mã Sách bạn nhap không có trong danh sách nên bạn phải nhập thông tin của Sách đó .");
			arrTG.Them1(mas, maNXB, gia);
		}

		return mas;
	}

	public int KT_MAHD_CTHD(String ma) throws IOException {
		int vt;
		while (true) {
			ArrHoaDon arrTG = new ArrHoaDon();
			vt = arrTG.TimKiem_MaSo(ma);
			if (vt == -1) {
				System.out.print("- Mã bạn nhập không có . Hãy nhập lai : ");
			} else
				break;

		}
		return vt;
	}

	public String KTTaiKhoan(String ma) throws IOException {
		ArrTK arrTk = new ArrTK();
		while (true) {

			if (arrTk.TimKiem_MaSo(ma) != -1) {
				System.out.print("- Tên đăng nhập đã tồn tại. Nhập lại: ");
				ma = sc.nextLine();
			} else
				break;
		}
		return ma;
	}

	public String KTTaiKhoanCo(String user) throws IOException {
		ArrTK arrTk = new ArrTK();
		while (true) {

			if (arrTk.TimKiem_MaSo(user) == -1) {
				System.out.print("- Tên đăng nhập khong tồn tại. Nhập lại: ");
				user = sc.nextLine();
			} else
				break;
		}
		return user;
	}

	public String KTMKCo(String pass, int vt) throws IOException {
		ArrTK arrTk = new ArrTK();
		while (true) {

			if (arrTk.TruyXuatPT(vt).getPassword().compareTo(pass) != 0) {
				System.out.print("- Mk ban nhap ko dung . Nhap lai: ");
				pass = sc.nextLine();
			} else
				break;
		}
		return pass;
	}

	public String KTTaiKhoan(String ma, int vt) throws IOException {
		ArrTK arrTk = new ArrTK();
		while (true) {

			if (arrTk.TimKiem_MaSo(ma, vt) != -1) {
				System.out.print("- Tên đăng nhập đã tồn tại. Nhập lại: ");
				ma = sc.nextLine();
			} else
				break;
		}
		return ma;
	}
	
	public String  Out() {
		System.out.print("- Nhấn phím bất kì để tiếp tục: ");
		return sc.nextLine();
			
	}
	
	public void Phim() {
		System.out.println("- Nhấn phím bất kì để tiếp tục: ");
		String s = sc.nextLine();
	}

}
