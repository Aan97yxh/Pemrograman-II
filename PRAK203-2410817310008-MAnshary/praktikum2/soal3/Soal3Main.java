package praktikum2.soal3;

public class Soal3Main {
    public static void main(String[] args) {
        Pegawai p1 = new Pegawai();

        // Kesalahan: kekurangan titik koma
        // p1.nama = "Roi"
        p1.nama = "Roi";
        
        p1.asal = "Kingdom of Orvel";
        p1.setJabatan("Assasin");
        
        // Tambahan kode untuk menginisialisasi variabel umur yang sebelumnya belum ada nilainya
        p1.umur = 17;

        // Menyesuaikan agar output menjadi sama dengan yang diminta
        // System.out.println("Nama Pegawai: " + p1.getNama());
        System.out.println("Nama: " + p1.getNama());
        
        System.out.println("Asal: " + p1.getAsal());
        System.out.println("Jabatan: " + p1.jabatan);
        
        // Menyesuaikan agar output menjadi sama dengan yang diminta
        // System.out.println("Umur: " + p1.umur);
        System.out.println("Umur: " + p1.umur + " tahun");
    }
}