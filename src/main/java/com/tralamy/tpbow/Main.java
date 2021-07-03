package com.tralamy.tpbow;

import com.tralamy.tpbow.commands.GetTpBow;
import com.tralamy.tpbow.events.OnShoot;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

	@Override
	public void onEnable() {
		configureConfig();
		registerCommand();
		registerEvents();
	}

	private void configureConfig() {
		getConfig().options().copyDefaults();
		saveDefaultConfig();
	}

	private void registerCommand() {
		getCommand("gettpbow").setExecutor(new GetTpBow());

	}

	private void registerEvents() {
		getServer().getPluginManager().registerEvents(new OnShoot(), this);
	}
}
