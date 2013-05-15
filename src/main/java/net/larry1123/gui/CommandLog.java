package net.larry1123.gui;

import java.util.LinkedList;

public class CommandLog {

    private final LinkedList<String> commands = new LinkedList<String>();

    private int index = 0;

    public void addCommand(String command) {
        commands.addFirst(command);
        if (commands.size() >= 40) {
            commands.removeLast();
        }
        resetIndex();
    }

    public void clear() {
        resetIndex();
        commands.clear();
    }

    public int getIndex() {
        return index;
    }

    public boolean isEmpty() {
        return commands.isEmpty();
    }

    public boolean isMax() {
        return (getIndex() == 39);
    }

    public boolean isMin() {
        return (getIndex() == 0);
    }

    /**
     * aka UP
     * 
     * @return
     */
    public String nextCommand() {
        String ret = "";
        try {
            if (getIndex() - 1 <= commands.size()) {
                if (getIndex() <= 39) {
                    ret =  commands.get(index + 1);
                    index++;
                } else {
                    ret = commands.get(39);
                }
            } else {
                if (getIndex() <= 39) {
                    ret =  commands.get(index);
                } else {
                    ret = commands.get(39);
                }
            }
        } catch (IndexOutOfBoundsException e) {
            ret = commands.get(getIndex());
        }
        return ret;
    }

    /**
     * aka DOWN
     * 
     * @return
     */
    public String preCommand() {
        if (getIndex() >= 0) {
            return commands.get(--index);
        } else {
            return "";
        }
    }

    public void resetIndex() {
        index = 0;
    }

}
