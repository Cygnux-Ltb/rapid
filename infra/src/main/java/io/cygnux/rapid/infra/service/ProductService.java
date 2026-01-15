package io.cygnux.rapid.infra.service;

import io.cygnux.rapid.infra.dto.resp.ProductRsp;
import io.cygnux.rapid.infra.persistence.dao.ProductDao;
import io.cygnux.rapid.infra.persistence.entity.ProductEntity;
import io.cygnux.rapid.infra.service.util.RespConverter;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.List;

import static io.cygnux.rapid.infra.persistence.JpaExecutor.select;


@Component
public class ProductService {

    @Resource
    private ProductDao productDao;

    /**
     * @return List<ProductDTO>
     */
    public List<ProductRsp> getAll() {
        return select(ProductEntity.class, () -> productDao.findAll())
                .stream()
                .map(RespConverter::fromEntity)
                .toList();
    }

    /**
     * @param productCode String
     * @return ProductRsp
     */
    public ProductRsp get(String productCode) {
        return RespConverter.fromEntity(productDao.queryByProductCode(productCode));
    }

}
