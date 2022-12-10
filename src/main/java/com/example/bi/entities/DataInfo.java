package com.example.bi.entities;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "data")
public class DataInfo {

    @Id
    @Column(name = "id", nullable = false)
    private Long patientId;

    @Column(name = "name")
    private String name;

    @Column(name = "gender")
    private String gender;

    @Column(name = "level")
    private String level;

    @Column(name = "micro_area")
    private String microArea;

    @Column(name = "care_info")
    private Integer careInfo;

    @Column(name = "PA")
    private Integer pa;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        DataInfo dataInfo = (DataInfo) o;
        return patientId != null && Objects.equals(patientId, dataInfo.patientId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
