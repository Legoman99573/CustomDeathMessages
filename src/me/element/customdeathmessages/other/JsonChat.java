package me.element.customdeathmessages.other;

import java.lang.reflect.Method;

import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class JsonChat {

	private static String convertItemStackToJson(ItemStack itemStack)
	{
		Class<?> craftItemStackClazz = ReflectionUtil.getOBCClass("inventory.CraftItemStack");
		Method asNMSCopyMethod = ReflectionUtil.getMethod(craftItemStackClazz, "asNMSCopy", ItemStack.class);

		Class<?> nmsItemStackClazz = ReflectionUtil.getNMSClass("ItemStack");
		Class<?> nbtTagCompoundClazz = ReflectionUtil.getNMSClass("NBTTagCompound");
		Method saveNmsItemStackMethod = ReflectionUtil.getMethod(nmsItemStackClazz, "save", nbtTagCompoundClazz);

		Object nmsNbtTagCompoundObj;
		Object nmsItemStackObj;
		Object itemAsJsonObject = null;

		try 
		{
			nmsNbtTagCompoundObj = nbtTagCompoundClazz.getConstructor().newInstance();
			nmsItemStackObj = asNMSCopyMethod.invoke(null, itemStack);
			itemAsJsonObject = saveNmsItemStackMethod.invoke(nmsItemStackObj, nmsNbtTagCompoundObj);
		} 
		catch (Throwable t) 
		{
		}

		return itemAsJsonObject.toString();
	}

	public void sendItemTooltipMessage(String message, ItemStack item) 
	{
		String itemJson = convertItemStackToJson(item);

		BaseComponent [] hoverEventComponents = new BaseComponent []
				{
						new TextComponent(itemJson)
				};

		HoverEvent event = new HoverEvent(HoverEvent.Action.SHOW_ITEM, hoverEventComponents);

		TextComponent component = new TextComponent(message);
		component.setHoverEvent(event);

		Bukkit.spigot().broadcast(component);
	}
}
