package filemanager.resource;

import com.google.inject.Inject;
import filemanager.directorytracker.ScheduleFileDirectory;
import filemanager.model.Command;
import org.eclipse.jetty.http.HttpStatus;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Path("/main")
public class ClientResource {

    private ScheduleFileDirectory scheduleFileDirectory;

    public ClientResource() {
    }

    public ClientResource(ScheduleFileDirectory scheduleFileDirectory) {
        this.scheduleFileDirectory = scheduleFileDirectory;
    }

    @POST
    @Path("/check")
    @Consumes(MediaType.APPLICATION_JSON)
    public int checkForFiles(Command command) {
        String time = command.getDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH");
        LocalDateTime dateTime = LocalDateTime.parse(time, formatter);
        scheduleFileDirectory.goThroughToCheckFile(dateTime);
        return HttpStatus.OK_200;
    }

}