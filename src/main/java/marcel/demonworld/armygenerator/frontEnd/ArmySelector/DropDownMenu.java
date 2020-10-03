package marcel.demonworld.armygenerator.frontEnd.ArmySelector;

import marcel.demonworld.armygenerator.dto.statCardDTOs.UnitCard;
import marcel.demonworld.armygenerator.services.SelectArmyService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.*;
import java.awt.event.ItemEvent;

//combobox
public class DropDownMenu {

    @Autowired
    private SelectArmyService service;
    private String selected;

    public JComponent createArmySelectMenu() {

        String[] armies = (String[]) service.returnAll().stream().map(UnitCard::getFaction).distinct().toArray();

        JComboBox<String> DropDownMenu = new JComboBox<>(armies);
        selected = (String) DropDownMenu.getSelectedItem();
        
        return DropDownMenu;
    }
}
