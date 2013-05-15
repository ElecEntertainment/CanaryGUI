package net.larry1123.gui.tick;

import java.util.LinkedList;

import net.canarymod.tasks.ServerTask;
import net.canarymod.tasks.TaskOwner;

public class UpdateTask extends ServerTask {

    public UpdateTask(TaskOwner owner, long delay) {
        this(owner, delay, true);
    }

    public UpdateTask(TaskOwner owner, long delay, boolean continuous) {
        super(owner, delay, continuous);
    }

    private static LinkedList<TickUpdate> tickupdates = new LinkedList<TickUpdate>();

    public static void addTickUpdate(TickUpdate update) {
        tickupdates.add(update);
    }

    public static void removeTickUpdate(TickUpdate update) {
        tickupdates.remove(update);
    }

    @Override
    public void run() {
        for (TickUpdate update : tickupdates) {
            update.onTickUpdate();
        }
    }

}
