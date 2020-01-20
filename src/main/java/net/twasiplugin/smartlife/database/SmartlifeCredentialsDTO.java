package net.twasiplugin.smartlife.database;

import net.twasi.core.database.models.BaseEntity;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;

import java.util.Date;

@Entity(value = "net.twasi.smartlife.credentials", noClassnameStored = true)
public class SmartlifeCredentialsDTO extends BaseEntity {

    private ObjectId user;

    private String uid;
    private String accessToken;
    private String refreshToken;
    private Date expirationDate;

    public SmartlifeCredentialsDTO(ObjectId user, String uid, String accessToken, String refreshToken, Date expirationDate) {
        this.user = user;
        this.uid = uid;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.expirationDate = expirationDate;
    }

    public SmartlifeCredentialsDTO() {
    }

    public ObjectId getUser() {
        return user;
    }

    public void setUser(ObjectId user) {
        this.user = user;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void setExpireTime(int expireTime) {

    }
}
