package org.unibl.etf.db.dao;

import org.unibl.etf.db.dto.*;

import java.sql.*;
import java.util.*;

public class OsobaDAO {
    public OsobaDTO dohvatiOsobuPoJmbg(String jmbg) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        OsobaDTO retVal = null;

        String query = "SELECT * FROM osoba WHERE JMBG=?";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setString(1,jmbg);
            rs = ps.executeQuery();

            if (rs.next())
                retVal = new OsobaDTO(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDate(4),
                        rs.getString(5),
                        rs.getInt(6)
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

    public List<OsobaDTO> dohvatiSveOsobe() {
        Connection conn = null;
        Statement s = null;
        ResultSet rs = null;

        List<OsobaDTO> retVal = new ArrayList<OsobaDTO>();

        String query = "SELECT * FROM osoba";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            s = conn.createStatement();
            rs = s.executeQuery(query);

            while(rs.next())
                retVal.add(new OsobaDTO(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDate(4),
                        rs.getString(5),
                        rs.getInt(6))
                );
        } catch (SQLException e) {
            e.printStackTrace();
            MySQLUtilities.getInstance().showSQLException(e);
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            MySQLUtilities.getInstance().close(s, rs);
        }
        return retVal;
    }
}
