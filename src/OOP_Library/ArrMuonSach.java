/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OOP_Library;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Scanner;

/**
 *
 * @author ASUS
 */
public class ArrMuonSach implements Arr {
    private int lenArr = 0;
	private int lenFile = 0;
	private MuonSach [] ArrMuonSach = new MuonSach[1];
	private static final String filename = "MuonSach.txt";
        Calendar c = Calendar.getInstance();
	private NAM_SINH Now = new NAM_SINH(c.get(Calendar.DATE), c.get(Calendar.MONTH) + 1, c.get(Calendar.YEAR));
    
    Scanner sc = new Scanner(System.in);
    KT_NhapXuat kt = new KT_NhapXuat(); 
        
    public ArrMuonSach() throws IOException {
		HienDS();
	}
    @Override
    public void ThemDT() throws IOException {
     
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

		ArrMuonSach = Arrays.copyOf(ArrMuonSach, lenArr + lenFile);
		while ((data = br.readLine()) != null) {
			if (data.length() != 1) {
				ArrMuonSach[i] = new MuonSach();
				Scanner scan = new Scanner(data).useDelimiter("-");
				ArrMuonSach[i].setMAMuon(scan.next());
				ArrMuonSach[i].setNgay_xuat(scan.nextInt(), scan.nextInt(), scan.nextInt());
				ArrMuonSach[i].setTRANG_THAI(scan.nextInt());
				ArrMuonSach[i].setMAKH(scan.next());
				ArrMuonSach[i++].setMANV(scan.next());
				
			}

		}

		if (br != null) {
			br.close();
		}
		if (fr != null) {
			fr.close();
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
    
    public int TimKiem_MaSoMH(String ma, int j) {
		if (j == 0) {
			return -1;
		}

		for (int i = 0; i < j; i++) {
			if (ArrMuonSach[i].getTRANG_THAI() == 1 && ArrMuonSach[i].getMAMuon().compareTo(ma) == 0) {
				return i;
			}
		}
		return -1;

    }
    
    public int TimKiem_MaSoMXH(String ma, int j) {
		if (j == 0) {
			return -1;
		}

		for (int i = 0; i < j; i++) {
			if (ArrMuonSach[i].getTRANG_THAI() == 2 && ArrMuonSach[i].getMAMuon().compareTo(ma) == 0) {
				return i;
			}
		}
		return -1;

    }
    
    public void Them2(String maNV) throws IOException{
                lenArr += 1;
		ArrMuonSach = Arrays.copyOf(ArrMuonSach, lenArr + lenFile);
		int vt = lenArr + lenFile - 1, tt;
		String mahd;
		ArrMuonSach[vt] = new MuonSach();
		System.out.print("- Nhập Trạng Thái ( 1. Mượn sách\t2.Trả sách )");
		tt = kt.KTLuaChon(2);
		ArrMuonSach[vt].setTRANG_THAI(tt);
		System.out.print("- Nhap mã mượn: ");
                
		while (true) {
			mahd = kt.KTMaSo();
			ArrMuonSach[vt].setMAMuon(mahd);
			if (tt == 1) {
				if (TimKiem_MaSoMH("NH" + mahd, vt) != -1) {
					System.out.print("- Ma nhap da co. Nhap lai: ");

				} else {
					break;
				}
			} else if (TimKiem_MaSoMXH("XH" + mahd, vt) != -1) {
				System.out.print("- Ma nhap da co. Nhap lai: ");

			} else {
				break;
			}
		}

		// Nhập hàng
		if (tt == 1) {
			ArrMuonSach[vt].NhapMH(mahd, maNV);
			
		}
		// Xuất hàng
		else {
			ArrMuonSach[vt].NhapXH(mahd, maNV);
			

			ArrHoaDon[vt].setTONG_TIEN(arrBd.TinhTongTienChoHD(mahd));
		}

		// Cập nhật lại ngày xuất hoá đơn trùng vs ngày hiện tại
		ArrHoaDon[vt].setNgay_xuat(Now);
		GhiFile();
	}
    
    @Override
    public int TimKiem_MaSo(String ma) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void TimKiem_Ten() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Xoa() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Sua() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void GhiFile() throws IOException {
        File file = new File(filename);
		if (!file.exists()) {
			file.createNewFile();
		}
		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		for (int i = 0; i < lenArr + lenFile; i++) {

			String ns = ArrMuonSach[i].getNgay_muon().getNgay() + "-" + ArrMuonSach[i].getNgay_muon().getThang() + "-"
					+ ArrMuonSach[i].getNgay_muon().getNam();
			String data = ArrMuonSach[i].getMAMuon() + "-" + ns + "-" + ArrMuonSach[i].getTRANG_THAI() + "-"
					+ ArrMuonSach[i].getMAKH() + "-" + ArrMuonSach[i].getMANV();

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
    
}
