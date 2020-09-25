package marcel.demonworld.armygenerator.dto.armyDTOs;


import marcel.demonworld.armygenerator.dto.statCardDTOs.UnitCard;

import java.util.List;

/**
 * DTO that stores three things:
 * <ul>
 *  <li>All Units picked by the play up to this point</li>
 *  <li>All items picked by the player up to this point</li>
 *  <li>Meta information relevant to the army,e.g which dwarf realm was picked, which imperial province, ect.</li>
 * </ul>
 */
public class PlayerArmyList {

    //TODO: this should be a general card type for items and units
    List<UnitCard> unitCardList;

    //flag:  For armies with mutually exclusive subfaction choices, which one was picked?
    String choosenSubFaction;

}
