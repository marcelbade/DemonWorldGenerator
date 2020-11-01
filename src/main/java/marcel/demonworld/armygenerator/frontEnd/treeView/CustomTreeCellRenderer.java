package marcel.demonworld.armygenerator.frontEnd.treeView;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellRenderer;
import java.awt.*;

/**
 * A custom tree renderer. The reason for the custom implementation are the necessity to render the leaves differently
 * from the other tree nodes, as well as adding buttons to the leaves.
 */
public class CustomTreeCellRenderer implements TreeCellRenderer {

    JPanel renderer;
    JButton bttn = new JButton();

    DefaultTreeCellRenderer defaultRenderer = new DefaultTreeCellRenderer();

    Color backgroundSelectionColor;
    Color backgroundNonSelectionColor;

    // constructor dictates how to render the tree cells.
    public CustomTreeCellRenderer() {
        renderer = new JPanel();

        bttn.setForeground(Color.white);
        bttn.setBackground(Color.darkGray);

        renderer.add(bttn);

        renderer.setBorder(BorderFactory.createLineBorder(Color.black));
        backgroundSelectionColor = defaultRenderer.getBackgroundSelectionColor();
        backgroundNonSelectionColor = defaultRenderer.getBackgroundNonSelectionColor();
    }

    /**
     * Method overrides the standard renderer in the GUI Library for leaves in the tree view,
     * i.e if a node has no children, it is rendered differently. Here, the leaves are the buttons used to add units.
     * <p>
     * All parameters except tree, value and row are flags for the node state,
     * to determine how the node should be rendered in that state.
     *
     * @param tree     the JTree
     * @param value    the actual tree node
     * @param selected how to render node if selected
     * @param expanded how to render node if expanded
     * @param leaf     how to render node if leaf
     * @param row      which row in the tree
     * @param hasFocus how to render node if has focus
     * @return a component (node )
     */
    @Override
    public java.awt.Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded,
                                                           boolean leaf, int row, boolean hasFocus) {
        java.awt.Component TreeComponent = null;
        if (value instanceof DefaultMutableTreeNode && ((DefaultMutableTreeNode) value).isLeaf()) {
            Object userObject = ((DefaultMutableTreeNode) value).getUserObject();

            if (userObject instanceof String) {

                bttn.setText(userObject + " +");
                if (selected) {
                    renderer.setBackground(backgroundSelectionColor);
                } else {
                    renderer.setBackground(backgroundNonSelectionColor);
                }
                renderer.setEnabled(tree.isEnabled());
                TreeComponent = renderer;
            }
        }
        if (TreeComponent == null) {
            TreeComponent = defaultRenderer.getTreeCellRendererComponent(tree,
                    value, selected, expanded, leaf, row, hasFocus);
        }
        return TreeComponent;
    }
}















