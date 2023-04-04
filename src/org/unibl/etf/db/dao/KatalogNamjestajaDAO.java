package org.unibl.etf.db.dao;

import org.unibl.etf.db.dto.KatalogNamjestajaDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class KatalogNamjestajaDAO {
    public KatalogNamjestajaDTO dohvatiKatalogPoIdentifikatoru(Integer id) {
        if(id == null)
            return null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        KatalogNamjestajaDTO retVal = null;

        String query = "SELECT * FROM katalog_namjestaja WHERE IdKataloga=?";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next())
                retVal = new KatalogNamjestajaDTO(
                        rs.getInt(1),
                        rs.getString(2)
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
