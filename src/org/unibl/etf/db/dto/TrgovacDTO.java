package org.unibl.etf.db.dto;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Objects;

public class TrgovacDTO {
    public TrgovacDTO() {
    }

    public TrgovacDTO(String jmbg,
                      String username,
                      String ime,
                      String prezime,
                      Date datumRodjenja,
                      String polOsobe,
                      String prebivaliste,
                      int idSalonaNamjestaja,
                      String nazivSalona,
                      Date ugovorOd,
                      Date ugovorDo,
                      Long ukupnoIzdatihRacuna,
                      BigDecimal ukupanIznosProdaje) {
        this.jmbg = jmbg;
        this.username = username;
        this.ime = ime;
        this.prezime = prezime;
        this.datumRodjenja = datumRodjenja;
        this.polOsobe = polOsobe;
        this.prebivaliste = prebivaliste;
        this.idSalonaNamjestaja = idSalonaNamjestaja;
        this.nazivSalona = nazivSalona;
        this.ugovorOd = ugovorOd;
        this.ugovorDo = ugovorDo;
        this.ukupnoIzdatihRacuna = ukupnoIzdatihRacuna;
        this.ukupanIznosProdaje = ukupanIznosProdaje;
    }

    private String jmbg;
    private String username;
    private String ime;
    private String prezime;
    private Date datumRodjenja;
    private String polOsobe;
    private String prebivaliste;
    private int idSalonaNamjestaja;
    private String nazivSalona;
    private Date ugovorOd;
    private Date ugovorDo;
    private Long ukupnoIzdatihRacuna;
    private BigDecimal ukupanIznosProdaje;

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(Date datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    public String getPrebivaliste() {
        return prebivaliste;
    }

    public void setPrebivaliste(String prebivaliste) {
        this.prebivaliste = prebivaliste;
    }

    public String getNazivSalona() {
        return nazivSalona;
    }

    public void setNazivSalona(String nazivSalona) {
        this.nazivSalona = nazivSalona;
    }

    public String getPolOsobe() {
        return polOsobe;
    }

    public void setPolOsobe(String polOsobe) {
        this.polOsobe = polOsobe;
    }

    public BigDecimal getUkupanIznosProdaje() {
        return ukupanIznosProdaje;
    }

    public void setUkupanIznosProdaje(BigDecimal ukupanIznosProdaje) {
        this.ukupanIznosProdaje = ukupanIznosProdaje;
    }

    public Long getUkupnoIzdatihRacuna() {
        return ukupnoIzdatihRacuna;
    }

    public void setUkupnoIzdatihRacuna(Long ukupnoIzdatihRacuna) {
        this.ukupnoIzdatihRacuna = ukupnoIzdatihRacuna;
    }

    public Date getUgovorOd() { return ugovorOd; }

    public void setUgovorOd(Date ugovorOd) { this.ugovorOd = ugovorOd; }

    public Date getUgovorDo() { return ugovorDo; }

    public void setUgovorDo(Date ugovorDo) { this.ugovorDo = ugovorDo; }

    public int getIdSalonaNamjestaja() { return idSalonaNamjestaja; }

    public void setIdSalonaNamjestaja(int idSalonaNamjestaja) {
        this.idSalonaNamjestaja = idSalonaNamjestaja;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrgovacDTO that = (TrgovacDTO) o;
        return Objects.equals(jmbg, that.jmbg);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jmbg);
    }

    @Override
    public String toString() {
        return "TrgovacDTO{" +
                "jmbg='" + jmbg + '\'' +
                ", username='" + username + '\'' +
                ", ime='" + ime + '\'' +
                ", prezime='" + prezime + '\'' +
                ", datumRodjenja=" + datumRodjenja +
                ", polOsobe='" + polOsobe + '\'' +
                ", prebivaliste='" + prebivaliste + '\'' +
                ", idSalonaNamjestaja=" + idSalonaNamjestaja +
                ", nazivSalona='" + nazivSalona + '\'' +
                ", ugovorOd=" + ugovorOd +
                ", ugovorDo=" + ugovorDo +
                ", ukupnoIzdatihRacuna=" + ukupnoIzdatihRacuna +
                ", ukupanIznosProdaje=" + ukupanIznosProdaje +
                '}';
    }
}
