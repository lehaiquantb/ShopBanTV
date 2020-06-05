package com.shoptivi.api;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.bouncycastle.jcajce.provider.asymmetric.dsa.DSASigner.noneDSA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shoptivi.dto.ApiError;
import com.shoptivi.dto.ProductDTO;
import com.shoptivi.dto.VariantDTO;
import com.shoptivi.exception.NonNullIdException;
import com.shoptivi.exception.NullIdException;
import com.shoptivi.exception.NullNameException;
import com.shoptivi.exception.TooManyOptionException;
import com.shoptivi.service.IProductService;

@RestController
public class ProductAPI {

	@Autowired
	private Validator validator;

	@Autowired
	private IProductService productService;

	// POST /api/products
	@PostMapping(value = "/api/products")
	public ResponseEntity<Object> save(@RequestBody ProductDTO dto) throws Exception {
		try {
			if (validator.validateProductBePost(dto))
				return new ResponseEntity<>(productService.save(dto), HttpStatus.OK);
		} catch (Exception e) {
			if (e instanceof TooManyOptionException) {
				throw new TooManyOptionException();
			}
			if (e instanceof ConstraintViolationException) {
				throw new ConstraintViolationException(null);
			}
			if (e instanceof NullNameException) {
				throw new NullNameException();
			}
			if (e instanceof NullIdException) {
				throw new NullIdException();
			}
			if (e instanceof NonNullIdException) {
				throw new NonNullIdException();
			}
			throw new Exception();
		}
		return null;
	}

	// GET /api/products
	@GetMapping(value = "/api/products")
	public List<ProductDTO> findAll() {
		try {
			return productService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// GET /api/products/{id}
	@GetMapping(value = "/api/products/{id}")
	public ProductDTO findById(@PathVariable("id") Long id) {
		try {
			return productService.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// PUT /api/products/{id}
	@PutMapping(value = "/api/products/{id}")
	public ProductDTO update(@RequestBody ProductDTO dto, @PathVariable("id") Long id) {
		dto.setId(id);
		try {
			if (validator.validateProductBeUpdate(dto)) {
				return productService.update(dto);
			}
		} catch (Exception e) {
			if (e instanceof NullIdException) {
				throw new NullIdException();
			}
		}
		return null;
	}

	// DELETE /api/products/{id}
	@DeleteMapping(value = "/api/products/{id}")
	public void deleteById(@PathVariable("id") Long id) {
		try {
			if (validator.validateProductBeDel(id)) {
				productService.deleteById(id);
			}
		} catch (Exception e) {
			if (e instanceof NullIdException) {
				throw new NullIdException();
			}
		}
	}

//	// POST /api/products
//	@PostMapping(value = "/api/products")
//	public ResponseEntity<?> saveAllProductDTO(@RequestBody ProductDTO dtos) {
//		return new ResponseEntity<?>(productService.save(dtos), HTTP);
//	}

}
