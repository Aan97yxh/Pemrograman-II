package com.modul1;

import java.util.Scanner;
public class PRAK104_2410817310008_MANSHARY {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		// Input
		System.out.print("Tangan Abu: ");
		String[] abu = input.nextLine().trim().split("\\s+");
		
		System.out.print("Tangan Bagas: ");
		String[] bagas = input.nextLine().trim().split("\\s+");
		
		int scoreAbu = 0, scoreBagas = 0;
		
		for (int i = 0; i < 3; i++) {
			String a = abu[i];
			String b = bagas[i];
			
			if (a.equals(b)) {
				continue;
			}
			
			if ((a.equals("B") && b.equals("G")) ||
	            (a.equals("G") && b.equals("K")) ||
	            (a.equals("K") && b.equals("B"))) {
				scoreAbu++;
			} else {
				scoreBagas++;
			}
		}
		
		if (scoreAbu > scoreBagas) {
			System.out.println("Abu");
		} else if (scoreBagas > scoreAbu) {
			System.out.println("Bagas");
		} else {
			System.out.println("Seri");
		}
		
		input.close();
		
	}

}