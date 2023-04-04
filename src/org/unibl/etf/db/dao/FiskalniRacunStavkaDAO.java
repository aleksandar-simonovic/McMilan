package org.unibl.etf.db.dao;

import javafx.scene.control.Alert;
import org.unibl.etf.db.dto.*;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FiskalniRacunStavkaDAO {
    public List<FiskalniRacunStavkaDTO> dohvatiStavkeZaRacunPoIdentifikatoru(int idRacuna) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<FiskalniRacunStavkaDTO> retVal = new ArrayList<>();

        String query = "SELECT * FROM fiskalni_racun_stavka WHERE IdRacuna=?";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setInt(1, idRacuna);
            rs = ps.executeQuery();

            while (rs.next())
                retVal.add(new FiskalniRacunStavkaDTO(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getBigDecimal(3),
                        rs.getInt(4))
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

    public boolean obrisiStavkuSaRacuna(Integer idRacuna, Integer idModela) {
        boolean retVal = false;
        Connection conn = null;
        CallableStatement cs = null;

        String query = "{CALL brisi_stavku_sa_racuna(?, ?, ?)}";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            cs = conn.prepareCall(query);
            cs.setInt(1, idRacuna);
            cs.setInt(2, idModela);
            cs.registerOutParameter(3, Types.BOOLEAN);
            cs.execute();
            retVal = cs.getBoolean(3);
        } catch (SQLException e) {
            e.printStackTrace();
            MySQLUtilities.getInstance().showSQLException(e);
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            MySQLUtilities.getInstance().close(cs);
        }
        return retVal;
    }
    public boolean daLiRacunSadrziModel(int idRacuna, int idModela) {
        boolean retVal = false;

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM fiskalni_racun_stavka WHERE IdRacuna=? AND IdModelaNamjestaja=?";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setInt(1, idRacuna);
            ps.setInt(2, idModela);
            rs = ps.executeQuery();

            if(rs.next())
                retVal = true;
        } catch (SQLException e) {
            e.printStackTrace();
            MySQLUtilities.getInstance().showSQLException(e);
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            MySQLUtilities.getInstance().close(ps, rs);
        }
        return retVal;
    }

    public boolean dodaj(Integer idRacuna, Integer idModela, Integer kolicina) {
        boolean retVal = false;
        Connection conn = null;
        PreparedStatement ps = null;

        BigDecimal prodajnaCijena = new ModelNamjestajaDAO().dohvatiModelNamjestajaPoIdentifikatoru(idModela).getProdajnaCijena();
        BigDecimal total = prodajnaCijena.multiply(BigDecimal.valueOf(kolicina));

        String query = "INSERT INTO FISKALNI_RACUN_STAVKA (IdRacuna, IdModelaNamjestaja, Cijena, Kolicina) VALUES (?, ?, ?, ?)";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);

            ps.setInt(1, idRacuna);
            ps.setInt(2, idModela);
            ps.setBigDecimal(3, total);
            ps.setInt(4, kolicina);

            retVal = ps.executeUpdate() == 1;

        } catch (SQLException e) {
            e.printStackTrace();
            MySQLUtilities.getInstance().showSQLException(e);
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            MySQLUtilities.getInstance().close(ps);
        }
        return retVal;
    }
}
