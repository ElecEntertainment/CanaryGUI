package net.larry1123.gui.player;

import java.awt.Choice;
import net.canarymod.api.GameMode;
import net.larry1123.gui.updaters.Reload;
import net.larry1123.gui.updaters.Reset;
import net.larry1123.gui.updaters.Save;

public class GameModes extends Choice implements Reload, Save, Reset {

    private final PlayerSettings playerSettings;

    public GameModes(PlayerSettings playerSettings) {
        this.playerSettings = playerSettings;
        this.playerSettings.getUpdater().addUpdater(this);

        for (GameMode mode : GameMode.values()) {
            add(mode.name());
        }
        reset();
    }

    @Override
    public void reset() {
        this.select(playerSettings.getPlayer().getMode().name());
    }

    @Override
    public void save() {
        playerSettings.getPlayer().setMode(GameMode.valueOf(this.getSelectedItem()));
    }

    @Override
    public void reload() {
        reset();
    }

}
