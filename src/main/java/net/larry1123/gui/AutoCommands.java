package net.larry1123.gui;

import java.util.LinkedList;

import net.canarymod.AutocompleteUtils;
import net.canarymod.Canary;

public class AutoCommands {

    private final LinkedList<String> commands = new LinkedList<String>();

    private String orinal = "";

    private int index = 0;

    public boolean isEmpty() {
	return commands.isEmpty();
    }

    public void newAuto(String part) {
	orinal = part;
	commands.clear();
	index = 0;
	for (String command : AutocompleteUtils
		.autoComplete("/" + part, Canary.getServer()).toString()
		.split("\u0000")) {
	    commands.add(command.replace("/", ""));
	}
    }

    public String nextTry() {
	if (index == (commands.size())) {
	    resetIndex();
	}
	if (commands.isEmpty()) {
	    return orinal;
	} else {
	    return commands.get(index++);
	}
    }

    public void reset() {
	commands.clear();
	orinal = "";
	resetIndex();
    }

    public void resetIndex() {
	index = 0;
    }

}