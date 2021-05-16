package com.lin.missyou.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Getter
@Setter
@Entity
public class Banner extends BaseEntity{
    @Id
    private Long id;
    private String name;
    private String description;
    private String title;
    private String img;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "bannerId")
    private List<BannerItem> items;
}
