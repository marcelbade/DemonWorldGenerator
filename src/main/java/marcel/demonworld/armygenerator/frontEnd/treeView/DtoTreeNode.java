package marcel.demonworld.armygenerator.frontEnd.treeView;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Getter
@Setter
/*
Tree data structure implementation.
TODO FOR NOTES: add to notes.
 */
public class DtoTreeNode<T> {

    private DtoTreeNode<T> parentNode;
    private T currentNode;
    private List<DtoTreeNode<T>> childNodes = new ArrayList<>();

    public DtoTreeNode(T currentNode) {
        this.currentNode = currentNode;
    }

    public DtoTreeNode( ) {
    }

    public void setChildNodes(List<DtoTreeNode<T>> childNodes) {
        this.childNodes = childNodes;
        setRoot();
    }


    private void setRoot() {
        this.childNodes.forEach(n -> n.setParentNode(this));
    }
}
