import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class djikstra {



    static public djikstraVertex calc_shortest(List<djikstraVertex> running, djikstraVertex v){

//        if (running.size() == 1){
//            System.out.println("running.size() == 1");
//            return running.get(0);
//        }

        Map<djikstraVertex, String> nextlist = v.getNext();//找到所有剩下的点

        if (nextlist == null){
            return null;
        }

        djikstraVertex vmin = null;//最小点
        //遍历当前下一个目标的点集合
        for (djikstraVertex vnext: nextlist.keySet()){
            if (nextlist.get(vnext) == null){
                System.out.println("null!");
            }
            int pathlen = Integer.parseInt(nextlist.get(vnext));

            pathlen += v.shortest;//加入当前点的路径长度



            for (int i=0;i<running.size();++i){
                if (running.get(i).equals(vnext)){
                    //找到了目标点
                    if (vnext.getShortest() == -1 || vnext.getShortest() > pathlen){
                        //目标点 未计算过路径  或者路径长
                        vnext.setShortest(pathlen);
                        vnext.setShortestPath(v.shortestPath+"->"+vnext.getData());
                    }

                    if(vmin == null)
                        vmin = vnext;
                    else if (vnext.getShortest()< vmin.getShortest() ){
                        vmin = vnext;
                    }
                }else{
                    if (running.get(i).getShortest() == -1)
                        continue;;
                    if(vmin == null)
                        vmin = running.get(i);
                    if (running.get(i).getShortest() < vmin.getShortest()){
                        vmin = running.get(i);
                    }
                }
            }
        }



       System.out.println("当前最小路径点 "+vmin.getData() + "|"+vmin.getShortest());



        return vmin;



    }

//1-2-20
//1-5-8
//2-3-30
//2-7-11
//3-4-10
//5-2-14
//5-6-3
//6-2-6
//6-7-18
//7-3-9
//7-4-12
//7-8-8
//8-4-3
//x





    public static void main(String[] args) throws IOException {

         Map<String,djikstraVertex> vertex = new HashMap<>();

        System.out.println("增加点 以空格分隔");

        DataInputStream in = new DataInputStream(new BufferedInputStream(System.in));
        String s;

        {

            s = in.readLine();


            String []res = s.split(" ");

            System.out.println("长度为:"+res.length);
            if (res.length > 0){
                System.out.println(res[0]);
            }

            int len = res.length;
            String [] data = new String[len];
            int j=0;
            for (int i=0;i< res.length;++i) {
                data[j] = res[i];
                if (res[i].equals("")) {
                    len--;
                }else{
                    j++;
                }


            }

            System.out.println("长度为:"+len);


//            vertex = new djikstraVertex[res.length];




            for (int i=0;i< len;++i){
                djikstraVertex v = new djikstraVertex();
                v.setData(data[i]);
                vertex.put(data[i],v);

            }


            System.out.println("你输入的为 ：");

            for (String str: vertex.keySet()){
                System.out.print(str+"");
            }


        }







        System.out.println("请给出边 [A-B-权值] 中间不要有多余  输入x结束");

        while(true){

            s = in.readLine();


            String []res = s.split("-");

           // System.out.println("长度为:"+res.length);
            if (res.length !=3){
                if (res.length == 1){
                    if (res[0].equals("x"))
                        break;

                }
                System.out.println("输入不合法");
                continue;
                //System.out.println(res[0]);
            }

            djikstraVertex v = vertex.get(res[0]);
            if (v!=null){
                djikstraVertex vnext = vertex.get(res[1]);
                if (vnext == null){
                    System.out.println("未找到节点1");
                    continue;
                }
                Map<djikstraVertex,String> next = v.getNext();
                if (next == null)
                    next = new HashMap<>();
                next.put(vnext,res[2].toString());
                v.setNext(next);
            }else{
                System.out.println("未找到节点2");
                continue;
            }



        }




        //开始计算



            int i = 1;
            for(String str : vertex.keySet()){


                System.out.println("计算所有节点开始的最短路径  当前第 ["+i+"] 个,节点信息为"+str);

                List<djikstraVertex> vrunning = new ArrayList<>();//正在计算的点
                List<djikstraVertex> vcomplete = new ArrayList<>();//结束的点



                //清空计算
                for (String sourcestr: vertex.keySet()){
                    vertex.get(sourcestr).setShortest(-1);
                    vertex.get(sourcestr).setShortestPath("");
                    vrunning.add(vertex.get(sourcestr));
                }


                //开始计算  将第一个点放入已经计算的点中

                vcomplete.add(vertex.get(str));
                vrunning.remove(vertex.get(str));
                vertex.get(str).setShortest(0);
                vertex.get(str).setShortestPath(vertex.get(str).getData());

                djikstraVertex vc = vertex.get(str);
                while(!vrunning.isEmpty()) {
//                    System.out.println("当前剩余节点:"+vrunning.size());
                    vc = calc_shortest(vrunning,vc);
                    if (vc==null)
                    {
                        System.out.println("当前节点没有后继,跳过计算 此处暂时有BUG 待修复");
                        break;
                    }
                    vcomplete.add(vc);
                    vrunning.remove(vc);
                }



                System.out.println("计算完毕");
                System.out.println("当前最短路径为:");

                for(djikstraVertex v: vcomplete){
                    System.out.println(v.getData()+"\t"+v.getShortest()+"\t"+v.getShortestPath());
                }


                i++;
            }



        }




        //System.out.println(s);
        //An empty line terminates the program



    }
