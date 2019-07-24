package dawid.app.post;


import dawid.app.mainController.MainPageController;
import dawid.app.post.comment.Comment;
import dawid.app.user.ProfileControllerCalculator;
import dawid.app.user.User;
import dawid.app.user.group.AllGroup;
import dawid.app.user.group.GroupService;
import dawid.app.user.photo.Photo;
import dawid.app.user.photo.PhotoService;
import dawid.app.user.userProfile.UserProfile;
import dawid.app.user.userProfile.UserProfileService;
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
import java.util.List;
import java.util.Locale;

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


    @POST
    @RequestMapping(value = "/addpost")
    public String addPost(Model model)
    {

        User user =profileControllerCalculator.onlineUser();
        Post post=new Post();
        List<AllGroup> allGroupList=groupService.findByUserID(user.getId());


        model.addAttribute("post", post);
        model.addAttribute("groupList",allGroupList);

        return "addpost";
    }
    @POST
    @RequestMapping(value = "/allpost")
    public String allpost(Model model)
    {

        User user =profileControllerCalculator.onlineUser();
        Post post=postService.findById(10);
        List<AllGroup> allGroupList=groupService.findByUserID(user.getId());
        allGroupList.get(0).getName();
        List<Post> posts=postService.findByGroupId(allGroupList.get(0));
        UserProfile userProfile=userProfileService.findUserProfileById(user.getId());
        System.out.println(user.getName()+"    lol" +userProfile.getLastName());
        System.out.println(post.getPostTitle()+"    lol" +post.getContent());
        System.out.println(post.getComment().size()+"comment size");
    //    model.addAttribute("post", post);
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
        AllGroup allGroup=groupService.findByGroupId(groupId);
        List<Post> posts=postService.findByGroupId(allGroup);
        List<AllGroup> allGroupList=groupService.findByUserID(user.getId());
        UserProfile userProfile=userProfileService.findUserProfileById(user.getId());
       // System.out.println(user.getName()+"    lol" +userProfile.getLastName());
        Comment newComment= new Comment();
        model.addAttribute("posts",posts);
        model.addAttribute("user",userProfile);
        model.addAttribute("groupList",allGroupList);
        model.addAttribute("groupId",groupId);
        model.addAttribute("newComment",newComment);
        return "allpost";
    }

    @POST
    @RequestMapping(value = "/addcomment/{postId}")
    public String addComment(@PathVariable("postId") int postId,int groupId,Comment newComment, Model model)
    {

        User user =profileControllerCalculator.onlineUser();
        List<AllGroup> allGroupList=groupService.findByUserID(user.getId());
        List<Post> posts=postService.findByGroupId(allGroupList.get(0));
        UserProfile userProfile=userProfileService.findUserProfileById(user.getId());
        System.out.println(postId+"this post id");
        model.addAttribute("posts",posts);
        model.addAttribute("user",userProfile);
        model.addAttribute("groupList",allGroupList);

        return "allpost";
    }
    @POST
    @RequestMapping(value = "/addpost/{groupid}")
    public String addPost(@PathVariable("groupid") int groupId, Model model)
    {
        LOG.info("     GroupID   " + groupId);
        LOG.info("     Group List    " + groupId);
      User user =profileControllerCalculator.onlineUser();
        Post post=new Post();
        List<AllGroup> allGroupList=groupService.findByUserID(user.getId());
        for (AllGroup allGroup:allGroupList) {
            LOG.info("     Group List    " + allGroup.getName());
            LOG.info("     Group List    " + allGroup.getGroupId());
        }
        post.setGroupId(groupService.findByGroupId(groupId));
        System.out.println(post.getGroupId().getGroupId());

        model.addAttribute("post", post);
        model.addAttribute("groupId", groupId);
        model.addAttribute("groupList",allGroupList);

        return "addpost";
    }

    @POST
    @RequestMapping(value = "/addnewpost/{groupid}")
    public String addNewPost(@PathVariable("groupid") int groupId,Post post, Model model)
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

        post.setPostDate(LocalDate.now());
        postService.save(post);
        return "addpost";
    }


    @POST
    @RequestMapping(value = "comment")
    public String commentView(User user, BindingResult result, Model model, Locale locale)
    {

        return "comment";
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

        post.setPostDate(LocalDate.now());
        postService.save(post);
        return "addpost";
    }

}
