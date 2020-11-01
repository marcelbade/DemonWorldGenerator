package marcel.demonworld.armygenerator.frontEnd.table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;

/**
 * class generates a table.
 */
@Component
public class MainTable {

    @Autowired
    TableUtilities utils;

    public JTable createMainTable(String faction) throws IllegalAccessException {

        JTable table = new JTable(utils.createTableData("Zwerge"), utils.getUnitStatNames());
        table.setBounds(0, 0, 800, 600);

        return table;
    }
}
