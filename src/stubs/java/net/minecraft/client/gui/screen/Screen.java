package net.minecraft.client.gui.screen;
import net.minecraft.text.Text;
import net.minecraft.client.util.math.MatrixStack;
public class Screen {
    protected int width;
    protected int height;
    public Screen(Text title) {}
    protected void init() {}
    protected void renderBackground(MatrixStack matrices) {}
    public void close() {}
    public <T> T addDrawableChild(T widget) { return widget; }
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {}
}
