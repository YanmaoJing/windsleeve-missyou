package com.lin.missyou.vo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SpuSimplifyVO {
    private Long id;
    private String title;
    private String subtitle;
    private String price;
    private Long sketchSpecId;
    private String img;
    private String discountPrice;
    private String description;
    private String tags;
    private String forThemeImg;
}
