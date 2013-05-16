package net.larry1123.gui.player;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class Reload implements ActionListener {

    private static LinkedList<Reloader> updaters = new LinkedList<Reloader>();

    public Reload() {
        //
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (Reloader updated : updaters) {
            updated.reset();
        }
    }

    public static void addUpdater(Reloader updated) {
        updaters.add(updated);
    }

    public static void removeUpdater(Reloader updated) {
        updaters.remove(updated);
    }

}
