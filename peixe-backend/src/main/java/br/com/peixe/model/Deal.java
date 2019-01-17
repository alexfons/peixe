package br.com.peixe.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import br.com.peixe.enums.TypeEnum;
import br.com.peixe.repository.DealRepository;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(description = "All details about the Deal. ")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Deal {

	@ApiModelProperty(notes = "The database generated Deal ID")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;
	private String text;
	private Date createDate;
	private Date publishDate;
	private Date endDate;
	private String url;
	private long totalSold;
	@Enumerated(EnumType.STRING)
	private TypeEnum type;
	@OneToMany(mappedBy = "deal")
	private Set<BuyOption> buyOptions;

	@Transient
	private DealRepository repository;

	public Deal(DealRepository repository) {
		this.repository = repository;
	}

	public void addOption(BuyOption option) {
		if (buyOptions == null) {
			buyOptions = new HashSet<>();
		}
		buyOptions.add(option);
	}

	public void delete() {
		repository.delete(id);
	}

	public void insert() {
		repository.save(this);
	}

	public void update() {
		repository.save(this);
	}

	public void updateTotalSold(long totalSold) {
		this.totalSold = totalSold;
	}
}
