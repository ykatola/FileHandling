package filemanager.launcher;

import com.google.inject.Guice;
import com.google.inject.Injector;
import filemanager.binder.FileServiceBinderModule;
import filemanager.controller.Application;

import java.util.concurrent.TimeUnit;

public class Launcher {
    public static void main(String[] args) {
        Injector guice = Guice.createInjector(new FileServiceBinderModule());
        Application application = guice.getInstance(Application.class);
        application.runProgram(0, 1, TimeUnit.MINUTES);
    }

}