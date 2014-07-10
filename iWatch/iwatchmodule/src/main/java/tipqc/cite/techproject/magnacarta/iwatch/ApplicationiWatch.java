package tipqc.cite.techproject.magnacarta.iwatch;




import tipqc.cite.techproject.magnacarta.libraries.MCXApplication;
import java.util.List;




public class ApplicationiWatch extends MCXApplication {

    public static final String LOG_TAG = "YAI";

    private boolean syncAdapterRunning = false;


    @Override
    public void onCreate() {
        super.onCreate();
       this.injectSelf();



    }


    @Override
    public void buildDaggerModules(List<Object> modules) {
        modules.add(new iWatchModule());
    }



    public boolean isSyncAdapterRunning() {
        return syncAdapterRunning;
    }

    public void setSyncAdapterRunning(boolean syncAdapterRunning) {
        this.syncAdapterRunning = syncAdapterRunning;
    }
}
