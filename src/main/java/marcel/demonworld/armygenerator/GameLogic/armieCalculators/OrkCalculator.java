package marcel.demonworld.armygenerator.GameLogic.armieCalculators;

import marcel.demonworld.armygenerator.GameLogic.ResultContainers.OrkResultContainer;
import marcel.demonworld.armygenerator.dto.armyDTOs.CalculatedArmyResult;
import marcel.demonworld.armygenerator.dto.statCardDTOs.DemonWorldCard;
import marcel.demonworld.armygenerator.dto.statCardDTOs.UnitCard;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static marcel.demonworld.armygenerator.GameLogic.constants.SubFactions.orks.OrksSubFaction.*;

public class OrkCalculator implements ArmyCalculator {

    @Autowired
    OrkResultContainer container;

    /**
     * TODO: WYVERN -> count as item
     * TODO: every ork army must belong to a clan OR be lead by one of Clanngetts Lieutenants
     * ok, orks is mildly annoying
     * you choose -> clan OR clangett
     * Clan: special troops of that one clan 50 percent
     * Clangett: lead by one of Clanngetts Lieutenants and troops of all clans can be picked, but at a lower max (20%)
     * ALSO:
     * The lieutenants are Trazzag,  Fherniak,  Ärrig,  Khazzar  und  Nallian
     */

    private boolean checkForClangettLieutenant(List<DemonWorldCard> list) {

        for (DemonWorldCard dc : list) {
            if (dc instanceof UnitCard && (
                    ((UnitCard) dc).getUnitName().equalsIgnoreCase("Trazzag") ||
                            ((UnitCard) dc).getUnitName().equalsIgnoreCase("Fherniak") ||
                            ((UnitCard) dc).getUnitName().equalsIgnoreCase("Ärrig") ||
                            ((UnitCard) dc).getUnitName().equalsIgnoreCase("Khazzar") ||
                            ((UnitCard) dc).getUnitName().equalsIgnoreCase("Nallian"))) {
                return true;
            }
        }
        return false;
    }


    @Override
    public CalculatedArmyResult CalculatePointCost(List<DemonWorldCard> list, float maximumPointValue) {

        for (DemonWorldCard uc : list) {

            switch (uc.getSubFaction()) {
                case SONDERTRUPPEN:

                    container.setSondertruppenSum(container.getSondertruppenSum() + uc.getPoints());

                    if (container.getSondertruppenSum() <= maximumPointValue * .50) {
                        container.setFlagSondertruppen(true);
                    }
                    break;
                case ZAUBERER:

                    container.setZaubererSum(container.getZaubererSum() + uc.getPoints());

                    if (container.getZaubererSum() <= maximumPointValue * .30) {
                        container.setFlagZauberer(true);
                    }
                    break;
                case CLANNGETT:

                    container.setClanngettSum(container.getClanngettSum() + uc.getPoints());

                    if (container.getClanngettSum() <= maximumPointValue * .30) {
                        container.setFlagClanngett(true);
                    }
                    break;
                case HELDEN_BEFEHLSHABER:

                    container.setHelden_BefehlshaberSum(container.getHelden_BefehlshaberSum() + uc.getPoints());

                    if (container.getHelden_BefehlshaberSum() <= maximumPointValue * .30) {
                        container.setFlagHelden_Befehlshaber(true);
                    }
                    break;
                case GERAETE:

                    container.setGeraeteSum(container.getGeraeteSum() + uc.getPoints());

                    if (container.getGeraeteSum() <= maximumPointValue * .20) {
                        container.setFlagGeraete(true);
                    }

                    break;
                case GIGANTEN:

                    container.setGigantenSum(container.getGigantenSum() + uc.getPoints());

                    if (container.getGigantenSum() <= maximumPointValue * .30) {
                        container.setFlagGiganten(true);
                    }

                    break;
                case EINHEITEN:

                    container.setOrkGrundEinheitenSum(container.getOrkGrundEinheitenSum() + uc.getPoints());

                    if (container.getOrkGrundEinheitenSum() >= maximumPointValue * .25) {
                        container.setOrkGrundEinheitenFlag(true);
                    }
                    break;
            }
        }

        if (container.getTotalSum() <= maximumPointValue) {
            container.setArmyFlag(true);
        }

        //TODO: you got to rework the return DTO!
        return null;
    }

    @Override
    public boolean commanderPresent(List<DemonWorldCard> list) {
        for (DemonWorldCard dc : list) {
            if (dc instanceof UnitCard && ((UnitCard) dc).getCommandStars() >= 2) {
                return true;
            }
        }
        return false;
    }
}
