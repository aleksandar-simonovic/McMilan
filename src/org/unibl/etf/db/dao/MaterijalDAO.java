package org.unibl.etf.db.dao;

import javafx.scene.control.Alert;
import org.unibl.etf.db.dto.MaterijalDTO;
import org.unibl.etf.db.dto.ModelNamjestajaDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MaterijalDAO {
    public boolean dodajMaterijal(MaterijalDTO materijal) {
        boolean retVal = false;
        Connection conn = null;
        PreparedStatement ps = null;

        String query = "INSERT INTO materijal (IdMaterijala,TipMaterijala,NazivMaterijala) VALUES" +
                "(?, ?, ?)";
        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);

            if (materijal.getIdMaterijala() == null) {
                ps.setNull(1, Types.INTEGER);
            } else {
                ps.setInt(1, materijal.getIdMaterijala());
            }
            if (materijal.getTipMaterijala() == null) {
                ps.setNull(2, Types.VARCHAR);
            } else {
                ps.setString(2, materijal.getTipMaterijala());
            }
            if (materijal.getNazivMaterijala() == null) {
                ps.setNull(3, Types.VARCHAR);
            } else {
                ps.setString(3, materijal.getNazivMaterijala());
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

    public boolean azurirajMaterijal(MaterijalDTO materijal) {
        boolean retVal = false;
        Connection conn = null;
        PreparedStatement ps = null;

        String query = "UPDATE materijal SET TipMaterijala=?,NazivMaterijala=? WHERE IdMaterijala=?";
        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);

            if (materijal.getTipMaterijala() == null) {
                ps.setNull(1, Types.VARCHAR);
            }
            else {
                ps.setString(1, materijal.getTipMaterijala());
            }

            if (materijal.getNazivMaterijala() == null) {
                ps.setNull(2, Types.VARCHAR);
            }
            else {
                ps.setString(2, materijal.getNazivMaterijala());
            }

            if (materijal.getIdMaterijala() == null) {
                ps.setNull(3, Types.INTEGER);
            }
            else {
                ps.setInt(3, materijal.getIdMaterijala());
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

    public boolean obrisiMaterijal(Integer idMaterijala) {
        boolean retVal = false;
        Connection conn = null;
        PreparedStatement ps = null;

        String query = "DELETE FROM materijal "
                + "WHERE IdMaterijala=? ";
        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setInt(1, idMaterijala);

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
    public MaterijalDTO dohvatiMaterijalPoNazivuMaterijala(String naziv) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        MaterijalDTO retVal = null;

        String query = "SELECT * FROM materijal WHERE NazivMaterijala=?";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setString(1,naziv);
            rs = ps.executeQuery();

            if (rs.next())
                retVal = new MaterijalDTO(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3));
        } catch (SQLException e) {
            e.printStackTrace();
            MySQLUtilities.getInstance().showSQLException(e);
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            MySQLUtilities.getInstance().close(ps, rs);
        }
        return retVal;
    }
    public MaterijalDTO dohvatiMaterijalPoIdentifikatoru(Integer id) {
        if(id == null)
            return null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        MaterijalDTO retVal = null;

        String query = "SELECT * FROM materijal WHERE IdMaterijala=?";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setInt(1,id);
            rs = ps.executeQuery();

            if (rs.next())
                retVal = new MaterijalDTO(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3));
        } catch (SQLException e) {
            e.printStackTrace();
            MySQLUtilities.getInstance().showSQLException(e);
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            MySQLUtilities.getInstance().close(ps, rs);
        }
        return retVal;
    }

    public List<MaterijalDTO> dohvatiPoNazivuMaterijala(String nazivMaterijala) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<MaterijalDTO> retVal = new ArrayList<MaterijalDTO>();

        String query = "SELECT * FROM materijal WHERE NazivMaterijala LIKE ? ";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setString(1, "%" + nazivMaterijala + "%");
            rs = ps.executeQuery();

            while (rs.next())
                retVal.add(new MaterijalDTO(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3))
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
    public List<MaterijalDTO> dohvatiSveMaterijale() {
        Connection conn = null;
        Statement s = null;
        ResultSet rs = null;

        List<MaterijalDTO> retVal = new ArrayList<MaterijalDTO>();

        String query = "SELECT * FROM materijal";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            s = conn.createStatement();
            rs = s.executeQuery(query);

            while (rs.next())
                retVal.add(new MaterijalDTO(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3))
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
