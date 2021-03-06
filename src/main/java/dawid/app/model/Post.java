package dawid.app.model;


import javax.persistence.*;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    @Column(name = "post_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "post_user_id")
    @Getter
    @Setter
    private User postUserId;

    @Column(name = "post_title")
    @Getter
    @Setter
    private String postTitle;

    @Column(name = "content")
    @Getter
    @Setter
    private String content;

    @Column(name = "post_date")
    @Getter
    @Setter
    private LocalDateTime postDate;

    @Column(name = "user_like")
    @Getter
    @Setter
    private int userLike;

    @Transient
    @Getter
    @Setter
    private String timeAgo;

    @Column(name = "user_dislike")
    @Getter
    @Setter
    private int userDislike;

    @OneToMany
    @Getter
    @Setter
    private List<Comment> comment;

    @ManyToOne
    @JoinColumn(name = "group_id")
    @Getter
    @Setter
    private AllGroup groupId;

    @Transient
    @Getter
    @Setter
    private String photoEncoded;


}
