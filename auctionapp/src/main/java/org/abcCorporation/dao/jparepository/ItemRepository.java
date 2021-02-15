package org.abcCorporation.dao.jparepository;

import org.abcCorporation.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository  extends JpaRepository<Item, String>{
	
}
