package OOP_Library;

public class Employee extends PerSon {
	private String MS;

	public String getMS() {
		return MS;
	}

	public void setMS(String MS) {
		this.MS = MS;
	}

	public Employee() {
		super();

	}

	public Employee(String ten, String dienThoai, String email, String diaChi, int gioiTinh, NAM_SINH ngaySinh) {
		super(ten, dienThoai, email, diaChi, gioiTinh, ngaySinh);

	}

	@Override
	public void Nhap() {
		// TODO Auto-generated method stub
		super.Nhap();
	}

	@Override
	public void Xuat() {
		// TODO Auto-generated method stub

		System.out.printf("%5s%-10s", "", MS);
		super.Xuat();
	}
}
