package pl.pk99.simplemelody;

import android.content.Context;

import pl.pk99.simplemelody.model.MenuButton;

//Klasa zarządza GUI (aktywuje i dezaktywuje przyciski na podstawie aktualnego
//stanu aplikacji)
class AppGuiManager {
    private MenuButtonsManager menuButtonsManager;
    private SoundManager soundManager;
    private Context context;

    AppGuiManager(MenuButtonsManager menuButtonsManager, SoundManager soundManager, Context context) {
        this.menuButtonsManager = menuButtonsManager;
        this.soundManager = soundManager;
        this.context = context;
        appStart();
    }

    private void appStart() {
        menuButtonsManager.setActiveMenuButtons(soundManager.melodyExists(), 1,2);
    }

    void noteClicked (MenuButton noteButton) {
        menuButtonsManager.noteButtonToBlue(noteButton, context);
    }

    void recordingStart () {
        menuButtonsManager.setColorMenuButton(0, R.color.colorRed);
        menuButtonsManager.setActiveMenuButtons(false, 1,2);
    }

    void recordingStopped () {
        menuButtonsManager.setColorMenuButton(0, R.color.colorEnabled);
        menuButtonsManager.setActiveMenuButtons(true, 1,2);
    }

    void melodyStart () {
        menuButtonsManager.setActiveMenuButtons(false, 0,1,2);
        menuButtonsManager.setActiveNoteButtons(false, 0,1,2,3,4,5);
    }

    void melodyStopped () {
        menuButtonsManager.setActiveMenuButtons(true, 0,1,2);
        menuButtonsManager.setActiveNoteButtons(true, 0,1,2,3,4,5);
    }
}
