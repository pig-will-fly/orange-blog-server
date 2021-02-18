package com.wt.orange.vo;

/**
 * <p> 用户信息 </p>
 *
 * @author Wang Tao
 * @date 2021-02-01 13:53:43
 */
public class UserVo {
    /**
     * 用户昵称
     */
    private String nickname;
    /**
     * 用户头像
     */
    private String avatar;

    public UserVo(String nickname, String avatar) {
        this.nickname = nickname;
        this.avatar = avatar;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
