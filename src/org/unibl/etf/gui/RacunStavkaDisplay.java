package org.unibl.etf.gui;

import org.unibl.etf.db.dao.FiskalniRacunStavkaDAO;
import org.unibl.etf.db.dao.ModelNamjestajaDAO;
import org.unibl.etf.db.dto.FiskalniRacunStavkaDTO;
import org.unibl.etf.db.dto.ModelNamjestajaDTO;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RacunStavkaDisplay {
    public String nazivModela;
    public BigDecimal cijena;
    public Integer kolicina;

    public RacunStavkaDisplay(String nazivModela, BigDecimal cijena, Integer kolicina) {
        this.nazivModela = nazivModela;
        this.cijena = cijena;
        this.kolicina = kolicina;
    }

    public RacunStavkaDisplay() {
        super();
    }

    public String getNazivModela() {
        return nazivModela;
    }

    public void setNazivModela(String nazivModela) {
        this.nazivModela = nazivModela;
    }

    public BigDecimal getCijena() {
        return cijena;
    }

    public void setCijena(BigDecimal cijena) {
        this.cijena = cijena;
    }

    public Integer getKolicina() {
        return kolicina;
    }

    public void setKolicina(Integer kolicina) {
        this.kolicina = kolicina;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RacunStavkaDisplay that = (RacunStavkaDisplay) o;
        return Objects.equals(nazivModela, that.nazivModela) && Objects.equals(cijena, that.cijena) && Objects.equals(kolicina, that.kolicina);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nazivModela, cijena, kolicina);
    }

    static List<RacunStavkaDisplay> dohvatiStavkeZaRacunPoIdentifikatoru(int idRacuna) {
        List<FiskalniRacunStavkaDTO> stavke = new FiskalniRacunStavkaDAO().dohvatiStavkeZaRacunPoIdentifikatoru(idRacuna);
        List <RacunStavkaDisplay> result = new ArrayList<>();
        for(FiskalniRacunStavkaDTO x : stavke) {
            ModelNamjestajaDTO temp = new ModelNamjestajaDAO().dohvatiModelNamjestajaPoIdentifikatoru(x.getIdModelaNamjestaja());
            result.add(new RacunStavkaDisplay(temp.getNazivModela(),x.getCijena(),x.getKolicina()));
        }
        return result;
    }
    static int dohvatiIdModelaPoNazivuModela(String nazivModela) {
        return new ModelNamjestajaDAO().dohvatiModelNamjestajaPoNazivuModela(nazivModela).getIdModelaNamjestaja();
    }
}