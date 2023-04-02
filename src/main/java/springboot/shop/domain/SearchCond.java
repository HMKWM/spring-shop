package springboot.shop.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SearchCond {
    private String keyword;
    private Integer page;
    private Integer pageSize;

    public SearchCond(){}

    //MyBatis 전용
    public Integer getOffset(){
        if(page == null || page <= 0 ){
            return 0;
        }
        return (page-1)*pageSize;
    }
}
