package persistence;

import java.io.PrintWriter;

// Represents data that can be saved to file
public interface Saveable {

    // MODIFIES: printWriter
    // EFFECTS: writes the saveable data to printWriter
    void save(PrintWriter printWriter);
}
