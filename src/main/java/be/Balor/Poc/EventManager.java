/************************************************************************
 * This file is part of CustomEvent.									
 *																		
 * CustomEvent is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by	
 * the Free Software Foundation, either version 3 of the License, or		
 * (at your option) any later version.									
 *																		
 * CustomEvent is distributed in the hope that it will be useful,	
 * but WITHOUT ANY WARRANTY; without even the implied warranty of		
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the			
 * GNU General Public License for more details.							
 *																		
 * You should have received a copy of the GNU General Public License
 * along with CustomEvent.  If not, see <http://www.gnu.org/licenses/>.
 ************************************************************************/
package be.Balor.Poc;

import java.lang.reflect.Field;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;

import org.bukkit.event.Event;
import org.bukkit.event.Event.Category;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredListener;

/**
 * @author Balor (aka Antoine Aflalo)
 * 
 */
public class EventManager {
	@SuppressWarnings("unchecked")
	public static void addEvent(Plugin plugin, String name, Category type) {
		DynamicEnum.addEnum(Event.Type.class, name, new Class<?>[] { Category.class },
				new Object[] { type });
		try {
			PluginManager pm = plugin.getServer().getPluginManager();
			Object listeners = getPrivateField(pm, "listeners");
			if (listeners instanceof EnumMap) {
				HashMap<Event.Type, SortedSet<RegisteredListener>> tmp = new HashMap<Event.Type, SortedSet<RegisteredListener>>(
						((Map<Event.Type, SortedSet<RegisteredListener>>) getPrivateField(plugin
								.getServer().getPluginManager(), "listeners")));
				tmp.put(Event.Type.valueOf(name), null);
				setPrivateField(pm, "listeners", tmp);
			} else
				(((Map<Event.Type, SortedSet<RegisteredListener>>) getPrivateField(plugin
						.getServer().getPluginManager(), "listeners"))).put(
						Event.Type.valueOf(name), null);
			setFieldAccessibility(pm, "listeners", false);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static Object getPrivateField(Object object, String field) throws SecurityException,
			NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
		Class<?> clazz = object.getClass();
		Field objectField = clazz.getDeclaredField(field);
		objectField.setAccessible(true);
		return objectField.get(object);
	}

	private static void setPrivateField(Object object, String field, Object value)
			throws SecurityException, NoSuchFieldException, IllegalArgumentException,
			IllegalAccessException {
		Class<?> clazz = object.getClass();
		Field objectField = clazz.getDeclaredField(field);
		objectField.setAccessible(true);
		objectField.set(object, value);
	}

	private static void setFieldAccessibility(Object object, String field, boolean accessibility)
			throws SecurityException, NoSuchFieldException, IllegalArgumentException,
			IllegalAccessException {
		Class<?> clazz = object.getClass();
		Field objectField = clazz.getDeclaredField(field);
		objectField.setAccessible(accessibility);

	}
}
