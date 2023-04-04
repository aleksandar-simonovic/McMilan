package org.unibl.etf.db.dao;

import org.unibl.etf.db.dto.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SalonNamjestajaDAO {
    public SalonNamjestajaDTO dohvatiSalonPoIdentifikatoru(int idSalona) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        SalonNamjestajaDTO retVal = null;

        String query = "SELECT * FROM salon_namjestaja WHERE IdSalonaNamjestaja=?";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setInt(1,idSalona);
            rs = ps.executeQuery();

            if (rs.next())
                retVal = new SalonNamjestajaDTO(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getString(4)
                );
        } catch (SQLException e) {
            e.printStackTrace();
            MySQLUtilities.getInstance().showSQLException(e);
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            MySQLUtilities.getInstance().close(ps, rs);
        }
        return retVal;
    }
}
