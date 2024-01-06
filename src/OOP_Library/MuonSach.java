/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OOP_Library;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author ASUS
 */
public class MuonSach {
        private String MAMuon,MAKH,MANV;
        private NAM_SINH Ngay_muon = new NAM_SINH(); 
        private NAM_SINH Ngay_tra = new NAM_SINH();
        private int TRANG_THAI;
        private int tienmuon=1000;
        private int songaymuon;

        Scanner sc = new Scanner(System.in);
	KT_NhapXuat kt = new KT_NhapXuat();

    public String getMAMuon() {
        return MAMuon;
    }
    
    public int getSongaymuon() {
        return songaymuon;
    }

    public void setSongaymuon(int songaymuon) {
        this.songaymuon = songaymuon;
    }
    
    public int gettienmuon(){
        return tienmuon;
    }
    
    public void settienmuon(int tienmuon){
        this.tienmuon=tienmuon;
    }
    
    public void setMAMuon(String MAMuon) {
        this.MAMuon = MAMuon;
    }

    public String getMAKH() {
        return MAKH;
    }

    public void setMAKH(String MAKH) {
        this.MAKH = MAKH;
    }

    public String getMANV() {
        return MANV;
    }

    public void setMANV(String MANV) {
        this.MANV = MANV;
    }

    public NAM_SINH getNgay_muon() {
        return Ngay_muon;
    }

    public void setNgay_muon(NAM_SINH Nm) {
        this.Ngay_muon = Nm;
    }
     public void setNgay_xuat(int ngay, int thang, int nam) {
        Ngay_muon.setNgay(ngay);
	Ngay_muon.setThang(thang);
	Ngay_muon.setNam(nam);
    }

    public NAM_SINH getNgay_tra() {
        return Ngay_tra;
    }

    public void setNgay_tra(NAM_SINH Ngay_tra) {
        this.Ngay_tra = Ngay_tra;
    }
    
    public void setNgay_tra(int ngay, int thang, int nam) {
        Ngay_tra.setNgay(ngay);
	Ngay_tra.setThang(thang);
	Ngay_tra.setNam(nam);
    }

    public int getTRANG_THAI() {
        return TRANG_THAI;
    }

    public void setTRANG_THAI(int TRANG_THAI) {
        this.TRANG_THAI = TRANG_THAI;
    }

    public MuonSach(String MAMuon, String MAKH, String MANV, int TRANG_THAI) {
        this.MAMuon = MAMuon;
        this.MAKH = MAKH;
        this.MANV = MANV;
        this.TRANG_THAI = TRANG_THAI;
    }
    
    public MuonSach(){
        
    }
    
    public void nhap(String maMuon,String maNV)throws IOException{
        MAMuon="M"+maMuon;
        System.out.println("Nhập vào MaKH: ");
        System.out.print("- Bạn có là thành viên của shop không ( y / n ) ??? ");
		String yn = kt.KTYesNo();
		String regex = "y|Y";
		if(yn.matches(regex)) {
			
			System.out.print("- Nhập Mã Khách Hàng: ");
			MAKH = kt.KT_MaKH();
		}
		else
			MAKH = null;
		MANV = maNV;
    }
    
    
    public void nhapmuonsach(){
        
    }
    
    public void xuat()throws IOException{
        ArrEmployee ae = new ArrEmployee();
	ArrCustomer ac = new ArrCustomer();
	int vte = ae.TimKiem_MaSo(MANV);
	int vtc = ac.TimKiem_MaSo(MAKH);
	String tenNV = ae.getArremp()[vte].getTen();
	String tenKH="";
	if(vtc != -1)
	tenKH = ac.getArrcus()[vtc].getTen();
	System.out.printf("%5s%-10s", "", MAMuon);
	Ngay_muon.Xuat();
	System.out.printf("%-11s%-20s%-20s%-20s\n","", KT_TT(), tenKH, tenNV);
    }
    
    public String KT_TT(){
        if(TRANG_THAI==1)return "Đã mượn";
                else return "Đã trả";
    }
}
