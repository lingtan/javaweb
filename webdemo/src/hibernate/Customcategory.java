package hibernate;

/**
 * Customcategory entity. @author MyEclipse Persistence Tools
 */
public class Customcategory extends AbstractCustomcategory implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Customcategory() {
	}

	/** minimal constructor */
	public Customcategory(String name) {
		super(name);
	}

	/** full constructor */
	public Customcategory(Integer unitId,String name, String note) {
		super(name, note);
	}

}
