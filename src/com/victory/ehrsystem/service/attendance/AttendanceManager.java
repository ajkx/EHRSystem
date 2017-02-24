package com.victory.ehrsystem.service.attendance;

import com.victory.ehrsystem.dao.Hrm.HrmResourceDao;
import com.victory.ehrsystem.dao.attendance.AttendanceDetailDao;
import com.victory.ehrsystem.dao.attendance.AttendanceGroupDao;
import com.victory.ehrsystem.dao.attendance.AttendanceRecordDao;
import com.victory.ehrsystem.dao.attendance.AttendanceTypeDao;
import com.victory.ehrsystem.entity.attendance.AttendanceGroup;
import com.victory.ehrsystem.entity.hrm.HrmResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ajkx
 * Date: 2017/2/24.
 * Time:15:47
 */
@Service
public class AttendanceManager {

    @Autowired
    private HrmResourceDao resourceDao;

    @Autowired
    private AttendanceGroupDao groupDao;

    @Autowired
    private AttendanceRecordDao recordDao;

    @Autowired
    private AttendanceDetailDao detailDao;

    @Autowired
    private AttendanceTypeDao typeDao;

    public void autoAttendance() {

        //获取并遍历有考勤组的在职员工
        List<HrmResource> resourceList = resourceDao.findHaveSchedule();
        for (HrmResource resource : resourceList) {
            AttendanceGroup group = resource.getAttendanceGroup();
            //根据考勤组的类型分别执行不同的考勤方法
            int groupType = group.getGroupType();
            switch (groupType) {
                case 1:
                    fixedScheduling(resource, group);
                    break;
                case 2:
                    arrangeScheduling(resource, group);
                    break;
                case 3:
                    freeScheduling(resource, group);
                    break;
            }
        }
    }

    /**
     * 固定排班
     * @param resource
     * @param group
     */
    public void fixedScheduling(HrmResource resource,AttendanceGroup group){

    }

    /**
     * 排班制
     * @param resource
     * @param group
     */
    public void arrangeScheduling(HrmResource resource,AttendanceGroup group){

    }

    /**
     * 自由打卡
     * @param resource
     * @param group
     */
    public void freeScheduling(HrmResource resource, AttendanceGroup group) {

    }
}
