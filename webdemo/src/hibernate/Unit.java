package hibernate;

/**
 * AbstractUnit entity provides the base persistence definition of the Unit
 * entity. @author MyEclipse Persistence Tools
 */

public class Unit{

	// Fields

	private Integer unitId;
	private String name;
	private String note;

	// Constructors

	/** default constructor */
	public Unit() {
	}

	/** minimal constructor */
	public Unit(String name) {
		this.name = name;
	}

	/** full constructor */
	public Unit(Integer unitId,String name, String note) {
		this.unitId=unitId;
		this.name = name;
		this.note = note;
	}

	// Property accessors

	public Integer getUnitId() {
        return unitId;
    }

    public void setUnitId(Integer unitId) {
        this.unitId = unitId;
    }

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}