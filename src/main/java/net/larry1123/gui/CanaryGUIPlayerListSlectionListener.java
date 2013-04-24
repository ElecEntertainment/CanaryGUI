package net.larry1123.gui;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import net.canarymod.Canary;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.logger.Logman;

public class CanaryGUIPlayerListSlectionListener implements
ListSelectionListener {

    private final CanaryGUIPlayerListBox playerBox;
    private PlayerSettings playersettingsbox;
    public static Boolean isRunning = false;

    public CanaryGUIPlayerListSlectionListener(CanaryGUIPlayerListBox playerbox) {
	playerBox = playerbox;
    }

    public PlayerSettings getPlayerSettings() {
	return playersettingsbox;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
	if ((playerBox.getSelectedValue() != null) && !isRunning) {
	    Logman.getLogman("Box").info(playerBox.getSelectedValue());
	    Player test = Canary.getServer().matchPlayer(
		    playerBox.getSelectedValue());
	    if (test == null) {
		Logman.getLogman("GUI").info("DERP");
	    } else {
		Logman.getLogman("GUI").info(test.getName());
	    }
	    playersettingsbox = new PlayerSettings(test);
	}
    }

}