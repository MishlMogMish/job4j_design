package ru.job4j.question;

import java.util.Objects;

public class Info {
private int added;
private int changed;
private int deleted;

    public Info(int added, int changed, int deleted) {
        this.added = added;
        this.changed = changed;
        this.deleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Info info = (Info) o;
        return added == info.added && changed == info.changed && deleted == info.deleted;
    }

    @Override
    public int hashCode() {
        return Objects.hash(added, changed, deleted);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Info{");
        sb.append("added=").append(added);
        sb.append(", changed=").append(changed);
        sb.append(", deleted=").append(deleted);
        sb.append('}');
        return sb.toString();
    }
}
