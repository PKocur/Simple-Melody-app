package pl.pk99.simplemelody;

import android.content.Context;
import android.media.MediaPlayer;

public class SoundManager implements MediaPlayer.OnCompletionListener {
    private int[] sounds = {
            R.raw.note1,
            R.raw.note2,
            R.raw.note3,
            R.raw.note4,
            R.raw.note5,
            R.raw.note6
    };

    void playSound(int soundID, Context context) {
        MediaPlayer mp = MediaPlayer.create(context, sounds[soundID]);
        mp.start();
        mp.setOnCompletionListener(this);
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        mp.release();
    }
}
