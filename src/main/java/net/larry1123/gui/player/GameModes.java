package net.larry1123.gui.player;

import java.util.Vector;

import javax.swing.JList;

import net.canarymod.api.GameMode;
import net.larry1123.gui.PlayerSettings;
import net.larry1123.gui.updaters.Reload;
import net.larry1123.gui.updaters.Reset;
import net.larry1123.gui.updaters.Save;

public class GameModes extends JList implements Reload, Save, Reset {

    private final PlayerSettings playerSettings;

    public GameModes(PlayerSettings playerSettings) {
        this.playerSettings = playerSettings;
        this.playerSettings.getUpdater().addUpdater(this);

        Vector<String> gameModeList = new Vector<String>();
        for (GameMode mode : GameMode.values()) {
            gameModeList.add(mode.name());
        }
        setListData(gameModeList);
        reset();
    }

    @Override
    public void reset() {
        this.setSelectedValue(playerSettings.getPlayer().getMode().name(), true);
    }

    @Override
    public void save() {
        playerSettings.getPlayer().setMode(GameMode.valueOf((String) this.getSelectedValue()));
    }

    @Override
    public void reload() {
        reset();
    }

}
