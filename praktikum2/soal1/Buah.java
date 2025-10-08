package praktikum2.soal1;

public class Buah {
    private String nama;	
    private double berat;      
    private double harga;      
    private double jumlahBeli;  

    public Buah(String nama, double berat, double harga, double jumlahBeli) {
        this.nama = nama;
        this.berat = berat;
        this.harga = harga;
        this.jumlahBeli = jumlahBeli;
    }
    
    // Harga per kg
    double hargaPerKg() {
        return this.harga / this.berat;
    }
    // Harga sebelum diskon (total)
    double hargaSebelumDiskon() {
        return this.jumlahBeli * hargaPerKg();
    }
    // Kelipatan diskon
    double kelipatanDiskon() {
    	return (int) (this.jumlahBeli / 4);
    }
    // Total diskon sesuai contoh: diskon% = 2% * berat_unit
    double totalDiskon() {
        return kelipatanDiskon() * (this.harga * 0.08);
    }
    // Harga setelah diskon
    double hargaSetelahDiskon() {
        return hargaSebelumDiskon() - totalDiskon();
    }


    public void tampilkanInfo() {
        System.out.println("Nama Buah: " + nama);
        System.out.println("Berat: " + berat);
        System.out.println("Harga: " + harga);
        System.out.println("Jumlah Beli: " + jumlahBeli + "kg");
        System.out.printf("Harga Sebelum Diskon: Rp%.2f%n", hargaSebelumDiskon());
        System.out.printf("Total Diskon: Rp%.2f%n", totalDiskon());
        System.out.printf("Harga Setelah Diskon: Rp%.2f%n", hargaSetelahDiskon());
        System.out.println();
    }
}