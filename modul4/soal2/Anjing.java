package soal2;

public class Anjing extends HewanPeliharaan {
	private String warnaBulu;
	private String[] kemampuan;
	
	public Anjing(String n, String r, String w, String[] k) {
		super(n, r);
		this.warnaBulu = w;
		this.kemampuan = k;
	}
	
	public String getWarnaBulu() { return warnaBulu; }
	
	public String[] getKemampuan() { return kemampuan; }
	
	public void displayDetailAnjing() {
		super.display();
		System.out.println("Memiliki warna bulu : " + getWarnaBulu());
		System.out.print("Memiliki kemampuan : ");
		
		for (int i = 0; i < kemampuan.length; i++) {
			System.out.print(kemampuan[i]);
			if(i < kemampuan.length - 1) {
				System.out.print("  ");
			}
		}
		System.out.println();
	}
}