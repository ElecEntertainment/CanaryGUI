package net.larry1123.gui.player;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import net.larry1123.gui.GUIPlayerListSlectionListener;

public class WindowChangeEvent extends WindowAdapter {

    @Override
    public void windowClosed(WindowEvent arg0) {
        GUIPlayerListSlectionListener.isRunning = false;
    }

}
