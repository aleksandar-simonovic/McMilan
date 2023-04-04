package org.unibl.etf.db.dao;

import org.unibl.etf.db.dto.*;
import java.sql.*;

public class KorisnickiNalogDAO {
    private String dohvatiKorisnickiNalogQuery = "{CALL login_provjera(?, ?, ?)}";

    public KorisnickiNalogDTO dohvatiKorisnickiNalog(String username, String password) {
        boolean retVal = false;
        Connection conn = null;
        CallableStatement cs = null;

        try {
            conn = ConnectionPool.getInstance().checkOut();
            cs = conn.prepareCall(dohvatiKorisnickiNalogQuery);
            cs.setString(1, username);
            cs.setString(2, password);
            cs.registerOutParameter(3, Types.BOOLEAN);
            cs.execute();
            retVal = cs.getBoolean(3);
            if(retVal)
                return new KorisnickiNalogDTO(username, password);
        } catch (SQLException e) {
            e.printStackTrace();
            MySQLUtilities.getInstance().showSQLException(e);
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            MySQLUtilities.getInstance().close(cs);
        }
        return null;
    }
}
