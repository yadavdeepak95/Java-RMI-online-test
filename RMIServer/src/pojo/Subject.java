package pojo;
// Generated 11 Nov, 2018 9:22:47 PM by Hibernate Tools 5.1.7.Final

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Subject generated by hbm2java
 */
@Entity
@Table(name = "subject", catalog = "rmi")
public class Subject implements java.io.Serializable {

	private Integer subid;
	private String subname;
	private boolean substatus;
	private Date subupdatedate;

	public Subject() {
	}

	public Subject(String subname, boolean substatus, Date subupdatedate) {
		this.subname = subname;
		this.substatus = substatus;
		this.subupdatedate = subupdatedate;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "subid", unique = true, nullable = false)
	public Integer getSubid() {
		return this.subid;
	}

	public void setSubid(Integer subid) {
		this.subid = subid;
	}

	@Column(name = "subname", nullable = false, length = 50)
	public String getSubname() {
		return this.subname;
	}

	public void setSubname(String subname) {
		this.subname = subname;
	}

	@Column(name = "substatus", nullable = false)
	public boolean isSubstatus() {
		return this.substatus;
	}

	public void setSubstatus(boolean substatus) {
		this.substatus = substatus;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "subupdatedate", nullable = false, length = 19)
	public Date getSubupdatedate() {
		return this.subupdatedate;
	}

	public void setSubupdatedate(Date subupdatedate) {
		this.subupdatedate = subupdatedate;
	}

}
