package dawid.app.post;


import dawid.app.mainController.MainPageController;
import dawid.app.user.ProfileControllerCalculator;
import dawid.app.user.User;
import dawid.app.user.group.AllGroup;
import dawid.app.user.group.GroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.ws.rs.POST;
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


    @POST
    @RequestMapping(value = "/addpost/{groupid}")
    public String addComment(@PathVariable("groupid") int groupId, Model model)
    {
        LOG.info("     GroupID   " + groupId);
        LOG.info("     Group List    " + groupId);
      User user =profileControllerCalculator.onlineUser();
        Post post=new Post();
        List<AllGroup> allGroupList=groupService.findByUserID(user.getId());
        for (AllGroup allGroup:allGroupList) {
            LOG.info("     Group List    " + allGroup.getName());
            LOG.info("     Group List    " + allGroup.getGroupID());
        }
        model.addAttribute("post", post);
        model.addAttribute("groupList",allGroupList);
        return "addpost";
    }

    @POST
    @RequestMapping(value = "/addnewpost")
    public String addNewPost(Post post, Model model)
    {
        User user =profileControllerCalculator.onlineUser();
        List<AllGroup> allGroupList=groupService.findByUserID(user.getId());
        for (AllGroup allGroup:allGroupList) {
            LOG.info("     Group List    " + allGroup.getName());
        }
        LOG.info("     POST CONTENT    "+post.getContent());
        LOG.info("     POST Title    "+post.getPostTitle());
        LOG.info("     POST Title    "+post.getGroupId());
        model.addAttribute("post",post);

        model.addAttribute("groupList",allGroupList);
        return "addpost";
    }


    @POST
    @RequestMapping(value = "comment")
    public String commentView(User user, BindingResult result, Model model, Locale locale)
    {

        return "comment";
    }

}
