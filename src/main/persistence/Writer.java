package persistence;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

// A writer that can write character data to a file
//credit for class: https://github.students.cs.ubc.ca/CPSC210/TellerApp Writer class
public class Writer {
    private PrintWriter printWriter;

    // EFFECTS: constructs a writer that will write data to file
    //credit: https://github.students.cs.ubc.ca/CPSC210/TellerApp Writer class' constructor
    public Writer(File file) throws FileNotFoundException, UnsupportedEncodingException {
        printWriter = new PrintWriter(file, "UTF-8");
    }

    // MODIFIES: this
    // EFFECTS: writes savable to file
    //credit: https://github.students.cs.ubc.ca/CPSC210/TellerApp Writer class' write method
    public void write(Savable savable) {
        savable.save(printWriter);
    }

    // MODIFIES: this
    // EFFECTS: close print writer
    // NOTE: you MUST call this method when you are done writing data!
    //credit: https://github.students.cs.ubc.ca/CPSC210/TellerApp
    public void close() {
        printWriter.close();
    }
}
