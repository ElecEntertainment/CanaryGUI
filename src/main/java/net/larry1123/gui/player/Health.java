package net.larry1123.gui.player;

import javax.swing.JTextField;

import net.canarymod.api.entity.living.humanoid.Player;

public class Health extends JTextField implements Reload, Save, Reset {

    private final Player player;

    public Health(Player player) {
        Reloader.addUpdater(this);
        Saver.addSaver(this);
        Reseter.addReset(this);
        this.player = player;
        reset();
    }

    @Override
    public void reset() {
        setText("" + player.getHealth());
    }

    @Override
    public void save() {
        player.setHealth(Integer.parseInt(this.getText()));
    }

}
