package pl.pk99.simplemelody;

//Klasa melodii
class Melody {
    //Sekwencja nut zapisana w tekście
    private String melody;
    //Opóźnienia w graniu danych nut
    private long[] delays;

    String getMelody() {
        return melody;
    }

    long[] getDelays() {
        return delays;
    }

    Melody(String melody, long[] delays) {
        this.melody = melody;
        this.delays = delays;
    }
}
