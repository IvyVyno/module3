package Handlers;

import DBCP.DBCPool;
import Environment.Cell;
import Environment.Table;
import Environment.TableSQLRequests;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TableHandler implements Handler<Table> {

    private static CellHandler cellHandler;

    static {
        cellHandler = new CellHandler();
    }

    @Override
    public Table get(int id) {
        Table table = new Table();
        try (Connection connection = DBCPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(TableSQLRequests.SQL_SELECT_REQUEST.getRequest());
             ResultSet rs = ps.executeQuery();) {
            ps.setInt(1,id);
            while(rs.next()) {
                table.setId(rs.getInt(1));
                table.setName(rs.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return table;
    }

    @Override
    public List<Table> get() {
        List<Table> tables = new ArrayList<>();
        try(Connection connection = DBCPool.getConnection();
            PreparedStatement ps = connection.prepareStatement(TableSQLRequests.SQL_SELECT_ALL_REQUEST.getRequest());
            ResultSet rs = ps.executeQuery();) {
                while(rs.next()) {
                    Table table = new Table();
                    table.setId(rs.getInt(1));
                    table.setName(rs.getString(2));
                    tables.add(table);
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tables;
    }

    @Override
    public void add(Table object) {
        try(Connection connection = DBCPool.getConnection();
            PreparedStatement ps = connection.prepareStatement(TableSQLRequests.SQL_INSERT_ONE_REQUEST.getRequest());) {
            connection.setAutoCommit(false);
            ps.setString(1, object.getName());
            ps.execute();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void add(List<Table> objects) {
        for(Table table : objects) {
            add(table);
        }
    }

    @Override
    public void delete(int id) {
        try(Connection connection = DBCPool.getConnection();
            PreparedStatement ps = connection.prepareStatement(TableSQLRequests.SQL_DELETE_ONE_REQUEST.getRequest());) {
            ps.setInt(1, id);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(List<Table> objects) {
        for (Table table : objects) {
            delete(table.getId());
        }
    }

    @Override
    public Object update() {
        return null;
    }

    public static List<Cell> getAllCells(int id) {
        return tableSort(cellHandler.get());
    }

    public static List<Cell> tableSort(List<Cell> cells) {
        Collections.sort(cells, new Comparator<Cell>() {

            @Override
            public int compare(Cell o1, Cell o2) {
                int res = 0;
                if (o1.getRow() < o2.getRow())
                    res = -1;
                if (o1.getRow() > o2.getRow())
                    res = 1;
                if (o1.getRow() == o2.getRow()) {
                    if (o1.getCol() < o2.getCol())
                        res = -1;
                    if (o1.getCol() > o2.getCol())
                        res = 1;
                    if (o1.getCol() == o2.getCol())
                        res = 0;
                }
                return res;
            }
        });
        return cells;
    }

    public static void deleteRow(List<Cell> cells, int row) {
        for (Cell c : cells) {
            if(c.getRow() == row) {
                cells.remove(c);
                cellHandler.delete(c.getId());
            }
        }
    }

    public static void deleteCol(List<Cell> cells, int col) {
        for (Cell c : cells) {
            if(c.getCol() == col) {
                cells.remove(c);
                cellHandler.delete(c.getId());
            }
        }
    }

}
