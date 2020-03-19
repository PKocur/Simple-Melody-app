package pl.pk99.simplemelody;

import android.content.Context;
import android.util.AttributeSet;

public class MenuButtons extends androidx.appcompat.widget.AppCompatButton {
    private int buttonID;

    public int getButtonID() {
        return buttonID;
    }

    public void setButtonID(int buttonID) {
        this.buttonID = buttonID;
    }

    public MenuButtons(Context context) {
        super(context);
    }

    public MenuButtons(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
