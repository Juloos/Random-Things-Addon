package btw.community.randomthings;

import java.util.Arrays;
import java.util.Map;

import org.lwjgl.input.Keyboard;

import btw.AddonHandler;
import btw.BTWAddon;
import net.minecraft.client.Minecraft;
import net.minecraft.src.GameSettings;
import net.minecraft.src.KeyBinding;
import net.minecraft.src.StatCollector;

public class RandomThingsAddon extends BTWAddon {
    public static boolean f5enabled;
    public static boolean f5Vanillaenabled;

    private static RandomThingsAddon instance;
    public static KeyBinding third_person_key;
    public static KeyBinding first_person_key;
    public static KeyBinding backwards_facing_key;

    private RandomThingsAddon() {
        super("Random Things", "1.0.1", "Rand");
    }

    @Override
    public void initialize() {
        AddonHandler.logMessage(this.getName() + " Version " + this.getVersionString() + " Initializing...");
    }

    @Override
    public void preInitialize() {
        registerProperty("f5_change_enabled", "true", "Set this to false to disable the F5 change.");
        registerProperty("f5_vanilla_enabled", "true", "Set this to false to disable F5 from working.");

    }

    @Override
    public void handleConfigProperties(Map<String, String> propertyValues) {
        f5enabled = Boolean.parseBoolean(propertyValues.get("f5_change_enabled"));
        f5Vanillaenabled = Boolean.parseBoolean(propertyValues.get("f5_change_enabled"));
    }

    public static RandomThingsAddon getInstance() {
        if (instance == null)
            instance = new RandomThingsAddon();
        return instance;
    }

    public void initKeybind(){
        third_person_key = new KeyBinding(StatCollector.translateToLocal("key.randomthings.thirdperson"), Keyboard.KEY_X);
        first_person_key = new KeyBinding(StatCollector.translateToLocal("key.randomthings.firstperson"), Keyboard.KEY_Z);
        backwards_facing_key = new KeyBinding(StatCollector.translateToLocal("key.randomthings.backwardsfacing"), Keyboard.KEY_C);

        GameSettings settings = Minecraft.getMinecraft().gameSettings;
        KeyBinding[] keyBindings = settings.keyBindings;
        keyBindings = Arrays.copyOf(keyBindings, keyBindings.length + 3);
        keyBindings[keyBindings.length - 3] = first_person_key;
        keyBindings[keyBindings.length - 2] = third_person_key;
        keyBindings[keyBindings.length - 1] = backwards_facing_key;
        settings.keyBindings = keyBindings;
    }
}
