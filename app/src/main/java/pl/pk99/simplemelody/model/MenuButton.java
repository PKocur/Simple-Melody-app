package pl.pk99.simplemelody.model;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;

//Klasa przycisku
public class MenuButton extends androidx.appcompat.widget.AppCompatButton {
    private int buttonID;

    public int getButtonID() {
        return buttonID;
    }

    public void setButtonID(int buttonID) {
        this.buttonID = buttonID;
    }

    public MenuButton(Context context) {
        super(context);
    }

    public MenuButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setActive (boolean enable, int colorEnabled, int colorDisabled) {
        int textColor = enable ? Color.WHITE : Color.BLACK;
        int color = enable ? colorEnabled : colorDisabled;
        setBackgroundTintList(getResources().getColorStateList(color));
        setTextColor(textColor);
        setEnabled(enable);
    }

    public void setColor (int color) {
        setBackgroundTintList(getResources().getColorStateList(color));
    }
}
