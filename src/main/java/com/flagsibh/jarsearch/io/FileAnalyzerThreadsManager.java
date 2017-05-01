package com.flagsibh.jarsearch.io;

import com.flagsibh.jarsearch.cfg.Configurator;
import com.flagsibh.jarsearch.event.FinishedExecutionEventArgs;
import com.flagsibh.jarsearch.event.NoElementsProducedEventArgs;
import com.jayway.awaitility.Awaitility;
import com.jayway.awaitility.core.ConditionTimeoutException;

import java.io.File;
import java.lang.Thread.State;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author flags, Jan 9, 2016, 9:59:42 PM
 */
public class FileAnalyzerThreadsManager extends Observable implements Runnable {

	private static final int MILLISECONDS = 1000;
	private static final int TIMEOUT = 15;

	private final BlockingQueue<File> fileQueue;
	private final String searchText;
	private long startTime;
	private long endTime;
	private final List<Observer> analyzerObservers = new ArrayList<>();
	private final List<Thread> threads = new ArrayList<>();

	public FileAnalyzerThreadsManager(BlockingQueue<File> fileQueue, String searchText) {
		this.fileQueue = fileQueue;
		this.searchText = searchText;
	}

	@Override
	public void run() {
		startTime = System.currentTimeMillis();

		// wait for producers to put a file into the queue
		try {
			Awaitility.await().atMost(TIMEOUT, TimeUnit.SECONDS).until(new Callable<Boolean>() {

				@Override
				public Boolean call() throws Exception {
					return !fileQueue.isEmpty();
				}
			});
		} catch (ConditionTimeoutException e) {
			Logger.getLogger(FileAnalyzerThreadsManager.class.getName()).log(Level.SEVERE, null, e);
			notifyObservers(new NoElementsProducedEventArgs(TIMEOUT));
			return;
		}

		for (int i = 0; i < Configurator.getInstance().getNumberOfThreads(); i++) {
			FileAnalyzer fileAnalyzer = new FileAnalyzer(fileQueue, searchText);
			for (Observer anaObserver : analyzerObservers) {
				fileAnalyzer.addObserver(anaObserver);
			}
			Thread t = new Thread(fileAnalyzer);
			threads.add(t);
			t.start();
		}

		// wait until all threads are waiting or blocked
		Awaitility.with().pollInterval(20, TimeUnit.MILLISECONDS).await().forever().until(new Callable<Boolean>() {

			@Override
			public Boolean call() throws Exception {
				for (Thread t : threads) {
					if (!t.getState().equals(State.WAITING)) {
						return false;
					}
				}
				return true;
			}
		});

		endTime = System.currentTimeMillis();

		notifyObservers(new FinishedExecutionEventArgs(getExecutionTime()));
	}

	public void addAnalyzerObserver(Observer o) {
		if (!analyzerObservers.contains(o)) {
			analyzerObservers.add(o);
		}
	}

	public double getExecutionTime() {
		return (endTime - startTime) / (double) MILLISECONDS;
	}

	@Override
	public void notifyObservers(Object arg) {
		setChanged();
		super.notifyObservers(arg);
	}
}
