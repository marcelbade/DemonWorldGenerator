package marcel.demonworld.armygenerator.GameLogic.armieCalculators;

import marcel.demonworld.armygenerator.GameLogic.ResultContainers.OrkResultContainer;
import marcel.demonworld.armygenerator.dto.armyDTOs.CalculatedArmyResult;
import marcel.demonworld.armygenerator.dto.statCardDTOs.DemonWorldCard;
import marcel.demonworld.armygenerator.dto.statCardDTOs.UnitCard;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static marcel.demonworld.armygenerator.GameLogic.constants.SubFactions.orks.OrksSubFaction.*;

/**
 * TODO: WYVERN -> count as item
 * TODO: LIMIT TO ONE CLAN
 * <p>
 * ok, orks is mildly annoying
 * you choose -> clan OR clangett
 * Clan: special troops of that one clan 50 percent
 * Clangett: lead by one of Clanngetts Lieutenants and troops of all clans can be picked, but at a lower max (20%)
 * ALSO:
 * The lieutenants are Trazzag,  Fherniak,  Ärrig,  Khazzar  and  Nallian
 * <p>
 * DONE
 * ======================
 * - check for clangett lt. method
 * - check if clangett or clan, make max numbers dependent on that choice
 * - limit to one clan , else FALSE

 */
public class OrkCalculator implements ArmyCalculator {

    @Autowired
    OrkResultContainer container;

    @Override
    public CalculatedArmyResult CalculatePointCost(List<DemonWorldCard> list, float maximumPointValue) {


        double sondertruppenTotalPointValue;
        double clangettTotalPointValue;

        if (container.isClangettSelected()) {

            sondertruppenTotalPointValue = .20 * maximumPointValue;
            clangettTotalPointValue = .20 * maximumPointValue;

        } else {

            sondertruppenTotalPointValue = .50 * maximumPointValue;
            clangettTotalPointValue = 0;
        }


        for (DemonWorldCard uc : list) {

            switch (uc.getSubFaction()) {
                case SONDERTRUPPEN:

                    container.setSondertruppenSum(container.getSondertruppenSum() + uc.getPoints());

                    if (container.getSondertruppenSum() <= sondertruppenTotalPointValue) {
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

                    if (container.getClanngettSum() <= clangettTotalPointValue) {
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

        if (container.getTotalSum() <= maximumPointValue && commanderPresent(list)) {
            container.setArmyFlag(true);
        }

        if (container.isClangettSelected()) {
            container.setArmyFlag(checkForClangettLieutenant(list));
        } else {
            container.setArmyFlag(limitToOneClan(list));
        }


        //TODO: you got to rework the return DTO!
        return null;
    }

    private boolean checkForClangettLieutenant(List<DemonWorldCard> armyList) {
        return armyList.stream().
                anyMatch(card -> ((UnitCard) card).
                        getUnitName().
                        equalsIgnoreCase("Trazzag|Fherniak|Ärrig|Khazzar|Nallian"));
    }

    private boolean limitToOneClan(List<DemonWorldCard> armyList) {
        String[] clansPresent = {};
        boolean result = false;

        Optional<DemonWorldCard> firstClanUnit = armyList.stream().filter(c -> c.getSubFaction().contains("clan")).findFirst();
        if (firstClanUnit.isPresent()) {
            clansPresent = firstClanUnit.get().getSubFaction().split("/");
        }

        for (String s : clansPresent) {
            if (armyList.stream().allMatch(c -> c.getSubFaction().contains(s))) {
                result = true;
            }
        }
        return result;
    }

    @Override
    public boolean commanderPresent(List<DemonWorldCard> list) {
        return list.stream().filter(c -> c instanceof UnitCard).anyMatch(card -> ((UnitCard) card).getCommandStars() >= 2);
    }
}
