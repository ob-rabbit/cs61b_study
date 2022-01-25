import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author Bunny
 * @create 2022-01-25 14:01
 */
public class TrieNode {

    private Map<Character, TrieNode> children;

    private Character value;
    private boolean isEnd;
    private List<String> names;
    public TrieNode() {
        children = new HashMap<>();
        names = new LinkedList<>();
    }

    public TrieNode(Character ch, boolean isEnd) {
        this.value = ch;
        this.isEnd = isEnd;
        children = new HashMap<>();
        names = new LinkedList<>();
    }

    public Character getValue() {
        return value;
    }

    public void setValue(Character value) {
        this.value = value;
    }

    public Map<Character, TrieNode> getChildren() {
        return children;
    }

    public void addChild(Character ch, TrieNode node) {
        children.put(ch, node);
    }

    public TrieNode getChild(Character ch) {
        return children.get(ch);
    }

    public boolean isEnd() {
        return isEnd;
    }

    public void setEnd(boolean end) {
        isEnd = end;
    }

    public boolean containKey(Character ch) {
        return children.containsKey(ch);
    }

    public List<String> getNames() {
        return names;
    }
}
