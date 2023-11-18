package com.cydeo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Where(clause = "is_deleted = false")
public class UserResetPassWord {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String token;
        @ManyToOne
        private User user;
        private boolean isDeleted;

        @Column(columnDefinition = "TIMESTAMP")
        private LocalDateTime createdDate;

        public UserResetPassWord(User user) {
            this.token = UUID.randomUUID().toString();
            this.user = user;
            this.createdDate = LocalDateTime.now();
        }
}
