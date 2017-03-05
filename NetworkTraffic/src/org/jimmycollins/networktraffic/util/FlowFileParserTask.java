
package org.jimmycollins.networktraffic.util;

import javafx.concurrent.Task;

public class FlowFileParserTask<Void> extends Task<Void> {

    @Override
    protected void succeeded() {
        super.succeeded();
        // e.g. show "copy finished" dialog
    }

    @Override
    protected void running() {
        super.running();
        // e.g. change mouse courser
    }

    @Override
    protected void failed() {
        super.failed();
        // do stuff if call threw an excpetion
    }

    @Override
    protected Void call() {
        // do expensive the expensive stuff
        
        return null ;
    }
}
