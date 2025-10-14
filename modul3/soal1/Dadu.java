package soal1;

import java.util.Random;

public class Dadu {
    private int nilai;
    private static final Random RNG = new Random();

    // Konstruktor
    public Dadu() {
        acakNilai();
    }

    // Method RNG
    private void acakNilai() {
        this.nilai = RNG.nextInt(6) + 1; // angka random dari 1-6
    }
    
    public int getNilai() {
        return nilai;
    }
}