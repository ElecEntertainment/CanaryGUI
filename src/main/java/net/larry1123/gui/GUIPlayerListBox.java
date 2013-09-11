package net.larry1123.gui;

import java.util.Collections;
import java.util.Vector;

import javax.swing.JList;

import net.canarymod.Canary;

@SuppressWarnings("serial")
public class GUIPlayerListBox extends JList implements net.larry1123.gui.tick.TickUpdate {

    private int b = 0;

    private final Vector<String> playerList = new Vector<String>();

    public GUIPlayerListBox() {
        addListSelectionListener(new GUIPlayerListSlectionListener(this));
    }

    @Override
    public void onTickUpdate() {
        updatePlayerList();
    }

    public Vector<String> getPlayerList() {
        return playerList;
    }

    public void updatePlayerList() {
        if ((b++ % 20) == 0) {
            playerList.clear();
            Collections.addAll(playerList, Canary.getServer().getPlayerNameList());
            this.setListData(playerList);
        }
    }

}
