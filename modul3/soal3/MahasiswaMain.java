package soal3;

import java.util.List;
import java.util.Scanner;

// Interaktif, menampilkan menu, menerima input
public class MahasiswaMain {
    private static final Scanner sc = new Scanner(System.in);
    private static final ManajemenMahasiswa manajemen = new ManajemenMahasiswa();

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            tampilMenu();
            int pilihan = bacaPilihan();
            switch (pilihan) {
                case 1:
                    aksiTambah();
                    break;
                case 2:
                    aksiHapus();
                    break;
                case 3:
                    aksiCari();
                    break;
                case 4:
                    aksiTampil();
                    break;
                case 0:
                    manajemen.clear();
                    System.out.println("Terima kasih!");
                    running = false;
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        }
        sc.close();
    }

    // Menu
    private static void tampilMenu() {
        System.out.println("Menu:");
        System.out.println("1. Tambah Mahasiswa");
        System.out.println("2. Hapus Mahasiswa berdasarkan NIM");
        System.out.println("3. Cari Mahasiswa berdasarkan NIM");
        System.out.println("4. Tampilkan Daftar Mahasiswa");
        System.out.println("0. Keluar");
        System.out.print("Pilihan: ");
    }
    
    // Method Pengubah String ke Integer dan Validasi jika input huruf
    private static int bacaPilihan() {
        String line = sc.nextLine().trim();
        try {
            return Integer.parseInt(line);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    // Method Tambah
    private static void aksiTambah() {
    	// Tambah Nama
        System.out.print("Masukkan Nama Mahasiswa: ");
        String nama = sc.nextLine().trim();
        
        while (nama.isEmpty()) {
            System.out.print("Nama tidak boleh kosong. Masukkan Nama Mahasiswa: ");
            nama = sc.nextLine().trim();
        }

        // Tambah NIM
        System.out.print("Masukkan NIM Mahasiswa (harus unik): ");
        String nim = sc.nextLine().trim();
        
        while (nim.isEmpty() || manajemen.nimExists(nim)) {
            if (nim.isEmpty()) {
                System.out.print("NIM tidak boleh kosong. Masukkan NIM Mahasiswa (harus unik): ");
            } else {
                System.out.print("NIM sudah ada. Masukkan NIM Mahasiswa yang lain (harus unik): ");
            }
            nim = sc.nextLine().trim();
        }

        // Feedback Berhasil Tambah
        boolean sukses = manajemen.tambahMahasiswa(nama, nim);
        if (sukses) {
            System.out.println("Mahasiswa " + nama + " ditambahkan.");
        }
    }

    // Method Hapus
    private static void aksiHapus() {
    	// Feedback jika kosong
        if (manajemen.isEmpty()) {
            System.out.println("Daftar mahasiswa kosong. Tidak ada yang bisa dihapus.");
            return;
        }
        
        // Hapus NIM
        System.out.print("Masukkan NIM Mahasiswa yang akan dihapus: ");
        String nim = sc.nextLine().trim();
        
        if (nim.isEmpty()) {
            System.out.println("NIM kosong. Batalkan operasi hapus.");
            return;
        }
        
        // Feedback Berhasil Hapus
        boolean sukses = manajemen.hapusMahasiswa(nim);
        if (sukses) {
            System.out.println("Mahasiswa dengan NIM " + nim + " dihapus.");
        } else {
            System.out.println("Mahasiswa dengan NIM " + nim + " tidak ditemukan.");
        }
    }

    // Method Cari
    private static void aksiCari() {
        if (manajemen.isEmpty()) {
            System.out.println("Daftar mahasiswa kosong.");
            return;
        }
        
        // Cari NIM
        System.out.print("Masukkan NIM yang dicari: ");
        String nim = sc.nextLine().trim();
        
        if (nim.isEmpty()) {
            System.out.println("NIM kosong. Batalkan pencarian.");
            return;
        }
        
     // Feedback Berhasil Cari
        Mahasiswa m = manajemen.cariMahasiswa(nim);
        if (m != null) {
            System.out.println("Data Mahasiswa ditemukan:");
            System.out.println("NIM: " + m.getNim() + ", Nama: " + m.getNama());
        } else {
            System.out.println("Mahasiswa dengan NIM " + nim + " tidak ditemukan.");
        }
    }
    
    // Method Tampilkan Seluruh Daftar
    private static void aksiTampil() {
        System.out.println("Daftar Mahasiswa:");
        List<Mahasiswa> semua = manajemen.getAll();
        if (semua.isEmpty()) {
            System.out.println("(kosong)");
            return;
        }
        for (Mahasiswa m : semua) {
            System.out.println(m.toString());
        }
    }
}