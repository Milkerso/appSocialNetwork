package dawid.app.post;


import javax.persistence.*;

import dawid.app.post.comment.Comment;
import dawid.app.user.User;
import dawid.app.user.group.AllGroup;
import lombok.*;

import java.time.LocalDate;
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
    private LocalDate postDate;

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


}
