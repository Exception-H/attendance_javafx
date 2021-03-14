package org.example.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: attendance
 * @description:
 * @author: Curtain
 * @create: 2021-03-14 11:09
 */
@Data
public class AttendanceModel implements Serializable {
    private Integer id;
    private String mydate;
    private String starthour;
    private String endhour;
    private String remeak;
    private String year;
    private String month;
    private String day;
    private String week;
}
