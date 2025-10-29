package soal2;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Pilih jenis hewan yang ingin diinputkan:");
        System.out.println("1 = Kucing");
        System.out.println("2 = Anjing");
        System.out.print("Masukkan pilihan: ");
        int pilihan = scan.nextInt();
        scan.nextLine();
        
        if(pilihan == 1) {
        	System.out.print("Nama hewan peliharaan: ");
            String nama = scan.nextLine();
            System.out.print("Ras: ");
            String ras = scan.nextLine();
            System.out.print("Warna Bulu: ");
            String warnaBulu = scan.nextLine();
            
            Kucing kucing1 = new Kucing(nama, ras, warnaBulu);
            kucing1.displayDetailKucing();
        }
        
        else if(pilihan == 2) {
        	System.out.print("Nama hewan peliharaan: ");
            String nama = scan.nextLine();
            System.out.print("Ras: ");
            String ras = scan.nextLine();
            System.out.print("Warna Bulu: ");
            String warnaBulu = scan.nextLine();
            System.out.print("Kemampuan: ");
            String inputKemampuan = scan.nextLine();
            String[] kemampuan = inputKemampuan.split(", ");
            
            Anjing anjing1 = new Anjing(nama, ras, warnaBulu, kemampuan);
            anjing1.displayDetailAnjing();
        }
        
        else {
        	System.out.println("Pilihan tidak valid.");
        }
        
        scan.close();
	}
}