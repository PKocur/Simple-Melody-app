package pl.pk99.simplemelody;

import android.content.Context;

//Klasa umożliwia zarządzanie stanem przycisków
class MenuButtonsManager {
    private MenuButton[] noteButtons;
    private MenuButton[] menuButtons;

    MenuButtonsManager(MenuButton[] noteButtons, MenuButton[] menuButtons) {
        this.noteButtons = noteButtons;
        this.menuButtons = menuButtons;
    }

    void setColorMenuButton (int index, int color) {
        menuButtons[index].setColor(color);
    }

    void setActiveMenuButtons(boolean enable, int... indexes) {
        for (int index : indexes) {
            menuButtons[index].setActive(enable, R.color.colorEnabled, R.color.colorDisabled);
        }
    }

    void setActiveNoteButtons(boolean enable, int... indexes) {
        for (int index : indexes) {
            noteButtons[index].setActive(enable, R.color.colorRed, R.color.colorRedDark);
        }
    }

    void noteButtonToGreen(MenuButton menuButton, Context context) {
        noteButtonsToRed(context);
        menuButton.setBackgroundTintList(context.getResources().getColorStateList(R.color.colorEnabled));
    }

    private void noteButtonsToRed(Context context) {
        for (MenuButton btn : noteButtons) {
            btn.setBackgroundTintList(context.getResources().getColorStateList(R.color.colorRed));
        }
    }
}
