package com.modul1;

import java.util.Scanner;
public class PRAK105_2410817310008_MANSHARY {
	
	public static final double PHI = 3.14;
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		double r, t;
		
		do {
            System.out.print("Masukkan jari-jari: ");
            r = input.nextDouble();
            if (r <= 0) {
                System.out.println("Jari-jari harus lebih dari 0, silakan masukkan lagi.");
            }
        } while (r <= 0);
		
		do {
            System.out.print("Masukkan tinggi: ");
            t = input.nextDouble();
            if (t <= 0) {
                System.out.println("Tinggi harus lebih dari 0, silakan masukkan lagi.");
            }
        } while (t <= 0);

        double volume = PHI * r * r * t;

        System.out.printf("Volume tabung dengan jari-jari %.1f cm dan \n", r);
        System.out.printf("tinggi %.1f cm adalah %.3f m3\n", t, volume);

        input.close();
        
	}

}