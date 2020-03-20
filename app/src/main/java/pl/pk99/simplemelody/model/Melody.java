package pl.pk99.simplemelody.model;

//Klasa melodii
public class Melody {
    //Sekwencja nut zapisana w tekście
    private String melody;
    //Opóźnienia w graniu danych nut
    private long[] delays;

    public String getMelody() {
        return melody;
    }

    public long[] getDelays() {
        return delays;
    }

    public Melody(String melody, long[] delays) {
        this.melody = melody;
        this.delays = delays;
    }
}
