package soal1;

import java.util.Scanner;

public class DaduMain {
	public static void main(String[] args) {
        Scanner Scan = new Scanner(System.in);

        // Validasi integer > 0
        int jumlah;
        do {
            while (!Scan.hasNextInt()) {
                System.out.print("Input tidak valid. Masukkan jumlah dadu (bilangan bulat > 0): ");
                Scan.next();
            }
            jumlah = Scan.nextInt();
            
            if (jumlah <= 0) {
                System.out.println("Jumlah dadu harus lebih besar dari 0. Coba lagi.");
            }
        } while (jumlah <= 0);

        // Buat objek GameDadu dan inisialisasi koleksi sesuai input
        DaduGame permainan = new DaduGame();
        permainan.tambahBanyak(jumlah);

        // Tampilkan hasil dan total dadu
        permainan.tampilkanHasil();

        Scan.close();
    }
}