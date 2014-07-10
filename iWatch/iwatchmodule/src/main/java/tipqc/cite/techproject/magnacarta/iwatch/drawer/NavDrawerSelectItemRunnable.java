package tipqc.cite.techproject.magnacarta.iwatch.drawer;

/**
 * Created by Benjoe on 12/31/13.
 */
public class NavDrawerSelectItemRunnable implements Runnable {

    private AbstractNavDrawerActivity activity ;

    private int itemId ;

    public NavDrawerSelectItemRunnable(AbstractNavDrawerActivity act, int itemId) {
        this.activity = act ;
        this.itemId = itemId ;
    }

    @Override
    public void run() {
        activity.selectItem(itemId);
    }
}

