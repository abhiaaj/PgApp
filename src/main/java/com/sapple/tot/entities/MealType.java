package com.sapple.tot.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "master_meal_type")
public class MealType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "is_Active")
	private Integer isActive;
	
	@OneToMany(mappedBy = "mealType", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<MealSkipRequest> mealSkipRequests;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getIsActive() {
		return isActive;
	}

	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}

	public List<MealSkipRequest> getMealSkipRequests() {
		return mealSkipRequests;
	}

	public void setMealSkipRequests(List<MealSkipRequest> mealSkipRequests) {
		this.mealSkipRequests = mealSkipRequests;
	}
}
