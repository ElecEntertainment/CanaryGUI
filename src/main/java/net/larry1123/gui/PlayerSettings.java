package net.larry1123.gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Component;
import java.awt.Label;
import java.awt.Panel;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import net.canarymod.api.entity.living.humanoid.Player;
import net.larry1123.gui.player.DisplayName;
import net.larry1123.gui.player.GameModes;
import net.larry1123.gui.player.Health;
import net.larry1123.gui.player.WindowChangeEvent;
import net.larry1123.gui.updaters.Reloader;
import net.larry1123.gui.updaters.Reseter;
import net.larry1123.gui.updaters.Saver;
import net.larry1123.gui.updaters.Updater;

public class PlayerSettings extends JFrame {

    private final JPanel contentPane;
    private Player player; // Needed to make this no longer final because the player needs to be able to change!
    private final Health txtA;
    private final DisplayName txtA_1;
    private Updater updater = new Updater();

    /**
     * Create the frame.
     */
    public PlayerSettings(Player play) {
        setResizable(false);
        GUIPlayerListSlectionListener.isRunning = true;
        addWindowListener(new WindowChangeEvent());
        player = play;
        setTitle("Player: " + player.getName());

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenuItem mntmSave = new JMenuItem("Save");
        mntmSave.addActionListener(new Saver());
        menuBar.add(mntmSave);

        JMenuItem mntmReset = new JMenuItem("Reset");
        mntmReset.addActionListener(new Reseter());
        menuBar.add(mntmReset);

        Component horizontalStrut_3 = Box.createHorizontalStrut(100);
        menuBar.add(horizontalStrut_3);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        Panel panel_1 = new Panel();
        contentPane.add(panel_1, BorderLayout.SOUTH);

        Button button = new Button("Reload");
        button.addActionListener(new Reloader());
        panel_1.add(button);

        JTabbedPane tabbedPane = new JTabbedPane(SwingConstants.TOP);
        contentPane.add(tabbedPane, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        tabbedPane.addTab("Stats", null, panel, null);

        Component horizontalStrut_1 = Box.createHorizontalStrut(20);
        panel.add(horizontalStrut_1);

        {
            JSplitPane splitPane = new JSplitPane();
            panel.add(splitPane);

            Label label = new Label("Health");
            splitPane.setLeftComponent(label);

            txtA = new Health(this);
            splitPane.setRightComponent(txtA);
            txtA.setColumns(10);
        }

        Component horizontalStrut = Box.createHorizontalStrut(20);
        panel.add(horizontalStrut);

        {
            JSplitPane splitPane_1 = new JSplitPane();
            panel.add(splitPane_1);

            JLabel lblNewLabel = new JLabel("Display Name");
            splitPane_1.setLeftComponent(lblNewLabel);

            txtA_1 = new DisplayName(this);
            splitPane_1.setRightComponent(txtA_1);
            txtA_1.setColumns(10);
        }

        Component horizontalStrut_2 = Box.createHorizontalStrut(20);
        panel.add(horizontalStrut_2);

        JList list = new GameModes(this);
        panel.add(list);

        setVisible(true);
    }

    public Player getPlayer() {
        return player;
    }

    public Updater getUpdater() {
        return updater;
    }

}
