package com.victory.ehrsystem.service.attendance;

import com.victory.ehrsystem.dao.attendance.AttendanceRecordDao;
import com.victory.ehrsystem.entity.attendance.AttendanceRecord;
import com.victory.ehrsystem.entity.hrm.HrmResource;
import com.victory.ehrsystem.service.BaseService;
import com.victory.ehrsystem.util.DateUtil;
import com.victory.ehrsystem.util.StringUtil;
import com.victory.ehrsystem.vo.PageInfo;
import org.codehaus.jackson.map.MapperConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ajkx
 * Date: 2017/3/10.
 * Time:14:29
 */
@Service
public class AttendanceRecordService extends BaseService<AttendanceRecord>{
    @Autowired
    private AttendanceRecordDao recordDao;

    public PageInfo findByPage(HttpServletRequest request,int type){
        int pageNo = Integer.parseInt(request.getParameter("cPage"));
        int pageSize = Integer.parseInt(request.getParameter("pSize"));
        String beginStr = StringUtil.nullString(request.getParameter("beginDate"));
        String endStr = StringUtil.nullString(request.getParameter("endDate"));
        String resources = StringUtil.nullString(request.getParameter("resources"));
        Date beginDate = null;
        Date endDate = null;
        List<HrmResource> resourceList = null;
        //如果开始或结束日期为空，则取当前月的第一天和今天
        if ("".equals(beginStr) || "".equals(endStr)) {
            endDate = DateUtil.getToday();
            beginDate = DateUtil.getMonthFristDay();
        }else{
            beginDate = DateUtil.parseDateByDay(beginStr);
            endDate = DateUtil.parseDateByDay(endStr);
        }
        PageInfo info = recordDao.listByMachine(beginDate, endDate, resourceList, pageNo, pageSize, type);
        return convertData(info,type);
    }

    private PageInfo convertData(PageInfo info,int type){
        List<AttendanceRecord> list = info.getData();
        List<Map<String, Object>> mapList = new ArrayList();
        for (AttendanceRecord record : list) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", record.getId());
            map.put("name", record.getResource().getName());
            map.put("department", record.getResource().getDepartment().getName());
            map.put("workCode", record.getResource().getWorkCode());
            map.put("date", record.getDate());

            if(type == 1) map.put("machine", StringUtil.nullString(record.getMachineNo()));
            if(type == 2) map.put("reason", StringUtil.nullString(record.getReason()));

            mapList.add(map);
        }
        info.setData(mapList);
        return info;
    }
}
