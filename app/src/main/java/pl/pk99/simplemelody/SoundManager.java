package pl.pk99.simplemelody;

import android.content.Context;
import android.media.MediaPlayer;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class SoundManager implements MediaPlayer.OnCompletionListener {
    private int[] sounds = {
            R.raw.note1,
            R.raw.note2,
            R.raw.note3,
            R.raw.note4,
            R.raw.note5,
            R.raw.note6
    };

    boolean recording = false;
    String melody = "";

    static final int MAX_MELODY_SIZE = 150;

    void playSound(int soundID, Context context) {
        MediaPlayer mp = MediaPlayer.create(context, sounds[soundID]);
        mp.start();
        mp.setOnCompletionListener(this);
        if(recording) {
            melody += (char)(65 + soundID);
        }
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        mp.release();
    }

    boolean recordMelody(Context context) {
        recording = !recording;
        if(!recording) {
            saveMelody(context, melody);
            melody = "";
        }
        return recording;
    }

    void loadMelody(Context context) {
        try {
            FileInputStream fileIn = context.openFileInput("melody.txt");
            InputStreamReader inputReader = new InputStreamReader(fileIn);

            char[] inputBuffer = new char[MAX_MELODY_SIZE];
            String s = "";
            int charRead;
            while((charRead = inputReader.read(inputBuffer)) > 0) {
                String readString = String.copyValueOf(inputBuffer, 0, charRead);
                s += readString;
            }

            Toast.makeText(context, context.getResources().getString(R.string.your_melody) + s,
                    Toast.LENGTH_LONG).show();
            inputReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveMelody(Context context, String melody) {
        try {
            FileOutputStream fileOut = context.openFileOutput("melody.txt", context.MODE_PRIVATE);
            OutputStreamWriter outputWriter = new OutputStreamWriter(fileOut);
            outputWriter.write(melody);
            outputWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
