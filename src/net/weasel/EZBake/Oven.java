package net.weasel.EZBake;

import com.nijiko.permissions.PermissionHandler;
import com.nijikokun.bukkit.Permissions.Permissions;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Oven extends JavaPlugin
{
	public static String pluginName = "";
	public static String pluginVersion = "";
	public static CommandExecutor cmdHandler = null;
	public static PluginManager manager = null;
	public static EventHandler evtHandler = null;
	public static PermissionHandler Permissions;
	public static boolean usePermissions = false;
	
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
	
		setupPermissions( manager );
		
		manager.registerEvent(Type.PLAYER_INTERACT, evtHandler, Priority.Normal, this );
		logOutput( pluginName + " v" + pluginVersion + " enabled!" );
	}
	
	public static void logOutput( String message )
	{
		System.out.println( "[" + pluginName + "] " + message );
	}

	private static void setupPermissions( PluginManager pm ) 
	{
		Plugin test = pm.getPlugin( "Permissions" );

	    if( Oven.Permissions == null ) 
	    {
	    	if (test != null) 
	    	{
	    		Oven.Permissions = ((Permissions)test).getHandler();
		    	usePermissions = true;
	    		logOutput( "Permissions system ready." );
	    	}
	    	else 
	    	{
		    	usePermissions = false;
	    		logOutput( "Permissions not detected. Let them ALL eat cake!" );
	        }
	    }
	}
}
