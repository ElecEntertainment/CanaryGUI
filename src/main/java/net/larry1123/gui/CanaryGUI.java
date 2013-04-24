package net.larry1123.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import net.minecraft.server.DedicatedServer;

public class CanaryGUI extends JComponent {

    // $FF: synthetic method
    static DedicatedServer a(CanaryGUI var0) {
	return var0.b;
    }

    public static void a(DedicatedServer var0) {
	try {
	    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	} catch (Exception var3) {
	    ;
	}

	CanaryGUI var1 = new CanaryGUI(var0);
	JFrame var2 = new JFrame("Derp! Minecraft server");
	var2.getContentPane().add(var1);
	var2.pack();
	var2.setLocationRelativeTo((Component) null);
	var2.setVisible(true);
	var2.addWindowListener(new ServerWindowAdapter(var0));
    }

    private final CommandLog commandlog = new CommandLog();
    private final AutoCommands autocommands = new AutoCommands();

    private final DedicatedServer b;

    /**
     * Create the frame.
     */
    public CanaryGUI(DedicatedServer var1) {
	b = var1;
	setPreferredSize(new Dimension(854, 480));
	setLayout(new BorderLayout());

	try {
	    this.add(d(), "Center");
	    this.add(b(), "West");
	} catch (Exception var3) {
	    var3.printStackTrace();
	}

    }

    private JComponent b() {
	JPanel var1 = new JPanel(new BorderLayout());
	var1.add(new GuiStatsComponent(b), "North");
	var1.add(c(), "Center");
	var1.setBorder(new TitledBorder(new EtchedBorder(), "Stats"));
	return var1;
    }

    private JComponent c() {
	CanaryGUIPlayerListBox var1 = new CanaryGUIPlayerListBox(b);
	JScrollPane var2 = new JScrollPane(var1, 22, 30);
	var2.setBorder(new TitledBorder(new EtchedBorder(), "Players"));
	return var2;
    }

    private JComponent d() {
	JPanel var1 = new JPanel(new BorderLayout());
	JTextArea var2 = new JTextArea();
	b.al().a().addHandler(new GuiLogOutputHandler(var2));
	JScrollPane var3 = new JScrollPane(var2, 22, 30);
	var2.setEditable(false);
	JTextField var4 = new JTextField();
	var4.addInputMethodListener(new CanaryGUITextBoxChangeListener(var4,
		autocommands));
	var4.setFocusTraversalKeysEnabled(false);
	var4.addKeyListener(new CanaryGUIKeyEventListener(commandlog,
		autocommands, var4));
	var4.addActionListener(new CanaryGUICommandListener(this, var4,
		commandlog, autocommands));
	var2.addFocusListener(new ServerGuiFocusAdapter(this));
	var1.add(var3, "Center");
	var1.add(var4, "South");
	var1.setBorder(new TitledBorder(new EtchedBorder(), "Log and chat"));
	return var1;
    }

}
