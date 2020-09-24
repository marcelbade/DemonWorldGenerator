package marcel.demonworld.armygenerator.GameLogic.armieCalculators;

import marcel.demonworld.armygenerator.dto.armyDTOs.CalculatedArmyResult;
import marcel.demonworld.armygenerator.dto.statCards.DemonWorldCard;

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
}
