package com.modul1;

import java.util.Scanner;
public class PRAK103_2410817310008_MANSHARY {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

        // Input
        int N = input.nextInt();
        int awal = input.nextInt();

        int i = 0;
        int angka = awal;

        do {
            if (angka % 2 != 0) {   // Jika ganjil maka tampilkan
                System.out.print(angka);
                i++;
                if (i < N) {
                    System.out.print(", ");
                }
            }
            angka++;
        } while (i < N);

        input.close();

	}

}