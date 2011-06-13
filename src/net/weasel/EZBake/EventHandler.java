package net.weasel.EZBake;

import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerListener;

public class EventHandler extends PlayerListener
{
	public static Oven instance;
	
	public EventHandler( Oven i )
	{
		instance = i;
	}

	@Override
	public void onPlayerInteract( PlayerInteractEvent event )
	{
		Block target = event.getClickedBlock();
		Action act = event.getAction();

		if( act == Action.RIGHT_CLICK_BLOCK )
		{
			if( target.getTypeId() == 92 )
			{
				Block below = target.getRelative(BlockFace.DOWN);
				
				if( below.getTypeId() == 35 && below.getData() == 4 )
				{
					if( isAllowedUse( event.getPlayer() ) == true )
					{
						if( target.getData() != 0 )
						{
							target.setData( (byte)0 );
							event.getPlayer().sendMessage( "Nom.." );
						}
					}
					else
					{
						event.getPlayer().sendMessage( "Hey! That's not your cake!" );
						event.setCancelled( true );
					}
				}
			}
		}
	}

	public static boolean isAllowedUse( Player player )
	{
		if( Oven.usePermissions == true )
		{
			if( Oven.Permissions.has(player, "ezbake.use")) 
			    return true;
			else
				return false;
		}
		else
		{
			return( player.isOp() );
		}
	}
}
