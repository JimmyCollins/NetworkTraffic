
package org.jimmycollins.networktraffic.util;

import org.jimmycollins.networktraffic.model.ExportCommand;

/**
 * Command Pattern Implementation - Receiver
 */
public class DataExporter 
{
  private ExportCommand command;

  public void setCommand(ExportCommand command)
  {
      this.command = command;
  }

  public void export()
  {
      command.execute();
  }
}