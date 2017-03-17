
package org.jimmycollins.networktraffic;

/**
 * Defines a custom exception that is thrown if a parsing error occurs
 */
public class ParsingException extends Exception {
    
    /**
     * Default Constructor
     */
    public ParsingException() {}
    
    /**
     * Constructor
     * @param message The exception message
     */
    public ParsingException(String message) 
    {
        super(message);
    } 
}