package f.sky.flight.model;

/**
 * Created by Administrator on 2016/12/12/012.
 */

public class CheckRequestNoteResObj extends  ServerResObj {
    private boolean checkRequestNoteResult;

    public CheckRequestNoteResObj(){}

    public boolean isCheckRequestNoteResult() {
        return checkRequestNoteResult;
    }
    public void setCheckRequestNoteResult(boolean checkRequestNote) {
        this.checkRequestNoteResult = checkRequestNote;
    }
}