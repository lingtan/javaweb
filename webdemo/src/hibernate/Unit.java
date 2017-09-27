package hibernate;

/**
 * Unit entity. @author MyEclipse Persistence Tools
 */
public class Unit extends AbstractUnit implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Unit() {
	}

	/** minimal constructor */
	public Unit(String name) {
		super(name);
	}

	/** full constructor */
	public Unit(Integer unitId,String name, String note) {
		super(unitId,name, note);
	}

}
