package marcel.demonworld.armygenerator.GameLogic.armieCalculators;

import marcel.demonworld.armygenerator.GameLogic.ResultContainers.GoblinResultContainer;
import marcel.demonworld.armygenerator.dto.armyDTOs.CalculatedArmyResult;
import marcel.demonworld.armygenerator.dto.statCardDTOs.DemonWorldCard;
import marcel.demonworld.armygenerator.dto.statCardDTOs.UnitCard;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * ArmyCalculator implementation. See interface.
 */
public class GoblinArmyCalculator implements ArmyCalculator {

    @Autowired
    GoblinResultContainer container;


    // emtpy dto  for the return values
    // CalculatedArmyResult calculatedArmyResult = new CalculatedArmyResult();

    @Override
    public CalculatedArmyResult CalculatePointCost(List<DemonWorldCard> list, final float maximumPointValue) {


        // min. and max. point fraction for subfactions
        final float infanterieTotal = 0.30f * maximumPointValue;
        final float reiterTotal = 0.40f * maximumPointValue;
        final float insektenTotal = 0.40f * maximumPointValue;
        final float geraeteTotal = 0.20f * maximumPointValue;
        final float orksTotal = 0.20f * maximumPointValue;
        final float schamamenTotal = 0.30f * maximumPointValue;
        final float heldenTotal = 0.30f * maximumPointValue;

        for (DemonWorldCard uc : list) {

            switch (uc.getSubFaction()) {
                case "infanterie":

                    container.setInfanterieSum(container.getInfanterieSum() + uc.getPoints());

                    if (container.getInfanterieSum() >= infanterieTotal) {
                        container.setFlagInfanterie(true);
                    }
                    break;

                case "insektenreiter":

                    container.setInsektenreiterSum(container.getInsektenreiterSum() + uc.getPoints());

                    if (container.getInsektenreiterSum() >= reiterTotal) {
                        container.setFlagInsektenreiter(true);
                    }
                    break;

                case "rieseninsekten":

                    container.setRieseninsektenSum(container.getRieseninsektenSum() + uc.getPoints());

                    if (container.getRieseninsektenSum() >= insektenTotal) {
                        container.setFlagRieseninsekten(true);
                    }
                    break;

                case "geraete":

                    container.setGeraeteSum(container.getGeraeteSum() + uc.getPoints());

                    if (container.getGeraeteSum() >= geraeteTotal) {
                        container.setFlagGeraete(true);
                    }
                    break;

                case "helden_befehlshaber":

                    container.setHelden_BefehlshaberSum(container.getHelden_BefehlshaberSum() + uc.getPoints());

                    if (container.getHelden_BefehlshaberSum() >= heldenTotal) {
                        container.setFlagHelden_Befehlshaber(true);
                    }
                    break;

                case "schamanen":

                    container.setSchamanenSum(container.getSchamanenSum() + uc.getPoints());

                    if (container.getSchamanenSum() >= schamamenTotal) {
                        container.setFlagSchamanen(true);
                    }
                    break;


                case "verbündete_orktruppen":

                    container.setVerbuendete_OrktruppenSum(container.getVerbuendete_OrktruppenSum() + uc.getPoints());

                    if (container.getVerbuendete_OrktruppenSum() >= orksTotal) {
                        container.setFlagverbuendete_Orktruppen(true);
                    }
                    break;
            }

            container.setTotalSum(container.getTotalSum() + uc.getPoints());
        }

        if (container.getTotalSum() <= maximumPointValue && commanderPresent(list)) {
            container.setArmyFlag(true);
        }

        //TODO: you got to rework the return DTO!
        return null;
    }


    /**
     * Method confirms whether the army has a valid commander.
     *
     * @param list the cards of the current army list.
     * @return boolean, is a valid commander present?
     */
    @Override
    public boolean commanderPresent(List<DemonWorldCard> list) {
        return list.stream().filter(c -> c instanceof UnitCard).anyMatch(card -> ((UnitCard) card).getCommandStars() >= 2);
    }

}// end class













