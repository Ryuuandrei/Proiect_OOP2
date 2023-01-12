import command.Action;
import command.ActionFactory;
import fileio.ActionInput;
import fileio.Input;
import fileio.MovieInput;
import fileio.UserInput;
import realistic.ActualMovie;
import realistic.Application;
import realistic.ErrorEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import realistic.Notification;

import java.io.File;
import java.io.IOException;
import java.util.*;

public final class Main {

    /**
     * @param args args[0] - input;
     *             args[1] - output
     * @throws IOException an exception
     */
    public static void main(final String[] args) throws IOException {
        // args[0] - input;
        // args[1] - output;

        ObjectMapper objectMapper = new ObjectMapper();
        Input inputData = objectMapper.readValue(new File(args[0]), Input.class);

        ArrayNode output = objectMapper.createArrayNode();

        ArrayList<UserInput> users = inputData.getUsers();
        ArrayList<MovieInput> movies = inputData.getMovies();
        ArrayList<ActionInput> actions = inputData.getActions();

        Application application = new Application(users, movies);

        ActionFactory actionFactory = new ActionFactory();

        for (ActionInput actionInput : actions) {
            Action action = actionFactory.getAction(actionInput);
            assert action != null;
            action.accept(application.getCurrentPage(), application);
            //application.getCurrentPage().debug();

            ErrorEntity out = ErrorEntity.update(application, actionInput);

            if (out != null) {
                output.addPOJO(out);
            }

            application.getEntity().setError(null);
        }

        if (application.getEntity().getCurrentUser() != null && application.getEntity()
                .getCurrentUser().getCredentials().getAccountType().equals("premium")) {

            ActualMovie movie = application.getEntity().recommend(application);

            ErrorEntity out = new ErrorEntity();
            out.setCurrentMoviesList(null);
            Notification notification = new Notification();
            notification.setMessage("Recommendation");
            if (movie == null) {
                notification.setMovieName("No recommendation");
            } else {
                notification.setMovieName(movie.getName());
            }
            application.getEntity().getCurrentUser().getNotifications().add(notification);
            out.setCurrentUser(application.getEntity().getCurrentUser());
            output.addPOJO(out);

        }

        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        objectWriter.writeValue(new File(args[1]), output);

    }
}
