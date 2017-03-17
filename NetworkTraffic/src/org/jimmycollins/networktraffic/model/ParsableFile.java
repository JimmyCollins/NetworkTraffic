
package org.jimmycollins.networktraffic.model;

import java.io.File;
import org.jimmycollins.networktraffic.ParsingException;

/**
 * Defines a parasable file
 */
public abstract class ParsableFile {
    
    private final File File;

    /**
     * Constructor
     * @param File The file to parse 
     */
    public ParsableFile(File File) {
        this.File = File;
    }
    
    /**
     * Return the file to be parsed
     * @return The file to be parsed
     */
    public File GetFile()
    {
        return File;
    }

    /**
     * Abstract method for parsing
     * @param Stats The statistics object to populate during parsing
     * @return The statistics object populated during parsing
     * @throws ParsingException Exception if one occurs during parsing
     */
    public abstract FlowFileStats ParseFile(FlowFileStats Stats) throws ParsingException;
    
    // Other file types could be support here if required and implemented in a subclass.
}
