package soal2;

public class Kucing extends HewanPeliharaan {
	private String warnaBulu;
	
	public Kucing(String n, String r, String w) {
		super(n, r);
		this.warnaBulu = w;
	}
	
	public String getWarnaBulu() { return warnaBulu; }
	
	public void displayDetailKucing() {
		super.display();
		System.out.println("Memiliki warna bulu : " + getWarnaBulu());
	}
}