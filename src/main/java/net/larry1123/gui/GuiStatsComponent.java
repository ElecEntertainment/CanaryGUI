package net.larry1123.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.text.DecimalFormat;
import java.util.Collection;

import javax.swing.JComponent;
import javax.swing.Timer;

import net.canarymod.Canary;
import net.canarymod.api.world.World;

public class GuiStatsComponent extends JComponent {

    private static final DecimalFormat decimalFormat = new DecimalFormat("########0.000");

    // $FF: synthetic method
    static void a(GuiStatsComponent var0) {
        var0.a();
    }

    private final int[] b = new int[256];
    private int c = 0;
    private final String[] d = new String[11];

    public GuiStatsComponent() {
        setPreferredSize(new Dimension(456, 246));
        setMinimumSize(new Dimension(456, 246));
        setMaximumSize(new Dimension(456, 246));
        (new Timer(500, new GuiStatsListener(this))).start();
        setBackground(Color.BLACK);
    }

    private void a() {
        long i0 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        System.gc();
        d[0] = "Memory use: " + (i0 / 1024L / 1024L) + " mb (" + ((Runtime.getRuntime().freeMemory() * 100L) / Runtime.getRuntime().maxMemory()) + "% free)";
        d[1] = "Threads: " + Canary.getServer().getTcpReaderThreadCount() + " + " + Canary.getServer().getTcpWriterThreadCount();
        d[2] = "Avg tick: " + decimalFormat.format(this.convertArrayToAmout(Canary.getServer().getTickTimeArray()) * 1.0E-6D) + " ms";
        d[3] = "Avg sent: " + (int) this.convertArrayToAmout(Canary.getServer().getSentPacketCountArray()) + ", Avg size: " + (int) this.convertArrayToAmout(Canary.getServer().getSentPacketSizeArray());
        d[4] = "Avg rec: " + (int) this.convertArrayToAmout(Canary.getServer().getReceivedPacketCountArray()) + ", Avg size: " + (int) this.convertArrayToAmout(Canary.getServer().getReceivedPacketSizeArray());

        // CanaryMod: Multiworld
        Collection<World> worlds = Canary.getServer().getWorldManager().getAllWorlds();

        if (worlds != null) {
            int i = 0;

            for (World world : worlds) {

                d[5 + i] = "World " + world.getName() + " lvl " + i + " tick " + decimalFormat.format(world.getNanoTick(i) * 1.0E-6D) + " ms";
                i++;
            }
        }
        //

        b[c++ & 255] = (int) ((this.convertArrayToAmout(Canary.getServer().getSentPacketSizeArray()) * 100.0D) / 12500.0D);
        this.repaint();
    }

    private double convertArrayToAmout(long[] var1) {
        long var2 = 0L;

        for (long aVar1 : var1) {
            var2 += aVar1;
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
