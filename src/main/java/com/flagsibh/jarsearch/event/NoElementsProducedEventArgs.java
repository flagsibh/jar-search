/**
 * 
 */
package com.flagsibh.jarsearch.event;

/**
 * @author Idael Banderas
 * @since Mar 25, 2016, 3:14:33 AM
 * 
 */
public class NoElementsProducedEventArgs {
	private int timeout;

	/**
	 * @param timeout
	 */
	public NoElementsProducedEventArgs(int timeout) {
		this.timeout = timeout;
	}

	public int getTimeout() {
		return timeout;
	}
}
