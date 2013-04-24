package net.larry1123.gui;

import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

import javax.swing.JTextArea;

public class GuiLogOutputHandler extends Handler {

    private final int[] b = new int[1024];
    private int c = 0;
    Formatter a = new GuiLogFormatter(this);
    private final JTextArea d;

    public GuiLogOutputHandler(JTextArea var1) {
	setFormatter(a);
	d = var1;
    }

    @Override
    public void close() {
    }

    @Override
    public void flush() {
    }

    @Override
    public void publish(LogRecord var1) {
	int var2 = d.getDocument().getLength();
	d.append(a.format(var1));
	d.setCaretPosition(d.getDocument().getLength());
	int var3 = d.getDocument().getLength() - var2;
	if (b[c] != 0) {
	    d.replaceRange("", 0, b[c]);
	}

	b[c] = var3;
	c = (c + 1) % 1024;
    }

}
