
package br.com.peixe.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import br.com.peixe.model.BuyOption;

@CrossOrigin(origins = "*", maxAge = 3600)
@RepositoryRestResource(collectionResourceRel = "buyOption", path = "buyOption")
public interface BuyOptionRepository extends PagingAndSortingRepository<BuyOption, Long> {

}
