package io.cygnuxltb.console.service;

import io.cygnuxltb.console.persistence.dao.ProductDao;
import io.cygnuxltb.console.persistence.entity.SysProductEntity;
import io.cygnuxltb.console.service.util.DtoUtil;
import io.cygnuxltb.protocol.http.response.dto.ProductDTO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.List;

import static io.cygnuxltb.console.persistence.JpaExecutor.select;
import static java.util.stream.Collectors.toList;

@Component
public final class ProductService {

    @Resource
    private ProductDao dao;

    /**
     * @return List<ProductDTO>
     */
    public List<ProductDTO> get() {
        return select(SysProductEntity.class,
                () -> dao.findAll())
                .stream()
                .map(DtoUtil::toDto)
                .collect(toList());
    }

    /**
     * @param productId int
     * @return ProductDTO
     */
    public ProductDTO get(int productId) {
        return DtoUtil.toDto(dao.queryByProductId(productId));
    }

}
