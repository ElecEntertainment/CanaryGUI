package net.larry1123.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.text.DecimalFormat;
import java.util.Collection;

import javax.swing.JComponent;
import javax.swing.Timer;

import net.canarymod.Canary;
import net.canarymod.api.world.CanaryWorld;
import net.canarymod.api.world.World;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.TcpConnection;
import net.minecraft.server.WorldServer;

public class GuiStatsComponent extends JComponent {

    private static final DecimalFormat a = new DecimalFormat("########0.000");

    // $FF: synthetic method
    static void a(GuiStatsComponent var0) {
	var0.a();
    }

    private final int[] b = new int[256];
    private int c = 0;
    private final String[] d = new String[11];

    private final MinecraftServer e;

    public GuiStatsComponent(MinecraftServer var1) {
	e = var1;
	setPreferredSize(new Dimension(456, 246));
	setMinimumSize(new Dimension(456, 246));
	setMaximumSize(new Dimension(456, 246));
	(new Timer(500, new GuiStatsListener(this))).start();
	setBackground(Color.BLACK);
    }

    private void a() {
	long i0 = Runtime.getRuntime().totalMemory()
		- Runtime.getRuntime().freeMemory();

	System.gc();
	d[0] = "Memory use: "
		+ (i0 / 1024L / 1024L)
		+ " mb ("
		+ ((Runtime.getRuntime().freeMemory() * 100L) / Runtime
			.getRuntime().maxMemory()) + "% free)";
	d[1] = "Threads: " + TcpConnection.a.get() + " + "
		+ TcpConnection.b.get();
	d[2] = "Avg tick: " + a.format(this.a(e.i) * 1.0E-6D) + " ms";
	d[3] = "Avg sent: " + (int) this.a(e.e) + ", Avg size: "
		+ (int) this.a(e.f);
	d[4] = "Avg rec: " + (int) this.a(e.g) + ", Avg size: "
		+ (int) this.a(e.h);

	// CanaryMod: Multiworld
	Collection<World> worlds = Canary.getServer().getWorldManager()
		.getAllWorlds();

	if (worlds != null) {
	    int i = 0;

	    for (World world : worlds) {
		WorldServer level = (WorldServer) (((CanaryWorld) world)
			.getHandle());

		d[5 + i] = "World " + world.getName() + " lvl " + i + " tick "
			+ a.format(world.getNanoTick(i) * 1.0E-6D) + " ms";
		if ((world != null) && (level.b != null)) {
		    d[5 + i] += ", " + level.b.d();
		}
		i++;
	    }
	}
	//

	b[c++ & 255] = (int) ((this.a(e.f) * 100.0D) / 12500.0D);
	this.repaint();
    }

    private double a(long[] var1) {
	long var2 = 0L;

	for (int var4 = 0; var4 < var1.length; ++var4) {
	    var2 += var1[var4];
	}

	return (double) var2 / (double) var1.length;
    }

    @Override
    public void paint(Graphics var1) {
	var1.setColor(new Color(16777215));
	var1.fillRect(0, 0, 456, 246);

	int var2;
	for(var2 = 0; var2 < 256; ++var2) {
	    int var3 = b[(var2 + c) & 255];
	    var1.setColor(new Color((var3 + 28) << 16));
	    var1.fillRect(var2, 100 - var3, 1, var3);
	}

	var1.setColor(Color.BLACK);

	for(var2 = 0; var2 < d.length; ++var2) {
	    String var4 = d[var2];
	    if(var4 != null) {
		var1.drawString(var4, 32, 116 + (var2 * 16));
	    }
	}

    }

}
