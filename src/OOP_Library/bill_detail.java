package OOP_Library;

import java.io.IOException;
import java.util.Scanner;

public class bill_detail {

    String MAHD, MASACH;
    int S_LUONG, GIA;

    public int getGIA() {
        return GIA;
    }

    public void setGIA(int gIA) {
        GIA = gIA;
    }

    Scanner sc = new Scanner(System.in);
    KT_NhapXuat kt = new KT_NhapXuat();

    public String getMAHD() {
        return MAHD;
    }

    public void setMAHD(String MAHD) {
        this.MAHD = MAHD;
    }

    public String getMASACH() {
        return MASACH;
    }

    public void setMASACH(String MASACH) {
        this.MASACH = MASACH;
    }

    public int getS_LUONG() {
        return S_LUONG;
    }

    public void setS_LUONG(int S_LUONG) {
        this.S_LUONG = S_LUONG;
    }

    public bill_detail() {
    }

    public bill_detail(String MAHD, String MASACH, int S_LUONG) {
        this.MAHD = MAHD;
        this.MASACH = MASACH;
        this.S_LUONG = S_LUONG;

    }

    public void Nhap() throws IOException {

        System.out.print("- Nhap So Luong sản phẩm trong CTHD");
        S_LUONG = kt.KTNhapInt(0);

    }

    public void Xuat() throws IOException {
    	ArrSach as = new ArrSach();
    	int vts = as.TimKiem_MaSo(MASACH);
    	String tens = as.getArrSach()[vts].getTenSach();
        System.out.printf("%-5s%-10s%-20s%-6s\n", "", MAHD, MASACH, S_LUONG);
    }
}
