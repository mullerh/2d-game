package model;

import persistence.Reader;
import persistence.Savable;

import java.io.PrintWriter;
import java.util.List;

//Represents a cell (meaning a location on the map)
//modified from: https://github.students.cs.ubc.ca/cpsc210-2019w-t2/lab6_y3n2b.git Cell class
public class Cell implements Savable {

    public static final int CELL_PIXELS = 30;

    private int column;
    private int row;

    //EFFECTS: creates a new cell with a row number and column number
    //credit to: https://github.students.cs.ubc.ca/cpsc210-2019w-t2/lab6_y3n2b.git Cell class' constructor
    public Cell(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    // EFFECTS: returns horizontal screen coordinate of top-left corner of cell
    //credit to: https://github.students.cs.ubc.ca/cpsc210-2019w-t2/lab6_y3n2b.git Cell class'
    // getScreenHorizontalCoord method
    public int getScreenHorizontalCoord() {
        return column * CELL_PIXELS;
    }

    // EFFECTS: returns vertical screen coordinate of top-left corner of cell
    //credit to: https://github.students.cs.ubc.ca/cpsc210-2019w-t2/lab6_y3n2b.git Cell class'
    // getScreenVerticalCoord method
    public int getScreenVerticalCoord() {
        return row * CELL_PIXELS;
    }

    //EFFECTS: returns true if Cell components are equal, false otherwise
    //credit to: https://github.students.cs.ubc.ca/cpsc210-2019w-t2/lab6_y3n2b.git Cell class'
    // equals method
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Cell cell = (Cell) o;

        if (column != cell.column) {
            return false;
        }
        return row == cell.row;
    }

    //EFFECTS: returns a hashcode generated from Cell values
    //credit to: https://github.students.cs.ubc.ca/cpsc210-2019w-t2/lab6_y3n2b.git Cell class'
    // hashcode method
    @Override
    public int hashCode() {
        int result = column;
        result = 31 * result + row;
        return result;
    }

    //REQUIRES: first element must be a row and the second must be a column
    //EFFECTS: given components required to make a Cell, construct a cell with said elements
    public static Cell parseCell(List<String> components) {
        String rowString = components.get(0);
        String colString = components.get(1);
        int row = Integer.parseInt(rowString);
        int col = Integer.parseInt(colString);
        return new Cell(row, col);
    }

    // MODIFIES: printWriter
    // EFFECTS: writes the savable(Cell) to printWriter
    //credit: modified from: https://github.students.cs.ubc.ca/CPSC210/TellerApp Account class' save method
    @Override
    public void save(PrintWriter printWriter) {
        printWriter.print(row);
        printWriter.print(Reader.SUB_DELIMITER);
        printWriter.print(column);
    }
}
