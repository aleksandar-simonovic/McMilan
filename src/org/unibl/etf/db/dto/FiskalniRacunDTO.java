package org.unibl.etf.db.dto;

import org.unibl.etf.db.dao.OsobaDAO;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Objects;

public class FiskalniRacunDTO {
    private Integer idRacuna;
    private Date datumRacuna;
    private BigDecimal ukupanIznos;
    private String jmbg;
    private Integer idSalonaNamjestaja;
    private Boolean izdat;
    private String prodavac;
    private String izdatString;

    public FiskalniRacunDTO() {
        super();
    }

    public FiskalniRacunDTO(Integer idRacuna, Date datumRacuna, BigDecimal ukupanIznos, String jmbg, Integer idSalonaNamjestaja, Boolean izdat) {
        this.idRacuna = idRacuna;
        this.datumRacuna = datumRacuna;
        this.ukupanIznos = ukupanIznos;
        this.jmbg = jmbg;
        this.idSalonaNamjestaja = idSalonaNamjestaja;
        this.izdat = izdat;
        if(izdat == true) {
            izdatString = "DA";
        } else {
            izdatString = "NE";
        }
        prodavac = new OsobaDAO().dohvatiOsobuPoJmbg(jmbg).getIme() + " " + new OsobaDAO().dohvatiOsobuPoJmbg(jmbg).getPrezime();
    }

    public Integer getIdRacuna() { return idRacuna; }
    public void setIdRacuna(Integer idRacuna) { this.idRacuna = idRacuna; }
    public Date getDatumRacuna() { return datumRacuna; }
    public void setDatumRacuna(Date datumRacuna) { this.datumRacuna = datumRacuna; }
    public BigDecimal getUkupanIznos() { return ukupanIznos; }
    public void setUkupanIznos(BigDecimal ukupanIznos) { this.ukupanIznos = ukupanIznos; }
    public String getJmbg() { return jmbg; }
    public void setJmbg(String jmbg) { this.jmbg = jmbg; }
    public Integer getIdSalonaNamjestaja() { return idSalonaNamjestaja; }
    public void setIdSalonaNamjestaja(Integer idSalonaNamjestaja) { this.idSalonaNamjestaja = idSalonaNamjestaja; }
    public Boolean getIzdat() { return izdat; }
    public void setIzdat(Boolean izdat) { this.izdat = izdat; }
    public String getProdavac() { return prodavac; }
    public void setProdavac(String prodavac) { this.prodavac = prodavac; }
    public String getIzdatString() { return izdatString; }
    public void setIzdatString(String izdatString) { this.izdatString = izdatString; }

    @Override
    public String toString() {
        return "FiskalniRacunDTO{" +
                "idRacuna=" + idRacuna +
                ", datumRacuna=" + datumRacuna +
                ", ukupanIznos=" + ukupanIznos +
                ", jmbg='" + jmbg + '\'' +
                ", idSalonaNamjestaja=" + idSalonaNamjestaja +
                ", izdat=" + izdat +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FiskalniRacunDTO that = (FiskalniRacunDTO) o;
        return Objects.equals(idRacuna, that.idRacuna);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRacuna);
    }
}
