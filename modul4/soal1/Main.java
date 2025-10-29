package soal1;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		System.out.print("Nama Hewan Peliharaan: ");
        String namaHewan = scan.nextLine();
        System.out.print("Ras: ");
        String rasHewan = scan.nextLine();

        HewanPeliharaan hewan1 = new HewanPeliharaan(namaHewan, rasHewan);
        hewan1.display();
        scan.close();
	}
}