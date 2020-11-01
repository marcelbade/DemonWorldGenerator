package marcel.demonworld.armygenerator.frontEnd.treeView;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
/*
Tree data structure implementation.
TODO FOR NOTES: add to notes.
 */
class DtoTreeNode<T> {

    private DtoTreeNode<T> Owner;
    private T type;
    private List<DtoTreeNode<T>> childNodes = new ArrayList<>();

    public DtoTreeNode(T type) {
        this.type = type;
    }

    public void setChildNodes(List<DtoTreeNode<T>> childNodes) {
        this.childNodes = childNodes;
        setRoot();
    }

    public void addChildNode(DtoTreeNode<T> newChildNode) {
        this.childNodes.add(newChildNode);
        setRoot();
    }

    private void setRoot() {
        this.childNodes.forEach(n -> n.setOwner(this));
    }
}
