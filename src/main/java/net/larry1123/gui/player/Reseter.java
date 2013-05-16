package net.larry1123.gui.player;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class Reseter implements ActionListener {

    private static LinkedList<Reset> resets = new LinkedList<Reset>();

    public static void addReset(Reset reset) {
        resets.add(reset);
    }

    public static void removeReset(Reset reset) {
        resets.remove(reset);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (Reset reset : resets) {
            reset.reset();
        }
    }

}
