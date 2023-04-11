package springboot.shop.domain;

import org.junit.jupiter.api.Test;

class PageHandlerTest {

    @Test
    void test(){
        SearchCond sc = new SearchCond();
        sc.setPage(1);
        sc.setPageSize(10);

        PageHandler ph = new PageHandler(80,5, sc);

        ph.print();
    }
}