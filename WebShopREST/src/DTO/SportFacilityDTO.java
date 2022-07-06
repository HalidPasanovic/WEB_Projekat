package DTO;

import java.util.ArrayList;
import java.util.List;
import Model.Location;
import Model.Facilities.FacilityType;
import Model.Facilities.RecreationType;
import Model.Facilities.SportFacility;

public class SportFacilityDTO {

    /**
	 * 
	 */
	public int id;

    /**
	 * 
	 */
	public String name;
	
	/**
	 * 
	 */
	public FacilityType type;

	/**
	 * 
	 */
	public List<RecreationType> recreationTypes = new ArrayList<>();

	/**
	 * 
	 */
	public boolean status;

	/**
	 * 
	 */
	public Location location;

	/**
	 * 
	 */
	public String workRange;

	/**
	 * 
	 */
	public String logoLocation;

    /**
	 * 
	 */
	public float rating;

    public SportFacilityDTO(SportFacility sportFacility) {
        id = sportFacility.getId();
        this.name = sportFacility.getName();
        this.type = sportFacility.getType();
        this.recreationTypes = sportFacility.getRecreationTypes();
        status = sportFacility.isStatus();
        location = sportFacility.getLocation();
        workRange = sportFacility.getWorkRange();
        logoLocation = sportFacility.getLogoLocation();
    }
}
