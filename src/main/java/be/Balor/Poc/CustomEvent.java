package be.Balor.Poc;

import java.util.logging.Logger;

import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Event;
import org.bukkit.event.Listener;
import org.bukkit.event.Event.Category;
import org.bukkit.event.Event.Priority;
import org.bukkit.plugin.EventExecutor;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import be.Balor.Poc.EventManager;
import be.Balor.Poc.PlayerTestEvent;
import be.Balor.Poc.TestListener;

/**
 * AdminCmd for Bukkit (fork of PlgEssentials)
 * 
 * @authors Balor
 */
public class CustomEvent extends JavaPlugin {
	private static Server server = null;

	public static Server getBukkitServer() {
		return server;
	}

	public static final Logger log = Logger.getLogger("Minecraft");

	public void onEnable() {
		server = getServer();
		PluginManager pm = getServer().getPluginManager();
		EventManager.addEvent(this, "PLAYER_TEST", Category.PLAYER);
		EventManager.addEvent(this, "PLAYER_YATTA", Category.PLAYER);
		pm.registerEvent(Event.Type.valueOf("PLAYER_TEST"), new TestListener(),
				new EventExecutor() {

					public void execute(Listener listener, Event event) {
						((TestListener) listener).onPlayerTest((PlayerTestEvent) event);

					}
				}, Priority.Normal, this);
		pm.registerEvent(Event.Type.valueOf("PLAYER_YATTA"), new TestListener(),
				new EventExecutor() {

					public void execute(Listener listener, Event event) {
						((TestListener) listener).onPlayerTest((PlayerTestEvent) event);

					}
				}, Priority.Normal, this);
		PluginDescriptionFile pdfFile = this.getDescription();
		log.info("[" + pdfFile.getName() + "]" + " Plugin Enabled. (version "
				+ pdfFile.getVersion() + ")");
	}

	public void onDisable() {
		PluginDescriptionFile pdfFile = this.getDescription();
		log.info("[" + pdfFile.getName() + "]" + " Plugin Disabled. (version "
				+ pdfFile.getVersion() + ")");
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		getServer().getPluginManager().callEvent(new PlayerTestEvent("Command issued", sender));
		return true;
	}
}
