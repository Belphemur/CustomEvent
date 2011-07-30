/************************************************************************
 * This file is part of AdminCmd.									
 *																		
 * AdminCmd is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by	
 * the Free Software Foundation, either version 3 of the License, or		
 * (at your option) any later version.									
 *																		
 * AdminCmd is distributed in the hope that it will be useful,	
 * but WITHOUT ANY WARRANTY; without even the implied warranty of		
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the			
 * GNU General Public License for more details.							
 *																		
 * You should have received a copy of the GNU General Public License
 * along with AdminCmd.  If not, see <http://www.gnu.org/licenses/>.
 ************************************************************************/
package be.Balor.Poc;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;

/**
 * @author Balor (aka Antoine Aflalo)
 * 
 */
public class PlayerTestEvent extends Event implements Cancellable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -890300077534613924L;
	private boolean cancelled;
	private String testString;

	/**
	 * @param type
	 */
	public PlayerTestEvent(String testString) {
		super(Event.Type.valueOf("PLAYER_TEST"));
		this.testString = testString;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bukkit.event.Cancellable#isCancelled()
	 */
	public boolean isCancelled() {
		return cancelled;
	}

	public void setCancelled(boolean cancel) {
		cancelled = cancel;
	}

	/**
	 * @return the testString
	 */
	public String getTestString() {
		return testString;
	}

}
