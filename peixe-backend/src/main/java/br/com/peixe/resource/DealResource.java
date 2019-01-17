package br.com.peixe.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.peixe.model.Deal;
import br.com.peixe.repository.BuyOptionRepository;
import br.com.peixe.repository.DealRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/deal")
public class DealResource {

	@Autowired
	private DealRepository dealRepository;

	@Autowired
	private BuyOptionRepository buyOptionRepository;

	@PostMapping("/associar")
	public ResponseEntity<Deal> associar(@RequestBody Deal deal) {
		deal.getBuyOptions().stream().forEach(buyOption -> {
			buyOption.setDeal(deal);
			buyOptionRepository.save(buyOption);
		});
		return ResponseEntity.ok(deal);
	}

	@GetMapping("/processa/{dealId}/{buyOptionId}")
	public ResponseEntity<Deal> processa(@PathVariable(value = "dealId") Long dealId, @PathVariable(value = "buyOptionId") Integer buyOptionId) {
		final Deal deal = dealRepository.findOne(dealId);
		deal.updateTotalSold(deal.getTotalSold() + 1);
		return ResponseEntity.ok(dealRepository.save(deal));
	}

}
