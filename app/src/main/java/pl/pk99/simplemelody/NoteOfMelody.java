package pl.pk99.simplemelody;

//Klasa nuty melodii
class NoteOfMelody {
    //Nuta
    private char note;
    //Opóźnienie nuty
    private long delay;

    char getNote() {
        return note;
    }

    long getDelay() {
        return delay;
    }

    NoteOfMelody(char note, long delay) {
        this.note = note;
        this.delay = delay;
    }
}
