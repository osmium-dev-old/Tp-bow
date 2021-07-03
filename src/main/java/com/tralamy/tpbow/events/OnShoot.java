package com.tralamy.tpbow.events;

import com.tralamy.tpbow.Main;
import com.tralamy.tpbow.items.TPBow;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class OnShoot implements Listener {

	private Map<LivingEntity, Entity> arrows = new HashMap<>();
	private Map<Entity, LivingEntity> livingEntity = new HashMap<>();

	@EventHandler
	private void onShoot(EntityShootBowEvent e) {
		if (Objects.requireNonNull(e.getBow()).getItemMeta().getLore().equals(Objects.requireNonNull(new TPBow().get().getItemMeta()).getLore())) {
			LivingEntity entity = e.getEntity();
			if (!arrows.containsKey(e.getEntity())) {
				arrows.put(entity, e.getProjectile());
				livingEntity.put(e.getProjectile(), entity);
			}else {
				if (arrows.get(entity).getTicksLived() >= Main.getPlugin(Main.class).getConfig().getInt("max-arrow-tick-live")) {
					arrows.remove(entity);
					onShoot(e);
					return;
				}
				entity.sendMessage(ChatColor.RED + "Only one arrow at a time!" + ChatColor.YELLOW + " Wait, it is maybe stuck.");
				e.setCancelled(true);
			}

			if (entity instanceof Player) {
				int i = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(Main.class), () -> {
					Location l = e.getProjectile().getLocation();
					((Player) entity).spawnParticle(Particle.valueOf(Main.getPlugin(Main.class).getConfig().getString("particle")), l, 2);
				}, 1, 1);
				Bukkit.getScheduler().runTaskTimer(Main.getPlugin(Main.class), () -> Bukkit.getScheduler().cancelTask(i), 30, 1);
			}
		}
	}

	@EventHandler
	private void onArrowHit(ProjectileHitEvent e) {
		if (livingEntity.containsKey(e.getEntity())) {
			LivingEntity p = livingEntity.get(e.getEntity());
			Location target = e.getEntity().getLocation();
			target.setYaw(p.getLocation().getYaw());
			p.teleport(target);
			if (p instanceof Player) {
				((Player) p).playSound(p.getLocation(), Sound.ITEM_CHORUS_FRUIT_TELEPORT, 1f, 1f);
			}
			livingEntity.remove(e.getEntity());
			arrows.remove(p);
		}
	}
}
