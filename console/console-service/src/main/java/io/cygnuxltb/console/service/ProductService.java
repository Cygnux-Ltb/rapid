package io.cygnuxltb.console.service;

import io.cygnuxltb.console.persistence.entity.SysProductEntity;
import io.cygnuxltb.console.persistence.dao.ProductDao;
import io.cygnuxltb.console.service.util.DtoUtil;
import io.cygnuxltb.protocol.http.response.ProductDTO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static io.cygnuxltb.console.persistence.JpaExecutor.select;

@Component
public final class ProductService {

    @Resource
    private ProductDao dao;

    public List<ProductDTO> get() {
        return select(SysProductEntity.class, () -> dao.findAll())
                .stream()
                .map(DtoUtil::toDto)
                .collect(Collectors.toList());
    }

    public ProductDTO get(int productId) {
        return DtoUtil.toDto(dao.queryByProductId(productId));
    }

}
