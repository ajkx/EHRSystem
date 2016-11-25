package com.victory.ehrsystem.vo;

import java.util.List;

/**
 * @author ajkx_Du
 * @create 2016-11-23 16:43
 */
public class JsonTreeData {
    //private String id;
    //private String type;
    //public String pid;      //
    private String text;     //json 显示文本
    private String icon;
    private String href;
    //public String state;    //json 'open','closed'
    private List<JsonTreeData> nodes;
    //public List<JsonTreeData> subchildren;
    //private List<JsonTreeData> depchildren;

    public JsonTreeData() {
    }

    //public String getType() {
    //    return type;
    //}
    //
    //public void setType(String type) {
    //    this.type = type;
    //}
    //
    //public String getId() {
    //    return id;
    //}
    //
    //public void setId(String id) {
    //    this.id = id;
    //}
    //
    //public String getPid() {
    //    return pid;
    //}
    //
    //public void setPid(String pid) {
    //    this.pid = pid;
    //}

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public List<JsonTreeData> getNodes() {
        return nodes;
    }

    public void setNodes(List<JsonTreeData> nodes) {
        this.nodes = nodes;
    }
    //public String getState() {
    //    return state;
    //}
    //
    //public void setState(String state) {
    //    this.state = state;
    //}
    //
    //public List<JsonTreeData> getSubchildren() {
    //    return subchildren;
    //}
    //
    //public void setSubchildren(List<JsonTreeData> subchildren) {
    //    this.subchildren = subchildren;
    //}
    //
    //public List<JsonTreeData> getDepchildren() {
    //    return depchildren;
    //}
    //
    //public void setDepchildren(List<JsonTreeData> depchildren) {
    //    this.depchildren = depchildren;
    //}
}
