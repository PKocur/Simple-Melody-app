package pl.pk99.simplemelody;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import pl.pk99.simplemelody.model.MenuButton;

//Klasa zarządza activity aplikacji
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    MenuButton[] noteButtons;
    MenuButton[] menuButtons;
    MenuButtonsManager menuButtonsManager;
    SoundManager soundManager;
    AppGuiManager appGuiManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        noteButtons = new MenuButton[6];
        menuButtons = new MenuButton[3];

        for (int x = 0; x < noteButtons.length; x++) {
            String buttonID = "noteButton" + (x + 1);
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

        menuButtonsManager = new MenuButtonsManager(noteButtons, menuButtons);
        soundManager = new SoundManager(getApplicationContext());
        appGuiManager = new AppGuiManager
                (menuButtonsManager, soundManager, getApplicationContext());
        soundManager.setAppGuiManager(appGuiManager);
    }

    public void onClick(View view) {
        MenuButton MenuButton = (MenuButton) view;
        int buttonID = MenuButton.getButtonID();

        if (buttonID < 6) {
            soundManager.playSound(MenuButton.getButtonID());
            appGuiManager.noteClicked(MenuButton);

        } else {
            switch (buttonID) {
                case 6:
                    soundManager.recordMelody();
                    break;
                case 7:
                    soundManager.loadMelody(true);
                    break;
                case 8:
                    soundManager.playMelody(soundManager.loadMelody(false));
                    break;
            }
        }
    }
}
