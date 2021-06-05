package com.eloim.edu_service.entity.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserVo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String avatar;
    private String nickname;
}
