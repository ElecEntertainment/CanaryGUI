package net.larry1123.gui.player;

import javax.swing.JTextField;

import net.larry1123.gui.PlayerSettings;
import net.larry1123.gui.updaters.Reload;
import net.larry1123.gui.updaters.Reset;
import net.larry1123.gui.updaters.Save;

public class ArrowCount extends JTextField implements Reload, Save, Reset {

    private final PlayerSettings playerSettings;

    public ArrowCount(PlayerSettings playerSettings) {
        this.playerSettings = playerSettings;
        this.playerSettings.getUpdater().addUpdater(this);
    }

    @Override
    public void reset() {
        this.setText("" + playerSettings.getPlayer().getArrowCountInEntity());
    }

    @Override
    public void save() {
        playerSettings.getPlayer().setArrowCountInEntity(Integer.parseInt(getText()));
    }

    @Override
    public void reload() {
        reset();
    }

}
