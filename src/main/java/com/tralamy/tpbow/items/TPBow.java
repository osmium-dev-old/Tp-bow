package com.tralamy.tpbow.items;

import com.tralamy.tpbow.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class TPBow implements CustomItem {
	@Override
	public String displayName() {
		return ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(Main.getPlugin(Main.class).getConfig().getString("bow-name")));
	}

	@Override
	public Material material() {
		return Material.BOW;
	}

	@Override
	public ItemMeta meta() {
		ItemMeta meta = new ItemStack(material()).getItemMeta();
		assert meta != null;
		meta.setDisplayName(displayName());
		meta.setLore(lore());
		meta.setUnbreakable(true);
		return meta;
	}

	@Override
	public ArrayList<String> lore() {
		ArrayList<String> lines = new ArrayList<>();
		Main.getPlugin(Main.class).getConfig().getStringList("bow-lore").forEach((e) -> lines.add(ChatColor.translateAlternateColorCodes('&', e)));
		return lines;
	}

	@Override
	public Map<Enchantment, Integer> enchantments() {
		Map<Enchantment, Integer> map = new HashMap<>();
		Main.getPlugin(Main.class).getConfig().getStringList("bow-enchantments").forEach((e) -> {
			String[] enchant = e.split(":");
			map.put(Enchantment.getByKey(NamespacedKey.minecraft(enchant[0])), Integer.parseInt(enchant[1]));
		});
		return map;
	}

}
