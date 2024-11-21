package com.sophra.unistone.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "ProjectUser")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectUser {

    @Id
    @GeneratedValue
    private Long id; // 단일 기본 키를 사용

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user; // User와의 관계 정의

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project; // Project와의 관계 정의

    @Column(name = "project_name", nullable = true)
    private String projectName; // 프로젝트 이름 (Optional)

    
}
