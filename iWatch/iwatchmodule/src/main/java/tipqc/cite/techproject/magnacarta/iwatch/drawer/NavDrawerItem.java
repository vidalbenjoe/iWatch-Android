package tipqc.cite.techproject.magnacarta.iwatch.drawer;

/**
 * Created by Benjoe on 1/31/14.
 */
public interface NavDrawerItem {
    public int getId();

    public int getLabel();

    public int getType();

    public boolean updateActionBarTitle();

    public boolean isCheckable();

}