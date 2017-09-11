package com.igalaxy.boot.domain.dto;

import com.igalaxy.boot.domain.auth.AuthResource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jinlong
 */
public class MenuJson {
    public MenuJson(Long id, String name, String url, String iconCls) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.iconCls = iconCls;
        this.list = new ArrayList<>();
    }

    private Long id;
    private String name;
    private String url;
    private int sub;
    private String iconCls;

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public int getSub() {
        return sub;
    }

    public void setSub(int sub) {
        this.sub = sub;
    }

    private List<MenuJson> list;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<MenuJson> getList() {
        return list;
    }

    public void setList(List<MenuJson> list) {
        this.list = list;
    }

    public static List<MenuJson> convertMemuList(List<AuthResource> resList) {
        return getMemuListByParentId(null, resList);
    }

    private static List<MenuJson> getMemuListByParentId(Long parentId, List<AuthResource> resList) {
        List<MenuJson> menuJsons = new ArrayList<>();
        if (resList == null || resList.size() == 0) {
            return menuJsons;
        }
        for (AuthResource res : resList) {
            if ((parentId == null && res.getParentId() == null) || (parentId != null && res.getParentId() != null && parentId.longValue() == res.getParentId().longValue())) {
                MenuJson json = new MenuJson(res.getId(), res.getName(), res.getUrl(), res.getIconCls());
                menuJsons.add(json);
                List<MenuJson> subList = getMemuListByParentId(res.getId(), resList);
                json.setList(subList);
                if (subList == null || subList.size() == 0) {
                    json.setSub(0);
                } else {
                    json.setSub(1);
                }
            }
        }
        return menuJsons;
    }
}
