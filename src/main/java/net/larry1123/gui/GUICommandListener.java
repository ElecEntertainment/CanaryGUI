package net.larry1123.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import net.canarymod.Canary;

public class GUICommandListener implements ActionListener {

    // $FF: synthetic field
    final JTextField a;
    // $FF: synthetic field
    final GUI b;

    private final CommandLog commandlog;
    private final AutoCommands autocommands;

    GUICommandListener(GUI var1, JTextField var2, CommandLog commandlog, AutoCommands autocommands) {
        b = var1;
        a = var2;
        this.commandlog = commandlog;
        this.autocommands = autocommands;
    }

    @Override
    public void actionPerformed(ActionEvent var1) {
        String var2 = a.getText().trim();
        if (var2.length() > 0) {
            commandlog.addCommand(var2);
            autocommands.reset();
            Canary.getServer().consoleCommand(var2);
        }

        a.setText("");
    }

}
