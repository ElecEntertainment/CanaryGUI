package net.larry1123.gui.player;

import javax.swing.JTextField;

import net.canarymod.api.entity.living.humanoid.Player;

public class Health extends JTextField implements Reloader {

    private final Player player;

    public Health(Player player) {
        Reload.addUpdater(this);
        this.player = player;
        reset();
    }

    @Override
    public void reset() {
        setText("" + player.getHealth());
    }

}
