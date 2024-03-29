package com.cos.photogramstart.domain.subscribe;

import com.cos.photogramstart.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(
                name="subscribe_uk",
                columnNames = {
                        "fromUserId","toUserId"
                }
        )
})
public class Subscribe {
    // N:N 인 User 들 간의 중간 테이블!
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JoinColumn(name="fromUserId")
    @ManyToOne
    private User fromUser;

    @JoinColumn(name="toUserId")
    @ManyToOne
    private User toUser;
    private LocalDateTime createDate;

    @PrePersist
    public void createDate() {
        this.createDate = LocalDateTime.now();
    }
}
