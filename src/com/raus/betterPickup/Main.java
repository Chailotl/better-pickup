package com.raus.betterPickup;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin
{
	private boolean autoPickup;
	private int blockDropDelay;
	private boolean invulnerable;
	private int playerDropDelay;
	private int stealDelay;

	@Override
	public void onEnable()
	{
		// Config stuff
		saveDefaultConfig();
		reload();

		// Register command
		getCommand("betterpickup").setExecutor(new ReloadCommand());

		// Listeners
		getServer().getPluginManager().registerEvents(new DropListener(), this);
	}

	@Override
	public void onDisable()
	{

	}

	public void reload()
	{
		reloadConfig();
		autoPickup = getConfig().getBoolean("autoPickup");
		blockDropDelay = getConfig().getInt("blockDropDelay");
		invulnerable = getConfig().getBoolean("invulnerable");
		playerDropDelay = getConfig().getInt("playerDropDelay");
		stealDelay = getConfig().getInt("stealDelay");
	}

	public boolean autoPickup() { return autoPickup; }
	public int blockDropDelay() { return blockDropDelay; }
	public boolean invulnerable() { return invulnerable; }
	public int playerDropDelay() { return playerDropDelay; }
	public int stealDelay() { return stealDelay; }
}