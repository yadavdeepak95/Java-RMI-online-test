package pojo;
// Generated 31 Oct, 2018 2:21:46 PM by Hibernate Tools 5.1.7.Final

/**
 * SubstudentId generated by hbm2java
 */
// Embeddable
public class SubstudentId implements java.io.Serializable {

	private int sid;
	private int subid;

	public SubstudentId() {
	}

	public SubstudentId(int sid, int subid) {
		this.sid = sid;
		this.subid = subid;
	}

	@Override
	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof SubstudentId))
			return false;
		SubstudentId castOther = (SubstudentId) other;

		return (this.getSid() == castOther.getSid()) && (this.getSubid() == castOther.getSubid());
	}

	// Column(name = "sid", nullable = false)
	public int getSid() {
		return this.sid;
	}

	// Column(name = "subid", nullable = false)
	public int getSubid() {
		return this.subid;
	}

	@Override
	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getSid();
		result = 37 * result + this.getSubid();
		return result;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public void setSubid(int subid) {
		this.subid = subid;
	}

}
