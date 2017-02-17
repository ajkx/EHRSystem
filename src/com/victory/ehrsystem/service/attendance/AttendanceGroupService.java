package com.victory.ehrsystem.service.attendance;

import com.victory.ehrsystem.entity.attendance.AttendanceGroup;
import com.victory.ehrsystem.entity.attendance.AttendanceSchedule;
import com.victory.ehrsystem.service.BaseService;
import com.victory.ehrsystem.util.StringUtil;
import com.victory.ehrsystem.vo.PageInfo;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by ajkx on 2017/2/16.
 */
@Service
public class AttendanceGroupService extends BaseService<AttendanceGroup>{

    @Override
    public PageInfo findByPage(Class<AttendanceGroup> entityClazz, HttpServletRequest request) {
        PageInfo info = super.findByPage(entityClazz, request);
        List<AttendanceGroup> list = info.getData();
        List<Map<String, Object>> mapList = new ArrayList<>();
        for (AttendanceGroup group : list) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", group.getId());
            map.put("name", StringUtil.nullString(group.getName()));
            map.put("description", StringUtil.nullString(group.getDescription()));
            String type = "";
            StringBuilder builder = new StringBuilder();
            switch (group.getGroupType()){
                case 1:
                    type = "固定班制";
                    List<AttendanceSchedule> temp = new ArrayList<>();
                    temp.add(group.getMonday());
                    temp.add(group.getTuesday());
                    temp.add(group.getWednesday());
                    temp.add(group.getThursday());
                    temp.add(group.getFriday());
                    temp.add(group.getSaturday());
                    temp.add(group.getSunday());
                    LinkedHashMap<AttendanceSchedule, String> tempMap = new LinkedHashMap<>();
                    for(int i = 0; i < temp.size(); i++) {
                        String value = "";
                        for (AttendanceSchedule key : tempMap.keySet()) {
                            if (key.getName().equals(temp.get(i).getName())) {
                                value = tempMap.get(key) + "&nbsp&nbsp" + StringUtil.getWeek(i).substring(1, 2);
                            }
                        }
                        tempMap.put(temp.get(i), value.equals("") ? StringUtil.getWeek(i) + "" : value);
                    }
                    for (AttendanceSchedule key : tempMap.keySet()) {
                        builder.append("每"+tempMap.get(key)+"&nbsp;&nbsp;&nbsp;");
                        if(key.getRest() != null && key.getRest()){
                            builder.append(key.getName()+"</br>");
                        }else {
                            builder.append(key.getName() + ":" + StringUtil.getScheduleTime(key)+"</br>");
                        }
                    }
                    break;
                case 2:
                    type = "排班制";
                    for (AttendanceSchedule schedule : group.getSchedules()) {
                        builder.append(schedule.getName()+":"+StringUtil.getScheduleTime(schedule)+"</br>");
                    }
                    break;
                case 3:
                    type = "自由打卡";
                    break;
            }
            map.put("type",type);
            map.put("time", builder.toString());
            map.put("count", group.getResources().size());
            mapList.add(map);
        }
        info.setData(mapList);
        return info;
    }

}

