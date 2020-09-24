package marcel.demonworld.armygenerator.GameLogic.armieCalculators;

import marcel.demonworld.armygenerator.GameLogic.ResultContainers.ElvesReturnContainer;
import marcel.demonworld.armygenerator.dto.armyDTOs.CalculatedArmyResult;
import marcel.demonworld.armygenerator.dto.statCards.DemonWorldCard;
import marcel.demonworld.armygenerator.dto.statCards.UnitCard;

import java.util.List;

import static marcel.demonworld.armygenerator.GameLogic.constants.SubFactions.elves.ElvesSubFaction.*;

/**
 * ArmyCalculator implementation. See interface.
 */
public class ElvesCalculator implements ArmyCalculator {

    ElvesReturnContainer container = new ElvesReturnContainer();

    @Override
    public CalculatedArmyResult CalculatePointCost(List<DemonWorldCard> list, float maximumPointValue) {

//        //*******************************************************
//        //TEST -> reflection solution instead of switch statement !
//
//        int reflectionTest_PointTotal = 0;
//
//        Field[] containerFields = container.getClass().getDeclaredFields();
//        list.stream().forEach(demonWorldCard -> {
//
//            Arrays.stream(containerFields).
//                    filter(f -> f.getName().contains(demonWorldCard.getSubFaction()) && f.getName().contains("sum")).
//                    map(field -> {
//                                field.setAccessible(true);
//                                try {
//                                    field.setInt(field.getName(), field.getInt(field.getName()) + demonWorldCard.getPointCost());
//
//
//                                } catch (IllegalAccessException e) {
//                                    e.printStackTrace();
//                                }
//                                return container;
//                            }
//                    );
//        });
//
//        // army net points?
//        Arrays.stream(containerFields).filter( fieldNetpoints -> fieldNetpoints.getName().contains("totalSum")).map(
//
//                fnp -> {
//                    try {
//                        return fnp.setInt(fnp.getName(), field.getInt(field.getName()) + demonWorldCard.getPointCost());
//                    } catch (IllegalAccessException e) {
//                        e.printStackTrace();
//                    }
//                }
//        ) ;
//
//
//        //*******************************************************


        int maxNumberOfOldHeroes = calculateNumberOfOldHeroes(list);

        for (DemonWorldCard uc : list) {


            switch (uc.getSubFaction()) {

                case THANARILCLANTRUPPEN:

                    container.setThanarilClantruppenSum(container.getThanarilClantruppenSum() + uc.getPoints());

                    if (container.getThanarilClantruppenSum() >= maximumPointValue * 0.30 &&
                            container.getThanarilClantruppenSum() >= maximumPointValue * 0.70) {
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

                    container.setThanaril_Clanlords_Barden_BefehlshaberSum(container.getThanaril_Clanlords_Barden_BefehlshaberSum() + uc.getPoints());

                    if (container.getThanaril_Clanlords_Barden_BefehlshaberSum() <= maximumPointValue * 0.30) {
                        container.setFlagThanaril_Clanlords_Barden_Befehlshaber(true);
                    }
                    break;

                case ALTE_HELDEN:

                    //TODO: confirm that this is correct!

                    container.setNumberOfOldHeroes(container.getNumberOfOldHeroes() + 1);
                    container.setAlte_HeldenSum(container.getAlte_HeldenSum() + uc.getPoints());

                    if (container.getNumberOfOldHeroes() <= maxNumberOfOldHeroes) {
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

                    container.setOrea_Vanar_Truppen_MeisterSum(container.getOrea_Vanar_Truppen_MeisterSum() + uc.getPoints());

                    if (container.getOrea_Vanar_Truppen_MeisterSum() <= maximumPointValue * 0.30) {
                        container.setFlagOrea_Vanar_Truppen_Meister(true);
                    }
                    break;

                case RATSARMEE_EINHEITEN_BEFEHLSHABER:

                    container.setRatsarmee_Einheiten_BefehlshaberSum(container.getRatsarmee_Einheiten_BefehlshaberSum() + uc.getPoints());
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

        container.setArmyFlag(ArmyCompliance.checkALlComplianceFlags(this));

        if (container.getTotalSum() <= maximumPointValue && container.isArmyFlag()) {
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


    /**
     * Method calculates number of "old Heroes" for the army.
     * The maximum is 1 hero per 5 units with the attribute "Thanaril"  OR "Ratsarmee"
     *
     * @param armyList elve faction army list.
     * @return maximun number of units with the "old Heroes" subfaction value.
     */
    private int calculateNumberOfOldHeroes(List<DemonWorldCard> armyList) {

        String[] attributes = {"Thanaril", "Ratsarmee", "ratsarmee", "thanaril"};
        int allowance = 0;

        for (DemonWorldCard dc : armyList) {
            for (String s : attributes) {
                if (dc instanceof UnitCard && dc.getSubFaction().contains(s))
                    allowance += 1;
            }
        }
        return allowance / 5;
    }
}
