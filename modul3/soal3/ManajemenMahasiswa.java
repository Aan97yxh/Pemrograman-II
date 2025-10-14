package soal3;

import java.util.ArrayList;
import java.util.List;

public class ManajemenMahasiswa {
    private final ArrayList<Mahasiswa> daftar;

    public ManajemenMahasiswa() {
        this.daftar = new ArrayList<>();
    }

    public boolean isEmpty() {
        return daftar.isEmpty();
    }

    public int ukuran() {
        return daftar.size();
    }

    // Cek NIM
    public boolean nimExists(String nim) {
        for (Mahasiswa m : daftar) {
            if (m.getNim().equals(nim)) return true;
        }
        return false;
    }

    // Tambah Mahasiswa jika NIM unik
    public boolean tambahMahasiswa(String nama, String nim) {
        if (nimExists(nim)) return false;
        Mahasiswa m = new Mahasiswa(nama, nim);
        daftar.add(m);
        return true;
    }

    // Hapus Mahasiswa jika NIM ditemukan
    public boolean hapusMahasiswa(String nim) {
        for (int i = 0; i < daftar.size(); i++) {
            if (daftar.get(i).getNim().equals(nim)) {
                daftar.remove(i);
                return true;
            }
        }
        return false;
    }

    // Cari mahasiswa berdasarkan NIM
    public Mahasiswa cariMahasiswa(String nim) {
        for (Mahasiswa m : daftar) {
            if (m.getNim().equals(nim)) return m;
        }
        return null;
    }

    // Mengembalikan salinan daftar mahasiswa
    public List<Mahasiswa> getAll() {
        return new ArrayList<>(daftar);
    }

    // Method Hapus Array
    public void clear() {
        daftar.clear();
    }
}