package com.lin.missyou.service;

import com.lin.missyou.model.Spu;
import com.lin.missyou.repository.SpuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class SpuService {

    @Autowired
    private SpuRepository spuRepository;

    public Spu getSpu(Long id) {
        Spu spu = spuRepository.findOneById(id);
        return spu;
    }

    public Page<Spu> getLatestPagingSpu(Integer pageNum, Integer size){
        Pageable page = PageRequest.of(pageNum, size, Sort.by("createTime").descending());
        return spuRepository.findAll(page);
    }

    public Page<Spu> getByCategory(Long cid, Boolean isRoot, Integer pageNum, Integer size){
        Pageable page = PageRequest.of(pageNum, size);
        if(isRoot){
            return this.spuRepository.findByRootCategoryIdOrderByCreateTimeDesc(cid, page);
        }else {
            return this.spuRepository.findByCategoryIdOrderByCreateTimeDesc(cid, page);
        }
    }

}
