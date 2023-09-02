package com.aswemake.service.product;

import com.aswemake.dao.PrevProductInfoDAO;
import com.aswemake.dao.ProductDAO;
import com.aswemake.dto.PrevProductInfoDTO;
import com.aswemake.dto.ProductDTO;
import com.aswemake.entity.PrevProductInfoEntity;
import com.aswemake.entity.ProductEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductDAO productDAO;
    private final PrevProductInfoDAO prevProductInfoDAO;

    @Override
    @Transactional
    public ProductDTO save(ProductDTO dto) {
        ProductEntity toEntity = ProductDTO.toEntity(dto);

        if (dto.getModifiedDate() != null) {
            PrevProductInfoEntity prevEntity = PrevProductInfoEntity.toEntity(dto);
            prevProductInfoDAO.save(prevEntity);
        }

        ProductEntity save = productDAO.save(toEntity);

        return ProductEntity.toDTO(save);
    }

    @Override
    public List<PrevProductInfoDTO> findPrevProduct(Long id) {
        List<PrevProductInfoEntity> entities = prevProductInfoDAO.findAllByProductId(id);

        List<PrevProductInfoDTO> dtoList = new ArrayList<>();
        for (PrevProductInfoEntity x : entities) {
            PrevProductInfoDTO infoDTO = PrevProductInfoDTO.toDTO(x);
            dtoList.add(infoDTO);
        }
        return dtoList;
    }

    @Override
    public ProductDTO findById(Long id) {
        ProductEntity byId = productDAO.findProductById(id);
        return ProductEntity.toDTO(byId);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        prevProductInfoDAO.deleteByProductId(id);
        productDAO.deleteById(id);
    }
}
