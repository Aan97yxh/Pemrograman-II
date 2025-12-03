package model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Objects;

public class Penjualan implements Serializable {
    private Integer penjualanId;
    private Integer jumlah;
    private BigDecimal totalHarga;
    private Date tanggal;
    private Integer pelangganId; // nullable
    private Integer bukuId;

    public Penjualan() { }

    public Penjualan(Integer penjualanId, Integer jumlah, BigDecimal totalHarga, Date tanggal, Integer pelangganId, Integer bukuId) {
        this.penjualanId = penjualanId;
        this.jumlah = jumlah;
        this.totalHarga = totalHarga;
        this.tanggal = tanggal;
        this.pelangganId = pelangganId;
        this.bukuId = bukuId;
    }

    public Integer getPenjualanId() {
        return penjualanId;
    }

    public void setPenjualanId(Integer penjualanId) {
        this.penjualanId = penjualanId;
    }

    public Integer getJumlah() {
        return jumlah;
    }

    public void setJumlah(Integer jumlah) {
        this.jumlah = jumlah;
    }

    public BigDecimal getTotalHarga() {
        return totalHarga;
    }

    public void setTotalHarga(BigDecimal totalHarga) {
        this.totalHarga = totalHarga;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public Integer getPelangganId() {
        return pelangganId;
    }

    public void setPelangganId(Integer pelangganId) {
        this.pelangganId = pelangganId;
    }

    public Integer getBukuId() {
        return bukuId;
    }

    public void setBukuId(Integer bukuId) {
        this.bukuId = bukuId;
    }

    @Override
    public String toString() {
        return "Penjualan{" +
                "penjualanId=" + penjualanId +
                ", jumlah=" + jumlah +
                ", totalHarga=" + totalHarga +
                ", tanggal=" + tanggal +
                ", pelangganId=" + pelangganId +
                ", bukuId=" + bukuId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
			return true;
		}
        if (!(o instanceof Penjualan)) {
			return false;
		}
        Penjualan that = (Penjualan) o;
        return Objects.equals(penjualanId, that.penjualanId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(penjualanId);
    }
}
