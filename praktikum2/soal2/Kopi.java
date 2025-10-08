package praktikum2.soal2;

public class Kopi {
    public String namaKopi;
    public String ukuran;
    public double harga;

    private String pembeli;

    // Setter Getter Pembeli
    public void setPembeli(String pembeli) {
        this.pembeli = pembeli;
    }
    public String getPembeli() {
        return pembeli;
    }

    // Pajak 11% dari harga
    public double getPajak() {
        return 0.11 * harga;
    }

    // Menampilkan info
    public void info() {
        System.out.println("Nama Kopi: " + namaKopi);
        System.out.println("Ukuran: " + ukuran);
        System.out.println("Harga: Rp. " + harga);
    }
}