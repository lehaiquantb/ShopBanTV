package com.shoptivi.util;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;

import com.shoptivi.entity.OptionEntity;
import com.shoptivi.entity.VariantEntity;

@Component
public class Duplicater {

	public boolean isDuplicateVariant(VariantEntity entityA, VariantEntity entityB) {

		List<OptionEntity> listA = entityA.getOptions();
		List<OptionEntity> listB = entityB.getOptions();
		
		System.out.println(listA.size());
		System.out.println(listB.size());
		if (listA.size() != listB.size())
			return false;
		else {
			HashMap<String, String> nameMapValueA = new HashMap<>();
			HashMap<String, String> nameMapValueB = new HashMap<>();
			for (OptionEntity entity : listA) {
				nameMapValueA.put(entity.getName(), entity.getValue());
			}
			for (OptionEntity entity : listB) {
				nameMapValueB.put(entity.getName(), entity.getValue());
			}
			
			for (String nameA : nameMapValueA.keySet()) {
				if (nameMapValueB.containsKey(nameA)) {
					if (nameMapValueA.get(nameA).equals(nameMapValueB.get(nameA)))
						continue;
					else
						return false;
				} else
					return false;
			}
			return true;
		}

	}
}
