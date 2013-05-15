package net.larry1123.gui;

import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;

import javax.swing.JTextField;

public class GUITextBoxChangeListener implements InputMethodListener {

    private final JTextField textField;

    private final AutoCommands autocommands;

    public GUITextBoxChangeListener(JTextField textField, AutoCommands autocommands) {
        this.textField = textField;
        this.autocommands = autocommands;
    }

    @Override
    public void caretPositionChanged(InputMethodEvent event) {
        // TODO Auto-generated method stub
    }

    @Override
    public void inputMethodTextChanged(InputMethodEvent event) {
        if (InputMethodEvent.INPUT_METHOD_TEXT_CHANGED == event.getCommittedCharacterCount()) {
            // autocommands.reset();
            // autocommands.newAuto(textField.getText());
        }
    }

}
