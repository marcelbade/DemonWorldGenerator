package marcel.demonworld.armygenerator.frontEnd.treeView;


import marcel.demonworld.armygenerator.dto.statCardDTOs.UnitCard;
import marcel.demonworld.armygenerator.services.SelectArmyService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.*;
import java.awt.*;
import java.util.Optional;

public class TreeButton {

    @Autowired
    SelectArmyService service;

    public JButton createButton() {

        JButton bttn = new JButton();

        bttn.setForeground(Color.white);
        bttn.setBackground(Color.darkGray);

        bttn.addActionListener(e -> {
            addUnitToArmyList(bttn.getName());
        });
        return bttn;
    }

    //todo: access modifier appropriate
    public void addUnitToArmyList(String unitName) {

        //TODO: ADD UNIT TO LIST
        // get the unit name, faction name
        String faction;
        Optional<String> unit = service.returnAll().stream().filter(c -> c.getName().equals(unitName)).map(UnitCard::getFaction).findFirst();

        //if there is an ReturnContainer object, great, add this, if not make it


    }
}




