import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import models.Post;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import static spark.Spark.*;

public class App {
    private static String success;

    public static void main(String[] args) { //type “psvm + tab” to auto create this :)
        staticFileLocation("/public");

    get("/", (req, res) -> {
        Map<String, Object> model = new HashMap<>();
        ArrayList<Post> posts = Post.getAll();
        model.put("posts", posts);

        return new ModelAndView(model, "index.hbs");
    }, new HandlebarsTemplateEngine());

    post("/posts/new", (request, response) -> { //URL to make new post on POST route
        Map<String, Object> model = new HashMap<String, Object>();
        String content = request.queryParams("content");
        Post newPost = new Post(content);
        return new ModelAndView(model, "success.hbs");
    }, new HandlebarsTemplateEngine());
}
}