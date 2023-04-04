package org.unibl.etf.db.dao;

import javafx.scene.control.Alert;
import org.unibl.etf.db.dto.*;
import org.unibl.etf.gui.MenuController;

import java.sql.Date;
import java.time.LocalDate;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FiskalniRacunDAO {
    public FiskalniRacunDTO dohvatiRacunPoIdentifikatoru(int idRacuna) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        FiskalniRacunDTO retVal = null;

        String query = "SELECT * FROM fiskalni_racun WHERE IdRacuna=?";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setInt(1,idRacuna);
            rs = ps.executeQuery();

            if (rs.next())
                retVal = new FiskalniRacunDTO(
                        rs.getInt(1),
                        rs.getDate(2),
                        rs.getBigDecimal(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getBoolean(6)
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

    public boolean noviFiskalniRacun(String jmbg, Integer idSalonaNamjestaja) {
        boolean retVal = false;
        Connection conn = null;
        CallableStatement cs = null;

        String query = "{CALL novi_fiskalni_racun(?, ?, ?)}";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            cs = conn.prepareCall(query);
            cs.setString(1, jmbg);
            cs.setInt(2, idSalonaNamjestaja);
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

    public boolean obrisiFiskalniRacun(Integer idRacuna) {
        boolean retVal = false;
        Connection conn = null;
        CallableStatement cs = null;
        FiskalniRacunDTO racun = new FiskalniRacunDAO().dohvatiRacunPoIdentifikatoru(idRacuna);

        if(!racun.getIzdat()) {
            String query = "{CALL brisi_fiskalni_racun(?, ?)}";
            try {
                conn = ConnectionPool.getInstance().checkOut();
                cs = conn.prepareCall(query);
                cs.setInt(1, idRacuna);
                cs.registerOutParameter(2, Types.BOOLEAN);
                cs.execute();
                retVal = cs.getBoolean(2);
            } catch (SQLException e) {
                e.printStackTrace();
                MySQLUtilities.getInstance().showSQLException(e);
            } finally {
                ConnectionPool.getInstance().checkIn(conn);
                MySQLUtilities.getInstance().close(cs);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("McMilan");
            alert.setHeaderText("Brisanje računa");
            alert.setContentText("Ne možete obrisati račun koji je već izdat kupcu");
            alert.showAndWait();
        }
        return retVal;
    }

    public List<FiskalniRacunDTO> racuniIzSalonaNamjestaja(int idSalonaNamjestaja) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<FiskalniRacunDTO> retVal = new ArrayList<FiskalniRacunDTO>();

        String query = "SELECT * FROM fiskalni_racun WHERE IdSalonaNamjestaja=?";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setInt(1,idSalonaNamjestaja);
            rs = ps.executeQuery();

            while (rs.next())
                retVal.add(new FiskalniRacunDTO(
                        rs.getInt(1),
                        rs.getDate(2),
                        rs.getBigDecimal(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getBoolean(6))
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
    public List<FiskalniRacunDTO> dohvatiPoDatumu(int idSalonaNamjestaja, LocalDate datum) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<FiskalniRacunDTO> retVal = new ArrayList<FiskalniRacunDTO>();

        String query = "SELECT * FROM fiskalni_racun WHERE IdSalonaNamjestaja=? AND DatumRacuna=?";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setInt(1,idSalonaNamjestaja);
            ps.setDate(2, Date.valueOf(datum));
            rs = ps.executeQuery();

            while (rs.next())
                retVal.add(new FiskalniRacunDTO(
                        rs.getInt(1),
                        rs.getDate(2),
                        rs.getBigDecimal(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getBoolean(6))
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

    public boolean izdajFiskalniRacun(Integer idRacuna) {
        boolean retVal = false;

        Connection conn = null;
        PreparedStatement ps = null;

        FiskalniRacunDTO racun = new FiskalniRacunDAO().dohvatiRacunPoIdentifikatoru(idRacuna);
        String query = "UPDATE fiskalni_racun SET Izdat=1 WHERE idRacuna=?";
        if(!racun.getIzdat()) {
            try {
                conn = ConnectionPool.getInstance().checkOut();
                ps = conn.prepareStatement(query);
                ps.setInt(1, idRacuna);
                retVal = ps.executeUpdate() == 1;
            } catch (SQLException e) {
                e.printStackTrace();
                MySQLUtilities.getInstance().showSQLException(e);
            } finally {
                ConnectionPool.getInstance().checkIn(conn);
                MySQLUtilities.getInstance().close(ps);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("McMilan");
            alert.setHeaderText("Izdavanje računa");
            alert.setContentText("Ovaj račun je već izdat kupcu");
            alert.showAndWait();
        }
        return retVal;
    }

    public List<FiskalniRacunDTO> dohvatiPoIznosu (String queryPrompt, Integer idSalonaNamjestaja) {
        List<FiskalniRacunDTO> retVal = new ArrayList<FiskalniRacunDTO>();

        String regex = "([<>]=?|=)([0-9]+)(\\.[0-9]+)?";
        if (queryPrompt.trim().matches(regex)) {
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(queryPrompt);
            matcher.find();
            String znak = matcher.group(1);
            String brojString = matcher.group(2) + (matcher.group(3) == null ? "" : matcher.group(3));
            Double brojVrijednost = Double.parseDouble(brojString);

            Connection conn = null;
            PreparedStatement ps = null;
            ResultSet rs = null;

            String query = "SELECT * FROM fiskalni_racun WHERE IdSalonaNamjestaja=? AND UkupanIznos" + znak + "?";

            try {
                conn = ConnectionPool.getInstance().checkOut();
                ps = conn.prepareStatement(query);
                ps.setInt(1,idSalonaNamjestaja);
                ps.setDouble(2,brojVrijednost);
                rs = ps.executeQuery();

                while (rs.next())
                    retVal.add(new FiskalniRacunDTO(
                            rs.getInt(1),
                            rs.getDate(2),
                            rs.getBigDecimal(3),
                            rs.getString(4),
                            rs.getInt(5),
                            rs.getBoolean(6))
                    );
            } catch (SQLException e) {
                e.printStackTrace();
                MySQLUtilities.getInstance().showSQLException(e);
            } finally {
                ConnectionPool.getInstance().checkIn(conn);
                MySQLUtilities.getInstance().close(ps, rs);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("McMilan");
            alert.setHeaderText("Pretraga po iznosu");
            alert.setContentText("Unos nije validan (primjer validnog unosa <=20.3)");
            retVal = new FiskalniRacunDAO().racuniIzSalonaNamjestaja(idSalonaNamjestaja);
            alert.showAndWait();
        }
        return retVal;
    }
}
