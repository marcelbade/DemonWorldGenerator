package marcel.demonworld.armygenerator.frontEnd.treeView;

import marcel.demonworld.armygenerator.dto.statCardDTOs.DemonWorldCard;
import marcel.demonworld.armygenerator.dto.statCardDTOs.UnitCard;
import marcel.demonworld.armygenerator.services.SelectArmyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellRenderer;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//https://www.codejava.net/java-se/swing/jtree-basic-tutorial-and-examples
@Component
public class ArmyTreeView {

    @Autowired
    SelectArmyService service;


    /**
     * @param faction the name of the army
     * @return a complete treeView consisting of DefaultMutableTreeNode
     */
    public JComponent createTree(String faction) {

        DtoTreeNode<String> allNodes = treeViewDataGenerator(faction);

        //root
        DefaultMutableTreeNode army = new DefaultMutableTreeNode(allNodes.getType());

        //units + subfactions
        for (DtoTreeNode<String> subFaction : allNodes.getChildNodes()) {

            //create a subFaction view node
            DefaultMutableTreeNode subFactionNode = new DefaultMutableTreeNode(subFaction.getType());

            //get units one subFaction
            //TODO: this is ordered alphabetically. Make the order configurable!
            List<DtoTreeNode<String>> unitsOfSubFaction = subFaction.getChildNodes();

            //add units -> subFaction
            for (DtoTreeNode<String> unitOfSubFaction : unitsOfSubFaction) {
                subFactionNode.add(new DefaultMutableTreeNode(unitOfSubFaction.getType()));
            }
            army.add(subFactionNode);
        }

        JTree tree = new JTree(army);

        TreeCellRenderer cellRenderer = new CustomTreeCellRenderer();
        tree.setCellRenderer(cellRenderer);

        return tree;
    }


    //TODO NOT WORKING. Cant find file ?!

    /**
     * Set custom Icons for every item in the tree view.
     *
     * @param tree the Swing tree view
     */
    private void setCustomTreeViewIcons(JTree tree) {

        ImageIcon leafIcon = new ImageIcon("/resources/static/images/TreeViewIcons/D20_black.jpg");
        DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();
        renderer.setOpenIcon(leafIcon);
        tree.setCellRenderer(renderer);

    }

    /**
     * Method generates data for root + all nodes.
     *
     * @param faction - DemonWorld army
     * @return a tree data structure with the form: army (root) -> subFaction (branch) => unit (leaf)
     */
    private DtoTreeNode<String> treeViewDataGenerator(String faction) {

        List<UnitCard> armyList = service.returnArmy(faction);

        //root
        DtoTreeNode<String> root = new DtoTreeNode<>(faction);

        //subfactions
        root.setChildNodes(armyList.stream().map(DemonWorldCard::getSubFaction).distinct().map(DtoTreeNode::new).collect(Collectors.toList()));

        //units, as adjuncts to the subFactions
        root.getChildNodes().forEach(t -> t.setChildNodes(armyList.stream().
                filter(c -> c.getSubFaction().equals(t.getType())).map(c -> new DtoTreeNode<>(c.getName())).collect(Collectors.toList())));


        return root;
    }
}
