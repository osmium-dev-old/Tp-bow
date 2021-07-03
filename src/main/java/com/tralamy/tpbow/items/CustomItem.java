package com.tralamy.tpbow.items;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Map;

public interface CustomItem {

	String displayName();
	Material material();
	ItemMeta meta();
	ArrayList<String> lore();
	Map<Enchantment, Integer> enchantments();

	default ItemStack get() {
		ItemStack item = new ItemStack(material());
		item.setItemMeta(meta());
		item.addUnsafeEnchantments(enchantments());
		return item;
	};

}
