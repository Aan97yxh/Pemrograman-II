package soal1;

import java.util.LinkedList;

public class DaduGame {
    private LinkedList<Dadu> daftarDadu = new LinkedList<>();

    // Menambah satu objek Dadu
    public void tambahDadu() {
        daftarDadu.add(new Dadu());
    }

    // Loop jumlah Dadu
    public void tambahBanyak(int jumlah) {
        for (int i = 0; i < jumlah; i++) {
            tambahDadu();
        }
    }

    // Hitung total nilai seluruh dadu dalam koleksi
    public int hitungTotal() {
        int total = 0;
        for (Dadu d : daftarDadu) {
            total += d.getNilai();
        }
        return total;
    }

    // Tampilkan nilai tiap dadu dan total akhirnya
    public void tampilkanHasil() {
        for (int i = 0; i < daftarDadu.size(); i++) {
            System.out.println("Dadu ke-" + (i + 1) + " bernilai " + daftarDadu.get(i).getNilai());
        }
        System.out.println("Total nilai dadu keseluruhan " + hitungTotal());
    }
}