package net.larry1123.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.util.logging.Logger;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import net.canarymod.api.gui.GUIControl;
import net.larry1123.gui.tick.UpdateTask;

public class GUI extends JComponent implements GUIControl {

    private final CommandLog commandlog = new CommandLog();
    private final AutoCommands autocommands = new AutoCommands();
    private static JFrame jframe;

    public GUI() {
        setPreferredSize(new Dimension(854, 480));
        setLayout(new BorderLayout());

    }

    private JComponent b() {
        JPanel var1 = new JPanel(new BorderLayout());
        var1.add(new GuiStatsComponent(), "North");
        var1.add(c(), "Center");
        var1.setBorder(new TitledBorder(new EtchedBorder(), "Stats"));
        return var1;
    }

    private JComponent c() {
        GUIPlayerListBox var1 = new GUIPlayerListBox();
        UpdateTask.addTickUpdate(var1);
        JScrollPane var2 = new JScrollPane(var1, 22, 30);
        var2.setBorder(new TitledBorder(new EtchedBorder(), "Players"));
        return var2;
    }

    private JComponent d() {
        JPanel var1 = new JPanel(new BorderLayout());
        JTextArea var2 = new JTextArea();
        Logger.getLogger("Minecraft-Server").addHandler(new GuiLogOutputHandler(var2));
        JScrollPane var3 = new JScrollPane(var2, 22, 30);
        var2.setEditable(false);
        JTextField var4 = new JTextField();
        var4.addInputMethodListener(new GUITextBoxChangeListener(var4, autocommands));
        var4.setFocusTraversalKeysEnabled(false);
        var4.addKeyListener(new GUIKeyEventListener(commandlog, autocommands, var4));
        var4.addActionListener(new GUICommandListener(this, var4, commandlog, autocommands));
        var2.addFocusListener(new GuiFocusAdapter(this));
        var1.add(var3, "Center");
        var1.add(var4, "South");
        var1.setBorder(new TitledBorder(new EtchedBorder(), "Log and chat"));
        return var1;
    }

    @Override
    public void start() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception var3) {
            //
        }

        GUI canarygui = net.larry1123.CanaryGUI.canarygui;

        try {
            canarygui.add(d(), "Center");
            canarygui.add(b(), "West");
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        jframe = new JFrame("Derp! Minecraft server");
        jframe.getContentPane().add(canarygui);
        jframe.pack();
        jframe.setLocationRelativeTo((Component) null);
        jframe.setVisible(true);
        jframe.addWindowListener(new ServerWindowAdapter());
    }

    @Override
    public void closeWindow() {
        jframe.dispose();
        jframe = null;
    }

}
