package net.larry1123.gui;

import java.util.Vector;

import javax.swing.JList;

import net.canarymod.Canary;
import net.minecraft.server.IUpdatePlayerListBox;
import net.minecraft.server.MinecraftServer;

@SuppressWarnings("serial")
public class CanaryGUIPlayerListBox extends JList<String> implements
IUpdatePlayerListBox {

    private int b = 0;

    private final Vector<String> playerList = new Vector<String>();

    public CanaryGUIPlayerListBox(MinecraftServer minecraftServer) {
	minecraftServer.a(this);
	addListSelectionListener(new CanaryGUIPlayerListSlectionListener(this));
    }

    @Override
    public void a() {
	updatePlayerList();
    }

    public Vector<String> getPlayerList() {
	return playerList;
    }

    public void updatePlayerList() {
	if ((b++ % 20) == 0) {
	    playerList.clear();
	    for (String player : Canary.getServer().getPlayerNameList()) {
		playerList.add(player);
	    }
	    this.setListData(playerList);
	}
    }

}
