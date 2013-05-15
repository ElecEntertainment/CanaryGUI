package net.larry1123.gui;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;

public class GUIKeyEventListener extends KeyAdapter {

    private final JTextField textField;
    private final CommandLog commandlog;
    private final AutoCommands autocommands;

    public GUIKeyEventListener(CommandLog commandlog, AutoCommands autocommands, JTextField textField) {
        this.textField = textField;
        this.commandlog = commandlog;
        this.autocommands = autocommands;
    }

    @Override
    public void keyPressed(KeyEvent arg0) {
        int key = arg0.getKeyCode();

        if (!commandlog.isEmpty()) {
            if (KeyEvent.VK_UP == key) {
                if (!commandlog.isMax()) {
                    textField.setText(commandlog.nextCommand());
                }
            } else if (KeyEvent.VK_DOWN == key) {
                if (!commandlog.isMin()) {
                    textField.setText(commandlog.preCommand());
                } else {
                    textField.setText("");
                }
            }
        }

        if (KeyEvent.VK_TAB == key) {
            if (!textField.getText().isEmpty()) {
                if (autocommands.isEmpty()) {
                    autocommands.newAuto(textField.getText());
                }
                textField.setText(autocommands.nextTry());
            } else {
                textField.setText("say ");
            }
        } else {
            autocommands.reset();
            autocommands.newAuto(textField.getText());
        }

    }

}
