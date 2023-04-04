package org.unibl.etf.db.dao;

import javafx.scene.control.Alert;

import java.sql.*;

public class ModelNamjestajaSeCuvaUSalonuNamjestajaDAO {
    public boolean dodaj(Integer idModelaNamjestaja, Integer idSalonaNamjestaja, Integer kolicina) {
        boolean retVal = false;
        Connection conn = null;
        PreparedStatement ps = null;

        String query = "INSERT INTO model_namjestaja_se_cuva_u_salonu_namjestaja (IdModelaNamjestaja, IdSalonaNamjestaja, Kolicina) VALUES (?, ?, ?)";
        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);

            ps.setInt(1, idModelaNamjestaja);
            ps.setInt(2, idSalonaNamjestaja);
            ps.setInt(3, kolicina);

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
    public boolean daLiModelNamjestajaPostojiUSalonu(int idSalonaNamjestaja, int idModelaNamjestaja) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        boolean retVal = true;

        String query = "SELECT * FROM model_namjestaja_se_cuva_u_salonu_namjestaja WHERE IdModelaNamjestaja=? and IdSalonaNamjestaja=?";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setInt(1, idModelaNamjestaja);
            ps.setInt(2, idSalonaNamjestaja);
            rs = ps.executeQuery();
            if(rs.next()) {
                retVal = true;
            } else {
                retVal = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            MySQLUtilities.getInstance().showSQLException(e);
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            MySQLUtilities.getInstance().close(ps, rs);
        }
        return retVal;
    }

    public boolean azurirajKolicinu(Integer idModelaNamjestaja, Integer idSalonaNamjestaja, Integer kolicina) {
        boolean retVal = false;
        Connection conn = null;
        PreparedStatement ps = null;

        String query = "UPDATE model_namjestaja_se_cuva_u_salonu_namjestaja SET Kolicina=? WHERE IdModelaNamjestaja=? AND IdSalonaNamjestaja=?";
        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);

            ps.setInt(1, kolicina);
            ps.setInt(2, idModelaNamjestaja);
            ps.setInt(3, idSalonaNamjestaja);

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

    public boolean obrisi(Integer idModelaNamjestaja, Integer idSalonaNamjestaja) {
        boolean retVal = false;
        Connection conn = null;
        PreparedStatement ps = null;
        String query = "DELETE FROM model_namjestaja_se_cuva_u_salonu_namjestaja WHERE IdModelaNamjestaja=? AND IdSalonaNamjestaja=?";
        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);

            ps.setInt(1, idModelaNamjestaja);
            ps.setInt(2, idSalonaNamjestaja);

            retVal = ps.executeUpdate() == 1;
        } catch (SQLIntegrityConstraintViolationException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("McMilan");
            alert.setHeaderText("Ovaj red je referenciran iz druge tabele! Brisanje nije moguće");
            alert.setContentText("Provjerite vaš zahtjev.");
            alert.showAndWait();
        } catch (SQLException e) {
            e.printStackTrace();
            MySQLUtilities.getInstance().showSQLException(e);
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            MySQLUtilities.getInstance().close(ps);
        }
        return retVal;
    }

    public boolean daLiImaDovoljnoNamjestajaUSalonu(Integer idModelaNamjestaja, Integer idSalonaNamjestaja, Integer kolicina) {
        boolean retVal = false;
        Connection conn = null;
        CallableStatement cs = null;

        String query = "{CALL da_li_ima_dovoljno_namjestaja_u_salonu(?, ?, ?, ?)}";
        try {
            conn = ConnectionPool.getInstance().checkOut();
            cs = conn.prepareCall(query);
            cs.setInt(1, idModelaNamjestaja);
            cs.setInt(2, idSalonaNamjestaja);
            cs.setInt(3, kolicina);
            cs.registerOutParameter(4, Types.BOOLEAN);
            cs.execute();
            retVal = cs.getBoolean(4);
        } catch (SQLException e) {
            e.printStackTrace();
            MySQLUtilities.getInstance().showSQLException(e);
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            MySQLUtilities.getInstance().close(cs);
        }
        return retVal;
    }
}
