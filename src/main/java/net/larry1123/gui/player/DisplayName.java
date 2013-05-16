package net.larry1123.gui.player;

import javax.swing.JTextField;

import net.canarymod.api.entity.living.humanoid.Player;

public class DisplayName extends JTextField implements Reloader {

    private final Player player;

    public DisplayName(Player player) {
        Reload.addUpdater(this);
        this.player = player;
        reset();
    }

    public void save() {
        player.setDisplayName(this.getText());
    }

    public void reset() {
        setText(player.getDisplayName());
    }

}
