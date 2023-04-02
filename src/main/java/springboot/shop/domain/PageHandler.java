package springboot.shop.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PageHandler {
    private SearchCond cond;
    private int totalCnt;
    private int naviSize;
    private int beginPage;
    private int endPage;
    private int totalPage;
    private boolean showPrev;
    private boolean showNext;

    public PageHandler(int totalCnt, int naviSize, SearchCond cond){
        this.totalCnt = totalCnt;
        this.naviSize = naviSize;
        this.cond = cond;
    }

    public void doPaging(){
        totalPage = (int)Math.ceil(totalCnt/(double) cond.getPageSize());
        beginPage = (cond.getPage()-1)/naviSize*naviSize+1;
        endPage = Math.min(beginPage+naviSize-1,totalPage);
        showPrev = beginPage != 1;
        showNext = endPage != totalPage;
    }

    public void print(){
        System.out.println("page = " + cond.getPage());
        System.out.print(showPrev ? "[PREV] " : "");
        for(int i = beginPage; i <= endPage ; i++){
            System.out.print(i+" ");
        }
        System.out.println(showNext ? " [NEXT]" : "");
    }
}
