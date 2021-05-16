package com.lin.missyou.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class Spu extends BaseEntity{
    @Id
    private Long id;
    private String title;
    private String subtitle;
    private Long categoryId;
    private Long rootCategoryId;
    private Boolean online;
    private String price;
    private Long sketchSpecId;
    private Long defaultSkuId;
    private String img;
    private String discountPrice;
    private String description;
    private String tags;
    private Boolean isTest;
    //private Object spuThemeImg;
    private String forThemeImg;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "spuId")
    List<Sku> skuList;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "spuId")
    List<SpuImg> spuImgList;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "spuId")
    List<SpuDetailImg> spuDetailImgList;
}
