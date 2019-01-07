package Environment;

import java.util.List;

public class Table {

    private int id;
    private String name;
    private List<Cell> cells;

    public Table() {

    }

    public Table(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Cell> getTable() {
        return cells;
    }

    public void setTable(List<Cell> cells) {
        this.cells = cells;
    }

    public void addCell(Cell cell) {
        this.cells.add(cell);
    }

}
