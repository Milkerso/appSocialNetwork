package dawid.app.post.comment;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name= "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    @Column(name="comment_id")
    private int id;

    @Transient
    @Getter
    @Setter
    private String commentTimeAgo;

    @Column(name="comment_user_id")
    @Getter @Setter
    private int commentUserId;

    @Column(name="comment_title")
    @Getter @Setter
    private String commentTitle;

    @Column(name="comment_content")
    @Getter @Setter
    private String commentContent;

    @Column(name="comment_date")
    @Getter @Setter
    private String commentDate;


    @Column(name="comment_like")
    @Getter @Setter
    private int commentLike;

    @Column(name="comment_dislike")
    @Getter @Setter
    private int commentDislike;


}
