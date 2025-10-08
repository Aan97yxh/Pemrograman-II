package praktikum2.soal1;

public class BuahMain {
    public static void main(String[] args) {
        Buah apel = new Buah("Apel", 0.4, 7000.0, 40.0);
        Buah mangga = new Buah("mangga", 0.2, 3500, 15.0);
        Buah alpukat = new Buah("alpukat", 0.25, 10000.0, 12.0);

        apel.tampilkanInfo();
        mangga.tampilkanInfo();
        alpukat.tampilkanInfo();
    }
}