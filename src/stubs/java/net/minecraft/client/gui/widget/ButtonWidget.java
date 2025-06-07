package net.minecraft.client.gui.widget;
import net.minecraft.text.Text;
public class ButtonWidget extends ClickableWidget {
    public static Builder builder(Text text, java.util.function.Consumer<ButtonWidget> onPress) { return new Builder(); }
    public void setMessage(Text text) {}
    public static class Builder {
        public Builder dimensions(int x, int y, int width, int height) { return this; }
        public ButtonWidget build() { return new ButtonWidget(); }
    }
}
