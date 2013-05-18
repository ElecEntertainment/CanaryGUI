package net.larry1123.gui.player;

import javax.swing.JTextField;

import net.larry1123.gui.PlayerSettings;
import net.larry1123.gui.updaters.Reload;
import net.larry1123.gui.updaters.Reset;
import net.larry1123.gui.updaters.Save;

public class DisplayName extends JTextField implements Reload, Save, Reset {

    private final PlayerSettings playerSettings;

    public DisplayName(PlayerSettings playerSettings) {
        this.playerSettings = playerSettings;
        this.playerSettings.getUpdater().addUpdater(this);
        reset();
    }

    @Override
    public void save() {
        playerSettings.getPlayer().setDisplayName(this.getText());
    }

    @Override
    public void reset() {
        setText(playerSettings.getPlayer().getDisplayName());
    }

    @Override
    public void reload() {
        reset();
    }

}
