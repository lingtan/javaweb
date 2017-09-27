package hibernate;

/**
 * AbstractCustomcategory entity provides the base persistence definition of the
 * Customcategory entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractCustomcategory implements java.io.Serializable {

	// Fields

	private Integer unitId;
	private String name;
	private String note;

	// Constructors

	/** default constructor */
	public AbstractCustomcategory() {
	}

	/** minimal constructor */
	public AbstractCustomcategory(String name) {
		this.name = name;
	}

	/** full constructor */
	public AbstractCustomcategory(String name, String note) {
		this.name = name;
		this.note = note;
		this.unitId=unitId;
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