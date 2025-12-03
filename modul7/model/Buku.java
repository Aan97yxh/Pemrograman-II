package model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class Buku implements Serializable {
    private Integer bukuId;
    private String judul;
    private String penulis;
    private BigDecimal harga;
    private Integer stok;

    public Buku() { }

    public Buku(Integer bukuId, String judul, String penulis, BigDecimal harga, Integer stok) {
        this.bukuId = bukuId;
        this.judul = judul;
        this.penulis = penulis;
        this.harga = harga;
        this.stok = stok;
    }

    public Integer getBukuId() {
        return bukuId;
    }

    public void setBukuId(Integer bukuId) {
        this.bukuId = bukuId;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getPenulis() {
        return penulis;
    }

    public void setPenulis(String penulis) {
        this.penulis = penulis;
    }

    public BigDecimal getHarga() {
        return harga;
    }

    public void setHarga(BigDecimal harga) {
        this.harga = harga;
    }

    public Integer getStok() {
        return stok;
    }

    public void setStok(Integer stok) {
        this.stok = stok;
    }

    @Override
    public String toString() {
        // Berguna saat ditampilkan dalam ComboBox — tampilkan judul dan (opsional) stok
        return judul == null ? "" : judul + (stok != null ? " (stok: " + stok + ")" : "");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
			return true;
		}
        if (!(o instanceof Buku)) {
			return false;
		}
        Buku buku = (Buku) o;
        return Objects.equals(bukuId, buku.bukuId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bukuId);
    }
}
