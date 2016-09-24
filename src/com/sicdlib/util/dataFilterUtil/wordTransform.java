package com.sicdlib.util.dataFilterUtil;

import net.sf.chineseutils.ChineseUtils;

/**
 * Created by Yh on 2016/9/18.
 */
public class WordTransform {
    public static String FilterWord(String word) {
//        word = ChineseUtils.tradToSimp(word);
//        return word.replaceAll("[^(a-zA-Z0-9\\u4e00-\\u9fa5!,;:\'\".?，。？；：、‘’)]", "");
//        return word.replaceAll("[^(a-zA-Z0-9\\u4e00-\\u9fa5)]", ""); //过滤非字母、非中文字符
        return word.replaceAll("//@.+?[:]", " ").trim();
    }

    public static void main(String[] args) {
//        String trade = "有背光的機械式鍵盤";
//        System.out.println(SimpToTrade(trade));
//        String word = ". 쫌 움직여 쫌 쫌!!! 뛰어!!!!你好！！你好機械O网页链接";
//        String word = "//@123:你好//@234:不好//@qqq";
//        System.out.println(FilterWord(word));
//        System.out.println(word.substring(1, word.length() - 4));

        TransformData tfData = new TransformData();
        tfData.Transform();
    }
}
