package com.volunteers.util;

/**
 * 分页工具类
 * @author Administrator
 *
 */
public class PageUtil {

    /**
     * 生成分页代码
     * @param targetUrl     目录访问路径
     * @param totalNum      总记录数
     * @param currentPage   当前页码
     * @param pageSize      每页显示数量
     * @param param         查询条件参数
     * @return
     */
    public static String genPagination(String targetUrl,long totalNum,int currentPage,int pageSize,String param){
        //计算总页数
        long totalPage=totalNum%pageSize==0?totalNum/pageSize:totalNum/pageSize+1;
        if(totalPage==0){
            return "未查询到数据";
        }else{
            StringBuffer pageCode=new StringBuffer();
            pageCode.append("<ul style='display:inline-block;padding:0;margin:0'>");
            //如果当前页码大于1
            if(currentPage>1){
                //首页和上一页的可以点击
                pageCode.append("<li style='display:inline'><a style='color:black;float:left;padding:8px 16px;text-decoration:none' href='"+targetUrl+"?page=1&"+param+"'>首页</a></li>");
                pageCode.append("<li style='display:inline'><a style='color:black;float:left;padding:8px 16px;text-decoration:none' href='"+targetUrl+"?page="+(currentPage-1)+"&"+param+"'>上一页</a></li>");
            }else {
                //如果当前页码是第一页，首页和上一页不能点击
                pageCode.append("<li style='display:inline'><a style='color:black;float:left;padding:8px 16px;text-decoration:none'>首页</a></li>");
                pageCode.append("<li style='display:inline'><a style='color:black;float:left;padding:8px 16px;text-decoration:none'>上一页</a></li>");
            }
            //动态生成页码按钮（随着当前页码，页码按钮随之改变）
            for(int i=currentPage-2;i<=currentPage+2;i++){
                if(i<1||i>totalPage){
                    continue;
                }
                if(i==currentPage){
                    pageCode.append("<li style='display:inline'><a style='color:black;float:left;padding:8px 16px;text-decoration:none' href='"+targetUrl+"?page="+i+"&"+param+"'>"+i+"</a></li>");
                }else{
                    pageCode.append("<li style='display:inline'><a style='color:black;float:left;padding:8px 16px;text-decoration:none' href='"+targetUrl+"?page="+i+"&"+param+"'>"+i+"</a></li>");
                }
            }
            //如果当前页码小于总页数，下一页和尾页可以点击
            if(currentPage<totalPage){
                pageCode.append("<li style='display:inline'><a style='color:black;float:left;padding:8px 16px;text-decoration:none' href='"+targetUrl+"?page="+(currentPage+1)+"&"+param+"'>下一页</a></li>");
                pageCode.append("<li style='display:inline'><a style='color:black;float:left;padding:8px 16px;text-decoration:none' href='"+targetUrl+"?page="+totalPage+"&"+param+"'>尾页</a></li>");
            }else{
                pageCode.append("<li style='display:inline'><a style='color:black;float:left;padding:8px 16px;text-decoration:none'>下一页</a></li>");
                pageCode.append("<li style='display:inline'><a style='color:black;float:left;padding:8px 16px;text-decoration:none'>尾页</a></li>");
            }

            pageCode.append("</ul>");
            return pageCode.toString();
        }
    }


}
