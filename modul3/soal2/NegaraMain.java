package soal2;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class NegaraMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // HashMap nama bulan (1-12)
        HashMap<Integer, String> namaBulan = new HashMap<>();
        namaBulan.put(1, "Januari");
        namaBulan.put(2, "Februari");
        namaBulan.put(3, "Maret");
        namaBulan.put(4, "April");
        namaBulan.put(5, "Mei");
        namaBulan.put(6, "Juni");
        namaBulan.put(7, "Juli");
        namaBulan.put(8, "Agustus");
        namaBulan.put(9, "September");
        namaBulan.put(10, "Oktober");
        namaBulan.put(11, "November");
        namaBulan.put(12, "Desember");

        LinkedList<Negara> daftar = new LinkedList<>();

        // Baca jumlah negara
        int n = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < n; i++) {
            String nama = sc.nextLine().trim();
            String jenis = sc.nextLine().trim();
            String pemimpin = sc.nextLine().trim();

            Integer tgl = null, bln = null, thn = null;

            // jika bukan monarki, baca tanggal/bulan/tahun (asumsi valid)
            if (!jenis.equalsIgnoreCase("monarki")) {
                tgl = sc.nextInt();
                bln = sc.nextInt();
                thn = sc.nextInt();
                sc.nextLine();
            }

            Negara negara = new Negara(nama, jenis, pemimpin, tgl, bln, thn);
            daftar.add(negara);
        }

        // Output
        for (Negara negara : daftar) {
            String bulanName = (negara.getBulan() == null) ? "" : namaBulan.get(negara.getBulan());
            negara.TampilkanInfo(bulanName);
        }

        sc.close();
    }
}