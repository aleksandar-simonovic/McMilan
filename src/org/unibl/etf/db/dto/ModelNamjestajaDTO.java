package org.unibl.etf.db.dto;

import java.math.BigDecimal;
import java.util.Objects;

public class ModelNamjestajaDTO {
    private Integer idModelaNamjestaja;
    private String tipNamjestaja;
    private String nazivModela;
    private BigDecimal visinaCm;
    private BigDecimal sirinaCm;
    private BigDecimal dubinaCm;
    private String boja;
    private BigDecimal prodajnaCijena;
    private Integer idKataloga;

    public ModelNamjestajaDTO(Integer idModelaNamjestaja, String tipNamjestaja, String nazivModela,
                              BigDecimal visinaCm, BigDecimal sirinaCm, BigDecimal dubinaCm,
                              String boja, BigDecimal prodajnaCijena, Integer idKataloga) {
        this.idModelaNamjestaja = idModelaNamjestaja;
        this.tipNamjestaja = tipNamjestaja;
        this.nazivModela = nazivModela;
        this.visinaCm = visinaCm;
        this.sirinaCm = sirinaCm;
        this.dubinaCm = dubinaCm;
        this.boja = boja;
        this.prodajnaCijena = prodajnaCijena;
        this.idKataloga = idKataloga;
    }

    public ModelNamjestajaDTO() {
        super();
    }

    public Integer getIdModelaNamjestaja() {
        return idModelaNamjestaja;
    }

    public void setIdModelaNamjestaja(Integer idModelaNamjestaja) {
        this.idModelaNamjestaja = idModelaNamjestaja;
    }

    public String getTipNamjestaja() {
        return tipNamjestaja;
    }

    public void setTipNamjestaja(String tipNamjestaja) {
        this.tipNamjestaja = tipNamjestaja;
    }

    public String getNazivModela() {
        return nazivModela;
    }

    public void setNazivModela(String nazivModela) {
        this.nazivModela = nazivModela;
    }

    public BigDecimal getVisinaCm() {
        return visinaCm;
    }

    public void setVisinaCm(BigDecimal visinaCm) {
        this.visinaCm = visinaCm;
    }

    public BigDecimal getSirinaCm() {
        return sirinaCm;
    }

    public void setSirinaCm(BigDecimal sirinaCm) {
        this.sirinaCm = sirinaCm;
    }

    public BigDecimal getDubinaCm() {
        return dubinaCm;
    }

    public void setDubinaCm(BigDecimal dubinaCm) {
        this.dubinaCm = dubinaCm;
    }

    public String getBoja() {
        return boja;
    }

    public void setBoja(String boja) {
        this.boja = boja;
    }

    public BigDecimal getProdajnaCijena() {
        return prodajnaCijena;
    }

    public void setProdajnaCijena(BigDecimal prodajnaCijena) {
        this.prodajnaCijena = prodajnaCijena;
    }

    public Integer getIdKataloga() {
        return idKataloga;
    }

    public void setIdKataloga(Integer idKataloga) {
        this.idKataloga = idKataloga;
    }

    @Override
    public String toString() {
        return "ModelNamjestajaDTO{" +
                "idModelaNamjestaja=" + idModelaNamjestaja +
                ", tipNamjestaja='" + tipNamjestaja + '\'' +
                ", nazivModela='" + nazivModela + '\'' +
                ", visinaCm=" + visinaCm +
                ", sirinaCm=" + sirinaCm +
                ", dubinaCm=" + dubinaCm +
                ", boja='" + boja + '\'' +
                ", prodajnaCijena=" + prodajnaCijena +
                ", idKataloga=" + idKataloga +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ModelNamjestajaDTO that = (ModelNamjestajaDTO) o;
        return idModelaNamjestaja == that.idModelaNamjestaja;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idModelaNamjestaja);
    }
}