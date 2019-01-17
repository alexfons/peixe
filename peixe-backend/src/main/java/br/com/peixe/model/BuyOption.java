package br.com.peixe.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.peixe.repository.BuyOptionRepository;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel(description = "All details about the BuyOption. ")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class BuyOption {

	@ApiModelProperty(notes = "The database generated BuyOption ID")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;

	private Double normalPrice;

	private Double salePrice;
	private Double percentagelDiscount;
	private long quantityCupom;
	private Date startDate;
	private Date endDate;
	@ManyToOne
	@JsonIgnore
	private Deal deal;
	@Transient
	private BuyOptionRepository repository;

	public BuyOption(BuyOptionRepository repository) {
		this.repository = repository;
	}

	public void delete() {
		repository.delete(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final BuyOption other = (BuyOption) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (normalPrice == null) {
			if (other.normalPrice != null) {
				return false;
			}
		} else if (!normalPrice.equals(other.normalPrice)) {
			return false;
		}
		if (percentagelDiscount == null) {
			if (other.percentagelDiscount != null) {
				return false;
			}
		} else if (!percentagelDiscount.equals(other.percentagelDiscount)) {
			return false;
		}
		if (quantityCupom != other.quantityCupom) {
			return false;
		}
		if (salePrice == null) {
			if (other.salePrice != null) {
				return false;
			}
		} else if (!salePrice.equals(other.salePrice)) {
			return false;
		}
		if (title == null) {
			if (other.title != null) {
				return false;
			}
		} else if (!title.equals(other.title)) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (id == null ? 0 : id.hashCode());
		result = prime * result + (normalPrice == null ? 0 : normalPrice.hashCode());
		result = prime * result + (percentagelDiscount == null ? 0 : percentagelDiscount.hashCode());
		result = prime * result + (int) (quantityCupom ^ quantityCupom >>> 32);
		result = prime * result + (salePrice == null ? 0 : salePrice.hashCode());
		result = prime * result + (title == null ? 0 : title.hashCode());
		return result;
	}

	public void insert() {
		repository.save(this);
	}

	public void sellUnity() {
		deal.updateTotalSold(deal.getTotalSold() + 1);
	}

	public void update() {
		repository.save(this);
	}

}
