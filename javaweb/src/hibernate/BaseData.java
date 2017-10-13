package hibernate;

public class BaseData {
	
	

		private Integer unitId;
		private String name;
		private String note;

	

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
