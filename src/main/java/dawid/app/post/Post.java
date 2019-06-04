package dawid.app.post;


import javax.persistence.*;

import lombok.*;

@Entity
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    @Column(name = "post_id")
    private int id;

    @Column(name = "post_user_id")
    @Getter
    @Setter
    private int postUserId;

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
    private String postDate;

    @Column(name = "user_like")
    @Getter
    @Setter
    private int userLike;

    @Column(name = "user_dislike")
    @Getter
    @Setter
    private int userDislike;

    @Column(name = "photo_id")
    @Getter
    @Setter
    private int photoId;

    @Column(name = "group_id")
    @Getter
    @Setter
    private int groupId;


}
