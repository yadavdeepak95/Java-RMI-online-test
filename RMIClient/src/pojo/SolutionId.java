package pojo;
// Generated 8 Nov, 2018 11:29:30 PM by Hibernate Tools 4.0.1.Final

/**
 * SolutionId generated by hbm2java
 */
// Embeddable
public class SolutionId implements java.io.Serializable {

	private int pid;
	private int uid;

	public SolutionId() {
	}

	public SolutionId(int pid, int uid) {
		this.pid = pid;
		this.uid = uid;
	}

	@Override
	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof SolutionId))
			return false;
		SolutionId castOther = (SolutionId) other;

		return (this.getPid() == castOther.getPid()) && (this.getUid() == castOther.getUid());
	}

	// Column(name = "pid", nullable = false)
	public int getPid() {
		return this.pid;
	}

	// Column(name = "uid", nullable = false)
	public int getUid() {
		return this.uid;
	}

	@Override
	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getPid();
		result = 37 * result + this.getUid();
		return result;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

}