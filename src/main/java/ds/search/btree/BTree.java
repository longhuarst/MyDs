package ds.search.btree;


public class BTree {


    BTreeNode root;

    BTree(){
        root = null;
    }


    /*
    * 查找关键字所在结点
    * */
    BTreeNode find(int key){

        BTreeNode node = root;

        while(node != null){

            //!FixMe 这里现在采用暴力搜索法，不进行优化，后期再进行优化
            for (int i=0;i<node.getKeyLength();i++){
                if (node.getKey(i) == key){
                    return node;
                }
            }
            //没搜索到

            if (key < node.getKey(0)){
                node = node.getChild(0);
                continue;
            }

            if (key > node.getKey(node.getKeyLength()-1)){
                node = node.getChild(node.getKeyLength());
                continue;
            }

            for (int i=0;i<node.getKeyLength()-1;++i){
                if (key > node.getKey(i) && key < node.getKey(i+1)){
                    node = node.getChild(i+1);
                    continue;
                }
            }

            System.out.println("---- 出现异常 ----");
            return null;

        }
        return null;
    }


    /*
    * 插入关键字
    * */
    boolean insert(int key){

        //查找插入的点

        BTreeNode node = find(key);




        return true;
    }

}
