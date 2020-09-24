package marcel.demonworld.armygenerator.GameLogic.armieCalculators;

import marcel.demonworld.armygenerator.dto.armyDTOs.CalculatedArmyResult;
import marcel.demonworld.armygenerator.dto.statCards.DemonWorldCard;
import marcel.demonworld.armygenerator.dto.statCards.UnitCard;

import java.util.List;

/**
 * ArmyCalculator implementation. See interface.
 */
public class GoblinArmyCalculator implements ArmyCalculator {

    private boolean flagInfanterie = false;
    private boolean flagInsektenreiter = false;
    private boolean flagRieseninsekten = false;
    private boolean flagGeraete = false;
    private boolean flagSchamanen = false;
    private boolean flagverbuendete_Orktruppen = false;
    private boolean flagHelden_Befehlshaber = false;
    private boolean ArmyFlag = false;

    // emtpy dto  for the return values
   // CalculatedArmyResult calculatedArmyResult = new CalculatedArmyResult();

    @Override
    public CalculatedArmyResult CalculatePointCost(List<DemonWorldCard> list, final float maximumPointValue) {

        // net and subfaction totals
        int totalSum = 0;
        int infanterieSum = 0;
        int insektenreiterSum = 0;
        int rieseninsektenSum = 0;
        int geraeteSum = 0;
        int verbuendete_OrktruppenSum = 0;
        int schamanenSum = 0;
        int helden_BefehlshaberSum = 0;

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

                    infanterieSum += uc.getPoints();

                    if (infanterieSum >= infanterieTotal) {
                        flagInfanterie = true;
                    }
                    break;

                case "insektenreiter":

                    insektenreiterSum += uc.getPoints();

                    if (insektenreiterSum <= reiterTotal) {
                        flagInsektenreiter = true;
                    }
                    break;

                case "rieseninsekten":

                    rieseninsektenSum += uc.getPoints();

                    if (rieseninsektenSum <= insektenTotal) {
                        flagRieseninsekten = true;
                    }
                    break;

                case "geraete":

                    geraeteSum += uc.getPoints();

                    if (geraeteSum <= geraeteTotal) {
                        flagGeraete = true;
                    }
                    break;

                case "helden_befehlshaber":

                    helden_BefehlshaberSum += uc.getPoints();

                    if (helden_BefehlshaberSum <= heldenTotal) {
                        flagHelden_Befehlshaber = true;
                    }
                    break;

                case "schamanen":

                    schamanenSum += uc.getPoints();

                    if (schamanenSum < schamamenTotal) {
                        flagSchamanen = true;
                    }
                    break;

                case "verbündete_orktruppen":

                    verbuendete_OrktruppenSum += uc.getPoints();

                    if (verbuendete_OrktruppenSum <= orksTotal) {
                        flagverbuendete_Orktruppen = true;
                    }
                    break;
            }

            totalSum += uc.getPoints();
        }

        if (totalSum <= maximumPointValue) {
            ArmyFlag = true;
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


}// end class













