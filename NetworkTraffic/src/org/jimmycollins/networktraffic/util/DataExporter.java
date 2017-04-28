
package org.jimmycollins.networktraffic.util;

import org.jimmycollins.networktraffic.model.ExportCommand;

/**
 * Command Pattern Implementation - Receiver
 */
public class DataExporter 
{
  private ExportCommand command;

  /**
   * Set the command type
   * @param command The command type
   */
  public void setCommand(ExportCommand command)
  {
      this.command = command;
  }

  /**
   * Export the data
   */
  public void export()
  {
      command.execute();
  }
}