package net.larry1123.gui.player;

import javax.swing.JTextField;

import net.canarymod.api.entity.living.humanoid.Player;

public class DisplayName extends JTextField implements Reload, Save, Reset {

    private final Player player;

    public DisplayName(Player player) {
        Reloader.addUpdater(this);
        Saver.addSaver(this);
        Reseter.addReset(this);
        this.player = player;
        reset();
    }

    @Override
    public void save() {
        player.setDisplayName(this.getText());
    }

    @Override
    public void reset() {
        setText(player.getDisplayName());
    }

}
