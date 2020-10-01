package marcel.demonworld.armygenerator.frontEnd.treeView;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
class DtoTreeNode<T> {

    private DtoTreeNode<T> Owner;
    private T type;
    private List<DtoTreeNode<T>> childNodes = new ArrayList<>();

    public DtoTreeNode(T type) {
        this.type = type;
    }

    public void setChildNodes(List<DtoTreeNode<T>> childNodes) {
        this.childNodes = childNodes;
        this.childNodes.forEach(n -> n.setOwner(this));
    }
}
