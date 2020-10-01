package marcel.demonworld.armygenerator.frontEnd.treeView;

import marcel.demonworld.armygenerator.dto.statCardDTOs.DemonWorldCard;
import marcel.demonworld.armygenerator.dto.statCardDTOs.UnitCard;
import marcel.demonworld.armygenerator.services.SelectArmyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.util.*;
import java.util.stream.Collectors;

//https://www.codejava.net/java-se/swing/jtree-basic-tutorial-and-examples
@Component
public class ArmyTreeView {

    @Autowired
    SelectArmyService service;


    public JComponent createTree(String faction) {

        DtoTreeNode<String> allNodes = treeViewDataGenerator(faction);

        //root
        DefaultMutableTreeNode army = new DefaultMutableTreeNode(allNodes.getType());

        //units + subfactions
        for (DtoTreeNode<String> subFaction : allNodes.getChildNodes()) {

            //TEST
            System.out.println("SUBFACTION =>" + subFaction.getType());

            //create a subFaction view node
            DefaultMutableTreeNode subFactionNode = new DefaultMutableTreeNode(subFaction.getType());

            //get units filtered for one subFaction
            //TODO: impose an order!!
            List<DtoTreeNode<String>> unitsOfSubFaction = subFaction
                    .getChildNodes()
                    .stream()
                    .peek(p -> {
                        System.out.println("THESE STREAM SHOULD HAVE the units:: " + p.getType());
                    })
                    .filter(unit -> unit.getOwner().getType().equals(subFaction.getType())) // <= OWNER IS NULL!!
                    .collect(Collectors.toList());

            //add units -> subFaction
            for (DtoTreeNode<String> unitOfSubFaction : unitsOfSubFaction) {
                subFactionNode.add(new DefaultMutableTreeNode(unitOfSubFaction.getType()) {
                });
            }
            army.add(subFactionNode);
        }
        return new JTree(army);
    }

    //generate data for root + all nodes
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
//TODO: move this to its own file

