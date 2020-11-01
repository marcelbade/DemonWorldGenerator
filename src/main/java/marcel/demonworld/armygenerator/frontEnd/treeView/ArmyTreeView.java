package marcel.demonworld.armygenerator.frontEnd.treeView;

import lombok.Getter;
import lombok.Setter;
import marcel.demonworld.armygenerator.dto.statCardDTOs.DemonWorldCard;
import marcel.demonworld.armygenerator.dto.statCardDTOs.UnitCard;
import marcel.demonworld.armygenerator.frontEnd.ArmySelector.ArmySelection;
import marcel.demonworld.armygenerator.services.SelectArmyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import javax.swing.*;
import javax.swing.tree.*;
import java.util.Enumeration;
import java.util.List;
import java.util.stream.Collectors;

//https://www.codejava.net/java-se/swing/jtree-basic-tutorial-and-examples

/**
 * The class contains the methods for creating a treeView that functions as the select menu for the user.
 * - treeViewDataGenerator: generates q data tree from the DB data. The data tree contains an entire army and
 * has the structure army (root) -> subFaction (child node) -> unit (leaves connected to a child node)
 * - addChildNodesToParent: after constructing the treeView with the root mutableTreeNode, this method creates
 * and connects mutableTreeNode objects for the child and grandchild nodes.
 * - createTree: calls the methods above and  creats the treeView returned to the GUI class.
 */
@Component
@Getter
@Setter
public class ArmyTreeView {

    @Autowired
    SelectArmyService service;
    @Autowired
    ArmySelection armySelection;


    private JTree tree;
    private DefaultTreeModel treeModel;


    /**
     * Method creates a treeView of one army / faction in the game. This TreeView is used to select units for
     * the army list.
     *
     * @param faction the name of the army
     * @return a complete treeView consisting of DefaultMutableTreeNode
     */
    public void createTree(String faction) {

        // create the data tree for the army
        DtoTreeNode<String> armyAsNodes = treeViewDataGenerator(faction);
        // create root node
        DefaultMutableTreeNode armyRootNode = new DefaultMutableTreeNode(armyAsNodes.getCurrentNode());
        // add child nodes
        addChildNodesToParent(armyAsNodes, armyRootNode);
        // create data model for the view by adding the complete data tree

        this.setTreeModel(new DefaultTreeModel(armyRootNode));
        //add listener to the model
        this.getTreeModel().addTreeModelListener(new TreeModelListenerImplementation());
        //create tree
        this.setTree(new JTree(treeModel));
        // flag: set tree to editable
        this.getTree().setEditable(true);

        // set Selection Model
        this.getTree().getSelectionModel().setSelectionMode
                (TreeSelectionModel.SINGLE_TREE_SELECTION);

        //TODO -> THIS ONE :)  treeModel.reload();

        TreeCellRenderer cellRenderer = new CustomTreeCellRenderer();
        this.getTree().setCellRenderer(cellRenderer);
    }

    public void reloadTree() {
        System.out.println("<<FIRED:::ARMY SELECTED->" + armySelection.getSelectedArmy());

        DtoTreeNode<String> n = this.treeViewDataGenerator(armySelection.getSelectedArmy());
        showEntireTree(n);

        //RELOAD
        this.getTreeModel().reload(new DefaultMutableTreeNode(this.treeViewDataGenerator(armySelection.getSelectedArmy()).getCurrentNode()));

        visitAllNodes(this.getTree());
    }


    //TODO TEST OUTPUT: ANYTIME THE CHANGE METHOD FIRES; THIS SHOWS THE NODES CURRENTLY STORED IN THE TREE
    //0=============================================================

    private void showEntireTree(DtoTreeNode<String> root) {
        System.out.println("SHOW ARMY CURRENTLY IN TREE MODEL >>>");
        if (root.getChildNodes().size() > 0) {
            for (int n = 0; n < root.getChildNodes().size(); n++) {
                System.out.println(root.getChildNodes().get(n).toString());
                showEntireTree(root.getChildNodes().get(n));
            }
        }
    }


    private static void visitAllNodes(JTree tree) {
        TreeNode root = (TreeNode) tree.getModel().getRoot();
        visitAllNodes(root);
    }

    private static void visitAllNodes(TreeNode node) {
        System.out.println(node);
        if (node.getChildCount() >= 0) {
            for (Enumeration e = node.children(); e.hasMoreElements(); ) {
                TreeNode n = (TreeNode) e.nextElement();
                visitAllNodes(n);
            }
        }
    }
    //0=============================================================

    /**
     * Method adds the entire Army (subFaction to army, then units to the correct subFaction ) to the root node
     * of the treeView using the custom DTO tree model object created in treeViewDataGenerator.
     *
     * @param armyAsNodes a custom data tree object
     * @param army        the tree view node that represents the same data contained in armyAsNodes.
     */
    private void addChildNodesToParent(DtoTreeNode<String> armyAsNodes, DefaultMutableTreeNode army) {
        //units + subFactions
        for (DtoTreeNode<String> subFaction : armyAsNodes.getChildNodes()) {

            //create a subFaction view node
            DefaultMutableTreeNode subFactionNode = new DefaultMutableTreeNode(subFaction.getCurrentNode());

            //get units for one subFaction
            //TODO: this is ordered alphabetically. Make the order configurable!
            List<DtoTreeNode<String>> unitsOfSubFaction = subFaction.getChildNodes();

            //add the unit nodes to the subFaction node
            for (DtoTreeNode<String> unitOfSubFaction : unitsOfSubFaction) {
                subFactionNode.add(new DefaultMutableTreeNode(unitOfSubFaction.getCurrentNode()));
            }
            //add the subFaction node to the army / root node
            army.add(subFactionNode);
        }
    }


    //TODO NOT WORKING. Can't find file ?!

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
                filter(c -> c.getSubFaction().equals(t.getCurrentNode())).map(c -> new DtoTreeNode<>(c.getName())).collect(Collectors.toList())));


        return root;
    }
}
