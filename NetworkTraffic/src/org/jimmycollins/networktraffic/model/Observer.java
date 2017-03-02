package org.jimmycollins.networktraffic.model;
//https://stackoverflow.com/questions/42566161/javafx-and-the-observer-pattern-updating-a-ui
public abstract class Observer
//public interface Observer
{
   public FlowFileStats stats;
   
   public abstract void update();
}