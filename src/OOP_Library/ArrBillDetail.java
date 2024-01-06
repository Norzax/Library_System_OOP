package OOP_Library;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class ArrBillDetail implements Arr {

    private int lenArr = 0, lenFile = 0;
    private bill_detail[] ArrBD = new bill_detail[1];
    private static final String filename = "Bill_Detail.txt";

    Scanner sc = new Scanner(System.in);
    KT_NhapXuat kt = new KT_NhapXuat();
    ArrKho ak = new ArrKho();

    public ArrBillDetail() throws IOException {
        HienDS();   //khi khởi tạo là đọc file 
    }

    //không xài nhưng vì nó là cái khung bắt buộc phải có
    @Override
    public void ThemDT() throws IOException {

    }

    // Thêm 1 chi tiết hóa đơn
    public void Them1(String maHD, int tt, String maNXB) throws IOException {

  
        ArrHoaDon arrHD = new ArrHoaDon();
        String mahd, mas;
        System.out.print("- Nhập số lượng sản phẩm trong hóa đơn ");

        // Cập nhật lại lenArr ( lenFile đã dc cập nhật khi gọi lại Contructor ) 
        lenArr = kt.KTNhapInt(1);
        ArrBD = Arrays.copyOf(ArrBD, lenArr + lenFile + 1);
        int vt, gia = 0;
        for (int i = lenFile; i < lenArr + lenFile; i++) {
            System.out.printf("\n%60s %d%s\n\n", "------------CTHD", i + 1, "------------");
            ArrBD[i] = new bill_detail();

            // Xét trường hợp nhập hàng
            if (tt == 1) {
                mahd = "NH" + maHD;

                // Cập nhật lại vào đối tượng trong mảng
                ArrBD[i].setMAHD(mahd);
                System.out.print("- Nhập giá của loại sản phẩm này");
                gia = kt.KTNhapInt(0);
                ArrBD[i].setGIA(gia);

                // nhập số lượng trong đối tượng CTHD ( Nhập trong hàm ArrBD[i].Nhap(); )
                ArrBD[i].Nhap();

                // Lúc này giá sản phẩm sẽ = Giá của loại sản phẩm đó * Số lượng loại sản phẩm đó trong hoá đơn
                int gias = ArrBD[i].getGIA() / ArrBD[i].getS_LUONG();
                System.out.print("- Nhập Mã Sách: ");

                // Bắt lỗi khóa chính của Chi Tiết Hóa Đơn .( MAHD ,MASACH )
                while (true) {
                    mas = kt.KT_MaSach_KhiNH(maNXB, gias);
                    if (TimKiem_MaSo(mahd, mas, i) != -1) {
                        System.out.print("- Hóa đơn đã có sản phẩm này rồi . Nhập lại mã sản phẩm: ");
                    } else {
                        break;
                    }
                }

                // Lúc này thì cập nhật lại mã Sách vào trong đối tượng
                ArrBD[i].setMASACH(mas);
            } // Xét trường hợp xuất hàng.
            else {

                // Đổi mã Hoá đơn từ đối số truyền để nhận biết đó là Hoá đơn xuất hàng.
                mahd = "XH" + maHD;
                ArrBD[i].setMAHD(mahd);

                System.out.print("- Nhập Mã Sách: ");

                while (true) {
                    mas = kt.KT_MaSach_KhiXH();
                    if (TimKiem_MaSo(mahd, mas, i) != -1) {
                        System.out.print("- Hóa đơn đã có sản phẩm này rồi . Nhập lại mã sản phẩm: ");
                    } else {
                        break;
                    }
                }

                // Tìm vị trí của mã sách
                vt = ak.TimKiem_MaSach(mas);

                if (ak.TruyXuatPT(vt).getSLuong() == 0) {
                    System.out.println("- Hiện tại sách này đã hết số lượng.");

                    ArrBD[i].setS_LUONG(0);
                } else {

                    // nhập số lượng trong đối tượng CTHD ( Nhập trong hàm ArrBD[i].Nhap(); )
                    ArrBD[i].Nhap();
                }
                ArrBD[i].setMASACH(mas);

                // Đối với Hoá đơn xuất hàng thì Giá của loại sản phẩm lúc này sẽ lấy từ Kho .
                gia = LayGiaSanPhamTrongKho(mas);

                // Cập nhật lại Giá vào trong đối tượng trong mảng
                ArrBD[i].setGIA(gia);
            }

            // Sau khi thanh toán 1 Hoá đơn ( Nhập hoăc Xuất ) thì phải thay đổi Số lượng ở trong Kho
            kt.ThayDoiSlSach(mas, tt, ArrBD[i].getS_LUONG());
        }

        // Ghi lại vô File
        GhiFile();
    }

    @Override
    public void HienDS() throws IOException {
        File file = new File(filename);

        // Kiểm tra xem file có tồn tại hay không. Nếu chưa thì tạo file mới
        if (!file.exists()) {
            file.createNewFile();
        }
        FileReader fr = new FileReader(file.getAbsoluteFile());
        BufferedReader br = new BufferedReader(fr);
        String data;
        int i = 0;

        // Cập nhật lại lenFile
        lenFile = SoluongTrongFile();

        // cấp phát lại bộ nhớ cho mảng
        ArrBD = Arrays.copyOf(ArrBD, lenArr + lenFile);
        while ((data = br.readLine()) != null) {

            if (data.length() != 1) {
                ArrBD[i] = new bill_detail();

                //Sử dụng Scanner + useDelimiter cũng có tác dụng giống với split nhưng không cần sử dụng thêm một mảng String
                Scanner scan = new Scanner(data).useDelimiter("-");
                ArrBD[i].setMAHD(scan.next());
                ArrBD[i].setMASACH(scan.next());
                ArrBD[i].setS_LUONG(scan.nextInt());
                ArrBD[i++].setGIA(scan.nextInt());

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

        // Kiểm tra xem File có tồn tại hay ko. Nếu chưa tồn tại thì tạo 1 File mới.
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);
        for (int i = 0; i < lenArr + lenFile; i++) {
            String data = ArrBD[i].getMAHD() + "-" + ArrBD[i].getMASACH() + "-" + ArrBD[i].getS_LUONG() + "-"
                    + ArrBD[i].getGIA();

            // Ghi chuỗi data vào trong File
            bw.write(data);
            // Xuống dòng trong File.
            bw.newLine();
        }

        if (bw != null) {
            bw.close();
        }
        if (fw != null) {
            fw.close();
        }
    }

//	public void GhiDeFile() throws IOException {
//		File file = new File(filename);
//		if (!file.exists()) {
//			file.createNewFile();
//		}
//		FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
//		BufferedWriter bw = new BufferedWriter(fw);
//		for (int i = 0; i < lenArr + lenFile; i++) {
//			String data = ArrBD[i].getMAHD() + "-" + ArrBD[i].getMASACH() + "-" + ArrBD[i].getS_LUONG() + "-"
//					+ ArrBD[i].getGIA();
//
//			bw.write(data);
//			bw.newLine();
//		}
//
//		if (bw != null) {
//			bw.close();
//		}
//		if (fw != null) {
//			fw.close();
//		}
//	}
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

            // So sánh Mã Hoá đơn vs đối số truyền vào ( ma ) nếu bằng nhau thì trả về vị trí còn không thì trả về -1
            if (ArrBD[i].getMAHD().compareTo(ma) == 0) {
                return i;
            }
        }
        return -1;

    }

    // method này dùng để KT mã Sách truyền vô ( mas ) lúc này đã tồn tại trong Hoá đơn hiện tại chưa
    public int TimKiem_MaSo(String mahd, String mas, int j) {
        int dem = 0;
        if (j == 0) {
            return -1;
        }

        for (int i = 0; i < j; i++) {
            if (ArrBD[i].getMAHD().compareTo(mahd) == 0 && ArrBD[i].getMASACH().compareTo(mas) == 0) {
                dem++;
            }
        }
        if (dem == 0) {
            return -1;
        }
        for (int i = 0; i < j; i++) {
            if (ArrBD[i].getMAHD().compareTo(mahd) == 0 && ArrBD[i].getMASACH().compareTo(mas) == 0) {
                return i;
            }

        }
        return -1;
    }

    public void TimKiem_MS(String maHD) throws IOException {

        int dem = 0;
        System.out.println("==========CTHD " + maHD + "==========");
        for (int i = 0; i < lenArr + lenFile; i++) {

            // Đối số truyền vô lúc này có dạng ( NH , XH , hoặc số ) nên phải kt cả 3 dạng
            if (ArrBD[i].getMAHD().compareTo("NH" + maHD) == 0 || ArrBD[i].getMAHD().compareTo("XH" + maHD) == 0
                    || ArrBD[i].getMAHD().compareTo(maHD) == 0) {

                ArrBD[i].Xuat();
                dem++;
            }
        }
        if (dem == 0) {
            System.out.printf("- Không có mã %s .", maHD);
        }
    }

    @Override
    public void TimKiem_Ten() {

    }

//	public int XuatSlSach(String mahd, String mas) {
//		for (int i = 0; i < lenArr + lenFile; i++)
//			if (ArrBD[i].getMAHD() == mahd && ArrBD[i].getMASACH() == mas)
//				return ArrBD[i].getS_LUONG();
//		return 0;
//	}
    // method này dùng để lấy Giá của sản phẩm theo mã sản phẩm dc truyền vào (mas) ở trong Kho
    public int LayGiaSanPhamTrongKho(String mas) throws IOException {
        int gia = 0, vt;

        // Tìm vị trí của mã Sách ở trong kho
        vt = ak.TimKiem_MaSach(mas);

        // Lấy giá của sản phẩm đó . Hàm TruyXuatPT(vt) trả về 1 đối tượng Kho
        gia = ak.TruyXuatPT(vt).getGIA();
        return gia;
    }

    // method này chỉ sử dụng cho Hoá đơn xuất hàng.
    public double TinhTongTienChoHD(String maHD) {
        double gia = 0, vt;
        for (int i = 0; i < lenArr + lenFile; i++) {
            if (ArrBD[i].getMAHD().compareTo(maHD) == 0 || ArrBD[i].getMAHD().compareTo("XH" + maHD) == 0) {

                // Tổng tiền của Hoá đơn = Giá của sản phẩm trong kho + 10% * số lượng
                // của loại sản phẩm đó
                gia += (double) ArrBD[i].getGIA() * 1.1 * ArrBD[i].getS_LUONG();
            }
        }
        return gia;
    }

    @Override
    public void Xoa() {
        while (true) {
            System.out.print("- Nhập mã số sách bạn muốn xóa : ");
            String ma = kt.KTMaSo();
            int vt = TimKiem_MaSo(ma);
            System.out.println(vt);

            if (vt != -1) {
                System.out.print("- Bạn có thực sự muốn xóa sách này ( y / n ): ");
                while (true) {

                    // Dùng biểu thức chính quy ( Regular Expression ) 
                    // y|Y : có nghĩa là chỉ y hoặc Y.
                    String regex = "y|Y";
                    String yn = kt.KTYesNo();

                    // Kiểm tra cái chuỗi ( yn ) có khớp vs biểu thức hay ko. 
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
            ArrBD[i] = ArrBD[i + 1];
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
                            ArrBD[i] = MenuSua(ArrBD[i]);
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

    public bill_detail MenuSua(bill_detail tg) {
        int lc;
        String temp;
        do {
            System.out.println("** Bạn muốn sửa thông tin nào: ");
            System.out.println("1.Mã HD.");
            System.out.println("2.Mã Sách.");
            System.out.println("3.Số Lượng.");
            System.out.println("4.Thoát");

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
                    System.out.print("- Nhập Mã Sách: ");
                    temp = kt.KTMaSo();
                    tg.setMASACH(temp);
                    break;
                }
                case 3: {
                    System.out.print("- Nhập S.Lượng: ");
                    int sl = kt.KTNhapInt(1);
                    tg.setS_LUONG(sl);
                    break;
                }

                case 4: {
                    System.out.println("- Bạn đã thoát. ");
                    break;
                }
            }
        } while (lc != 4);
        return tg;
    }

    public void Xuat() throws IOException {
        if (SoluongTrongFile() == 0 && lenArr == 0) {
            String yn;
            System.out.print("- Danh HoaDon chua co nhan vien : Ban co muon them nhan vien ???.(y / n) : ");
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
            ArrBD[j].Xuat();
        }
    }

    public void MenuChinh() throws IOException {
        int lc = 0, loadnum = 0;
        boolean check = false;
        do {
            System.out.println("=============================MENU============================");
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
                    break;
                }
                case 2: {
                    Xuat();
                    break;
                }
                case 3: {
                    Xoa();
                    break;
                }
                case 4: {
                    Sua();
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

}
