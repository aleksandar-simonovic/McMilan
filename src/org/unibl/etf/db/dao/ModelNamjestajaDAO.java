package org.unibl.etf.db.dao;

import javafx.scene.control.Alert;
import org.unibl.etf.db.dto.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ModelNamjestajaDAO {
    public List<ModelNamjestajaDTO> dohvatiSveModeleNamjestaja() {
        Connection conn = null;
        Statement s = null;
        ResultSet rs = null;

        List<ModelNamjestajaDTO> retVal = new ArrayList<ModelNamjestajaDTO>();

        String query = "SELECT * FROM model_namjestaja";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            s = conn.createStatement();
            rs = s.executeQuery(query);

            while (rs.next())
                retVal.add(new ModelNamjestajaDTO(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getBigDecimal(4),
                        rs.getBigDecimal(5),
                        rs.getBigDecimal(6),
                        rs.getString(7),
                        rs.getBigDecimal(8),
                        MySQLUtilities.getInteger(rs, 9))
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

    public List<ModelNamjestajaDTO> dohvatiPoNazivuModela(String nazivModela) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<ModelNamjestajaDTO> retVal = new ArrayList<ModelNamjestajaDTO>();

        String query = "SELECT * FROM model_namjestaja WHERE NazivModela LIKE ? ";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setString(1, "%" + nazivModela + "%");
            rs = ps.executeQuery();

            while (rs.next())
                retVal.add(new ModelNamjestajaDTO(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getBigDecimal(4),
                        rs.getBigDecimal(5),
                        rs.getBigDecimal(6),
                        rs.getString(7),
                        rs.getBigDecimal(8),
                        rs.getInt(9))
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

    public ModelNamjestajaDTO dohvatiModelNamjestajaPoIdentifikatoru(Integer id) {
        if(id == null)
            return null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        ModelNamjestajaDTO retVal = null;

        String query = "SELECT * FROM model_namjestaja WHERE IdModelaNamjestaja=?";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setInt(1,id);
            rs = ps.executeQuery();

            if (rs.next())
                retVal = new ModelNamjestajaDTO(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getBigDecimal(4),
                        rs.getBigDecimal(5),
                        rs.getBigDecimal(6),
                        rs.getString(7),
                        rs.getBigDecimal(8),
                        rs.getInt(9));
        } catch (SQLException e) {
            e.printStackTrace();
            MySQLUtilities.getInstance().showSQLException(e);
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            MySQLUtilities.getInstance().close(ps, rs);
        }
        return retVal;
    }
    public ModelNamjestajaDTO dohvatiModelNamjestajaPoNazivuModela(String nazivModela) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        ModelNamjestajaDTO retVal = null;

        String query = "SELECT * FROM model_namjestaja WHERE NazivModela=?";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setString(1,nazivModela);
            rs = ps.executeQuery();

            if (rs.next())
                retVal = new ModelNamjestajaDTO(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getBigDecimal(4),
                        rs.getBigDecimal(5),
                        rs.getBigDecimal(6),
                        rs.getString(7),
                        rs.getBigDecimal(8),
                        rs.getInt(9));
        } catch (SQLException e) {
            e.printStackTrace();
            MySQLUtilities.getInstance().showSQLException(e);
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            MySQLUtilities.getInstance().close(ps, rs);
        }
        return retVal;
    }

    public boolean dodajModelNamjestaja(ModelNamjestajaDTO model) {
        boolean retVal = false;
        Connection conn = null;
        PreparedStatement ps = null;

        String query = "INSERT INTO model_namjestaja (IdModelaNamjestaja,TipNamjestaja,NazivModela,VisinaCm,SirinaCm," +
                "DubinaCm,Boja,ProdajnaCijena,IdKataloga) VALUES" +
                "(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);

            if (model.getIdModelaNamjestaja() == null) {
                ps.setNull(1, Types.INTEGER);
            }
            else {
                ps.setInt(1, model.getIdModelaNamjestaja());
            }

            if (model.getTipNamjestaja() == null) {
                ps.setNull(2, Types.VARCHAR);
            }
            else {
                ps.setString(2, model.getTipNamjestaja());
            }

            ps.setString(3, model.getNazivModela());

            if (model.getVisinaCm() == null) {
                ps.setNull(4, Types.DECIMAL);
            }
            else {
                ps.setBigDecimal(4, model.getVisinaCm());
            }

            if (model.getSirinaCm() == null) {
                ps.setNull(5, Types.DECIMAL);
            }
            else {
                ps.setBigDecimal(5, model.getSirinaCm());
            }

            if (model.getDubinaCm() == null) {
                ps.setNull(6, Types.DECIMAL);
            }
            else {
                ps.setBigDecimal(6, model.getDubinaCm());
            }

            if (model.getBoja() == null) {
                ps.setNull(7, Types.VARCHAR);
            }
            else {
                ps.setString(7, model.getBoja());
            }

            ps.setBigDecimal(8, model.getProdajnaCijena());

            if (model.getIdKataloga() == null) {
                ps.setNull(9, Types.INTEGER);
            }
            else {
                ps.setInt(9, model.getIdKataloga());
            }

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

    public boolean azurirajModelNamjestaja(ModelNamjestajaDTO model) {
        boolean retVal = false;
        Connection conn = null;
        PreparedStatement ps = null;

        String query = "UPDATE model_namjestaja SET TipNamjestaja=?,NazivModela=?,VisinaCm=?,SirinaCm=?, " +
                "DubinaCm=?,Boja=?,ProdajnaCijena=?,IdKataloga=? WHERE IdModelaNamjestaja=?";
        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);

            if (model.getTipNamjestaja() == null) {
                ps.setNull(1, Types.VARCHAR);
            }
            else {
                ps.setString(1, model.getTipNamjestaja());
            }

            ps.setString(2, model.getNazivModela());

            if (model.getVisinaCm() == null) {
                ps.setNull(3, Types.DECIMAL);
            }
            else {
                ps.setBigDecimal(3, model.getVisinaCm());
            }

            if (model.getSirinaCm() == null) {
                ps.setNull(4, Types.DECIMAL);
            }
            else {
                ps.setBigDecimal(4, model.getSirinaCm());
            }

            if (model.getDubinaCm() == null) {
                ps.setNull(5, Types.DECIMAL);
            }
            else {
                ps.setBigDecimal(5, model.getDubinaCm());
            }

            if (model.getBoja() == null) {
                ps.setNull(6, Types.VARCHAR);
            }
            else {
                ps.setString(6, model.getBoja());
            }

            ps.setBigDecimal(7, model.getProdajnaCijena());

            if (model.getIdKataloga() == null) {
                ps.setNull(8, Types.INTEGER);
            }
            else {
                ps.setInt(8, model.getIdKataloga());
            }

            if (model.getIdModelaNamjestaja() == null) {
                ps.setNull(9, Types.INTEGER);
            }
            else {
                ps.setInt(9, model.getIdModelaNamjestaja());
            }

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

    public boolean obrisiModelNamjestaja(Integer idModela) {
        boolean retVal = false;
        Connection conn = null;
        PreparedStatement ps = null;

        String query = "DELETE FROM model_namjestaja "
                + "WHERE IdModelaNamjestaja=? ";
        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setInt(1, idModela);

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
}