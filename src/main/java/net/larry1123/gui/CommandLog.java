package net.larry1123.gui;

import java.util.LinkedList;

public class CommandLog {

    private final LinkedList<String> commands = new LinkedList<String>();

    private int index = 0;

    public void addCommand(String command) {
	commands.push(command);
	if (index >= 39) {
	    commands.removeLast();
	}
	resetIndex();
    }

    public void clear() {
	index = 0;
	commands.clear();
    }

    public int getIndex() {
	return index;
    }

    public boolean isEmpty() {
	return commands.isEmpty();
    }

    public boolean isMax() {
	return (index == 39);
    }

    public boolean isMin() {
	return (index == 0);
    }

    /**
     * aka UP
     * 
     * @return
     */
    public String nextCommand() {
	if (index < 40) {
	    return commands.get(index++);
	} else {
	    return commands.get(39);
	}
    }

    /**
     * aka DOWN
     * 
     * @return
     */
    public String preCommand() {
	if (index >= 0) {
	    return commands.get(--index);
	} else {
	    return "";
	}
    }

    public void resetIndex() {
	index = 0;
    }

}
