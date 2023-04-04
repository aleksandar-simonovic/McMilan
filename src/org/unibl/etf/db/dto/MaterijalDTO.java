package org.unibl.etf.db.dto;

import java.util.Objects;

public class MaterijalDTO {
    private Integer idMaterijala;
    private String tipMaterijala;
    private String nazivMaterijala;

    public MaterijalDTO(Integer idMaterijala, String tipMaterijala, String nazivMaterijala) {
        this.idMaterijala = idMaterijala;
        this.tipMaterijala = tipMaterijala;
        this.nazivMaterijala = nazivMaterijala;
    }

    public MaterijalDTO() {
        super();
    }

    public Integer getIdMaterijala() {
        return idMaterijala;
    }

    public void setIdMaterijala(Integer idMaterijala) {
        this.idMaterijala = idMaterijala;
    }

    public String getTipMaterijala() {
        return tipMaterijala;
    }

    public void setTipMaterijala(String tipMaterijala) {
        this.tipMaterijala = tipMaterijala;
    }

    public String getNazivMaterijala() {
        return nazivMaterijala;
    }

    public void setNazivMaterijala(String nazivMaterijala) {
        this.nazivMaterijala = nazivMaterijala;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MaterijalDTO that = (MaterijalDTO) o;
        return Objects.equals(idMaterijala, that.idMaterijala);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMaterijala);
    }

    @Override
    public String toString() {
        return "MaterijalDTO{" +
                "idMaterijala=" + idMaterijala +
                ", tipMaterijala='" + tipMaterijala + '\'' +
                ", nazivMaterijala='" + nazivMaterijala + '\'' +
                '}';
    }
}
