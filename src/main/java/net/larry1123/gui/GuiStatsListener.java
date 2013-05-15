package net.larry1123.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiStatsListener implements ActionListener {

    // $FF: synthetic field
    final GuiStatsComponent a;

    GuiStatsListener(GuiStatsComponent var1) {
        a = var1;
    }

    @Override
    public void actionPerformed(ActionEvent var1) {
        GuiStatsComponent.a(a);
    }

}
