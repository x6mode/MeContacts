package me.com.meContacts.listeners;

import me.com.meContacts.managers.ContactManager;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import java.util.List;

public class PlayerInteractEvent implements Listener {
    @EventHandler
    public void onPlayerClickToPlayer(PlayerInteractEntityEvent event) {
        if (!(event.getRightClicked() instanceof Player target)) return;
        Player player = event.getPlayer();

        if (!player.isSneaking()) return;
        if (!ContactManager.isPlayerHasContacts(target.getPersistentDataContainer())) return;

        List<String> contacts = ContactManager.getContactByPlayer(
                target.getPersistentDataContainer()
        );

        player.sendActionBar(
                Component.text("§f" + contacts.get(0) + " §7/ " + "§b" + contacts.get(1))
        );
    }
}
