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

import org.bukkit.event.Listener;

/**
 * @author Balor (aka Antoine Aflalo)
 *
 */
public class TestListener implements Listener {
	public void onPlayerTest(PlayerTestEvent event) { 
		System.out.print("Event Worked : "+event.getTestString());
		event.getSender().sendMessage(event.getTestString());
	}
}
