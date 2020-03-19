package pl.pk99.simplemelody;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ButtonNote[] btnNotes;
    SoundManager soundManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        soundManager = new SoundManager();
        btnNotes = new ButtonNote[6];

        for (int x = 0; x < btnNotes.length; x++) {
            String buttonID = "btnNote" + (x + 1);
            int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
            btnNotes[x] = findViewById(resID);
            btnNotes[x].setSoundID(x);
            btnNotes[x].setOnClickListener(this);
        }
    }

    public void onClick(View view) {
        ButtonNote buttonNote = (ButtonNote) view;
        soundManager.playSound(buttonNote.getSoundID(), getApplicationContext());
        changeButtonsColors(buttonNote);
    }

    public void changeButtonsColors (ButtonNote buttonNote) {
        for (ButtonNote btnNote : btnNotes) {
            btnNote.setBackgroundTintList(getResources().getColorStateList(R.color.colorRed));
        }
        buttonNote.setBackgroundTintList(getResources().getColorStateList(R.color.colorGreen));
    }
}
