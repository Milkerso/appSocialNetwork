package dawid.app.post.comment;
import dawid.app.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name= "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    @Column(name="comment_id")
    private int id;

    @Column(name = "comment_date")
    @Getter
    @Setter
    private LocalDateTime commentDate;

    @Transient
    @Getter
    @Setter
    private String commentTimeAgo;

    @ManyToOne
    @JoinTable(name="comment_user_id")
    @Getter @Setter
    private User commentUserId;

    @Column(name="comment_title")
    @Getter @Setter
    private String commentTitle;

    @Column(name="comment_content")
    @Getter @Setter
    private String commentContent;

    @Transient
    @Column(name="comment_date")
    @Getter @Setter
    private String commentDateString;


    @Column(name="comment_like")
    @Getter @Setter
    private int commentLike;

    @Column(name="comment_dislike")
    @Getter @Setter
    private int commentDislike;

    @Transient
    @Getter
    @Setter
    private String photoEncoded;
}
