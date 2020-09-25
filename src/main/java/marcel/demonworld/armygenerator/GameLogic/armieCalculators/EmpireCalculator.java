package marcel.demonworld.armygenerator.GameLogic.armieCalculators;

import static marcel.demonworld.armygenerator.GameLogic.constants.SubFactions.empire.EmpireSubFaction.*;

import marcel.demonworld.armygenerator.GameLogic.ResultContainers.EmpireResultContainer;
import marcel.demonworld.armygenerator.dto.armyDTOs.CalculatedArmyResult;
import marcel.demonworld.armygenerator.dto.statCardDTOs.DemonWorldCard;
import marcel.demonworld.armygenerator.dto.statCardDTOs.UnitCard;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * additional rules for the empire
 * <p>
 * - only one mark!
 * - one ** commander
 */
public class EmpireCalculator implements ArmyCalculator {

    @Autowired
    EmpireResultContainer container;

    @Override
    public CalculatedArmyResult CalculatePointCost(List<DemonWorldCard> list, float maximumPointValue) {

        double[] nordmarkotalPointValue = {0, 0};
        double suedmarkTotalPointValue = 0;
        double ostmarkotalPointValue = 0;
        double westmarkTotalPointValue = 0;

        //best data structure?
        Map< String, double []>  borderMarkPointValues =  new HashMap<>();
        borderMarkPointValues.


        if (container.getPickedMark().equalsIgnoreCase("Nordmark")) {

            borderMarkPointValues.put("Nordmark", new double[]{ 0,1, 0.5});


            nordmarkotalPointValue[0] = 0.1 * maximumPointValue;
            nordmarkotalPointValue[1] = 0.5 * maximumPointValue;
            suedmarkTotalPointValue = 0;
            ostmarkotalPointValue = 0;
            westmarkTotalPointValue = 0;

        } else if (container.getPickedMark().equalsIgnoreCase("Südmark")) {
            suedmarkTotalPointValue = 0.5 * maximumPointValue;
            nordmarkotalPointValue = 0;
            ostmarkotalPointValue = 0;
            westmarkTotalPointValue = 0;
        } else if (container.getPickedMark().equalsIgnoreCase("Ostmark")) {
            ostmarkotalPointValue = 0.5 * maximumPointValue;
            nordmarkotalPointValue = 0;
            suedmarkTotalPointValue = 0;
            westmarkTotalPointValue = 0;

        } else if (container.getPickedMark().equalsIgnoreCase("Westmark")) {
            westmarkTotalPointValue = 0.5 * maximumPointValue;
            nordmarkotalPointValue = 0;
            suedmarkTotalPointValue = 0;
            ostmarkotalPointValue = 0;
        }


        for (DemonWorldCard uc : list) {

            switch (uc.getSubFaction()) {
                case NORDMARK:
                    container.setNordmarkSum(container.getNordmarkSum() + uc.getPoints());

                    if (container.getNordmarkSum() >= nordmarkotalPointValue[0] && container.getNordmarkSum() <= nordmarkotalPointValue[1]) {
                        container.setFlagnordmark(true);
                    }
                    break;
                case SUEDMARK:
                    container.setSuedmarkSum(container.getSuedmarkSum() + uc.getPoints());

                    if (container.getSuedmarkSum() >= maximumPointValue * .10 && container.getSuedmarkSum() <= maximumPointValue * .50) {
                        container.setFlagsuedmark(true);
                    }
                    break;
                case WESTMARK:
                    container.setWestmarkSum(container.getWestmarkSum() + uc.getPoints());

                    if (container.getWestmarkSum() >= maximumPointValue * .10 && container.getWestmarkSum() <= maximumPointValue * .50) {
                        container.setFlagwestmark(true);
                    }
                    break;
                case OSTMARK:
                    container.setOstmarkSum(container.getOstmarkSum() + uc.getPoints());

                    if (container.getOstmarkSum() >= maximumPointValue * .10 && container.getOstmarkSum() <= maximumPointValue * .50) {
                        container.setFlagostmark(true);
                    }
                    break;
                case ZENTRALMARK:
                    container.setNordmarkSum(container.getNordmarkSum() + uc.getPoints());

                    if (container.getNordmarkSum() >= maximumPointValue * .10 && container.getNordmarkSum() <= maximumPointValue * .50) {
                        container.setFlagnordmark(true);
                    }
                    break;
                case ORDEN:
                    break;
                case PROVINZHEER:
                    break;
                case KAISERHEER:
                    break;
                case HELDEN_BEFEHLSHABER:
                    break;
                case MAGIER_PRIESTER:
                    break;
            }

            container.setTotalSum(container.getTotalSum() + uc.getPoints());
        }

        if (container.getTotalSum() <= maximumPointValue) {
            container.setArmyFlag(true);
        }
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
