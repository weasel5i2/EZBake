package net.weasel.EZBake;

import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Oven extends JavaPlugin
{
	public static String pluginName = "";
	public static String pluginVersion = "";
	public static CommandExecutor cmdHandler = null;
	public static PluginManager manager = null;
	public static EventHandler evtHandler = null;
	
	@Override
	public void onDisable() 
	{
		logOutput( pluginName + " v" + pluginVersion + " disabled." );
	}

	@Override
	public void onEnable() 
	{
		pluginName = getDescription().getName();
		pluginVersion = getDescription().getVersion();
		manager = getServer().getPluginManager();
		
		evtHandler = new EventHandler(this);
		cmdHandler = new CommandHandler(this);
		getCommand( "ezbake" ).setExecutor( cmdHandler );
		
		manager.registerEvent(Type.PLAYER_INTERACT, evtHandler, Priority.Normal, this );
		logOutput( pluginName + " v" + pluginVersion + " enabled!" );
	}
	
	public static void logOutput( String message )
	{
		System.out.println( "[" + pluginName + "] " + message );
	}
}
