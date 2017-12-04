package donm.sky.hotel.model;

/**
 * Created by Administrator on 2017/1/22/022.
 */

public class ItemP {
    int pos;
    boolean isSelect;

    public ItemP() {
    }

    public ItemP(int pos, boolean isSelect) {
        this.pos = pos;
        this.isSelect = isSelect;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}
