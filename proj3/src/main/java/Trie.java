/**
 * @author Bunny
 * @create 2022-01-25 13:48
 */
public class Trie {

    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void add(String key, String name) {
        TrieNode curNode = root;

        for (int i = 0; i < key.length(); i++) {
            char ch = key.charAt(i);
            if (curNode.containKey(ch)) {
                curNode = curNode.getChild(ch);
            } else {
                TrieNode trieNode = new TrieNode(ch, false);
                curNode.addChild(ch, trieNode);
                curNode = trieNode;
            }
            if (i == key.length() - 1) {
                curNode.setEnd(true);
                curNode.getNames().add(name);
            }
        }
    }

    public TrieNode find(String key){
        TrieNode cur = root;

        for (int i = 0; i < key.length(); i++) {
            Character ch = key.charAt(i);
            TrieNode child = cur.getChild(ch);
            if (child == null){
                return null;
            }
            cur = child;
        }
        return cur;
    }


}
