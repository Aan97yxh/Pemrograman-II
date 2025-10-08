package praktikum2.soal3;

// Jika file bernama Pegawai.java maka kelas publik harus bernama Pegawai.
// public class Employee {
public class Pegawai {
    public String nama;

    // Mengubah tipe menjadi String karena getAsal() mengembalikan String
    // public char asal;
    public String asal;
    
    public String jabatan;
    public int umur;

    public String getNama() {
        return nama;
    }
    
    public String getAsal() {
        return asal;
    }
    

    // Kesalahan: tak ada parameter tapi menggunakan variabel j.
    // public void setJabatan() {
    //     this.jabatan = j;
    // }
    public void setJabatan(String j) {
        this.jabatan = j;
    }
}