package net.larry1123.gui;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class GuiLogFormatter extends Formatter {

    // $FF: synthetic field
    final GuiLogOutputHandler a;

    GuiLogFormatter(GuiLogOutputHandler var1) {
        a = var1;
    }

    @Override
    public String format(LogRecord var1) {
        StringBuilder var2 = new StringBuilder();
        var2.append(" [").append(var1.getLevel().getName()).append("] ");
        var2.append(formatMessage(var1));
        var2.append('\n');
        Throwable var3 = var1.getThrown();
        if (var3 != null) {
            StringWriter var4 = new StringWriter();
            var3.printStackTrace(new PrintWriter(var4));
            var2.append(var4.toString());
        }

        return var2.toString();
    }

}
