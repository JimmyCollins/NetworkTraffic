package org.jimmycollins.networktraffic.model;

public abstract class Observer
{
   public FlowFileStats stats;
   
   public abstract void update();
}