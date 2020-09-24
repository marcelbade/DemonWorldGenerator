package marcel.demonworld.armygenerator.GameLogic.armieCalculators;

import marcel.demonworld.armygenerator.dto.armyDTOs.CalculatedArmyResult;
import marcel.demonworld.armygenerator.dto.statCards.DemonWorldCard;
import marcel.demonworld.armygenerator.dto.statCards.UnitCard;
import java.util.List;
import static marcel.demonworld.armygenerator.GameLogic.constants.SubFactions.dwarves.DwarvesSubFaction.*;

public class DwarfCalculator implements ArmyCalculator {

    // rule compliance flags
    private boolean flagGeneraltroops = false;
    private boolean flagGaeta = false;
    private boolean flagZahra = false;
    private boolean flagHeroes_characters_priests = false;
    private boolean flagAllies = false;

    // point total for the entire army and all subfactions
    private int generaltroopsSum = 0;
    private int gaetaSum = 0;
    private int zahraSum = 0;
    private int heroes_characters_priestsSum = 0;
    private int alliesSum = 0;


    @Override
    public CalculatedArmyResult CalculatePointCost(List<DemonWorldCard> list, float maximumPointValue) {


            // TODO: determine choosen dwarf realm? chosen one allowed total is 40 other one or allies is 20!
            // allie or other realm ! EXCLUSIVE OR!!!

        /**
         * TODO how to transport which realm was picked ->
         * idea 1: one extra unitCard that has the information (makes little sense )
         * better idea: dto of return values. contains the picked units and meta info
         *  one for every army
         *
         *  DwarfPickDTO:
         * {
         *     String Name_of_List
         *     Date CreatedOn
         *     String Author
         *     list<UnitCards>
         *     list<ItemCards>
         *     boolean Gaeta
         *     boolean Allies
         * }
         *
         *
         * TODO: item value :P -> needs to be added to the sums!
         * easiest would be an abstract class DemonWorldCard. Simple class cast Problem: DTO and persistence??
         *
         */



        for (DemonWorldCard uc : list) {

            switch (uc.getSubFaction()) {
                case GAETA:
                    gaetaSum += uc.getPoints();

                    //TODO FINISH THIS
                    if (gaetaSum >= maximumPointValue   ) {
                        flagGaeta = true;
                    }


                    break;

                case ZAHRA:
                    zahraSum += uc.getPoints();

                    //TODO FINISH THIS
                    if (zahraSum >= maximumPointValue    ) {
                        flagZahra = true;
                    }


                    break;
                case GENERALTROOPS:
                    generaltroopsSum += uc.getPoints();

                    if (generaltroopsSum >= maximumPointValue * 0.3) {
                        flagGeneraltroops = true;
                    }

                    break;
                case HEROES_CHARACTERS_PRIESTS:

                    break;
                case ALLIES:

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
