package com.spawpaw.mybatis.generator.gui.controller;

import com.spawpaw.mybatis.generator.gui.util.Constants;
import com.spawpaw.mybatis.generator.gui.util.MBGRunner;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created By spawpaw@hotmail.com  2018-04-09
 *
 * @author BenBenShang spawpaw@hotmail.com
 */
public class GenerationProgressController extends BaseController {
    Logger log = LoggerFactory.getLogger(GenerationProgressController.class);
    public TextArea ta_msg;
    public ProgressBar progressbar;
    public Label label_prompt;
    public Button btn_ok;
    public Button btn_cancel;

    /**
     * 开始生成代码
     *
     * @param generateAll 是否生成全部表,如为false，则只生成当前选中的表
     */
    public void beginGenerate(boolean generateAll) {
        ta_msg.setText("start...");
        //check
        String msg = "";
        //是否选中数据库
        if (selectedDatabaseConfig == null) {
            msg += "\n" + Constants.getI18nStr("msg.error.NoDatabaseSelected");
        } else if (!generateAll && (selectedProjectConfig.selectedTable == null || selectedProjectConfig.selectedTable.getValue().isEmpty())) {  //是否选中表
            msg += "\n" + Constants.getI18nStr("msg.error.NoTableSelected");
        } else {
            //是否填写 Mapper目录、包名，接口目录、包名，Mapper名称
            if (selectedProjectConfig.mapperDir.getValue().isEmpty())
                msg += "\n" + Constants.getI18nStr("msg.error.MapperDirNotConfigured");
            if (selectedProjectConfig.mapperPackage.getValue().isEmpty())
                msg += "\n" + Constants.getI18nStr("msg.error.MapperPackageNotConfigured");
            if (selectedProjectConfig.daoDir.getValue().isEmpty())
                msg += "\n" + Constants.getI18nStr("msg.error.DAODirNotConfigured");
            if (selectedProjectConfig.daoPackage.getValue().isEmpty())
                msg += "\n" + Constants.getI18nStr("msg.error.DAOPackageNotConfigured");
            if (!generateAll && selectedProjectConfig.daoObjName.getValue().isEmpty())
                msg += "\n" + Constants.getI18nStr("msg.error.MapperObjNameNotConfigured");
            // 是否填写Entity目录、包名、名称、Example名称
            if (selectedProjectConfig.entityDir.getValue().isEmpty())
                msg += "\n" + Constants.getI18nStr("msg.error.EntityDirNotConfigured");
            if (selectedProjectConfig.entityPackage.getValue().isEmpty())
                msg += "\n" + Constants.getI18nStr("msg.error.EntityPackageNotConfigured");
            if (!generateAll && selectedProjectConfig.entityObjName.getValue().isEmpty())
                msg += "\n" + Constants.getI18nStr("msg.error.EntityObjNameNotConfigured");
        }
        if (!msg.isEmpty()) {
            appendMsg(Constants.getI18nStr("msg.error.configHasProblems") + "\n" + msg);
            return;
        }

        int tableCount = generateAll ? selectedDatabaseConfig.tableConfigs.keySet().size() : 1;//表的数量
        int generatedTableCount = 0;
        try {
            if (generateAll) {
                appendMsg(String.format("%d tables in total.", tableCount));
                for (String t : selectedDatabaseConfig.tableConfigs.keySet()) {
                    log.info("正在处理第{}个表{}，共{}个表", ++generatedTableCount, t, tableCount);
                    appendMsg(String.format("[%d/%d]\tprocessing %s", generatedTableCount, tableCount, t));
                    progressbar.setProgress(generatedTableCount / tableCount);
                    synchronized (selectedProjectConfig) {
                        selectedProjectConfig.selectedTable.set(t);
                    }
                    String warnings = new MBGRunner(selectedProjectConfig, selectedDatabaseConfig).generate();
                    if (!warnings.isEmpty())
                        appendMsg("warning: \n" + warnings);
                }
            } else {
                appendMsg(new MBGRunner(selectedProjectConfig, selectedDatabaseConfig).generate());
            }
        } catch (Exception e) {
            appendMsg("Error when generating code, message: \n" + e.getMessage());
            e.printStackTrace();
        }
        appendMsg("complete.");
    }

    /**
     * 向输入框中追加信息
     */
    private void appendMsg(String msg) {
        if (!msg.isEmpty())
            ta_msg.setText(ta_msg.getText() + "\n" + msg);
    }

    public void onBtnOkClicked(MouseEvent mouseEvent) {
        generationProgressStage.close();
    }
}
