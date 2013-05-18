package net.larry1123.gui.updaters;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class Saver implements ActionListener {

    private static LinkedList<Save> savers = new LinkedList<Save>();

    @Override
    public void actionPerformed(ActionEvent e) {
        for (Save save : savers) {
            save.save();
        }
    }

    public static void addSaver(Save save) {
        savers.add(save);
    }

    public static void removeSaver(Save save) {
        savers.remove(save);
    }

}
