package com.chengw.utils.htmlutil;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * 解析html  中的table
 *
 * @author chengw
 */
public class tableUtils {


    public static void main(String[] args) {


        String s = "<table>" +
                "<tbody>" +
                "<tr class=\"firstRow\">" +
                "<td width=\"314\" valign=\"top\" style=\"word-break: break-all;\">建筑高度（m）</td>" +
                "<td width=\"314\" valign=\"top\" style=\"word-break: break-all;\">275</td>" +
                "<td width=\"314\" valign=\"top\" style=\"word-break: break-all;\">人防范围</td>" +
                "<td width=\"314\" valign=\"top\"><br/></td>" +
                "</tr>" +
                "<tr>" +
                "<td width=\"314\" valign=\"top\" style=\"word-break: break-all;\">主楼建筑层数（地上/地下）</td>" +
                "<td width=\"314\" valign=\"top\"><br/></td>" +
                "<td width=\"314\" valign=\"top\" style=\"word-break: break-all;\">人防防护类别</td>" +
                "<td width=\"314\" valign=\"top\"><br/></td>" +
                "</tr>" +
                "<tr>" +
                "<td width=\"314\" valign=\"top\" style=\"word-break: break-all;\">裙房建筑层数(地上/地下）</td>" +
                "<td width=\"314\" valign=\"top\"><br/></td>" +
                "<td width=\"314\" valign=\"top\" style=\"word-break: break-all;\">人防抗力级别</td>" +
                "<td width=\"314\" valign=\"top\"><br/></td></tr><tr><td width=\"314\" valign=\"top\" style=\"word-break: break-all;\">主楼结构形式</td>" +
                "<td width=\"314\" valign=\"top\"><br/></td>" +
                "<td width=\"314\" valign=\"top\" style=\"word-break: break-all;\">裙房结构形式</td><td width=\"314\" valign=\"top\"><br/></td>" +
                "</tr>" +
                "<tr>" +
                "<td width=\"314\" valign=\"top\" style=\"word-break: break-all;\">基础形式（主楼）</td><td width=\"314\" valign=\"top\"><br/></td>" +
                "<td width=\"314\" valign=\"top\" style=\"word-break: break-all;\">基础形式</td><td width=\"314\" valign=\"top\"><br/></td>" +
                "</tr><tr><td width=\"314\" valign=\"top\" style=\"word-break: break-all;\">结构超限情况</td><td width=\"314\" valign=\"top\"><br/></td>" +
                "<td width=\"314\" valign=\"top\"><br/></td>" +
                "<td width=\"314\" valign=\"top\"><br/></td>" +
                "</tr>" +
                "</tbody>" +
                "</table>" +
                "<p><br/></p>";
        Document doc = Jsoup.parse(s);
        Elements rows = doc.select("tbody").select("tr");
        for(int i = 0;i < rows.size();i++){
            Elements cols = rows.get(i).select("td");
            for(int j = 0;j < cols.size();j++){
                System.out.print(cols.get(j).text() + "==");
            }
            System.out.println();
        }

    }

}
