package net.larry1123.gui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import net.canarymod.Canary;

public class ServerWindowAdapter extends WindowAdapter {

    @Override
    public void windowClosing(WindowEvent var1) {
        Canary.getServer().initiateShutdown();

        while (!Canary.getServer().isRunning()) {
            try {
                Thread.sleep(100L);
            } catch (InterruptedException var3) {
                var3.printStackTrace();
            }
        }

        System.exit(0);
    }

}
