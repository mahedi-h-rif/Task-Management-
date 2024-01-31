package com.project.Task.Managment.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {

      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Long id;
      @NotNull(message = "Title shouldn't be null.")
      private String title;
      @NotNull(message = "Description shouldn't be null.")
      private String description;
      @NotNull(message = "Status shouldn't be null.")
      private String status;
      @ManyToOne(cascade=CascadeType.ALL)
      @JoinColumn(name = "user_info_id")
      @JsonIgnoreProperties("tasks")
      private UserInfo userInfo;
}
