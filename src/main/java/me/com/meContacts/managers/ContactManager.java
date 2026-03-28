package me.com.meContacts.managers;

import io.papermc.paper.persistence.PersistentDataContainerView;
import me.com.meContacts.constants.MetaFieldEnum;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.List;

public class ContactManager {
    public static void setPlayerContacts(
            Player player,
            String irl_name,
            String telegram
    ) {
        PersistentDataContainer cont = player.getPersistentDataContainer();
        PersistentContainerManager.setMeta(
                cont,
                MetaFieldEnum.IRL_NAME,
                PersistentDataType.STRING,
                irl_name
        );

        PersistentContainerManager.setMeta(
                cont,
                MetaFieldEnum.TELEGRAM,
                PersistentDataType.STRING,
                telegram
        );
    }

    public static boolean isPlayerHasContacts(PersistentDataContainerView cont) {
        return PersistentContainerManager.isHasMeta(cont, MetaFieldEnum.IRL_NAME) &&
                PersistentContainerManager.isHasMeta(cont, MetaFieldEnum.TELEGRAM);
    }

    public static List<String> getContactByPlayer(PersistentDataContainerView cont) {
        return List.of(
                PersistentContainerManager
                        .getMeta(cont, MetaFieldEnum.IRL_NAME, PersistentDataType.STRING),
                PersistentContainerManager
                        .getMeta(cont, MetaFieldEnum.TELEGRAM, PersistentDataType.STRING)
        );
    }
}