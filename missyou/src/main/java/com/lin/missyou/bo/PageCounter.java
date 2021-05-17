package com.lin.missyou.bo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class PageCounter {
    private Integer page;
    private Integer count;
}
