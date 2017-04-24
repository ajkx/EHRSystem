package com.victory.ehrsystem.service.attendance;

import com.victory.ehrsystem.dao.attendance.AttendanceDetailDao;
import com.victory.ehrsystem.dao.attendance.AttendanceScheduleDao;
import com.victory.ehrsystem.dao.attendance.AttendanceScheduleInfoDao;
import com.victory.ehrsystem.dao.attendance.OverTimeDao;
import com.victory.ehrsystem.entity.attendance.AttendanceDetail;
import com.victory.ehrsystem.entity.attendance.AttendanceRecord;
import com.victory.ehrsystem.entity.attendance.AttendanceSchedule;
import com.victory.ehrsystem.entity.attendance.OverTimeRecord;
import com.victory.ehrsystem.entity.hrm.HrmResource;
import com.victory.ehrsystem.service.BaseService;
import com.victory.ehrsystem.service.hrm.impl.HrmResourceService;
import com.victory.ehrsystem.util.DateUtil;
import com.victory.ehrsystem.util.StringUtil;
import com.victory.ehrsystem.vo.JsonVo;
import com.victory.ehrsystem.vo.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.*;

/**
 * Created by ajkx
 * Date: 2016/12/23.
 * Time:14:35
 */
@Service
public class OverTimeRecordService extends BaseService<OverTimeRecord>{

    @Autowired
    private OverTimeDao overTimeDao;

    @Autowired
    private AttendanceDetailDao detailDao;

    @Autowired
    private AttendanceCalculate attendanceCalculate;

    @Autowired
    private HrmResourceService resourceService;

    public JsonVo updateRecord(int id){
        JsonVo jsonVo = new JsonVo();
        OverTimeRecord record = findOne(OverTimeRecord.class, id);
        if (record.getStatus() != OverTimeRecord.Status.abnormal) {
            jsonVo.setStatus(false);
            jsonVo.setMsg("该加班申请不为异常状态");
        }else{
            AttendanceDetail detail = record.getDetail();
            if(detail == null){
                jsonVo.setStatus(false);
                jsonVo.setMsg("该加班没有对应的考勤明细");
            }else{
                record.setTimeUp(record.getDate());
                record.setTimeDown(record.getEndDate());
                record.setActualCount(record.getCount());
                record.setStatus(OverTimeRecord.Status.success);
                record.setRemark("异常修改为正常");
                attendanceCalculate.updateDetailByOverTimeRecord(detail,record);
                attendanceCalculate.calculateTime(detail);
                overTimeDao.update(record);
                detailDao.update(detail);
            }

            jsonVo.setStatus(true);
            jsonVo.setMsg("修改成功");
        }
        return jsonVo;
    }

    public void checkRepeat(OverTimeRecord record,int type){
        java.util.Date beginDate = record.getDate();
        java.util.Date endDate = record.getEndDate();
        HrmResource resource = record.getResource();
        List<OverTimeRecord> recordList = overTimeDao.findByDateForRepeat(beginDate, endDate,resource);

        for (OverTimeRecord temp : recordList) {
            if (temp.getDate().compareTo(beginDate) < 0) {
                beginDate = temp.getDate();
            }
            if (temp.getEndDate().compareTo(endDate) > 0) {
                endDate = temp.getEndDate();
            }
            if(type == 2){
                //如果为本身
                if(temp.getId() != record.getId()){
                    continue;
                }
            }
            //删除了重复的
            temp.setStatus(OverTimeRecord.Status.error);
            temp.setRemark("加班时间存在交集");
        }
        record.setDate(beginDate);
        record.setCount(endDate.getTime() - beginDate.getTime());
        record.setEndDate(endDate);
    }
    //加班记录的查询分页
    public PageInfo findList(HttpServletRequest request){
        return convertData(list(OverTimeRecord.class, request, resourceService));
    }

    private PageInfo convertData(PageInfo info) {
        List<OverTimeRecord> list = info.getData();
        List<Map<String, Object>> mapList = new ArrayList();
        for (OverTimeRecord record : list) {
            Map<String, Object> map = new HashMap<>();

            map.put("id", record.getId());
            map.put("name", record.getResource().getName());
            map.put("department", record.getResource().getDepartment().getName());
            map.put("workCode", record.getResource().getWorkCode());
            map.put("type", record.getType());
            map.put("beginDate", record.getDate());
            map.put("actualUp", record.getTimeUp());
            map.put("endDate", record.getEndDate());
            map.put("actualDown", record.getTimeDown());
            map.put("sum", record.getCount());
            map.put("actualSum", record.getActualCount());
            map.put("reason", record.getReason());
            map.put("status", record.getStatus().getInfo());
            map.put("remark", record.getRemark());
//            map.put("createDate", record.getCreateDate());
            mapList.add(map);
        }
        info.setData(mapList);
        return info;
    }
}
