package net.larry1123.gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Component;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
import net.larry1123.gui.player.Health;
import net.larry1123.gui.player.Reload;

public class PlayerSettings extends JFrame {

    private final JPanel contentPane;
    private final Player player;
    private final Health txtA;
    private final DisplayName txtA_1;

    /**
     * Create the frame.
     */
    public PlayerSettings(Player play) {
        setResizable(false);
        GUIPlayerListSlectionListener.isRunning = true;
        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosed(WindowEvent arg0) {
                GUIPlayerListSlectionListener.isRunning = false;
            }
        });
        player = play;
        setTitle("Player: " + player.getName());

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenuItem mntmSave = new JMenuItem("Save");
        menuBar.add(mntmSave);

        JMenuItem mntmReset = new JMenuItem("Reset");
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
        button.addActionListener(new Reload());
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

            txtA = new Health(player);
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

            txtA_1 = new DisplayName(player);
            splitPane_1.setRightComponent(txtA_1);
            txtA_1.setColumns(10);
        }

        Component horizontalStrut_2 = Box.createHorizontalStrut(20);
        panel.add(horizontalStrut_2);

        setVisible(true);
    }

}
