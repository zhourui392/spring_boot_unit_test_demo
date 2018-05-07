package cn.teleus.common.page;

import java.util.List;

public class ReturnResult<T> extends PageQuery {
    private List<T> items;
 
    public ReturnResult(List<T> items, int count) {
        this.items = items;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}
