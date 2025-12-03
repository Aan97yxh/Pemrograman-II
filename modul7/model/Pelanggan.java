package model;

import java.io.Serializable;
import java.util.Objects;

public class Pelanggan implements Serializable {
    private Integer pelangganId;
    private String nama;
    private String email;
    private String telepon;

    public Pelanggan() { }

    public Pelanggan(Integer pelangganId, String nama, String email, String telepon) {
        this.pelangganId = pelangganId;
        this.nama = nama;
        this.email = email;
        this.telepon = telepon;
    }

    public Integer getPelangganId() {
        return pelangganId;
    }

    public void setPelangganId(Integer pelangganId) {
        this.pelangganId = pelangganId;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelepon() {
        return telepon;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }

    @Override
    public String toString() {
        // Agar ComboBox menampilkan nama pelanggan
        return nama == null ? "" : nama;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
			return true;
		}
        if (!(o instanceof Pelanggan)) {
			return false;
		}
        Pelanggan that = (Pelanggan) o;
        return Objects.equals(pelangganId, that.pelangganId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pelangganId);
    }
}
