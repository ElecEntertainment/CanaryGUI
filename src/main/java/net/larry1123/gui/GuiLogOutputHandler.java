package net.larry1123.gui;

import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

import javax.swing.JTextArea;

import net.canarymod.Canary;

public class GuiLogOutputHandler extends Handler {

    private final int[] b = new int[1024];
    private int c = 0;
    Formatter a = new GuiLogFormatter(this);
    private final JTextArea textArea;

    public GuiLogOutputHandler(JTextArea var1) {
        setFormatter(a);
        textArea = var1;
        textArea.setText(Canary.getServer().getServerGUILog());
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }

    @Override
    public void close() {
    }

    @Override
    public void flush() {
    }

    @Override
    public void publish(LogRecord var1) {
        int var2 = textArea.getDocument().getLength();
        textArea.append(a.format(var1));
        textArea.setCaretPosition(textArea.getDocument().getLength());
        int var3 = textArea.getDocument().getLength() - var2;
        if (b[c] != 0) {
            textArea.replaceRange("", 0, b[c]);
        }

        b[c] = var3;
        c = (c + 1) % 1024;
    }

}
