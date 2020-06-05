package com.shoptivi.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shoptivi.converter.OptionConverter;
import com.shoptivi.converter.ProductConverter;
import com.shoptivi.converter.VariantConverter;
import com.shoptivi.dto.OptionDTO;
import com.shoptivi.dto.ProductDTO;
import com.shoptivi.dto.VariantDTO;
import com.shoptivi.entity.OptionEntity;
import com.shoptivi.entity.ProductEntity;
import com.shoptivi.entity.VariantEntity;
import com.shoptivi.repository.OptionRepository;
import com.shoptivi.repository.ProductRepository;
import com.shoptivi.repository.VariantRepository;
import com.shoptivi.service.IProductService;
import com.shoptivi.validator.VariantValidator;

@Service
@Transactional
public class ProductService implements IProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private VariantRepository variantRepository;

	@Autowired
	private OptionRepository optionRepository;

	@Autowired
	private ProductConverter productConverter;

	@Autowired
	private VariantConverter variantConverter;

	@Autowired
	private OptionConverter optionConverter;

	@Autowired
	private VariantValidator variantValidator;

	@Override
	public ProductDTO save(ProductDTO dto)  throws Exception{
		/*
		 * (Lưu product) Chuyển (Product) từ dto sang entity rồi lưu xuống db đồng thời
		 * thu lại entity đã lưu
		 */
		ProductEntity productEntity = productConverter.toEntity(dto);
		productEntity = productRepository.save(productEntity);
		/*
		 * (Lưu variant) Chuyển (Variant) từ dto sang entity đồng thời thu được các
		 * OptionEntity từ các Variant đó
		 */
		List<VariantEntity> variantEntities = variantConverter.toEntities(dto.getVariantDTOs(), productEntity);
		variantRepository.save(variantEntities);

		/*
		 * (Lưu option) Lấy List OptionEntity thu được từ variantEntity ở trên và lưu
		 * xuống db
		 */
		for (int i = 0; i < variantEntities.size(); i++) {
			List<OptionEntity> optionEntities = optionConverter.toEntities(dto.getVariantDTOs().get(i).getOptionDTOs(),
					variantEntities.get(i), productEntity);
			variantEntities.get(i).setOptions(optionEntities);// Thêm tham chiếu option cho mỗi variant
			optionRepository.save(optionEntities);
		}

		productEntity.setVariants(variantEntities);// Thêm lại tham chiếu variant cho product
		return productConverter.toDTO(productEntity);
	}

	@Override
	public List<ProductDTO> findAll() throws Exception{
		return productConverter.toDTOs(productRepository.findAll());
	}

	@Override
	public ProductDTO findById(Long id) throws Exception {
		return productConverter.toDTO(productRepository.findOne(id));
	}

	@Override
	public ProductDTO update(ProductDTO newProduct) throws Exception{
		// Cập nhật lại product
		ProductEntity productEntity = productConverter.toEntity(newProduct);
		productEntity = productRepository.save(productEntity);
		Long productId = newProduct.getId();
		List<VariantDTO> variantDTOs = newProduct.getVariantDTOs();

		// Lấy danh sách variant từ db, nếu id của variant nào ko nằm trong DTO mới thì
		// variant
		// đó sẽ bị xóa khỏi db
		List<VariantEntity> variantEntitiesDB = variantRepository.findAllByProductId(productId);
		List<Long> listVariantIdFromDTO = new ArrayList<>();
		for (VariantDTO variantDTO : variantDTOs)
			if (variantDTO.getId() != null)
				listVariantIdFromDTO.add(variantDTO.getId());

		for (VariantEntity variantEntityDB : variantEntitiesDB) {
			if (!listVariantIdFromDTO.contains(variantEntityDB.getId())) {
				optionRepository.delete(variantEntityDB.getOptions());
				variantRepository.delete(variantEntityDB);
			}
		}

		// check trùng variant trong db
		boolean isExistVariant;
		for (VariantDTO variantDTO : variantDTOs) {

			isExistVariant = true;
			VariantEntity variantEntityUpdate = null;
			List<OptionEntity> optionEntitiesUpdate;

			if (variantDTO.getId() != null) {
				variantEntityUpdate = variantRepository.findOne(variantDTO.getId());
			}

			if (variantDTO.getId() != null && variantEntityUpdate != null) {// nếu variant có id != null có nghĩa là
																			// variant
				// cũ
				List<OptionEntity> optionEntitiesDB = optionRepository.findAllByVariantId_ProductId(variantDTO.getId(),
						productId);

				if (optionEntitiesDB.size() == variantDTO.getOptionDTOs().size()) {// Check trùng variant

					for (OptionDTO optionDTO : variantDTO.getOptionDTOs()) {
						OptionEntity optionEntityDB = optionRepository.findOptionExist(productId, variantDTO.getId(),
								optionDTO.getName(), optionDTO.getValue());
						if (optionEntityDB == null) {// nếu có option chưa tồn tại trong db suy ra variant đc cập nhật
							isExistVariant = false;
							break;
						}
					}

					if (isExistVariant) {
						// ném exception đã tồn tại variant
						System.out.println("exist");
					}

				}
				if (!isExistVariant) {// variant được cập nhật các option mới
					optionRepository.delete(optionEntitiesDB);
					variantRepository.save(variantEntityUpdate);
					optionEntitiesUpdate = optionConverter.toEntities(variantDTO.getOptionDTOs(), variantEntityUpdate,
							productEntity);
					optionRepository.save(optionEntitiesUpdate);
					variantEntityUpdate.setOptions(optionEntitiesUpdate);
					System.out.println("update");
				}
			}

			else {// tạo mới một variant
				variantEntityUpdate = variantRepository.save(variantConverter.toEntity(variantDTO, productEntity));
				optionEntitiesUpdate = optionConverter.toEntities(variantDTO.getOptionDTOs(), variantEntityUpdate,
						productEntity);
				optionRepository.save(optionEntitiesUpdate);
				variantEntityUpdate.setOptions(optionEntitiesUpdate);
				System.out.println("add new");
			}
		}
		return productConverter.toDTO(productRepository.findOne(productId));
	}

	@Override
	public void deleteById(Long id) throws Exception {
		// Xóa option và variant theo id của product
		ProductEntity productEntity = productRepository.findOne(id);

		// Xóa variants
		variantRepository.delete(productEntity.getVariants());

		// Xóa options
		optionRepository.delete(productEntity.getOptions());

		// Xóa product
		productRepository.delete(id);
	}

}
