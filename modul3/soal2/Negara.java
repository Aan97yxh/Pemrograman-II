package soal2;

public class Negara {
    private String nama;
    private String jenisKepemimpinan;
    private String namaPemimpin;
    private Integer tanggal;
    private Integer bulan;
    private Integer tahun;

    public Negara(String nama, String jenisKepemimpinan, String namaPemimpin,
                  Integer tanggal, Integer bulan, Integer tahun) {
        this.nama = nama;
        this.jenisKepemimpinan = jenisKepemimpinan;
        this.namaPemimpin = namaPemimpin;
        this.tanggal = tanggal;
        this.bulan = bulan;
        this.tahun = tahun;
    }

    // Getter
    public String getNama() { return nama; }
    public String getJenisKepemimpinan() { return jenisKepemimpinan; }
    public String getNamaPemimpin() { return namaPemimpin; }
    public Integer getTanggal() { return tanggal; }
    public Integer getBulan() { return bulan; }
    public Integer getTahun() { return tahun; }
    
    // Jika true maka output akan dijalankan 
    public boolean hasKemerdekaan() {
        return tanggal != null && bulan != null && tahun != null;
    }
    
    public String setGelar() {
        String jk = jenisKepemimpinan == null ? "" : jenisKepemimpinan.trim().toLowerCase();
        switch (jk) {
            case "monarki":
                return "Raja";
            case "presiden":
                return "Presiden";
            case "perdana menteri":
                return "Perdana Menteri";
        }
        return "";
    }

    public void TampilkanInfo(String bulanName) {
        System.out.println("Negara " + nama + " mempunyai " + setGelar() +
                           " bernama " + namaPemimpin);
        if (hasKemerdekaan()) {
            System.out.println("Deklarasi Kemerdekaan pada Tanggal " +
                               tanggal + " " + bulanName + " " + tahun);
        }
        System.out.println();
    }
}