package com.tentcoo.data.jpa.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

@MappedSuperclass
public class IdEntity implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 7282576412687617289L;

	private String id;

	
	// ------------------------------- mysql -------------------------------- //
	/**
	 * @return the id
	 */
	/*@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="F_ID")
	public Long getId() {
		return id;
	}*/
	
	
	// -------------------------- oracle -------------------------------- //
	/*@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "songSequenceGenerator")
	@SequenceGenerator(
	      name = "songSequenceGenerator",
	      sequenceName = "song_sequence",
	      initialValue = 2,
	      allocationSize = 20
	  )
	@Column(name="F_ID")
	public Long getId() {
		return id;
	}*/

	// --------------------------- UUID  ----------------------------------- //
	@Id
	@GeneratedValue(generator = "tableGenerator")
	@GenericGenerator(name = "tableGenerator", strategy = "uuid")
	@Column(name ="ID",nullable=false,length=32,unique=true)
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
}
