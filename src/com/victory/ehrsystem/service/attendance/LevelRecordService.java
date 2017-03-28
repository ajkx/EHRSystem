package com.victory.ehrsystem.service.attendance;

import com.victory.ehrsystem.dao.attendance.LevelRecordDao;
import com.victory.ehrsystem.dao.attendance.LevelTypeDao;
import com.victory.ehrsystem.dao.attendance.OverTimeDao;
import com.victory.ehrsystem.entity.attendance.LevelRecord;
import com.victory.ehrsystem.entity.attendance.OverTimeRecord;
import com.victory.ehrsystem.service.BaseService;
import com.victory.ehrsystem.service.hrm.impl.HrmResourceService;
import com.victory.ehrsystem.vo.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ajkx
 * Date: 2017/3/21.
 * Time:13:48
 */
@Service
public class LevelRecordService extends BaseService<LevelRecord>{

    @Autowired
    private LevelTypeDao levelTypeDao;

    @Autowired
    private LevelRecordDao recordDao;

    @Autowired
    private HrmResourceService resourceService;

    public PageInfo findList(HttpServletRequest request) {
        return convertData(list(LevelRecord.class, request, resourceService));
    }

    private PageInfo convertData(PageInfo info) {
        List<LevelRecord> list = info.getData();
        List<Map<String, Object>> mapList = new ArrayList();
        for (LevelRecord record : list) {
            Map<String, Object> map = new HashMap<>();

            map.put("id", record.getId());
            map.put("name", record.getResource().getName());
            map.put("department", record.getResource().getDepartment().getName());
            map.put("workCode", record.getResource().getWorkCode());
            map.put("type", record.getType().getName());
            map.put("beginDate", record.getDate());
            map.put("endDate", record.getEndDate());
            map.put("sum", record.getCount());
            map.put("reason", record.getReason());
            map.put("status", record.getStatus().getInfo());
            map.put("remark", record.getRemark());
            mapList.add(map);
        }
        info.setData(mapList);
        return info;
    }
}
