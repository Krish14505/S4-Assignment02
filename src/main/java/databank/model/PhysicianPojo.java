/********************************************************************************************************2*4*w*
 * File:  PhysicianPojo.java Course Materials CST8277
 *
 * @author Teddy Yap
 * @author Shahriar (Shawn) Emami
 * @author (original) Mike Norman
 */
package databank.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.faces.view.ViewScoped;
import jakarta.persistence.*;


/**
 * TODO 14.1 - Complete the @Entity with correct name.<br> DONE
 * TODO 14.2 - Complete the two @NamedQueries.<br> DONE
 * TODO 15 - Use the correct table name.<br> DONE
 * TODO 16 - Fix the AccessType.<br> DONE
 * TODO 17 - Make PhysicianPojoListener be the listener of this class.  Refer to: DONE
 * https://www.logicbig.com/tutorials/java-ee-tutorial/jpa/entity-listeners.html<br>
 * TODO 18.1 - Add the specialty field, add getter and setter, and update toString method to include the specialty field.<br> DONE
 * TODO 18.2 - Add the remaining @Basic and @Column to all the fields.<br>  DONE
 * TODO 19 - Use @Version on the correct field.  This annotation helps keeping track of what version of entity we are
 * working with.<br> DONE
 * TODO 20 - Dates (LocalDateTime) are to be mapped and 'editable' field is not to be mapped. DONE
 */
@ViewScoped
@Entity(name = "Physician")
@Table(name = "physician", catalog = "databank")
@Access(AccessType.FIELD)
@EntityListeners({PhysicianPojoListener.class})
@NamedQuery(name = PhysicianPojo.PHYSICIAN_FIND_ALL, query = "SELECT a FROM Physician a")
@NamedQuery(name = PhysicianPojo.PHYSICIAN_FIND_ID, query = "SELECT a FROM Physician a where a.id = :id")
public class PhysicianPojo implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String PHYSICIAN_FIND_ALL = "Physician.findAll";
	public static final String PHYSICIAN_FIND_ID = "Physician.findById";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	protected int id;

	@Basic
	@Column(name = "last_name")
	protected String lastName;

	@Basic
	@Column(name= "first_name")
	protected String firstName;

	@Basic
	@Column(name= "email")
	protected String email;

	@Basic
	@Column(name="phone")
	protected String phoneNumber;

	@Basic
	@Column(name = "created")
	protected LocalDateTime created;

	@Basic
	@Column(name = "updated")
	protected LocalDateTime updated;

	@Basic
	@Column(name = "SPECIALTY")
	protected String specialty;

	@Version
	protected int version = 1;

	@Transient
	protected boolean editable;

	public PhysicianPojo() {
		super();
	}

	public boolean getEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}


	public int getId() {
		return id;
	}

	/**
	 * @param id new value for id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the value for lastName
	 */

	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName new value for lastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the value for firstName
	 */

	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName new value for firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}


	public LocalDateTime getUpdated() {
		return updated;
	}

	public void setUpdated(LocalDateTime updated) {
		this.updated = updated;
	}


	public String getSpecialty(){
		return specialty;
	}

	public void setSpecialty(String specialty){this.specialty = specialty; }


	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	/**
	 * Very important:  Use getter's for member variables because JPA sometimes needs to intercept those calls<br/>
	 * and go to the database to retrieve the value.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		// Only include member variables that really contribute to an object's identity
		// i.e. if variables like version/updated/name/etc. change throughout an object's lifecycle,
		// they shouldn't be part of the hashCode calculation
		return prime * result + Objects.hash(getId());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}

		/* Enhanced instanceof - yeah!
		 * As of JDK 14, no need for additional 'silly' cast:
		    if (animal instanceof Cat) {
		        Cat cat = (Cat) animal;
		        cat.meow();
                // Other class Cat operations ...
            }
         * Technically, 'otherPhysicianPojo' is a <i>pattern</i> that becomes an in-scope variable binding.
         * Note:  Need to watch out just-in-case there is already a 'otherPhysicianPojo' variable in-scope!
		 */
		if (obj instanceof PhysicianPojo otherPhysicianPojo) {
			// See comment (above) in hashCode():  compare using only member variables that are
			// truly part of an object's identity.
			return Objects.equals(this.getId(), otherPhysicianPojo.getId());
		}
		return false;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Physician [id = ").append(getId());
		if (getLastName() != null) {
			builder.append(", ").append("lastName = ").append(getLastName());
		}
		if (getFirstName() != null) {
			builder.append(", ").append("firstName = ").append(getFirstName());
		}
		if (getPhoneNumber() != null) {
			builder.append(", ").append("phoneNumber = ").append(getPhoneNumber());
		}
		if (getEmail() != null) {
			builder.append(", ").append("email = ").append(getEmail());
		}
		if(getSpecialty() != null) {
			builder.append(", ").append("specialty = ").append(getSpecialty());
		}
		if (getCreated() != null) {
			builder.append(", ").append("created = ").append(getCreated());
		}
		if (getUpdated() != null) {
			builder.append(", ").append("updated = ").append(getUpdated());
		}
		builder.append("]");
		return builder.toString();
	}

}
