package pl.pk99.simplemelody;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    MenuButtons[] noteButtons;
    MenuButtons[] menuButtons;
    SoundManager soundManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        soundManager = new SoundManager();
        noteButtons = new MenuButtons[6];
        menuButtons = new MenuButtons[2];

        for (int x = 0; x < noteButtons.length; x++) {
            String buttonID = "button" + (x + 1);
            int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
            noteButtons[x] = findViewById(resID);
            noteButtons[x].setButtonID(x);
            noteButtons[x].setOnClickListener(this);
        }

        for (int x = 0; x < menuButtons.length; x++) {
            String buttonID = "menuButton" + (x + 1);
            int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
            menuButtons[x] = findViewById(resID);
            menuButtons[x].setButtonID(x + 6);
            menuButtons[x].setOnClickListener(this);
        }
    }

    public void onClick(View view) {
        MenuButtons MenuButtons = (MenuButtons) view;
        int buttonID = MenuButtons.getButtonID();

        if (buttonID < 6) {
            soundManager.playSound(MenuButtons.getButtonID(), getApplicationContext());
            changeNoteButtonsColors(MenuButtons);
        } else {
            switch (buttonID) {
                case 6:
                    boolean recording =  soundManager.recordMelody(getApplicationContext());
                    int color = recording ? R.color.colorRedDark : R.color.colorGreen;
                    MenuButtons.setBackgroundTintList(getResources().getColorStateList(color));
                    break;
                case 7:
                    soundManager.loadMelody(getApplicationContext());
            }
        }
    }

    public void changeNoteButtonsColors(MenuButtons MenuButtons) {
        for (MenuButtons btn : noteButtons) {
            btn.setBackgroundTintList(getResources().getColorStateList(R.color.colorRed));
        }
        MenuButtons.setBackgroundTintList(getResources().getColorStateList(R.color.colorGreen));
    }
}
