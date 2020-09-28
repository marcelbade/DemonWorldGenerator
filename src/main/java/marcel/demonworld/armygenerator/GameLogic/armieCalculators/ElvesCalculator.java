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
 *  - the army MUST have at least ONE Thanaril-Clanlord/Befehlshaber
 *  - centaurs OR treelords
 *  - Thanaril Kriegerbünde: the first one is picked freely. Foradditianl units of that Kriegerbund can only
 *    be recruited if the LEADER OF THAT KRIEGERBUND is selected
 *  - Every school of the Orea Vanar can be deployed ONCE
 *  - the orea vanar master can only b picked IF their school is also deployed
 *  - Units of the Ilah Ri Ratsarmee can only be deployed of a Befehlshaber of the illah ri is deployed
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

                case THANARIL_CLANLORDS_BARDEN_BEFEHLSHABER:

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

                case DYREA_LOREATHS:

                    container.setDyrea_LoreathsSum(container.getDyrea_LoreathsSum() + uc.getPoints());

                    if (container.getDyrea_LoreathsSum() <= maximumPointValue * 0.30) {
                        container.setFlagDyrea_Loreaths(true);
                    }

                    break;

                case OREA_VANAR_TRUPPEN_MEISTER:

                    container.setOrea_Vanar_Truppen_MeisterSum(
                            container.getOrea_Vanar_Truppen_MeisterSum() + uc.getPoints());

                    if (container.getOrea_Vanar_Truppen_MeisterSum() <= maximumPointValue * 0.30) {
                        container.setFlagOrea_Vanar_Truppen_Meister(true);
                    }
                    break;

                case RATSARMEE_EINHEITEN_BEFEHLSHABER:

                    container.setRatsarmee_Einheiten_BefehlshaberSum(
                            container.getRatsarmee_Einheiten_BefehlshaberSum() + uc.getPoints());
                    if (container.getRatsarmee_Einheiten_BefehlshaberSum() <= maximumPointValue * 0.50) {
                        container.setFlagRatsarmee_Einheiten_Befehlshaber(true);
                    }
                    break;

                case BAUMHERREN_ZENTAUREN:

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

    @Override
    public boolean commanderPresent(List<DemonWorldCard> list) {
        return list.stream().filter(c -> c instanceof UnitCard).anyMatch(card -> ((UnitCard) card).getCommandStars() >= 2);
    }

    /**
     * Method calculates number of "old Heroes" for the army. The maximum is 1 hero
     * per 5 units with the attribute "Thanaril" OR "Ratsarmee"
     *
     * @param armyList elves faction army list.
     * @return maximum number of units with the "old Heroes" subfaction value.
     */
    private int calculateNumberOfOldHeroes(List<DemonWorldCard> armyList) {

        return (int) armyList.stream().filter(c -> c.getSubFaction().equalsIgnoreCase("Thanaril | Ratsarmee")).count() / 5;
    }

    private boolean treePeopleOrCentaurs(List<DemonWorldCard> armyList) {

        boolean result = true;
        String exclude = "";

        // what is the first pick
        Optional<DemonWorldCard> firstPick = armyList.stream().filter(c -> c.getSubFaction().equalsIgnoreCase("Zentauren|Baumherren")).findFirst();
        //make sure all cards do NOT have the one not picked!

        if (firstPick.isPresent()) {
            exclude = firstPick.get().
                    getSubFaction().
                    equalsIgnoreCase("Zentauren") ? "baumherrem" : "Zentauren";
        }

        for (DemonWorldCard uc : armyList) {
            if (uc.getSubFaction().equalsIgnoreCase(exclude)) ;
            result = false;
        }

        return true;
    }


}
