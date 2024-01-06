package OOP_Library;

import java.io.IOException;

public interface Arr {

	void ThemDT() throws IOException;

	void HienDS() throws IOException;   //Đọc file

	int SoluongTrongFile() throws IOException;

	int TimKiem_MaSo(String ma) throws IOException;

	void TimKiem_Ten()throws IOException;

	void Xoa() throws IOException;

	void Sua() throws IOException;
	
	void GhiFile() throws IOException;
}
