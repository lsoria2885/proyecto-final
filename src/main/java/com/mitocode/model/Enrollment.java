package com.mitocode.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Enrollment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Integer idEnrollment;

	@ManyToOne
	@JoinColumn(name = "id_student", nullable = false, foreignKey = @ForeignKey(name = "FK_ENROLLMENT_STUDENT"))
	private Student student;

	@Column(nullable = false)
	private LocalDateTime dateTime;

	@Column(nullable = false)
	private boolean enabled;

	@OneToMany(mappedBy = "enrollment", cascade = CascadeType.ALL)
	private List<EnrollmentDetail> details;

}
