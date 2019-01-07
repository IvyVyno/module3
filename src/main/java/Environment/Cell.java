package Environment;

public class Cell {

    private int row;
    private int col;
    private String cellValue;
    private int id;
    private int tableNumber;

    public Cell() {
    }

    public Cell(int row, int col, String cellValue, int id, int tableNumber) {
        this.row = row;
        this.col = col;
        this.cellValue = cellValue;
        this.id = id;
        this.tableNumber = tableNumber;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public String getCellValue() {
        return cellValue;
    }

    public void setCellValue(String cellValue) {
        this.cellValue = cellValue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }
}
