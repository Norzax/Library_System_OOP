package OOP_Library;

import java.io.IOException;
import java.util.Calendar;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		
		
		KT_NhapXuat kt = new KT_NhapXuat();
		Scanner sc = new Scanner(System.in);
		Calendar c = Calendar.getInstance();
		NAM_SINH Now = new NAM_SINH(c.get(Calendar.DATE), c.get(Calendar.MONTH) + 1, c.get(Calendar.YEAR));
		
		ArrTK tk = new ArrTK();
		ArrHoaDon hd = new ArrHoaDon();
		// ArrBillDetail bd = new ArrBillDetail();
		ArrEmployee ae = new ArrEmployee();
		ArrCustomer kh = new ArrCustomer();
		ArrTG tg = new ArrTG();
		ArrNXB nxb = new ArrNXB();
		ArrTheLoai tl = new ArrTheLoai();
		ArrKho ak = new ArrKho();
		ArrSach as = new ArrSach();
		int lcdn, lcus, lcad,lcak;
		String temp , thoatAd=" ", thoatUs=" ";
		do {
			System.out.println("\n===================MENU=====================");
			System.out.println("1. Đăng nhập: ");
			System.out.println("2. Đăng kí: ");
			System.out.println("3. Thoát.");
			System.out.print("- Nhập lựa chọn  bạn");
			lcdn = kt.KTLuaChon(3);
			switch (lcdn) {
			case 1: {
				String manv = tk.DangNhap();
				String s = manv;
                                int quyen=tk.KTQuyen(manv);
				if (quyen==2) {
                                    int vt = ae.TimKiem_MaSo(manv);
                                    
					
					do {
						System.out.println("\n=========MENU==========");
						System.out.println("1. Nhập hóa đơn.");
						System.out.println("2. Xuất hóa đơn.");
						System.out.println("3. Tổng doanh thu hôm nay.");
						System.out.println("4. Thống kê hóa đơn trong ngày hôm nay.");
						System.out.println("5. Xuất danh sách Sách trong Kho.");
						System.out.println("6. Tìm kiếm Sách .");
						System.out.println("7. Thoát.");
						System.out.print("- Nhập lựa chọn  bạn");
						lcus = kt.KTLuaChon(7);
						switch (lcus) {
						case 1: {
							System.out.println("\n=========NHẬP HÀNG==========");
							hd.Them1(manv);
							kt.Phim();
							break;
						}
						case 2: {

							System.out.print("- Bạn muốn xuất hóa đơn ( 1.Hóa đơn Nhập  2.Hóa đơn Xuất  3.Tất cả )");
							int tt = kt.KTLuaChon(3);
							hd.Xuat(tt);
							kt.Phim();
							break;
						}
						case 3: {
							System.out.print("- Bạn muốn xuất tổng doanh thu  hóa đơn ( 1. Nhập  2. Xuất  3. All )");
							int lc = kt.KTLuaChon(3);
							hd.TongDoanhThuTrongNgay(lc);
							kt.Phim();
							break;
						}
						case 4: {
							// System.out.println("- Bạn muốn thống kê hoá đơn theo( 1. Ngày 2. Mã NV
							// 3. Mã NXB )");
							System.out.print("- Bạn muốn xuất hóa đơn ( 1. Nhập2. Xuất3. All )");
							int lc = kt.KTLuaChon(3);
							hd.TimKiem_Ngay(lc,Now);
							kt.Phim();
							break;
						}
						case 5: {
							System.out.println("- Danh sách trong kho: ");
							ak.Xuat();
							kt.Phim();
							break;
						}

						case 6: {
							System.out.println("\n=======MENU=======");
							System.out.println("1 .Tìm kiếm theo Tên Sách.");
							System.out.println("2 .Tìm kiếm theo Tên Tác giả.");
							System.out.print("- Nhập lựa chọn  bạn");
							int x = kt.KTLuaChon(2);
							switch (x) {
							case 1: {
								as.TimKiem_Ten();
								kt.Phim();
								break;
							}
							case 2: {
								as.TimKiem_TenTG();
								kt.Phim();
								break;
							}

							}

							break;
						}
						case 7: {
							System.out.println("- bạn đã thoát.");
							thoatUs = kt.Out();
						}
						}
					} while (thoatUs.compareTo(" ") == 0);
				} else if(quyen==1) {
					
					ArrCustomer ac = new ArrCustomer();
					ArrTG at = new ArrTG();
					ArrTheLoai atl = new ArrTheLoai();
					ArrNXB an = new ArrNXB();
					System.out.println("- Chào mừng admin.");
					do {
						System.out.println("\n==========MENU=========");
						System.out.println("------DANH MỤC------");
						System.out.println("1. Tài Khoản.");
						System.out.println("2. Khách hàng.");
						System.out.println("3. Nhân viên.");
						System.out.println("4. Tác giả.");
						System.out.println("5. Nhà Xuất Bản.");
						System.out.println("6. Thể loại.");
						System.out.println("7. Sách.");
						System.out.println("8. Hoá Đơn.");
						System.out.println("9. Thoát.");
						System.out.print("- Nhập lựa chọn  bạn");
						lcad = kt.KTLuaChon(9);
						switch (lcad) {
						case 1: {
							tk.MenuChinh();
							kt.Phim();
							break;
						}
						case 2: {
							kh.MenuChinh();
							kt.Phim();
							break;
						}
						case 3: {
							ae.MenuChinh();
							kt.Phim();
							break;
						}
						case 4: {
							tg.MenuChinh();
							kt.Phim();
							break;
						}
						case 5: {
							nxb.MenuChinh();
							kt.Phim();
							break;
						}
						case 6: {
							tl.MenuChinh();
							kt.Phim();
							break;
						}
						case 7:
						{
							as.MenuChinh();
							kt.Phim();
							break;
						}
						case 8:
						{
							hd.MenuChinh();
							kt.Phim();
							break;
						}
						case 9: {
							System.out.println("- Bạn đã thoát.");
							thoatAd = kt.Out();
							break;
						}
						}
					} while (thoatAd.compareTo(" ") == 0);
				}
                                if(quyen==3){/*****************************
                                        **************code khách hàng ở đây*******************/
                                    
                                    System.out.println("Khách hàng");
                                    do {
						System.out.println("\n==========MENU=========");
						System.out.println("------DANH MỤC------");
						System.out.println("1. Xem Sách.");
						System.out.println("2. Thoát.");
						System.out.print("- Nhập lựa chọn  bạn");
						lcak = kt.KTLuaChon(9);
						switch (lcak) {
						case 1: {
							as.menuphu();
							kt.Phim();
							break;
						}
						
						case 2: {
							System.out.println("- Bạn đã thoát.");
							thoatAd = kt.Out();
							break;
						}
						}
					} while (thoatAd.compareTo(" ") == 0);
                                }
				break;
			}
			case 2: {
				tk.DangKi();
				break;
			}
			}

		} while (lcdn != 3);

//		ArrSach ak = new ArrSach();
//		ak.TimKiem_Ten();
//		ArrTK at = new ArrTK();
//		at.MenuChinh();
	}
}
