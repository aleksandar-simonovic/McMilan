package org.unibl.etf.db.dto;

import java.util.Objects;

public class ModelNamjestajaSeCuvaUSalonuNamjestajaDTO {
    private int idModelaNamjestaja;
    private int idSkladista;
    private int kolicina;

    public ModelNamjestajaSeCuvaUSalonuNamjestajaDTO() {
        super();
    }

    public ModelNamjestajaSeCuvaUSalonuNamjestajaDTO(int idModelaNamjestaja, int idSkladista, int kolicina) {
        this.idModelaNamjestaja = idModelaNamjestaja;
        this.idSkladista = idSkladista;
        this.kolicina = kolicina;
    }

    public int getIdModelaNamjestaja() { return idModelaNamjestaja; }

    public void setIdModelaNamjestaja(int idModelaNamjestaja) { this.idModelaNamjestaja = idModelaNamjestaja; }

    public int getIdSkladista() { return idSkladista; }

    public void setIdSkladista(int idSkladista) { this.idSkladista = idSkladista; }

    public int getKolicina() { return kolicina; }

    public void setKolicina(int kolicina) { this.kolicina = kolicina; }

    @Override
    public String toString() {
        return "ModelNamjestajaSeCuvaUSkladistuDTO{" +
                "idModelaNamjestaja=" + idModelaNamjestaja +
                ", idSkladista=" + idSkladista +
                ", kolicina=" + kolicina +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ModelNamjestajaSeCuvaUSalonuNamjestajaDTO that = (ModelNamjestajaSeCuvaUSalonuNamjestajaDTO) o;
        return idModelaNamjestaja == that.idModelaNamjestaja && idSkladista == that.idSkladista;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idModelaNamjestaja, idSkladista);
    }
}
