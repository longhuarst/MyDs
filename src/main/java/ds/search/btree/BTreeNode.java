package ds.search.btree;

public class BTreeNode {

    int key[];//关键字

    int min; //最小关键字数
    int max; //最大关键字数

    BTreeNode child[];//子结点

    public int getKeyLength(){
        return key.length;
    }



    public BTreeNode getChild(int i){
        return child[i];
    }

    public int getKey(int i){
        return key[i];
    }

    public int[] getKey() {
        return key;
    }

    public void setKey(int[] key) {
        this.key = key;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public BTreeNode[] getChild() {
        return child;
    }

    public void setChild(BTreeNode[] child) {
        this.child = child;
    }
}
