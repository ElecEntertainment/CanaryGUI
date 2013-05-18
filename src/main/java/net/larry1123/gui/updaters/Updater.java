package net.larry1123.gui.updaters;

public class Updater {

    public final Saver saver = new Saver();
    public final Reloader reloader = new Reloader();
    public final Reseter reseter = new Reseter();

    public Updater() {
        //
    }

    public void addUpdater(Updateing updateing) {
        if (updateing instanceof Reload) {
            reloader.addUpdater((Reload) updateing);
        }
        if (updateing instanceof Save) {
            saver.addSaver((Save) updateing);
        }
        if (updateing instanceof Reset) {
            reseter.addReset((Reset) updateing);
        }
    }

    public void removeUpdater(Updateing updateing) {
        if (updateing instanceof Reload) {
            reloader.removeUpdater((Reload) updateing);
        }
        if (updateing instanceof Save) {
            saver.removeSaver((Save) updateing);
        }
        if (updateing instanceof Reset) {
            reseter.removeReset((Reset) updateing);
        }
    }

}
