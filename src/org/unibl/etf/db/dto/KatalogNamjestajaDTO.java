package org.unibl.etf.db.dto;

import java.util.Objects;

public class KatalogNamjestajaDTO {
    private int idKataloga;
    private String nazivKataloga;

    public KatalogNamjestajaDTO(int idKataloga, String nazivKataloga) {
        this.idKataloga = idKataloga;
        this.nazivKataloga = nazivKataloga;
    }

    public KatalogNamjestajaDTO() {
        super();
    }

    public int getIdKataloga() {
        return idKataloga;
    }

    public String getNazivKataloga() {
        return nazivKataloga;
    }

    public void setIdKataloga(int idKataloga) {
        this.idKataloga = idKataloga;
    }

    public void setNazivKataloga(String nazivKataloga) {
        this.nazivKataloga = nazivKataloga;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KatalogNamjestajaDTO that = (KatalogNamjestajaDTO) o;
        return idKataloga == that.idKataloga;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idKataloga);
    }

    @Override
    public String toString() {
        return "KatalogNamjestajaDTO{" +
                "idKataloga=" + idKataloga +
                ", nazivKataloga='" + nazivKataloga + '\'' +
                '}';
    }
}
