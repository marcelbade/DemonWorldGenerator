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
 * - only one border mark!
 * - one ** commander
 */
public class EmpireCalculator implements ArmyCalculator {

    @Autowired
    EmpireResultContainer container;

    double[] nordmarkTotalPointValue = {0, 0};
    double[] suedmarkTotalPointValue = {0, 0};
    double[] ostmarkTotalPointValue = {0, 0};
    double[] westmarkTotalPointValue = {0, 0};


    @Override
    public CalculatedArmyResult CalculatePointCost(List<DemonWorldCard> list, float maximumPointValue) {

        setTheBorderMarksPoints(maximumPointValue);

        for (DemonWorldCard uc : list) {

            switch (uc.getSubFaction()) {
                case NORDMARK:
                    container.setNordmarkSum(container.getNordmarkSum() + uc.getPoints());

                    if (container.getNordmarkSum() >= nordmarkTotalPointValue[0] && container.getNordmarkSum() <= nordmarkTotalPointValue[1]) {
                        container.setFlagNordmark(true);
                    }
                    break;
                case SUEDMARK:
                    container.setSuedmarkSum(container.getSuedmarkSum() + uc.getPoints());

                    if (container.getSuedmarkSum() >= suedmarkTotalPointValue[0] && container.getSuedmarkSum() <= suedmarkTotalPointValue[1]) {
                        container.setFlagSuedmark(true);
                    }
                    break;
                case WESTMARK:
                    container.setWestmarkSum(container.getWestmarkSum() + uc.getPoints());

                    if (container.getWestmarkSum() >= westmarkTotalPointValue[0] && container.getWestmarkSum() <= westmarkTotalPointValue[1]) {
                        container.setFlagWestmark(true);
                    }
                    break;
                case OSTMARK:
                    container.setOstmarkSum(container.getOstmarkSum() + uc.getPoints());

                    if (container.getOstmarkSum() >= ostmarkTotalPointValue[0] && container.getOstmarkSum() <= ostmarkTotalPointValue[1]) {
                        container.setFlagOstmark(true);
                    }
                    break;
                case ZENTRALMARK:
                    container.setZentralmarkSum(container.getZentralmarkSum() + uc.getPoints());

                    if (container.getZentralmarkSum() <= maximumPointValue * .30) {
                        container.setFlagZentralmark(true);
                    }
                    break;

                //TODO: Oi, Smartypants: order magicians count as order, not magicians. Plsease test.
                case ORDEN:
                    container.setOrdenSum(container.getOrdenSum() + uc.getPoints());

                    if (container.getOrdenSum() <= maximumPointValue * .40) {
                        container.setFlagOrden(true);
                    }
                    break;
                case PROVINZHEER:
                    container.setProvinzheerSum(container.getProvinzheerSum() + uc.getPoints());

                    if (container.getProvinzheerSum() >= maximumPointValue * .20 && container.getProvinzheerSum() <= maximumPointValue * .50) {
                        container.setFlagProvinzHeer(true);
                    }
                    break;

                case KAISERHEER:
                    container.setKaiserheerSum(container.getKaiserheerSum() + uc.getPoints());

                    if (container.getKaiserheerSum() >= maximumPointValue * .10 && container.getKaiserheerSum() <= maximumPointValue * .50) {
                        container.setFlagKaiserheer(true);
                    }
                    break;

                case HELDEN_BEFEHLSHABER:
                    container.setHelden_befehlshaberSum(container.getHelden_befehlshaberSum() + uc.getPoints());

                    if (container.getHelden_befehlshaberSum() <= maximumPointValue * .30) {
                        container.setFlagHelden_Befehlshaber(true);
                    }

                    break;
                case MAGIER_PRIESTER:

                    container.setMagier_priesterSum(container.getMagier_priesterSum() + uc.getPoints());

                    if (container.getMagier_priesterSum() <= maximumPointValue * .30) {
                        container.setFlagMagier_Priester(true);
                    }

                    break;
            }

            container.setTotalSum(container.getTotalSum() + uc.getPoints());
        }

        if (container.getTotalSum() <= maximumPointValue) {
            container.setArmyFlag(true);
        }
        return null;
    }

    private void setTheBorderMarksPoints(float maximumPointValue) {

        if (container.getPickedMark().equalsIgnoreCase("Nordmark")) {

            nordmarkTotalPointValue[0] = 0.1 * maximumPointValue;
            nordmarkTotalPointValue[1] = 0.5 * maximumPointValue;

        } else if (container.getPickedMark().equalsIgnoreCase("Südmark")) {

            suedmarkTotalPointValue[0] = 0.1 * maximumPointValue;
            suedmarkTotalPointValue[1] = 0.5 * maximumPointValue;

        } else if (container.getPickedMark().equalsIgnoreCase("Ostmark")) {

            ostmarkTotalPointValue[0] = 0.1 * maximumPointValue;
            ostmarkTotalPointValue[1] = 0.5 * maximumPointValue;

        } else if (container.getPickedMark().equalsIgnoreCase("Westmark")) {

            westmarkTotalPointValue[0] = 0.1 * maximumPointValue;
            westmarkTotalPointValue[1] = 0.5 * maximumPointValue;
        }

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
