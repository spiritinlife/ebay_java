package gr.geomike.ted.api.db.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import gr.geomike.ted.Views;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Message.findByFromUsername", query = "SELECT m FROM Message m WHERE m.fromUsername = :username"),
        @NamedQuery(name = "Message.findByToUsername", query = "SELECT m FROM Message m WHERE m.toUsername = :username")})
public class Message {
    private int id;
    private String fromUsername;
    private String toUsername;
    private String subject;
    private String body;

    private boolean fromSeen;
    private boolean toSeen;
    private boolean fromDeleted;
    private boolean toDeleted;

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    @JsonProperty("id")
    @JsonView(Views.Basic.class)
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "FROM_USERNAME")
    @JsonProperty("fromUsername")
    @JsonView(Views.Basic.class)
    public String getFromUsername() {
        return fromUsername;
    }
    public void setFromUsername(String fromUsername) {
        this.fromUsername = fromUsername;
    }

    @Basic
    @Column(name = "TO_USERNAME")
    @JsonProperty("toUsername")
    @JsonView(Views.Basic.class)
    public String getToUsername() {
        return toUsername;
    }
    public void setToUsername(String toUsername) {
        this.toUsername = toUsername;
    }

    @Basic
    @Column(name = "SUBJECT")
    @JsonProperty("subject")
    @JsonView(Views.Basic.class)
    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Basic
    @Column(name = "BODY")
    @JsonProperty("body")
    @JsonView(Views.Basic.class)
    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }

    @Basic
    @Column(name = "FROM_SEEN")
    @JsonProperty("fromSeen")
    @JsonView(Views.Basic.class)
    public boolean getFromSeen() {
        return fromSeen;
    }
    public void setFromSeen(boolean fromSeen) {
        this.fromSeen = fromSeen;
    }

    @Basic
    @Column(name = "TO_SEEN")
    @JsonProperty("toSeen")
    @JsonView(Views.Basic.class)
    public boolean getToSeen() {
        return toSeen;
    }
    public void setToSeen(boolean toSeen) {
        this.toSeen = toSeen;
    }

    @Basic
    @Column(name = "FROM_DELETED")
    @JsonProperty("fromDeleted")
    @JsonView(Views.Basic.class)
    public boolean getFromDeleted() {
        return fromDeleted;
    }
    public void setFromDeleted(boolean fromDeleted) {
        this.fromDeleted = fromDeleted;
    }

    @Basic
    @Column(name = "TO_DELETED")
    @JsonProperty("toDeleted")
    @JsonView(Views.Basic.class)
    public boolean getToDeleted() {
        return toDeleted;
    }
    public void setToDeleted(boolean toDeleted) {
        this.toDeleted = toDeleted;
    }
}
