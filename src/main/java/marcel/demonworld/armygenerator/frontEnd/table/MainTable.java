package marcel.demonworld.armygenerator.frontEnd.table;


import marcel.demonworld.armygenerator.dto.statCardDTOs.UnitCard;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.*;

public class MainTable {

    @Autowired
    TableUtilities utils;
    @Autowired
    UnitCard uc;

    public JTable createMainTable() {

        return new JTable();
    }

}
