package org.example.app;

import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Callback;
import org.example.model.AttendanceModel;

import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @program: attendance
 * @description:
 * @author: Curtain
 * @create: 2021-03-14 12:47
 */
public class GeneratePageController implements Initializable {
    public TableColumn dateData;
    public TableColumn week;
    public TableColumn startTime;
    public TableColumn endTime;
    public TableColumn remeark;
    public TableColumn operation;
    public TableView table;
    public Button returnBut;
    public ChoiceBox chiceBox;
    public Button poiCreate;

    private ObservableList<AttendanceModel> attendanceModels;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            showList();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showList() throws Exception{
        ObservableList<String> monthStrings = FXCollections.observableArrayList();
        monthStrings.clear();
        List<String> ls = new ArrayList<>();
        ls.add("1月");ls.add("2月");ls.add("3月");ls.add("4月");ls.add("5月");ls.add("6月");ls.add("7月");ls.add("8月");ls.add("9月");ls.add("10月");ls.add("11月");ls.add("12月");
        monthStrings.addAll(ls);
        chiceBox.setItems(monthStrings);

        //获取年月日
        Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DATE);
        int month = cal.get(Calendar.MONTH) + 1;
        int year = cal.get(Calendar.YEAR);

        attendanceModels = FXCollections.observableArrayList();
        //每次显示清空一次列表
        attendanceModels.clear();
        //链接数据库查询
        List<AttendanceModel> list  = Db.use().query("select * from t_sign_data where year = ? and month = ?", AttendanceModel.class, String.valueOf(year), String.valueOf(month));

        //list添加值对象
        attendanceModels.addAll(list);


        //映射数据进每列
        dateData.setCellValueFactory(new PropertyValueFactory("mydate"));
        week.setCellValueFactory(new PropertyValueFactory("week"));
        startTime.setCellValueFactory(new PropertyValueFactory("starthour"));
        endTime.setCellValueFactory(new PropertyValueFactory("endhour"));
        remeark.setCellValueFactory(new PropertyValueFactory("remeak"));

        //添加按钮进列表
/*        Bj.setCellFactory((col)->{

                    //UserLoad换成你自己的实体名称
                    TableCell<UserLoad, String> cell = new TableCell<UserLoad, String>(){
                        @Override
                        protected void updateItem(String item, boolean empty) {
                            super.updateItem(item, empty);
                            button1 = new JFXButton("编辑");
                            button1.setStyle("-fx-background-color: #00bcff;-fx-text-fill: #ffffff");

                            button1.setOnMouseClicked((col) -> {

                                //获取list列表中的位置，进而获取列表对应的信息数据
                                UserLoad userLoad1 = list.get(getIndex());
                                //按钮事件自己添加

                            });

                            if (empty) {
                                //如果此列为空默认不添加元素
                                setText(null);
                                setGraphic(null);
                            } else {
                                this.setGraphic(button1);
                            }
                        }
                    };
                    return cell;
                }
        );*/

/*        Sc.setCellFactory((col)->{
                    TableCell<UserLoad, String> cell = new TableCell<UserLoad, String>(){

                        @Override
                        public void updateItem(String item, boolean empty) {
                            super.updateItem(item, empty);
                            //按钮显示文字
                            button2 = new JFXButton("删除");
                            //设置按钮颜色
                            button2.setStyle("-fx-background-color: #00bcff;-fx-text-fill: #ffffff");
                            //按钮点击事件
                            button2.setOnMouseClicked((col) -> {
                                //获取list列表中的位置，进而获取列表对应的信息数据
                                UserLoad userLoad2 = list.get(getIndex());
                                //按钮事件自己添加
                            });

                            if (empty) {
                                //如果此列为空默认不添加元素
                                setText(null);
                                setGraphic(null);
                            } else {
                                //加载按钮
                                this.setGraphic(button2);
                            }
                        }



                    };
                    return cell;
                }
        );*/
        //所有项目添加进list
        table.setItems(attendanceModels);
    }


    public void setReturnBut(ActionEvent returnButEvent) throws Exception {
        Stage win = (Stage)returnBut.getScene().getWindow();
        win.close();
        AppMain a = new AppMain();
        a.start(new Stage());
    }

}
