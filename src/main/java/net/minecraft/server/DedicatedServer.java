package net.minecraft.server;


import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import net.canarymod.Canary;
import net.canarymod.config.Configuration;
import net.canarymod.config.ServerConfiguration;
import net.canarymod.config.WorldConfiguration;
import net.larry1123.gui.CanaryGUI;

public class DedicatedServer extends MinecraftServer implements IServer {

    private final List k = Collections.synchronizedList(new ArrayList());
    private final ILogAgent l;
    private RConThreadQuery m;
    private RConThreadMain n;
    // CanaryMod - Removed private PropertyManager o
    // CanaryMod - Removed private boolean p;
    // CanaryMod - Removed private EnumGameType q;
    private NetworkListenThread r;
    private boolean s = false;

    public DedicatedServer(File file1) {
	super(file1);
	l = net.canarymod.Main.getLogAgent(); // new
	// LogAgent("Minecraft-Server",
	// (String) null, (new File(file1,
	// "server.log")).getAbsolutePath());
	new DedicatedServerSleepThread(this);
    }

    @Override
    @Deprecated
    public void a() {
	throw new UnsupportedOperationException(
		"Cannot finish this request. DdedicatedServer.a() is deprecated");
    }

    @Override
    protected void a(CrashReport crashreport) {
	while (m()) {
	    am();

	    try {
		Thread.sleep(10L);
	    } catch (InterruptedException interruptedexception) {
		interruptedexception.printStackTrace();
	    }
	}
    }

    @Override
    public String a(EnumGameType enumgametype, boolean flag0) {
	return "";
    }

    @Override
    public void a(PlayerUsageSnooper playerusagesnooper) {
	playerusagesnooper.a("whitelist_enabled", Configuration
		.getServerConfig().isWhitelistEnabled());
	playerusagesnooper.a("whitelist_count", Canary.whitelist().getSize());
	super.a(playerusagesnooper);
    }

    public boolean a(String s0, boolean flag0) {
	throw new UnsupportedOperationException(
		"Setting boolean values to server.properties is disabled!");
    }

    public void a(String s0, ICommandSender icommandsender) {
	k.add(new ServerCommand(s0, icommandsender));
    }

    @Override
    public int a(String s0, int i0) {
	throw new UnsupportedOperationException(
		"Setting int values to server.properties is disabled!");
    }

    @Override
    public void a(String s0, Object object) {
	throw new UnsupportedOperationException(
		"Setting Object values to server.properties is disabled!");
    }

    @Override
    public String a(String s0, String s1) {
	throw new UnsupportedOperationException(
		"Setting String values to server.properties is disabled!");
    }

    @Override
    public boolean a(World world, int i0, int i1, int i2,
	    EntityPlayer entityplayer) {
	WorldConfiguration cfg = Configuration.getWorldConfig(world
		.getCanaryWorld().getFqName());
	if (world.t.h != 0) {
	    return false;
	} else if (an().i().isEmpty()) {
	    return false;
	} else if (an().e(entityplayer.bS)) {
	    return false;
	} else if (cfg.getSpawnProtectionSize() <= 0) {
	    return false;
	} else {
	    ChunkCoordinates chunkcoordinates = world.I();
	    int i3 = MathHelper.a(i0 - chunkcoordinates.a);
	    int i4 = MathHelper.a(i2 - chunkcoordinates.c);
	    int i5 = Math.max(i3, i4);

	    return i5 <= cfg.getSpawnProtectionSize();
	}
    }

    @Override
    public ServerConfigurationManager ad() {
	return an();
    }

    @Override
    public NetworkListenThread ae() {
	return r;
    }

    @Override
    public boolean ag() {
	return s;
    }

    @Override
    public int ak() {
	throw new UnsupportedOperationException(
		"spawn-protection has been moved to a per-world config!");
    }

    @Override
    public ILogAgent al() {
	return l;
    }

    public void am() {
	while (!k.isEmpty()) {
	    ServerCommand servercommand = (ServerCommand) k.remove(0);
	    // CanaryMod intercept command queue for our own commands
	    if (!Canary.getServer().consoleCommand(servercommand.a)) {
		E().a(servercommand.b, servercommand.a);
	    }
	}
    }

    public DedicatedPlayerList an() {
	return (DedicatedPlayerList) super.ad();
    }

    public void ao() {
	CanaryGUI.a(this);
	s = true;
    }

    @Override
    public CrashReport b(CrashReport crashreport) {
	crashreport = super.b(crashreport);
	crashreport.g().a("Is Modded", (new CallableType(this)));
	crashreport.g().a("Type", (new CallableServerType(this)));
	return crashreport;
    }

    @Override
    @Deprecated
    public String b_() {
	throw new UnsupportedOperationException(
		"Cannot finish this request. DdedicatedServer.b_() is deprecated");
    }

    @Override
    protected boolean c() {
	DedicatedServerCommandThread dedicatedservercommandthread = new DedicatedServerCommandThread(
		this);

	dedicatedservercommandthread.setDaemon(true);
	dedicatedservercommandthread.start();
	al().a("Starting minecraft server version 1.5.1");
	if ((Runtime.getRuntime().maxMemory() / 1024L / 1024L) < 512L) {
	    al().b("To start the server with more ram, launch it as \"java -Xmx1024M -Xms1024M -jar minecraft_server.jar\"");
	}

	al().a("Loading properties");
	// this.o = new PropertyManager(new File("server.properties"),
	// this.al()); //CanaryMod - Removed
	// CanaryMod use our config
	ServerConfiguration cfg = Configuration.getServerConfig();
	if (I()) {
	    this.d("127.0.0.1");
	} else {
	    this.d(cfg.isOnlineMode());
	    this.d(cfg.getBindIp());
	}
	// CanaryMod: Removed world-dependent settings
	this.o(cfg.getMotd());
	this.n(cfg.getTexturePack());
	InetAddress inetaddress = null;

	if (this.l().length() > 0) {
	    try {
		inetaddress = InetAddress.getByName(this.l());
	    } catch (UnknownHostException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	}

	if (G() < 0) {
	    this.b(cfg.getPort());
	}

	al().a("Generating keypair");
	this.a(CryptManager.b());
	al().a("Starting Minecraft server on "
		+ (this.l().length() == 0 ? "*" : this.l()) + ":" + G());

	try {
	    r = new DedicatedServerListenThread(this, inetaddress, G());
	} catch (IOException ioexception) {
	    al().b("**** FAILED TO BIND TO PORT!");
	    al().b("The exception was: {0}",
		    new Object[] { ioexception.toString() });
	    al().b("Perhaps a server is already running on that port?");
	    return false;
	}

	if (!U()) {
	    al().b("**** SERVER IS RUNNING IN OFFLINE/INSECURE MODE!");
	    al().b("The server will make no attempt to authenticate usernames. Beware.");
	    al().b("While this makes the game possible to play without internet access, it also opens up the ability for hackers to connect with any username they choose.");
	    al().b("To change this, set \"online-mode\" to \"true\" in the server.properties file.");
	}

	this.a((new DedicatedPlayerList(this)));
	long i1 = System.nanoTime();

	if (J() == null) {
	    this.l(Configuration.getServerConfig().getDefaultWorldName()); // CanaryMod
	    // use
	    // our
	    // world
	    // config
	}
	// CanaryMod use or configurations instead of native ones
	WorldConfiguration worldcfg = Configuration.getWorldConfig(J()
		+ "_NORMAL");

	String s0 = worldcfg.getWorldSeed(); // this.o.a("level-seed", "");
	String s1 = worldcfg.getWorldType().toString(); // this.o.a("level-type",
	// "DEFAULT");
	String s2 = worldcfg.getGeneratorSettings(); // this.o.a("generator-settings",
	// "");
	long i2 = (new Random()).nextLong();

	if (s0.length() > 0) {
	    try {
		long i3 = Long.parseLong(s0);

		if (i3 != 0L) {
		    i2 = i3;
		}
	    } catch (NumberFormatException numberformatexception) {
		i2 = s0.hashCode();
	    }
	}

	WorldType worldtype = WorldType.a(s1);

	if (worldtype == null) {
	    worldtype = WorldType.b;
	}

	this.d(worldcfg.getMaxBuildHeight());
	this.d(((ab() + 8) / 16) * 16);
	this.d(MathHelper.a(ab(), 64, 256));
	worldcfg.getFile().setInt("max-build-height", ab());

	al().a("Preparing level \"" + J() + "\"");
	// CanaryMod changed call to initWorld
	net.canarymod.api.world.DimensionType wt = net.canarymod.api.world.DimensionType
		.fromName("NORMAL");

	initWorld(J(), i2, worldtype, wt, s2);
	//
	long i4 = System.nanoTime() - i1;
	String s3 = String.format("%.3fs",
		new Object[] { Double.valueOf(i4 / 1.0E9D) });

	al().a("Done (" + s3 + ")! For help, type \"help\" or \"?\"");
	if (cfg.isQueryEnabled()) {
	    al().a("Starting GS4 status listener");
	    m = new RConThreadQuery(this);
	    m.a();
	}
	if (cfg.isRconEnabled()) {
	    al().a("Starting remote control listener");
	    n = new RConThreadMain(this);
	    n.a();
	}

	return true;
    }

    @Override
    public boolean f() {
	throw new UnsupportedOperationException(
		"Generate-structures setting has been moved to a per-world configuration!");
    }

    @Override
    public EnumGameType g() {
	throw new UnsupportedOperationException(
		"GameType setting has been moved to a per-world configuration!");
    }

    @Override
    public int h() {
	throw new UnsupportedOperationException(
		"Difficulty setting has been moved to a per-world configuration!");
    }

    @Override
    public boolean i() {
	throw new UnsupportedOperationException(
		"Hardcoremode setting has been moved to a per-world configuration!");
    }

    @Override
    public boolean L() {
	throw new UnsupportedOperationException(
		"spawn-monsters has been moved to a per-world config");
    }

    @Override
    protected void p() {
	System.exit(0);
    }

    @Override
    public void r() { // CanaryMod: protected => public
	super.r();
	am();
    }

    @Override
    public boolean R() {
	// CanaryMod moved to config/server.cfg
	return Configuration.getServerConfig().isSnooperEnabled();
    }

    @Override
    public void reload() {/*
     * WorldConfiguration defWorld =
     * Configuration.getWorldConfig
     * (Configuration.getServerConfig
     * ().getDefaultWorldName()); // this.d = new
     * OPropertyManager(new File("server.properties"));
     * this.y = Configuration.getNetConfig().getBindIp();
     * this.n =
     * Configuration.getNetConfig().isOnlineMode(); this.o
     * = defWorld.canSpawnAnimals(); this.p =
     * defWorld.canSpawnNpcs(); this.q =
     * defWorld.isPvpEnabled(); this.r =
     * defWorld.isFlightAllowed(); this.s =
     * Configuration.getServerConfig().getMotd(); this.z =
     * Configuration.getNetConfig().getPort(); this.t =
     * defWorld.getMaxBuildHeight(); this.t = (this.t + 8)
     * / 16 * 16; this.t = OMathHelper.a(this.t, 64, 256);
     * // TODO Update worlds (??)
     */
    }

    @Override
    public boolean s() {
	throw new UnsupportedOperationException(
		"allow-nether has been moved to a per-world config");
    }

    @Override
    public boolean T() {
	return true;
    }

    @Override
    public boolean Z() {
	//CanaryMod moved to config/server.cfg
	return Configuration.getServerConfig().isCommandBlockEnabled();
    }
}
