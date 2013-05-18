package net.larry1123.gui;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import net.canarymod.Canary;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.config.Configuration;
import net.canarymod.logger.Logman;

public class GUIPlayerListSlectionListener implements
ListSelectionListener {

    private final GUIPlayerListBox playerBox;
    private PlayerSettings playersettingsbox;
    public static Boolean isRunning = false;

    public GUIPlayerListSlectionListener(GUIPlayerListBox playerbox) {
        playerBox = playerbox;
    }

    public PlayerSettings getPlayerSettings() {
        return playersettingsbox;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if ((playerBox.getSelectedValue() != null) && !isRunning) {
            Logman.getLogman("Box").info((String) playerBox.getSelectedValue());
            Player test = Canary.getServer().getPlayer((String) playerBox.getSelectedValue());
            if (test == null) {
                if (Configuration.getServerConfig().isDebugMode()) {
                    Logman.getLogman("GUI").logDerp("Null Player from list!");
                }
            } else {
                Logman.getLogman("GUI").info(test.getName());
                playersettingsbox = new PlayerSettings(test);
            }
        }
    }

}