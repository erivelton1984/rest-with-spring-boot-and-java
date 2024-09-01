package br.com.erivelton.data.vo.v1.security;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class TokenVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userName;
    private Boolean authenticated;
    private Date create;
    private Date expiration;
    private String accessToken;
    private String refreshToken;

    public TokenVO(){}

    public TokenVO(String userName,
                   Boolean authenticated,
                   Date create,
                   Date expiration,
                   String accessToken,
                   String refreshToken) {
        this.userName = userName;
        this.authenticated = authenticated;
        this.create = create;
        this.expiration = expiration;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Boolean getAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(Boolean authenticated) {
        this.authenticated = authenticated;
    }

    public Date getCreate() {
        return create;
    }

    public void setCreate(Date create) {
        this.create = create;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TokenVO tokenVO)) return false;
        return Objects.equals(getUserName(), tokenVO.getUserName()) && Objects.equals(getAuthenticated(), tokenVO.getAuthenticated()) && Objects.equals(getCreate(), tokenVO.getCreate()) && Objects.equals(getExpiration(), tokenVO.getExpiration()) && Objects.equals(getAccessToken(), tokenVO.getAccessToken()) && Objects.equals(getRefreshToken(), tokenVO.getRefreshToken());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserName(), getAuthenticated(), getCreate(), getExpiration(), getAccessToken(), getRefreshToken());
    }
}
