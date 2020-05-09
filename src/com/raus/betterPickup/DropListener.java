package com.raus.betterPickup;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDropItemEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class DropListener implements Listener
{
	private Main plugin = JavaPlugin.getPlugin(Main.class);
	private Map<Item, Player> blockDrops = new HashMap<Item, Player>();
	private Map<Item, Map.Entry<Player, Integer>> playerDrops = new HashMap<Item, Map.Entry<Player, Integer>>();

	public DropListener()
	{
		// Scheduler
		new BukkitRunnable()
		{
			@Override
			public void run()
			{
				// Iterate through dropped items
				for (Map.Entry<Item, Player> entry : blockDrops.entrySet())
				{
					// Check if item is ready to be picked up
					if (entry.getKey().getPickupDelay() <= 0)
					{
						// Teleport to player
						entry.getKey().teleport(entry.getValue());
						blockDrops.remove(entry.getKey());
					}
				}
			}
		}.runTaskTimer(plugin, 0L, 1L);
	}

	@EventHandler
	public void onDrop(BlockDropItemEvent event)
	{
		for (Item i : event.getItems())
		{
			// Modify and keep track
			i.setPickupDelay(plugin.blockDropDelay());
			if (plugin.autoPickup())
			{
				blockDrops.put(i, event.getPlayer());
				playerDrops.put(i, new AbstractMap.SimpleEntry<Player, Integer>(event.getPlayer(), plugin.blockDropDelay() + 1));
			}
			if (plugin.invulnerable())
			{
				i.setInvulnerable(true);
			}
		}
	}

	@EventHandler
	public void onDrop(PlayerDropItemEvent event)
	{
		event.getItemDrop().setPickupDelay(plugin.playerDropDelay());
		if (plugin.stealDelay() > 0)
		{
			playerDrops.put(event.getItemDrop(), new AbstractMap.SimpleEntry<Player, Integer>(event.getPlayer(), plugin.stealDelay()));
		}
	}

	@EventHandler
	public void onPickup(EntityPickupItemEvent event)
	{
		// Check if item is in map
		Item item = event.getItem();
		if (playerDrops.containsKey(item))
		{
			Player ply = playerDrops.get(item).getKey();
			int delay = playerDrops.get(item).getValue();

			if (!event.getEntity().equals(ply) && item.getTicksLived() < delay)
			{
				// Cancel if it isn't the owner
				event.setCancelled(true);
			}
			else
			{
				// Remove from the map
				playerDrops.remove(item);
			}
		}
	}
}