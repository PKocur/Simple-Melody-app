package pl.pk99.simplemelody;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Handler;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Klasa zarządza dźwiękami (umożliwia granie melodii, jej zapis i odczyt)
public class SoundManager implements MediaPlayer.OnCompletionListener {
    private int[] sounds = {
            R.raw.note1,
            R.raw.note2,
            R.raw.note3,
            R.raw.note4,
            R.raw.note5,
            R.raw.note6
    };

    private AppGuiManager appGuiManager;
    private Context context;

    private boolean recording = false;

    private String recordedMelody = "";
    private byte recordingNote = 0;
    private long timeDelayTemp = 0;
    private long[] timeDelays = new long[MAX_MELODY_SIZE];

    private static final int MAX_MELODY_SIZE = 150;
    private static final String SAVE_PATH = "melody.txt";

    void setAppGuiManager(AppGuiManager appGuiManager) {
        this.appGuiManager = appGuiManager;
    }

    SoundManager(Context context) {
        this.context = context;
    }

    void playSound(int soundID) {
        MediaPlayer mp = MediaPlayer.create(context, sounds[soundID]);
        mp.start();
        mp.setOnCompletionListener(this);

        if(recording) {
            recordedMelody += (char)(65 + soundID);
            if(recordingNote == 0) {
                timeDelayTemp = System.currentTimeMillis();
                timeDelays[0] = 0;
            } else {
                timeDelays[recordingNote] = System.currentTimeMillis() - timeDelayTemp;
                timeDelayTemp = System.currentTimeMillis();
            }
            recordingNote ++;
        }
    }
    boolean melodyExists () {
        File file = context.getFileStreamPath(SAVE_PATH);
        return file.exists();
    }

    void recordMelody() {
        recording = !recording;
        if(!recording) {
            saveMelody(recordedMelody, timeDelays);
            appGuiManager.recordingStopped();
            resetRecording();
        } else {
            appGuiManager.recordingStart();
        }
    }

    private void resetRecording () {
        Arrays.fill(timeDelays, 0);
        timeDelayTemp = 0;
        recordingNote = 0;
        recordedMelody = "";
    }

    void playMelody (final Melody melody) {
        appGuiManager.melodyStart();

        final Handler handler = new Handler();
        final int[] count = {0};
        final Runnable runnable = new Runnable() {
            public void run() {
                int soundID = ((int) melody.getMelody().charAt(count[0]) - 65);
                playSound(soundID);
                if (count[0]++ < melody.getMelody().length() - 1) {
                    handler.postDelayed(this, melody.getDelays()[count[0]]);
                } else {
                    appGuiManager.melodyStopped();
                }
            }
        };
        handler.post(runnable);
    }

    Melody loadMelody(boolean showMelody) {
        List<NoteOfMelody> notesOfMelodies = loadNotesOfMelody();
        StringBuilder melodyS = new StringBuilder();
        long[] delays = new long[notesOfMelodies.size()];

        for(int x = 0; x < notesOfMelodies.size(); x ++) {
            melodyS.append(notesOfMelodies.get(x).getNote());
            delays[x] = notesOfMelodies.get(x).getDelay();
        }

        if(showMelody) {
            Toast.makeText(context, context.getResources().getString(R.string.your_melody)
                    + " " + melodyS, Toast.LENGTH_LONG).show();
        }

        return new Melody(melodyS.toString(), delays);
    }

    private List<NoteOfMelody> loadNotesOfMelody() {
        List<NoteOfMelody> noteMelodies = new ArrayList<NoteOfMelody>();
        String loadedMelody = "";
        try {
            FileInputStream fileIn = context.openFileInput(SAVE_PATH);
            InputStreamReader inputReader = new InputStreamReader(fileIn);

            char[] inputBuffer = new char[MAX_MELODY_SIZE];
            inputReader.read(inputBuffer);
            String readString = String.copyValueOf(inputBuffer);

            String[] melodyAndTime = readString.split(",");

            int length = melodyAndTime.length - 1;
            length = length % 2 == 0 ? length / 2 : (length + 1) / 2;

            long[] delay = new long[length];
            char[] note = new char[length];

            for(int x = 0; x < melodyAndTime.length - 1; x ++) {
                if(x % 2 == 0) {
                    note[x/2] = melodyAndTime[x].charAt(0);
                } else {
                    delay[x/2] = Integer.parseInt(melodyAndTime[x]);
                }
            }

            for(int x = 0; x < delay.length; x ++) {
                NoteOfMelody noteOfMelody = new NoteOfMelody(note[x], delay[x]);
                noteMelodies.add(noteOfMelody);
            }

            inputReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return noteMelodies;
    }

    private void saveMelody(String melody, long[] timeDelays) {
        try {
            FileOutputStream fileOut = context.openFileOutput(SAVE_PATH,
                    context.MODE_PRIVATE);
            OutputStreamWriter outputWriter = new OutputStreamWriter(fileOut);
            for(int x = 0; x < melody.length(); x++) {
                outputWriter.write(melody.charAt(x));
                outputWriter.write("," + timeDelays[x] + ",");
            }

            outputWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        mp.release();
    }
}
