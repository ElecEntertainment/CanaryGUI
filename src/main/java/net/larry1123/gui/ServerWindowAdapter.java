package net.larry1123.gui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import net.minecraft.server.DedicatedServer;

public class ServerWindowAdapter extends WindowAdapter {

    // $FF: synthetic field
    final DedicatedServer a;

    ServerWindowAdapter(DedicatedServer var1) {
	a = var1;
    }

    public void windowClosing(WindowEvent var1) {
	a.n();

	while (!a.ac()) {
	    try {
		Thread.sleep(100L);
	    } catch (InterruptedException var3) {
		var3.printStackTrace();
	    }
	}

	System.exit(0);
    }

}
