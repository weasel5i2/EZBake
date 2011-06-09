package net.weasel.EZBake;

import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandHandler implements CommandExecutor 
{
	public static Oven instance;
	
	public CommandHandler( Oven i )
	{
		instance = i;
	}
	
	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) 
	{
		if( arg0 instanceof Player )
		{
			Player p = (Player)arg0;
			Block targetBlock = p.getTargetBlock( null, 10 );
			
			if( targetBlock.getTypeId() == 35 && targetBlock.getData() == 3 )
			{
				Block blockAbove = targetBlock.getFace(BlockFace.UP);
				
				if( blockAbove.getTypeId() == 0 )
				{
					blockAbove.setTypeId( (byte)92 );
					p.sendMessage( "The air fills with the smell of baking.." );
				}
				
				return true;
			}
			else
			{
				p.sendMessage( "Unknown console command. Type \"help\" for help." );
				return true;
			}
		}
		
		return false;
	}
}
