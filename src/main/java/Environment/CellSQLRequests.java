package Environment;

public enum CellSQLRequests {

    SQL_SELECT_REQUEST ("SELECT cell.id, cell.row, cell.col, cell.value, cell.table_id FROM cell WHERE cell.id=?"),
    SQL_SELECT_ALL_REQUEST ("SELECT cell.id, cell.row, cell.col, cell.value, cell.table_id FROM cell"),
    SQL_INSERT_ONE_REQUEST ("INSERT INTO cell(cell.row, cell.col, cell.value, cell.table_id) VALUES (?,?,?,?)"),
    SQL_DELETE_ONE_REQUEST ("DELETE FROM cell WHERE cell.id = ?");

    private String sqlRequest;

    CellSQLRequests(String sqlRequest) {
        this.sqlRequest = sqlRequest;
    }

    public String getRequest() {
        return sqlRequest;
    }
}
