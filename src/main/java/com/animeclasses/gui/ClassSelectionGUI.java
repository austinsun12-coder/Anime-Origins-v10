package com.animeclasses.gui;

import com.animeclasses.AnimeClass;
import com.animeclasses.AnimeClassesPlugin;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ClassSelectionGUI implements Listener {

    private final AnimeClassesPlugin plugin;
    private static final String GUI_TITLE = "§8Select Your Class";
    private static final int ROWS = 3; // 27 slots — enough for 12 classes + borders

    public ClassSelectionGUI(AnimeClassesPlugin plugin) {
        this.plugin = plugin;
    }

    public void open(Player player) {
        Inventory inv = Bukkit.createInventory(null, ROWS * 9, Component.text(GUI_TITLE));

        AnimeClass[] classes = AnimeClass.values();
        // Slots: row 1 (0-8) and row 2 (9-17), skip edges → 1-7, 10-16 = 14 usable, we need 12
        int[] slots = {1, 2, 3, 4, 5, 6, 7, 10, 11, 12, 13, 14};
        for (int i = 0; i < classes.length && i < slots.length; i++) {
            inv.setItem(slots[i], buildIcon(classes[i], player));
        }

        // Slot 26 (bottom-right) = remove class
        ItemStack reset = new ItemStack(Material.BARRIER);
        ItemMeta rm = reset.getItemMeta();
        rm.displayName(Component.text("§cRemove Class"));
        rm.lore(List.of(Component.text("§7Resets you to no class.")));
        reset.setItemMeta(rm);
        inv.setItem(26, reset);

        player.openInventory(inv);
    }

    private ItemStack buildIcon(AnimeClass clazz, Player player) {
        ItemStack item = new ItemStack(clazz.getIcon());
        ItemMeta meta = item.getItemMeta();
        meta.displayName(Component.text(clazz.getColoredName()));

        AnimeClass current = plugin.getClassManager().getClass(player);
        boolean selected = clazz.equals(current);

        List<Component> lore = new ArrayList<>();
        lore.add(Component.text("§e" + clazz.getSeries()));
        lore.add(Component.text(""));

        // Description lines
        for (String line : clazz.getDescription()) {
            lore.add(Component.text(line));
        }

        lore.add(Component.text(""));
        lore.add(Component.text(selected ? "§a§l▶ Currently selected" : "§eClick to select"));
        meta.lore(lore);

        if (selected) {
            meta.addEnchant(org.bukkit.enchantments.Enchantment.UNBREAKING, 1, true);
            meta.addItemFlags(org.bukkit.inventory.ItemFlag.HIDE_ENCHANTS);
        }

        item.setItemMeta(meta);
        return item;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (!(e.getWhoClicked() instanceof Player player)) return;
        if (!e.getView().title().equals(Component.text(GUI_TITLE))) return;

        e.setCancelled(true);

        ItemStack clicked = e.getCurrentItem();
        if (clicked == null || clicked.getType() == Material.AIR) return;

        if (clicked.getType() == Material.BARRIER) {
            plugin.getClassManager().removeClass(player);
            // Clear cooldowns on remove
            plugin.getCooldownManager().clearAll(player.getUniqueId());
            player.closeInventory();
            player.sendMessage("§7Your class has been removed.");
            return;
        }

        for (AnimeClass clazz : AnimeClass.values()) {
            if (clazz.getIcon() == clicked.getType() && clicked.getItemMeta() != null && clicked.getItemMeta().hasDisplayName()) {
                String disp = LegacyComponentSerializer.legacySection()
                        .serialize(clicked.getItemMeta().displayName());
                if (disp.contains(clazz.getDisplayName())) {
                    // Clear cooldowns when switching class
                    plugin.getCooldownManager().clearAll(player.getUniqueId());
                    plugin.getClassManager().setClass(player, clazz);
                    player.closeInventory();
                    return;
                }
            }
        }
    }
}
