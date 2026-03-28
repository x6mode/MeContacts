package me.com.meContacts.commands;

import io.papermc.paper.persistence.PersistentDataContainerView;
import me.com.meContacts.managers.ContactManager;
import me.com.meContacts.utils.Utils;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

import java.util.List;

public class MainCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length == 0) return true;

        switch (strings[0]) {
            case "set":
                if (strings.length != 3) break;
                if (!(commandSender instanceof Player)) break;

                ContactManager
                        .setPlayerContacts(
                                (Player) commandSender,
                                strings[1],
                                strings[2]
                        );
                break;

            case "request":
                break;

            case "get":
                if (strings.length != 2) break;

                Object player = Utils.getPlayerInAnyOnline(strings[1]);
                if (player == null) break;
                if (player instanceof OfflinePlayer) {
                    PersistentDataContainerView cont = ((OfflinePlayer) player).getPersistentDataContainer();

                    if (!ContactManager.isPlayerHasContacts(cont)) break;

                    List<String> contacts = ContactManager.getContactByPlayer(cont);
                    sendPrettyMessage(commandSender, strings[1], contacts);
                }

                break;
        }
        return true;
    }

    private void sendPrettyMessage(
            CommandSender sender,
            String requestedNick,
            List<String> contacts
    ) {
        String name = contacts.get(0);
        String telegram = contacts.get(1);

        sender.sendMessage(
                getMessageComponent(
                        requestedNick,
                        name,
                        telegram
                )
        );
    }

    private Component getMessageComponent(String requestedNick, String name, String telegram) {
        return Component
                .text("\nИнформация о §7" + requestedNick + '\n')
                .append(Component.text("Имя: §7" + name + '\n'))
                .append(Component.text("Телеграмм: §b" + telegram + '\n'));
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String [] strings) {
        if (strings.length == 1) {
            return List.of("get", "set");
        } else if (strings.length == 2 && strings[0].equals("set")) {
            return List.of("Максим");
        } else if (strings.length == 3 && strings[0].equals("set")) {
            return List.of("@maskim67");
        } else if (strings.length == 2 && strings[0].equals("get")) {
            return Bukkit
                    .getOnlinePlayers()
                    .stream()
                    .map(Player::getName)
                    .filter(name -> name.toLowerCase().startsWith(
                            strings[1].toLowerCase()
                    ))
                    .toList();
        }

        return List.of();
    }
}

