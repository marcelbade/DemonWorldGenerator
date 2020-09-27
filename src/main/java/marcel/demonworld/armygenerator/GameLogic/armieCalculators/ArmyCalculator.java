package marcel.demonworld.armygenerator.GameLogic.armieCalculators;

import marcel.demonworld.armygenerator.dto.armyDTOs.CalculatedArmyResult;
import marcel.demonworld.armygenerator.dto.statCardDTOs.DemonWorldCard;

import java.util.Iterator;
import java.util.List;

/**
 * ArmyCalculator. CalculatedArmyResult returns the total point cost,
 * all subtotals and checks for rule compliance for a given army.
 * <p>
 * The idea: frontend sends a list of unit cards with a button click (add card to list). the current selection
 * of cards is sent to the backend and this class calculates everything. Returns a nested dto that entails total and
 * compliance.
 */
public interface ArmyCalculator {
    CalculatedArmyResult CalculatePointCost(List<DemonWorldCard> list, float maximumPointValue);

    boolean commanderPresent(List<DemonWorldCard> list);

    default void subtractPointsAndRemoveFromList(List<DemonWorldCard> list, DemonWorldCard card) {

        Iterator<DemonWorldCard> iterator = list.iterator();


        //todo: Determine, at a later time, if you need to make the substract method mroe complicated?
        while (iterator.hasNext()) {
            if (((DemonWorldCard) iterator).getName().equalsIgnoreCase(card.getName())) {
                iterator.remove();
            }
        }
    }
}
