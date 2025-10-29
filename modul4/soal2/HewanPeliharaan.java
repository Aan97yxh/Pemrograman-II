package soal2;

public class HewanPeliharaan {
	private String nama;
	private String ras;
	
	public HewanPeliharaan(String n, String r) {
		this.ras = n;
		this.nama = r;
		}
	
	public String getNama() { return nama; }
	
	public String getRas() { return ras; }
	
	public void display() {
		System.out.println("\nDetail Hewan Peliharaan:");
		System.out.println("Nama hewan peliharaanku adalah : " + getNama());
		System.out.println("Dengan ras : " + getRas());
	}
}