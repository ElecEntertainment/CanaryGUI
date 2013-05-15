package net.larry1123;

import net.canarymod.Canary;
import net.canarymod.hook.HookHandler;
import net.canarymod.hook.system.ServerGuiStartHook;
import net.canarymod.plugin.Plugin;
import net.canarymod.plugin.PluginListener;
import net.canarymod.tasks.ServerTaskManager;
import net.canarymod.tasks.TaskOwner;
import net.larry1123.gui.tick.UpdateTask;

public class CanaryGUI extends Plugin implements TaskOwner {

    public static net.larry1123.gui.GUI canarygui = new net.larry1123.gui.GUI();
    public UpdateTask ticksystem = new UpdateTask(this, 0);

    public class CanaryGUIHookListener implements PluginListener {

        @HookHandler
        public void StartGUI(ServerGuiStartHook guiHook) {
            guiHook.setGui(canarygui);
        }

    }

    @Override
    public boolean enable() {
        Canary.hooks().registerListener(new CanaryGUIHookListener(), this);
        ServerTaskManager.addTask(ticksystem);
        return true;
    }

    @Override
    public void disable() {
        ServerTaskManager.removeTasksForPlugin(this);
    }

}
