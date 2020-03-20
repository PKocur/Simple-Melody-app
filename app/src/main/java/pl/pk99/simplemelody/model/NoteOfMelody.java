package pl.pk99.simplemelody.model;

//Klasa nuty melodii
public class NoteOfMelody {
    //Nuta
    private char note;
    //Opóźnienie nuty
    private long delay;

    public char getNote() {
        return note;
    }

    public long getDelay() {
        return delay;
    }

    public NoteOfMelody(char note, long delay) {
        this.note = note;
        this.delay = delay;
    }
}
