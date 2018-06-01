package ru.job4j.tracker;

/**
 * Class Item contains variables of items.
 * @author Roman Bednyashov (hipnorosva@gmail.com).
 * @since 0.1
 * @version 0.1
 */
public class Item {

    private String id;
    private String name;
    private String desc;
    private long created;
    private String[] comments;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public long getCreated() {
        return this.created;
    }

    public void setCreated() {
        this.created = created;
    }

    public String[] getComments() {
        return this.comments;
    }

    public void setComments(String[] comments) {
        this.comments = comments;
    }

    public  Item(String name, String desc, long created) {
        this.name = name;
        this.desc = desc;
        this.created = created;
    }
}
