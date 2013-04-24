package net.larry1123.gui.player;

import javax.swing.JTextField;

import net.canarymod.api.entity.living.humanoid.Player;
import net.larry1123.gui.Update;

public class DisplayName extends JTextField implements Update {

    private final Player player;

    public DisplayName(Player player) {
	this.player = player;
	update();
    }

    @Override
    public void a() {
	update();
    }

    @Override
    public void update() {
	setText(player.getDisplayName());
    }

}