package com.shoptivi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.shoptivi.dto.ProductDTO;

@Service
public interface IProductService {
	ProductDTO save(ProductDTO dto) throws Exception;

	List<ProductDTO> findAll() throws Exception;

	ProductDTO findById(Long id) throws Exception;

	ProductDTO update(ProductDTO newProduct) throws Exception;

	void deleteById(Long id) throws Exception;
}
