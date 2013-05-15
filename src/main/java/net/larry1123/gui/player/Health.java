package net.larry1123.gui.player;

import javax.swing.JTextField;

import net.canarymod.api.entity.living.humanoid.Player;
import net.larry1123.gui.tick.TickUpdate;

public class Health extends JTextField implements TickUpdate {

    private final Player player;

    public Health(Player player) {
        this.player = player;
        onTickUpdate();
    }

    @Override
    public void onTickUpdate() {
        setText("" + player.getHealth());
    }

}
