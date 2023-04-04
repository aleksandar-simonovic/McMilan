package org.unibl.etf.db.dao;
import org.unibl.etf.db.dto.ModelNamjestajaDTO;
import org.unibl.etf.db.dto.SalonStatistikaDTO;
import org.unibl.etf.gui.MenuController;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SalonStatistikaDAO {
    public List<SalonStatistikaDTO> dohvatiStatistikuZaSalon(int idSalona) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<SalonStatistikaDTO> retVal = new ArrayList<SalonStatistikaDTO>();

        String query = "SELECT IdModelaNamjestaja, TipNamjestaja, NazivModela, NazivKataloga, ProdajnaCijena, Kolicina FROM salon_statistika where IdSalonaNamjestaja=?";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setInt(1, idSalona);
            rs = ps.executeQuery();

            while (rs.next())
                retVal.add(new SalonStatistikaDTO(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getBigDecimal(5),
                        rs.getInt(6))
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
    public List<SalonStatistikaDTO> dohvatiStatistikuZaSalonPoNazivuModela(int idSalona, String naziv) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<SalonStatistikaDTO> retVal = new ArrayList<SalonStatistikaDTO>();

        String query = "SELECT IdModelaNamjestaja, TipNamjestaja, NazivModela, NazivKataloga, ProdajnaCijena, Kolicina FROM salon_statistika WHERE NazivModela LIKE ? and IdSalonaNamjestaja=?";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setString(1, "%" + naziv + "%");
            ps.setInt(2, idSalona);
            rs = ps.executeQuery();

            while (rs.next())
                retVal.add(new SalonStatistikaDTO(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getBigDecimal(5),
                        rs.getInt(6))
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

    public boolean azurirajKolicinu(Integer idModelaNamjestaja, Integer idSalonaNamjestaja, Integer kolicina) {
        boolean retVal = false;
        Connection conn = null;
        PreparedStatement ps = null;

        String query = "UPDATE salon_statistika SET Kolicina=? WHERE IdModelaNamjestaja=? AND IdSalonaNamjestaja=?";
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
}
