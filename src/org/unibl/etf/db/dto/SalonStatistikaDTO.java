package org.unibl.etf.db.dto;

import java.math.BigDecimal;
import java.util.Objects;

public class SalonStatistikaDTO {
    private Integer idModelaNamjestaja;
    private String tipNamjestaja;
    private String nazivModela;
    private String nazivKataloga;
    private BigDecimal prodajnaCijena;
    private Integer kolicina;

    public String getNazivKataloga() { return nazivKataloga; }

    public void setNazivKataloga(String nazivKataloga) { this.nazivKataloga = nazivKataloga; }

    public void setTipNamjestaja(String tipNamjestaja) {
        this.tipNamjestaja = tipNamjestaja;
    }

    public void setIdModelaNamjestaja(Integer idModelaNamjestaja) {
        this.idModelaNamjestaja = idModelaNamjestaja;
    }

    public void setNazivModela(String nazivModela) {
        this.nazivModela = nazivModela;
    }

    public void setProdajnaCijena(BigDecimal prodajnaCijena) {
        this.prodajnaCijena = prodajnaCijena;
    }

    public void setKolicina(Integer kolicina) {
        this.kolicina = kolicina;
    }

    public String getTipNamjestaja() {
        return tipNamjestaja;
    }

    public Integer getIdModelaNamjestaja() {
        return idModelaNamjestaja;
    }

    public String getNazivModela() {
        return nazivModela;
    }

    public BigDecimal getProdajnaCijena() {
        return prodajnaCijena;
    }

    public Integer getKolicina() {
        return kolicina;
    }

    public SalonStatistikaDTO() {
        super();
    }

    public SalonStatistikaDTO(Integer idModelaNamjestaja, String tipNamjestaja, String nazivModela, String nazivKataloga, BigDecimal prodajnaCijena, Integer kolicina) {
        this.idModelaNamjestaja = idModelaNamjestaja;
        this.tipNamjestaja = tipNamjestaja;
        this.nazivModela = nazivModela;
        this.nazivKataloga = nazivKataloga;
        this.prodajnaCijena = prodajnaCijena;
        this.kolicina = kolicina;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SalonStatistikaDTO that = (SalonStatistikaDTO) o;
        return idModelaNamjestaja == that.idModelaNamjestaja;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idModelaNamjestaja);
    }

    @Override
    public String toString() {
        return "SalonStatistikaDTO{" +
                "idModelaNamjestaja=" + idModelaNamjestaja +
                ", tipNamjestaja='" + tipNamjestaja + '\'' +
                ", nazivModela='" + nazivModela + '\'' +
                ", nazivKataloga='" + nazivKataloga + '\'' +
                ", prodajnaCijena=" + prodajnaCijena +
                ", kolicina=" + kolicina +
                '}';
    }
}
