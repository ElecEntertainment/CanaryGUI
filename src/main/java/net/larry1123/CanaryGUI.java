package net.larry1123;

import net.canarymod.Canary;
import net.canarymod.hook.HookHandler;
import net.canarymod.hook.system.ServerGuiStartHook;
import net.canarymod.plugin.PluginListener;
import net.canarymod.tasks.ServerTaskManager;
import net.canarymod.tasks.TaskOwner;
import net.larry1123.gui.tick.UpdateTask;
import net.larry1123.lib.plugin.UtilPlugin;

public class CanaryGUI extends UtilPlugin implements TaskOwner {

    protected String version = "0.0.1";

    protected String author = "Larry1123";

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
        getLogger().info("Plugin Enabled");
        return true;
    }

    @Override
    public void disable() {
        ServerTaskManager.removeTasksForPlugin(this);
        getLogger().info("Plugin Disabled");
    }

}
