package net.larry1123.gui.player;

import javax.swing.JTextField;

import net.larry1123.gui.PlayerSettings;
import net.larry1123.gui.updaters.Reload;
import net.larry1123.gui.updaters.Reset;
import net.larry1123.gui.updaters.Save;

public class Health extends JTextField implements Reload, Save, Reset {

    private final PlayerSettings playerSettings;

    public Health(PlayerSettings playerSettings) {
        this.playerSettings = playerSettings;
        this.playerSettings.getUpdater().addUpdater(this);
        reset();
    }

    @Override
    public void reset() {
        setText("" + playerSettings.getPlayer().getHealth());
    }

    @Override
    public void save() {
        int health = Integer.parseInt(this.getText());
        if (health < Short.MAX_VALUE) {
            playerSettings.getPlayer().setHealth(health);
        } else {
            playerSettings.getPlayer().setHealth(Short.MAX_VALUE);
        }
    }

    @Override
    public void reload() {
        reset();
    }

}
