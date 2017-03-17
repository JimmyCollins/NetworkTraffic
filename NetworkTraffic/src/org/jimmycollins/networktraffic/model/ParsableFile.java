
package org.jimmycollins.networktraffic.model;

import java.io.File;
import org.jimmycollins.networktraffic.ParsingException;

public abstract class ParsableFile {
    
    private final File File;

    public ParsableFile(File File) {
        this.File = File;
    }
    
    public File GetFile()
    {
        return File;
    }
	
    public abstract FlowFileStats ParseFile(FlowFileStats Stats) throws ParsingException;
    
    // Other file types could be support here if required and implemented in a subclass.
}
