package me.itzmarcus.pingtracker.utils;

import io.puharesource.mc.titlemanager.api.ActionbarTitleObject;
import io.puharesource.mc.titlemanager.api.TitleObject;
import org.bukkit.entity.Player;

/**
 * Created by marcus on 22-08-2016.
 */
public class TitleManagerAPI {

    public void sendActionbarMessage(Player player, String message) {
        new ActionbarTitleObject(message).send(player);
    }

    public void sendFloatingText(Player player, String title, int fadeIn, int stay, int fadeOut) {
        new TitleObject(title, TitleObject.TitleType.TITLE).setFadeIn(fadeIn).setStay(stay).setFadeOut(fadeOut).send(player);
    }
}
