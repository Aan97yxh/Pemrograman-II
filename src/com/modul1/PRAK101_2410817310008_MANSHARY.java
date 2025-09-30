package com.modul1;

import java.util.Scanner;
public class PRAK101_2410817310008_MANSHARY {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		// Input
        System.out.print("Masukkan Nama Lengkap: ");
        String nama = input.nextLine();

        System.out.print("Masukkan Tempat Lahir: ");
        String tempatLahir = input.nextLine();

        System.out.print("Masukkan Tanggal Lahir: ");
        int tanggal = input.nextInt();
        while (tanggal < 1 || tanggal > 31) {
            System.out.print("Tanggal tidak valid, masukkan kembali (1-31): ");
            tanggal = input.nextInt();
        }

        System.out.print("Masukkan Bulan Lahir: ");
        int bulan = input.nextInt();
        while (bulan < 1 || bulan > 12) {
            System.out.print("Bulan tidak valid, masukkan kembali (1-12): ");
            bulan = input.nextInt();
        }

        System.out.print("Masukkan Tahun Lahir: ");
        int tahun = input.nextInt();

        System.out.print("Masukkan Tinggi Badan: ");
        int tinggi = input.nextInt();

        System.out.print("Masukkan Berat Badan: ");
        double berat = input.nextDouble();
        
        // Nama Bulan
        String[] namaBulan = {
            "", "Januari", "Februari", "Maret", "April", "Mei", "Juni",
            "Juli", "Agustus", "September", "Oktober", "November", "Desember"
        };

        // Output
        System.out.println("\nNama Lengkap " + nama + ", Lahir di " + tempatLahir +
                " pada Tanggal " + tanggal + " " + namaBulan[bulan] + " " + tahun + 
                "\nTinggi Badan " + tinggi + " cm dan Berat Badan " + berat + " kilogram");
        input.close();

	}
}