package com.myparty.model.house;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

import com.myparty.annotations.DataConverterType;
import com.myparty.converter.HouseConverter;
import com.myparty.model.Local;

import lombok.Data;

@Entity
@Data
@DataConverterType(HouseConverter.class)
public class House {

	@Id
	@GenericGenerator(strategy = "uuid", name = "system-uuid")
	@GeneratedValue(generator = "uuid")
	private Long id;
	private String name;

	@JoinColumn(name = "local_id", foreignKey = @ForeignKey(name = "house_local_fk"))
	@OneToOne(fetch = FetchType.LAZY)
	private Local local;

	@OneToMany(mappedBy = "house")
	private List<Zone> zones;

}
