package com.flagsibh.jarsearch.event;

/**
 *
 * @author flags, Jan 9, 2016, 8:19:42 PM
 */
public class FinishedExecutionEventArgs {

    private double executionTime;

    public FinishedExecutionEventArgs(double executionTime) {
        this.executionTime = executionTime;
    }

    public double getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(double executionTime) {
        this.executionTime = executionTime;
    }

}
