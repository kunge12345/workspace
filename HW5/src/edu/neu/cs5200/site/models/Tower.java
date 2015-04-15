package edu.neu.cs5200.site.models;

import java.util.List;

import javax.persistence.*;

@Entity
public class Tower {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name;
	private Double height;
	private int sides;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id")
	private int siteId;
	@OneToMany(mappedBy="towerId")
	private List<Equipment> equipments;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getHeight() {
		return height;
	}
	public void setHeight(Double height) {
		this.height = height;
	}
	public int getSides() {
		return sides;
	}
	public void setSides(int sides) {
		this.sides = sides;
	}
	public int getSiteId() {
		return siteId;
	}
	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}
	public List<Equipment> getEquipments() {
		return equipments;
	}
	public void setEquipments(List<Equipment> equipments) {
		this.equipments = equipments;
	}

	public Tower(int id, String name, Double height, int sides, int siteId,
			List<Equipment> equipments) {
		super();
		this.id = id;
		this.name = name;
		this.height = height;
		this.sides = sides;
		this.siteId = siteId;
		this.equipments = equipments;
	}
	public Tower() {
		super();
	}
	

}
