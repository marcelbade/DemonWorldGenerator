package marcel.demonworld.armygenerator.GameLogic.armieCalculators;

import marcel.demonworld.armygenerator.GameLogic.ResultContainers.ElvesResultContainer;
import marcel.demonworld.armygenerator.dto.armyDTOs.CalculatedArmyResult;
import marcel.demonworld.armygenerator.dto.statCardDTOs.DemonWorldCard;
import marcel.demonworld.armygenerator.dto.statCardDTOs.UnitCard;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import static marcel.demonworld.armygenerator.GameLogic.constants.SubFactions.elves.ElvesSubFaction.*;

/*
 *   let's rework elves
 *   here are the rules of army composition:
 *  - the army MUST have at least ONE Thanaril-Clanlord/Befehlshaber [DONE]
 *  - centaurs OR treelords[DONE]
 *  - Thanaril Kriegerbünde: the first one is picked freely. Additional units of that Kriegerbund can only
 *    be recruited if the LEADER OF THAT KRIEGERBUND is selected [DONE]
 *  - Every school of the Orea Vanar can be deployed ONCE  [DONE]
 *  - the orea vanar master can only b picked IF their school is also deployed
 *  - Units of the Ilah Ri Ratsarmee can only be deployed if a Befehlshaber of the illah ri is deployed  [DONE]
 * */
public class ElvesCalculator implements ArmyCalculator {

    @Autowired
    ElvesResultContainer container;

    @Override
    public CalculatedArmyResult CalculatePointCost(List<DemonWorldCard> list, float maximumPointValue) {


        int maxNumberOfOldHeroes = calculateNumberOfOldHeroes(list);

        for (DemonWorldCard uc : list) {

            switch (uc.getSubFaction()) {

                case THANARILCLANTRUPPEN:
                    container.setThanarilClantruppenSum(container.getThanarilClantruppenSum() + uc.getPoints());

                    if (container.getThanarilClantruppenSum() >= maximumPointValue * 0.30
                            && container.getThanarilClantruppenSum() >= maximumPointValue * 0.70) {
                        container.setFlagThanarilClantruppen(true);
                    }
                    break;

                case THANARILKRIEGERBÜNDE:
                    container.setThanarilKriegerbuendeSum(container.getThanarilKriegerbuendeSum() + uc.getPoints());

                    if (container.getThanarilKriegerbuendeSum() <= maximumPointValue * 0.30) {
                        container.setFlagThanarilKriegerbünde(true);
                    }
                    break;

                case THANARIL_BEFEHLSHABER:

                    container.setThanaril_Clanlords_Barden_BefehlshaberSum(
                            container.getThanaril_Clanlords_Barden_BefehlshaberSum() + uc.getPoints());

                    if (container.getThanaril_Clanlords_Barden_BefehlshaberSum() <= maximumPointValue * 0.30) {
                        container.setFlagThanaril_Clanlords_Barden_Befehlshaber(true);
                    }
                    break;

                case ALTE_HELDEN:
                    // TODO: confirm that this is correct!
                    container.setAlte_HeldenSum(container.getAlte_HeldenSum() + uc.getPoints());

                    if (container.getNumberOfOldHeroes() <= calculateNumberOfOldHeroes(list)) {
                        container.setFlagAlte_Helden(true);
                    }
                    break;

                case DYREA:
                case LOREATHS:
                    container.setDyrea_LoreathsSum(container.getDyrea_LoreathsSum() + uc.getPoints());

                    if (container.getDyrea_LoreathsSum() <= maximumPointValue * 0.30) {
                        container.setFlagDyrea_Loreaths(true);
                    }

                    break;

                case OREA_VANAR_TRUPPEN:
                case OREA_VANAR_BEFEHLSHABER:
                    container.setOrea_Vanar_Truppen_MeisterSum(
                            container.getOrea_Vanar_Truppen_MeisterSum() + uc.getPoints());

                    if (container.getOrea_Vanar_Truppen_MeisterSum() <= maximumPointValue * 0.30) {
                        container.setFlagOrea_Vanar_Truppen_Meister(true);
                    }
                    break;

                case Ilah_RI_EINHEITEN:
                case Ilah_RI_BEFEHLSHABER:
                    container.setRatsarmee_Einheiten_BefehlshaberSum(
                            container.getRatsarmee_Einheiten_BefehlshaberSum() + uc.getPoints());
                    if (container.getRatsarmee_Einheiten_BefehlshaberSum() <= maximumPointValue * 0.50) {
                        container.setFlagRatsarmee_Einheiten_Befehlshaber(true);
                    }
                    break;

                case ZENTAUREN:
                case BAUMHERREN:
                    container.setBaumherren_ZentaurenSum(container.getBaumherren_ZentaurenSum() + uc.getPoints());

                    if (container.getBaumherren_ZentaurenSum() <= maximumPointValue * 0.25) {
                        container.setFlagBaumherren_Zentauren(true);
                    }
                    break;

            }
            container.setTotalSum(container.getTotalSum() + uc.getPoints());
        }


        if (container.getTotalSum() <= maximumPointValue) {
            container.setArmyFlag(true);
        }

        // TODO: you got to rework the return DTO!
        return null;
    }


    /**
     * Every Army must contain a ** commander.
     *
     * @param list The current List of selected stat cards.
     * @return boolean flag
     */
    @Override
    public boolean commanderPresent(List<DemonWorldCard> list) {
        return list.stream().
                filter(c -> c instanceof UnitCard).anyMatch(card -> ((UnitCard) card).getCommandStars() >= 2);
    }

    /**
     * Method calculates number of "old Heroes" for the army. The maximum is 1 hero
     * per 5 units with the attribute "Thanaril" OR "Ratsarmee"
     *
     * @param armyList The current List of selected stat cards.
     * @return maximum number of units with the "old Heroes" subfaction value.
     */
    private int calculateNumberOfOldHeroes(List<DemonWorldCard> armyList) {
        return (int) armyList.stream().filter(
                c -> c.getSubFaction().equalsIgnoreCase(THANARILCLANTRUPPEN)
                        || c.getSubFaction().equalsIgnoreCase(Ilah_RI_EINHEITEN)).count() / 5;
    }


    /**
     * to pick more than one Thanaril Unit, you need to also deploy one Thanaril hero.
     *
     * @return boolean flag
     */
    private boolean thanarilRuleCompliance(List<DemonWorldCard> armyList) {
        boolean result = true;

        if (armyList.stream().filter(c -> c.getSubFaction().equalsIgnoreCase(THANARILKRIEGERBÜNDE)).count() > 1 &&
                armyList.stream().anyMatch(c -> !c.getSubFaction().equalsIgnoreCase(THANARIL_BEFEHLSHABER)))
            result = false;

        return result;
    }

    /**
     * @param armyList The current List of selected stat cards.
     * @return booelan flag
     */
    private boolean oreaVanarHeroCompliance(List<DemonWorldCard> armyList, DemonWorldCard OreaVanarHero) {

        boolean result = false;

        //last part of subfaction name of old hero == name of school
        String[] subfactions = OreaVanarHero.getSubFaction().split("/");

        result = armyList.stream().anyMatch(c -> c.getName().equalsIgnoreCase(subfactions[2]));

        //TODO: CONTINUE HERE !! -> problem is the subfaction in sql for these heroes

        return result;
    }


    /**
     * Your army can only have tree people or centaurs!
     *
     * @param armyList The current List of selected stat cards.
     * @return booelan flag
     */
    private boolean treePeopleOrCentaurs(List<DemonWorldCard> armyList) {

        boolean result = true;

        if (armyList.stream().anyMatch(c -> c.getSubFaction().equals(ZENTAUREN)))
            result = armyList.stream().anyMatch(c -> !c.getSubFaction().equals(BAUMHERREN));
        else if (armyList.stream().anyMatch(c -> c.getSubFaction().equals(BAUMHERREN)))
            result = armyList.stream().anyMatch(c -> !c.getSubFaction().equals(ZENTAUREN));

        return result;
    }
}
