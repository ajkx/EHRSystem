package com.victory.ehrsystem.util;

import com.victory.ehrsystem.entity.hrm.HrmDepartment;
import com.victory.ehrsystem.entity.hrm.HrmSubCompany;
import com.victory.ehrsystem.service.hrm.OrganizationService;
import com.victory.ehrsystem.vo.JsonTreeData;
import com.victory.ehrsystem.vo.JsonTreeState;

import java.util.ArrayList;
import java.util.List;

/**
 * 获取树节点集合
 *
 * @author ajkx_Du
 * @create 2016-11-23 17:21
 */
public class TreeNodeUtil {


    public static List<JsonTreeData> convertSubTreeList(List<HrmSubCompany> list,OrganizationService organizationService,String type) {
        List<JsonTreeData> tree = new ArrayList<>();
            for (HrmSubCompany subCompany : list) {
                //如果是封存的，则继续下一循环
                if(subCompany.getCancel()){
                    continue;
                }
                JsonTreeData temp = new JsonTreeData();
                //temp.setId(subCompany.getId().toString());
                //temp.setType("sub");
                temp.setText(subCompany.getName());
                //temp.setPid(subCompany.getParent().toString());
                List<JsonTreeData> sublist =  convertSubTreeList(organizationService.findAllSubcompanyBySubcompany(subCompany),organizationService,type);
                List<JsonTreeData> deplist = convertDepTreeList(organizationService.findRootDepartmentBySubcompany(subCompany),organizationService,type);
                deplist.addAll(sublist);
                temp.setIcon("fa fa-home");
                if(subCompany.getParent() == null){
                    JsonTreeState state = new JsonTreeState();
                    state.setExpanded(true);
                    temp.setState(state);
                }
                String link = "";
                if(type.equals("organization")){
                    link = "/organization/subcompany/" + subCompany.getId() + ".html";
                }else if(type.equals("resource")){
                    link = "/resource/subcompany/" + subCompany.getId();
                }else if(type.equals("checked")){
                   // temp.setSelectable(false);
                    link = "/resource/subcompany/" + subCompany.getId();
                }
                temp.setHref(link);
                temp.setNodes(deplist.size() == 0 ? null : deplist);
                tree.add(temp);
            }
        return tree;
    }

    public static List<JsonTreeData> convertDepTreeList(List<HrmDepartment> list,OrganizationService organizationService,String type) {
        List<JsonTreeData> tree = new ArrayList<>();
        for (HrmDepartment department : list) {
            //如果是封存的，则继续下一循环
            if (department.getCancel()) {
                continue;
            }
            JsonTreeData temp = new JsonTreeData();
            //temp.setId(department.getId().toString());
            //temp.setType("dep");
            temp.setText(department.getName());
            temp.setIcon("fa fa-folder");
            String link = "";
            if(type.equals("organization")){
                link = "/organization/department/" + department.getId() + ".html";
            }else if(type.equals("resource")){
                link = "/resource/department/" + department.getId();
            } else if (type.equals("checked")) {
                link = "/resource/department/" + department.getId();
            }
            temp.setHref(link);
            //temp.setPid(department.getParent().toString());
            List<JsonTreeData> deplist = convertDepTreeList(organizationService.findAllDepartmentByDepartment(department),organizationService,type);
            temp.setNodes(deplist.size() == 0 ? null : deplist);
            tree.add(temp);
        }
        return tree;
    }













    //
    //
    ///**
    // * @Title: getfatherNode
    // * @Description 方法描述: 父节点
    // * @param ： @param treeDataList
    // * @param ： @return
    // * @return 返回类型：List<JsonTreeData>
    // * @throws
    // * @date 最后修改时间：2015年6月9日 下午6:39:26
    // */
    //public final static List<JsonTreeData> getfatherNode(List<JsonTreeData> treeDataList) {
    //    List<JsonTreeData> newTreeDataList = new ArrayList<JsonTreeData>();
    //    for (JsonTreeData jsonTreeData : treeDataList) {
    //        if(jsonTreeData.getPid() == null) {
    //            //获取父节点下的子节点
    //            jsonTreeData.setChildren(getChildrenNode(jsonTreeData.getId(),treeDataList));
    //            jsonTreeData.setState("open");
    //            newTreeDataList.add(jsonTreeData);
    //        }
    //    }
    //    return newTreeDataList;
    //}
    //
    ///**
    // * @Title: getChildrenNode
    // * @Description 方法描述: 子节点
    // * @param ： @param pid
    // * @param ： @param treeDataList
    // * @param ： @return
    // * @return 返回类型：List<JsonTreeData>
    // * @throws
    // * @date 最后修改时间：2015年6月9日 下午6:39:50
    // */
    //private final static List<JsonTreeData> getChildrenNode(String pid , List<JsonTreeData> treeDataList) {
    //    List<JsonTreeData> newTreeDataList = new ArrayList<JsonTreeData>();
    //    for (JsonTreeData jsonTreeData : treeDataList) {
    //        if(jsonTreeData.getPid() == null)  continue;
    //        //这是一个子节点
    //        if(jsonTreeData.getPid().equals(pid)){
    //            //递归获取子节点下的子节点
    //            jsonTreeData.setChildren(getChildrenNode(jsonTreeData.getId() , treeDataList));
    //            newTreeDataList.add(jsonTreeData);
    //        }
    //    }
    //    return newTreeDataList;
    //}
}
