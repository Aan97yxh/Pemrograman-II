package Model;

public class Mahasiswa {
    private int id;
    private String nama;
    private String nim;

    public Mahasiswa(int id, String nama, String nim) {
        setId (id);
        setNama (nama);
        setNim (nim);
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }

    public String getNim() { return nim; }
    public void setNim(String nim) { this.nim = nim; }
}