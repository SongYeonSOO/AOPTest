package com.estsoft.aoptest;

import org.springframework.stereotype.Service;

@Service
public class ProductService {

	public ProductVo findProduct(String name){
		System.out.println("Finding product["+name+"]....");//원래는 dbcode를 넣고싶은 것

		
/*		//to test afterThrowing		
		if(true){
			throw new RuntimeException("ProductService Exception");
		}
*/		return new ProductVo(name);
	}
}
