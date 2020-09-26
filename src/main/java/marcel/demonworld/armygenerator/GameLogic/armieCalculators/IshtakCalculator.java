package marcel.demonworld.armygenerator.GameLogic.armieCalculators;

import marcel.demonworld.armygenerator.GameLogic.ResultContainers.IshtakResultContainer;
import marcel.demonworld.armygenerator.dto.armyDTOs.CalculatedArmyResult;
import marcel.demonworld.armygenerator.dto.statCardDTOs.DemonWorldCard;
import marcel.demonworld.armygenerator.dto.statCardDTOs.UnitCard;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static marcel.demonworld.armygenerator.GameLogic.constants.SubFactions.ishtak.IshtakSubFaction.*;

public class IshtakCalculator implements ArmyCalculator {

    @Autowired
    IshtakResultContainer container;

    @Override
    public CalculatedArmyResult CalculatePointCost(List<DemonWorldCard> list, float maximumPointValue) {


        for (DemonWorldCard uc : list) {

            switch (uc.getSubFaction()) {
                case EISHEXEN:
                    container.setEishexenSum(container.getEishexenSum() + uc.getPoints());

                    if (container.getEishexenSum() >= maximumPointValue * 0.10 && container.getEishexenSum() <= maximumPointValue * 0.60) {
                        container.setFlagEishexen(true);
                    }

                    break;
                case EISRIESEN:
                    container.setEisriesenSum(container.getEisriesenSum() + uc.getPoints());

                    if (container.getEisriesenSum() >= maximumPointValue * 0.30) {
                        container.setFlagEisriesen(true);
                    }

                    break;
                case DAEMONEN:
                    container.setDaemonenSum(container.getDaemonenSum() + uc.getPoints());

                    if (container.getDaemonenSum() <= maximumPointValue * 0.50) {
                        container.setFlagDaemonen(true);
                    }

                    break;
                case UNTOTE:
                    container.setUntoteSum(container.getUntoteSum() + uc.getPoints());

                    if (container.getUntoteSum() <= maximumPointValue * 0.50) {
                        container.setFlagUntote(true);
                    }

                    break;
                case TIERMENSCHEN:
                    container.setTiermenschenSum(container.getTiermenschenSum() + uc.getPoints());

                    if (container.getTiermenschenSum() >= maximumPointValue * 0.10 && container.getTiermenschenSum() <= maximumPointValue * 0.60) {
                        container.setFlagTiermenschen(true);
                    }

                    break;
                case MENSCHEN:
                    container.setMenschenSum(container.getMenschenSum() + uc.getPoints());

                    if (container.getMenschenSum() >= maximumPointValue * 0.10 && container.getMenschenSum() <= maximumPointValue * 0.60) {
                        container.setFlagMenschen(true);
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
