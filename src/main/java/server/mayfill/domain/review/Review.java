package server.mayfill.domain.review;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import server.mayfill.domain.common.AuditingTimeEntity;
import server.mayfill.domain.store.entity.Store;
import server.mayfill.domain.user.entity.User;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review extends AuditingTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String comments;

    private Review(Store store, User user, String comments) {
        this.store = store;
        this.user = user;
        this.comments = comments;
    }

    public static Review of(Store store, User user, String comments) {
        return new Review(store, user, comments);
    }

}
