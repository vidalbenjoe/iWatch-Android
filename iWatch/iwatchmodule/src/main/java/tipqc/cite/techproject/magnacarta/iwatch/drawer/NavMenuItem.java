package tipqc.cite.techproject.magnacarta.iwatch.drawer;

/**
 * Created by Benjoe on 1/31/14.
 */
public class NavMenuItem implements NavDrawerItem {

    public static final int ITEM_TYPE = 1;

    private int id ;

    private int label ;

    private int icon ;

    private boolean updateActionBarTitle ;

    private boolean checkable ;

    @Override
    public int getType() {
        return ITEM_TYPE;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLabel() {
        return label;
    }

    public void setLabel(int label) {
        this.label = label;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    @Override
    public boolean updateActionBarTitle() {
        return this.updateActionBarTitle;
    }

    public void setUpdateActionBarTitle(boolean updateActionBarTitle) {
        this.updateActionBarTitle = updateActionBarTitle;
    }

    public boolean isCheckable() {
        return checkable;
    }

    public void setCheckable(boolean checkable) {
        this.checkable = checkable;
    }
}