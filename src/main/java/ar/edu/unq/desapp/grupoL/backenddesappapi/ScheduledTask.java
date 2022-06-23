package ar.edu.unq.desapp.grupoL.backenddesappapi;


import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


// THIS CLASS IS JUST AN EXAMPLE ABOUT HOW TO CREATE A SCHEADULED TASK
@Component
public class ScheduledTask {

    //@Scheduled(fixedRate = 10000)
    public void tarea(){
        System.out.println("TAREA PROGRAMADA");
    }

    @Scheduled(cron = "*/60 * * * * *")   //se dispara cada 1 min
    public void tarea2(){
        System.out.println("TAREA PROGRAMADA2");
    }

}
