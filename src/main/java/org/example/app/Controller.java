package org.example.app;

import cn.hutool.core.io.FileUtil;
import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.example.model.AttendanceModel;
import org.example.utils.WeekDayUtil;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

/**
 * @author Administrator
 */
public class Controller implements Initializable {

    public DatePicker myDate;
    public TextField startHour;
    public TextField endHour;
    public TextField remark;
    public Button generatebut;

    public int day;
    public int month;
    public int year;

    Task myTask;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //初始化控件
        //获取年月日
        Calendar cal = Calendar.getInstance();
        day = cal.get(Calendar.DATE);
        month = cal.get(Calendar.MONTH) + 1;
        year = cal.get(Calendar.YEAR);
        if (myDate != null && startHour != null && endHour != null){
            myDate.setValue(LocalDate.of(year,month,day));
            startHour.setText("9:00");
            endHour.setText("18:00");
        }
    }

    public void insertdata(ActionEvent actionEvent) throws SQLException {
        AttendanceModel attendanceModel = new AttendanceModel();
        attendanceModel.setMydate(myDate.getValue().toString());
        attendanceModel.setEndhour(endHour.getText());
        attendanceModel.setStarthour(startHour.getText());
        attendanceModel.setRemeak(remark.getText()+"");
        attendanceModel.setYear(String.valueOf(year));
        attendanceModel.setMonth(String.valueOf(month));
        attendanceModel.setDay(String.valueOf(day));
        attendanceModel.setWeek("星期"+ WeekDayUtil.count(year,month,day));
        Db.use().insert(Entity.create("t_sign_data").parseBean(attendanceModel));
    }

    //未使用到 -- 进度条用
    public void startTest(ActionEvent actionEvent) {

        //获取Task任务(也就是主逻辑方法)
        myTask = creatTaskAndRunTask();

        //添加Task的监听方法:如果Task的Message变化,则会经过该方法
        myTask.messageProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                //根据项目情况补充逻辑
            }
        });

        //使用新线程启动 -- 这是重中之重
        new Thread(myTask).start();
    }

    //同上
    public Task creatTaskAndRunTask(){
        return new Task() {
            @Override
            protected Object call() throws Exception {
                //业务逻辑
                return true;
            }
        };
    }


    public void toGeneratePage(ActionEvent actionEvent) throws Exception {
        Stage s = (Stage) generatebut.getScene().getWindow();
        s.close();
        GeneratePageController c = new GeneratePageController();
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("generatePage.fxml"));
        Parent root = loader.load();
        stage.setTitle("生成页");
        stage.setScene(new Scene(root,780 , 520));
        stage.show();
    }
}
