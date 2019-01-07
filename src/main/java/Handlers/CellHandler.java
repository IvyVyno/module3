package Handlers;

import Environment.Cell;
import DBCP.DBCPool;
import Environment.CellSQLRequests;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CellHandler implements Handler<Cell> {

    @Override
    public Cell get(int id) {
        Cell cell = new Cell();
        try (Connection connection = DBCPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(CellSQLRequests.SQL_SELECT_REQUEST.getRequest());
             ResultSet rs = ps.executeQuery();) {
                ps.setInt(1, id);
                while(rs.next()) {
                    cell.setId(rs.getInt(1));
                    cell.setRow(rs.getInt(2));
                    cell.setCol(rs.getInt(3));
                    cell.setCellValue(rs.getString(4));
                    cell.setTableNumber(rs.getInt(5));
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cell;
    }

    @Override
    public List<Cell> get() {
        List cells = new ArrayList<Cell>();
        try (Connection connection = DBCPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(CellSQLRequests.SQL_SELECT_ALL_REQUEST.getRequest());
             ResultSet rs = ps.executeQuery();) {
            while(rs.next()) {
                Cell cell = new Cell();
                cell.setId(rs.getInt(1));
                cell.setRow(rs.getInt(2));
                cell.setCol(rs.getInt(3));
                cell.setCellValue(rs.getString(4));
                cell.setTableNumber(rs.getInt(5));
                cells.add(cell);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cells;
    }

    @Override
    public void add(Cell object) {
        try (Connection connection = DBCPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(CellSQLRequests.SQL_INSERT_ONE_REQUEST.getRequest());) {
            connection.setAutoCommit(false);
            ps.setInt(1, object.getRow());
            ps.setInt(2, object.getCol());
            ps.setString(3, object.getCellValue());
            ps.setInt(4, object.getTableNumber());
            ps.execute();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void add(List<Cell> objects) {
        for(Cell c : objects) {
            add(c);
        }
    }

    @Override
    public void delete(int id) {
        try (Connection connection = DBCPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(CellSQLRequests.SQL_DELETE_ONE_REQUEST.getRequest())) {
            ps.setInt(1, id);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(List<Cell> objects) {
        for(Cell c : objects) {
            delete(c.getId());
        }
    }

    @Override
    public Object update() {
        return null;
    }
}
