package dawid.app.post;


import dawid.app.mainController.MainPageController;
import dawid.app.post.comment.Comment;
import dawid.app.post.comment.CommentService;
import dawid.app.user.ProfileControllerCalculator;
import dawid.app.user.User;
import dawid.app.user.group.AllGroup;
import dawid.app.user.group.GroupService;
import dawid.app.user.photo.Photo;
import dawid.app.user.photo.PhotoService;
import dawid.app.user.userProfile.UserProfile;
import dawid.app.user.userProfile.UserProfileService;
import org.apache.tomcat.jni.Local;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.ws.rs.POST;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Controller
public class PostController {

    private static final Logger LOG= LoggerFactory.getLogger(MainPageController.class);
    @Autowired
    private ProfileControllerCalculator profileControllerCalculator;
    @Autowired
    PostService postService;
    @Autowired
    GroupService groupService;
    @Autowired
    PhotoService photoService;
    @Autowired
    UserProfileService userProfileService;
    @Autowired
    CommentService commentService;


    @POST
    @RequestMapping(value = "/addpost")
    public String addPost(Model model)
    {

        User user =profileControllerCalculator.onlineUser();
        Post post=new Post();
        List<AllGroup> allGroupList=groupService.findByUserID(user.getId());
        AllGroup allGroup=allGroupList.get(0);

        model.addAttribute("post", post);
        model.addAttribute("allGroup",allGroup);
        model.addAttribute("groupId",allGroup.getGroupId());
        model.addAttribute("groupList",allGroupList);

        return "addpost";
    }

    @POST
    @RequestMapping(value = "/addpost/{groupId}")
    public String addPostGroup(@PathVariable("groupId") int groupId,Model model)
    {

        User user =profileControllerCalculator.onlineUser();
        Post post=new Post();
        List<AllGroup> allGroupList=groupService.findByUserID(user.getId());
        AllGroup allGroup= groupService.findByGroupId(groupId);


        model.addAttribute("post", post);
        model.addAttribute("allGroup",allGroup);
        model.addAttribute("groupList",allGroupList);

        return "addpost";
    }
    @POST
    @RequestMapping(value = "/allpost")
    public String allpost(Model model)
    {

        User user =profileControllerCalculator.onlineUser();
        List<AllGroup> allGroupList=groupService.findByUserID(user.getId());
        AllGroup allGroup=allGroupList.get(0);
        List<Post> posts=postCalculator(allGroup,user);
        UserProfile userProfile=user.getUserProfile();
        model.addAttribute("posts",posts);
        model.addAttribute("user",userProfile);
        model.addAttribute("groupList",allGroupList);
        return "allpost";
    }
    @POST
    @RequestMapping(value = "/allpost/{groupId}")
    public String allGroupPost(@PathVariable("groupId") int groupId,Model model)
    {
        User user =profileControllerCalculator.onlineUser();
        List<AllGroup> allGroupList=groupService.findByUserID(user.getId());
        AllGroup allGroup=groupService.findByGroupId(groupId);
        List<Post> posts=postCalculator(allGroup,user);
        UserProfile userProfile=user.getUserProfile();
        model.addAttribute("posts",posts);
        model.addAttribute("user",userProfile);
        model.addAttribute("groupList",allGroupList);
        return "allpost";
    }

    @POST
    @RequestMapping(value = "/addcomment/{postId}")
    public String addComment(@PathVariable("postId") int postId, AllGroup allGroup,Comment newComment, Model model)
    {

        User user =profileControllerCalculator.onlineUser();
        List<AllGroup> allGroupList=groupService.findByUserID(user.getId());
        List<Post> posts=postCalculator(allGroup,user);
        UserProfile userProfile=userProfileService.findUserProfileById(user.getId());
        Post post = postService.findById(postId);
        System.out.println(newComment.getCommentContent());
        newComment.setCommentUserId(user);
        newComment.setCommentDislike(0);
        newComment.setCommentLike(0);
        newComment.setCommentDate(LocalDateTime.now());
        commentService.save(newComment);
        List<Comment> comments=post.getComment();
        comments.add(newComment);
        post.setComment(comments);
        postService.save(post);
        model.addAttribute("posts",posts);
        model.addAttribute("user",userProfile);
        model.addAttribute("groupList",allGroupList);
        model.addAttribute("allGroup",allGroup);

        return "redirect:/allpost/"+allGroup.getGroupId();
    }

    @POST
    @RequestMapping(value = "/addnewpost/{groupId}")
    public String addNewPost(@PathVariable("groupId") int groupId,Post post, Model model)
    {
        User user =profileControllerCalculator.onlineUser();
        List<AllGroup> allGroupList=groupService.findByUserID(user.getId());
        post.setGroupId(groupService.findByGroupId(groupId));
        model.addAttribute("post",post);
        model.addAttribute("groupList",allGroupList);
        post.setPostUserId(user);

        post.setPostDate(LocalDateTime.now());
        postService.save(post);

        return "redirect:/allpost/"+groupId;
    }



    @POST
    @RequestMapping(value = "/addnewcomment/{groupid}")
    public String addNewCommentForPost(@PathVariable("groupid") int groupId,Post post, Model model)
    {
        User user =profileControllerCalculator.onlineUser();
        List<AllGroup> allGroupList=groupService.findByUserID(user.getId());
        LOG.info("     POST CONTENT    "+post.getContent());
        LOG.info("     POST Title    "+post.getPostTitle());
        LOG.info("     POST Title    "+post.getGroupId());
        post.setGroupId(groupService.findByGroupId(groupId));


        model.addAttribute("post",post);
        model.addAttribute("groupList",allGroupList);
        post.setPostUserId(user);

        post.setPostDate(LocalDateTime.now());
        postService.save(post);
        return "addpost/"+groupId;
    }

    public List<Post> postCalculator(AllGroup allGroup,User user)
    {
        List<Post> posts=new ArrayList<>();

        try {
            posts = postService.findByGroupId(allGroup);
        }
        catch (Exception e)
        {
        }

        for (Post post:
                posts) {
            post.getPostUserId()
                    .getUserProfile()
                    .setPhotos( post.getPostUserId()
                            .getUserProfile()
                            .getPhotos()
                            .stream()
                            .filter(photo ->
                                    photo.getProfilePhoto()==1)
                            .collect(Collectors.toList()));

            for (Comment comment:
                    post.getComment()
                 ) {
                comment.getCommentUserId()
                        .getUserProfile()
                        .setPhotos( post.getPostUserId()
                                .getUserProfile()
                                .getPhotos()
                                .stream()
                                .filter(photo ->
                                        photo.getProfilePhoto()==1)
                                .collect(Collectors.toList()));
                comment.setPhotoEncoded(profileControllerCalculator.getProfilePhotoEncoded(comment.getCommentUserId().getId()));
                long diff = ChronoUnit.MILLIS.between(comment.getCommentDate(),LocalDateTime.now() );
                TimeAgoCalculator timeAgoCalculator=new TimeAgoCalculator();
                comment.setCommentTimeAgo(timeAgoCalculator.toDuration(diff));
            }


            post.setPhotoEncoded(profileControllerCalculator.getProfilePhotoEncoded(post.getPostUserId().getId()));
            long diff = ChronoUnit.MILLIS.between(post.getPostDate(),LocalDateTime.now() );
            TimeAgoCalculator timeAgoCalculator=new TimeAgoCalculator();
            post.setTimeAgo(timeAgoCalculator.toDuration(diff));
        }

        Collections.reverse(posts);

        return posts;
    }

}
