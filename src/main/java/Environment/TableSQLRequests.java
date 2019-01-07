package Environment;

public enum TableSQLRequests {

    SQL_SELECT_REQUEST ("SELECT table.id, table.name FROM table WHERE table.id=?"),
    SQL_SELECT_ALL_REQUEST ("SELECT table.id, table.name FROM table"),
    SQL_INSERT_ONE_REQUEST ("INSERT INTO table(table.name) VALUES (?)"),
    SQL_DELETE_ONE_REQUEST ("DELETE FROM table WHERE table.id = ?");

    private String sqlRequest;

    TableSQLRequests(String sqlRequest) {
        this.sqlRequest = sqlRequest;
    }

    public String getRequest() {
        return sqlRequest;
    }
}
