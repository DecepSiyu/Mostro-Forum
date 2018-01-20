package com.postBean;

public class Plate {
	private String plateID;
	private String name;

	public Plate() {
	}

	public Plate(String plateID, String name) {
		this.plateID = plateID;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String plateName) {
		this.name = plateName;
	}

	public String getPlateID() {
		return plateID;
	}

	public void setPlateID(String ID) {
		plateID = ID;
	}
}
