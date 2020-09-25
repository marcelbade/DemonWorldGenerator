package marcel.demonworld.armygenerator.GameLogic.armieCalculators;

import marcel.demonworld.armygenerator.GameLogic.ResultContainers.DwarfResultContainer;
import marcel.demonworld.armygenerator.dto.armyDTOs.CalculatedArmyResult;
import marcel.demonworld.armygenerator.dto.statCardDTOs.DemonWorldCard;
import marcel.demonworld.armygenerator.dto.statCardDTOs.UnitCard;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static marcel.demonworld.armygenerator.GameLogic.constants.SubFactions.dwarves.DwarvesSubFaction.*;


/**
 * // min. and max. point fraction for subfactions
 * //minimum
 * final float generalTroopsTotal = 0.30f * maximumPointValue;
 * //one realm is picked as major, max
 * final float majorRealm = 0.40f * maximumPointValue;
 * //other realm OR allies, max
 * final float minorRealmOrAlliesTotal = 0.20f * maximumPointValue;
 * //max
 * final float heroes_characters_priestTotal = 0.50f * maximumPointValue;
 */
public class DwarfCalculator implements ArmyCalculator {

    @Autowired
    DwarfResultContainer container;


    @Override
    public CalculatedArmyResult CalculatePointCost(List<DemonWorldCard> list, float maximumPointValue) {

        double gaetaTotalPointValue;
        double zahraTotalPointValue;

        if (container.getPickedRealm().equalsIgnoreCase("Gaeta")) {

            gaetaTotalPointValue = .40 * maximumPointValue;
            zahraTotalPointValue = .20 * maximumPointValue;

        } else {

            gaetaTotalPointValue = .20 * maximumPointValue;
            zahraTotalPointValue = .40 * maximumPointValue;
        }


        for (DemonWorldCard uc : list) {

            switch (uc.getSubFaction()) {


                case GAETA:
                    container.setGaetaSum(container.getGaetaSum() + uc.getPoints());

                    if (container.getGaetaSum() >= gaetaTotalPointValue) {
                        container.setFlagGaeta(true);
                    }
                    break;
                case ZAHRA:
                    container.setZahraSum(container.getZahraSum() + uc.getPoints());

                    if (container.getZahraSum() >= zahraTotalPointValue) {
                        container.setFlagZahra(true);
                    }
                    break;
                case HEROES_CHARACTERS_PRIESTS:

                    container.setHeroes_characters_priestsSum(container.getHeroes_characters_priestsSum() + uc.getPoints());

                    if (container.getHeroes_characters_priestsSum() >= maximumPointValue * 0.3) {
                        container.setFlagHeroes_characters_priests(true);
                    }
                    break;
                case GENERALTROOPS:
                    container.setGeneraltroopsSum(container.getGeneraltroopsSum() + uc.getPoints());

                    if (container.getGeneraltroopsSum() >= maximumPointValue * 0.3) {
                        container.setFlagGeneraltroops(true);
                    }
                    break;
                case ALLIES:
                    container.setAlliesSum(container.getAlliesSum() + uc.getPoints());

                    if (container.getAlliesSum() >= maximumPointValue * 0.2) {
                        container.setFlagAllies(true);
                    }

                    break;

            }

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
