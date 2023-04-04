package org.unibl.etf.db.dto;

import java.util.Objects;

public class SalonNamjestajaDTO {
    private int idSalonaNamjestaja;
    private String nazivSalona;
    private int adresaId;
    private String jib;

    public SalonNamjestajaDTO() {
        super();
    }

    public SalonNamjestajaDTO(int idSalonaNamjestaja, String nazivSalona, int adresaId, String jib) {
        this.idSalonaNamjestaja = idSalonaNamjestaja;
        this.nazivSalona = nazivSalona;
        this.adresaId = adresaId;
        this.jib = jib;
    }

    public int getIdSalonaNamjestaja() { return idSalonaNamjestaja; }

    public void setIdSalonaNamjestaja(int idSalonaNamjestaja) { this.idSalonaNamjestaja = idSalonaNamjestaja; }

    public String getNazivSalona() { return nazivSalona; }

    public void setNazivSalona(String nazivSalona) { this.nazivSalona = nazivSalona; }

    public int getAdresaId() { return adresaId; }

    public void setAdresaId(int adresaId) { this.adresaId = adresaId; }

    public String getJib() { return jib; }

    public void setJib(String jib) { this.jib = jib; }

    @Override
    public String toString() {
        return "SalonNamjestajaDTO{" +
                "idSalonaNamjestaja=" + idSalonaNamjestaja +
                ", nazivSalona='" + nazivSalona + '\'' +
                ", adresaId=" + adresaId +
                ", jib='" + jib + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SalonNamjestajaDTO that = (SalonNamjestajaDTO) o;
        return idSalonaNamjestaja == that.idSalonaNamjestaja;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSalonaNamjestaja);
    }
}
