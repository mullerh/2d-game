package model;

import persistence.Reader;
import persistence.Savable;

import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;

public class MapItems implements Savable {

    Map<Cell, Item> itemMap;

    public MapItems() {
        itemMap = new LinkedHashMap<Cell, Item>();
    }

    public void addItemMap(Cell cell, Item item) {
        itemMap.put(cell, item);
    }

    public Map<Cell, Item> getItemMap() {
        return itemMap;
    }

    @Override
    public void save(PrintWriter printWriter) {
        printWriter.print("\n");
        for (Cell cell : itemMap.keySet()) {
            cell.save(printWriter);
            printWriter.print(Reader.SUPER_DELIMITER);
            itemMap.get(cell).save(printWriter);
            printWriter.print(Reader.LIST_DELIMITER);
        }
        printWriter.print(Reader.LIST_DELIMITER);
    }
}
