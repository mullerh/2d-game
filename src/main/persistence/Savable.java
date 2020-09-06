package persistence;

import java.io.PrintWriter;

//credit for class: https://github.students.cs.ubc.ca/CPSC210/TellerApp
//Data which can be saved to a file
public interface Savable {
    // MODIFIES: printWriter
    // EFFECTS: writes the savable to printWriter
    void save(PrintWriter printWriter);
}
