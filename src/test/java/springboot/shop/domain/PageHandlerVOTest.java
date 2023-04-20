package springboot.shop.domain;

import org.junit.jupiter.api.Test;

class PageHandlerVOTest {
    @Test
    void test(){
        SearchCond sc = new SearchCond();
        sc.setPage(1);
        sc.setPageSize(10);

        PageHandlerVO ph = new PageHandlerVO(80,5, sc);

        ph.print();
    }
}