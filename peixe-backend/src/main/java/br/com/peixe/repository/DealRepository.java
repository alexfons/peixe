
package br.com.peixe.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import br.com.peixe.model.Deal;

@CrossOrigin(origins = "*", maxAge = 3600)
@RepositoryRestResource(collectionResourceRel = "deal", path = "deal")
public interface DealRepository extends PagingAndSortingRepository<Deal, Long> {

}
