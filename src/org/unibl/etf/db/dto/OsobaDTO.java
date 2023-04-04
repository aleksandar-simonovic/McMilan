package org.unibl.etf.db.dto;

import java.sql.Date;
import java.util.Objects;

public class OsobaDTO {
    private String jmbg;
    private String ime;
    private String prezime;
    private Date datumRodjenja;
    private String polOsobe;
    private int adresaId;

    public OsobaDTO() {
        super();
    }

    public OsobaDTO(String jmbg, String ime, String prezime, Date datumRodjenja, String polOsobe, int adresaId) {
        this.jmbg = jmbg;
        this.ime = ime;
        this.prezime = prezime;
        this.datumRodjenja = datumRodjenja;
        this.polOsobe = polOsobe;
        this.adresaId = adresaId;
    }

    public String getJmbg() { return jmbg; }

    public void setJmbg(String jmbg) { this.jmbg = jmbg; }

    public String getIme() { return ime; }

    public void setIme(String ime) { this.ime = ime; }

    public String getPrezime() { return prezime; }

    public void setPrezime(String prezime) { this.prezime = prezime; }

    public Date getDatumRodjenja() { return datumRodjenja; }

    public void setDatumRodjenja(Date datumRodjenja) { this.datumRodjenja = datumRodjenja; }

    public String getPolOsobe() { return polOsobe; }

    public void setPolOsobe(String polOsobe) { this.polOsobe = polOsobe; }

    public int getAdresaId() { return adresaId; }

    public void setAdresaId(int adresaId) { this.adresaId = adresaId; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OsobaDTO osobaDTO = (OsobaDTO) o;
        return Objects.equals(jmbg, osobaDTO.jmbg);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jmbg);
    }

    @Override
    public String toString() {
        return "OsobaDTO{" +
                "jmbg='" + jmbg + '\'' +
                ", ime='" + ime + '\'' +
                ", prezime='" + prezime + '\'' +
                ", datumRodjenja=" + datumRodjenja +
                ", polOsobe='" + polOsobe + '\'' +
                ", adresaId=" + adresaId +
                '}';
    }
}
