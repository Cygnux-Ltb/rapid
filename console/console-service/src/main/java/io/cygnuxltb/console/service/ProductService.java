package io.cygnuxltb.console.service;

import io.cygnuxltb.console.persistence.entity.sys.ProductEntity;
import io.cygnuxltb.console.persistence.repository.ProductRepository;
import io.cygnuxltb.console.service.util.DtoUtil;
import io.cygnuxltb.protocol.http.outbound.ProductDTO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static io.cygnuxltb.console.persistence.util.DaoExecutor.select;

@Component
public final class ProductService {

    @Resource
    private ProductRepository repo;

    public List<ProductDTO> getAll() {
        return select(ProductEntity.class, () -> repo.findAll())
                .stream()
                .map(DtoUtil::convertToDTO)
                .collect(Collectors.toList());
    }

    public ProductDTO getById(int productId) {
        return DtoUtil.convertToDTO(repo.queryByProductId(productId));
    }

}
