package com.lsw.carmanager.huffman;

/**
 * Created by sweeneyliu on 2019/7/31.
 */
public class HufTree {
    public byte Byte; //以8位为单元的字节
    public int weight;//该字节在文件中出现的次数
    public String code; //对应的哈夫曼编码
    public HufTree lchild,rchild;

    //统计字符频度的临时节点
    static class TmpNode implements Comparable<TmpNode>{
        public byte Byte;
        public int weight;

        @Override
        public int compareTo(TmpNode arg0) {
            if(this.weight < arg0.weight)
                return 1;
            else if(this.weight > arg0.weight)
                return -1;
            return 0;
        }
    }

}
