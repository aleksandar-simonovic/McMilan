package org.unibl.etf.db.dto;

import java.math.BigDecimal;
import java.util.Objects;

public class FiskalniRacunStavkaDTO {
    private int idRacuna;
    private int idModelaNamjestaja;
    private BigDecimal cijena;
    private int kolicina;

    public FiskalniRacunStavkaDTO() {
        super();
    }

    public FiskalniRacunStavkaDTO(int idRacuna, int idModelaNamjestaja, BigDecimal cijena, int kolicina) {
        this.idRacuna = idRacuna;
        this.idModelaNamjestaja = idModelaNamjestaja;
        this.cijena = cijena;
        this.kolicina = kolicina;
    }

    public int getIdRacuna() { return idRacuna; }

    public void setIdRacuna(int idRacuna) { this.idRacuna = idRacuna; }

    public int getIdModelaNamjestaja() { return idModelaNamjestaja; }

    public void setIdModelaNamjestaja(int idModelaNamjestaja) { this.idModelaNamjestaja = idModelaNamjestaja; }

    public BigDecimal getCijena() { return cijena; }

    public void setCijena(BigDecimal cijena) { this.cijena = cijena; }

    public int getKolicina() { return kolicina; }

    public void setKolicina(int kolicina) { this.kolicina = kolicina; }

    @Override
    public String toString() {
        return "FiskalniRacunStavka{" +
                "idRacuna=" + idRacuna +
                ", idModelaNamjestaja=" + idModelaNamjestaja +
                ", cijena=" + cijena +
                ", kolicina=" + kolicina +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FiskalniRacunStavkaDTO that = (FiskalniRacunStavkaDTO) o;
        return idRacuna == that.idRacuna && idModelaNamjestaja == that.idModelaNamjestaja;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRacuna, idModelaNamjestaja);
    }
}
