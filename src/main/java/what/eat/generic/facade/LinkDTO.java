package what.eat.generic.facade;

public class LinkDTO {

    private String id;
    private String desc;
    private String url;
    private String method;

    public LinkDTO() {
    }

    public LinkDTO(String id, String url, String method) {
        this.id = id;
        this.url = url;
        this.method = method;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}