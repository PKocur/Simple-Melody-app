package pl.pk99.simplemelody;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

public class ButtonNote extends androidx.appcompat.widget.AppCompatButton {
    private int soundID;

    public int getSoundID() {
        return soundID;
    }

    public void setSoundID(int soundID) {
        this.soundID = soundID;
    }

    public ButtonNote (Context context) {
        super(context);
    }

    public ButtonNote (Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
