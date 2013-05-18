package net.larry1123.gui.updaters;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class Reloader implements ActionListener {

    private static LinkedList<Reload> updaters = new LinkedList<Reload>();

    public Reloader() {
        //
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (Reload updated : updaters) {
            updated.reload();
        }
    }

    public static void addUpdater(Reload updated) {
        updaters.add(updated);
    }

    public static void removeUpdater(Reload updated) {
        updaters.remove(updated);
    }

}
