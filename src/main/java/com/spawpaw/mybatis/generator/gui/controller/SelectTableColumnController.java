package com.spawpaw.mybatis.generator.gui.controller;

import com.jfoenix.controls.*;
import com.jfoenix.controls.cells.editors.TextFieldEditorBuilder;
import com.jfoenix.controls.cells.editors.base.GenericEditableTreeTableCell;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableCell;
import javafx.scene.control.TreeTableColumn;
import javafx.util.Callback;
import javafx.util.StringConverter;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created By spawpaw@hotmail.com 2018.1.20
 * Description:
 * 自定义表配置的控制器
 *
 * @author BenBenShang spawpaw@hotmail.com
 */
public class SelectTableColumnController extends BaseController implements Initializable {
    public JFXTreeTableView<TableColumnMetaData> treeView;
    public JFXTextField filterField;

    public JFXTreeTableColumn<TableColumnMetaData, Boolean> c_checked;
    public JFXTreeTableColumn<TableColumnMetaData, String> c_column_name;
    public JFXTreeTableColumn<TableColumnMetaData, String> c_jdbc_type;
    public JFXTreeTableColumn<TableColumnMetaData, String> c_java_type;
    public JFXTreeTableColumn<TableColumnMetaData, String> c_property_name;
    public JFXTreeTableColumn<TableColumnMetaData, String> c_type_handler;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //set cell value factory
        c_checked.setCellValueFactory((TreeTableColumn.CellDataFeatures<TableColumnMetaData, Boolean> param) -> {
            if (c_checked.validateValue(param)) return param.getValue().getValue().checkedProperty();
            else return c_checked.getComputedValue(param);
        });
        c_column_name.setCellValueFactory(param -> c_column_name.validateValue(param) ? param.getValue().getValue().columnNameProperty() : c_column_name.getComputedValue(param));
        c_jdbc_type.setCellValueFactory(param -> c_column_name.validateValue(param) ? param.getValue().getValue().jdbcTypeProperty() : c_column_name.getComputedValue(param));
        c_java_type.setCellValueFactory(param -> c_column_name.validateValue(param) ? param.getValue().getValue().javaTypeProperty() : c_column_name.getComputedValue(param));
        c_property_name.setCellValueFactory(param -> c_column_name.validateValue(param) ? param.getValue().getValue().propertyNameProperty() : c_column_name.getComputedValue(param));
        c_type_handler.setCellValueFactory(param -> c_column_name.validateValue(param) ? param.getValue().getValue().typeHandleProperty() : c_column_name.getComputedValue(param));


        //set cell factory
        c_checked.setCellFactory(CheckBoxTreeTableCell.forTreeTableColumn(c_checked));
        c_java_type.setCellFactory((param) -> new GenericEditableTreeTableCell<>(new TextFieldEditorBuilder()));
        c_property_name.setCellFactory((param) -> new GenericEditableTreeTableCell<>(new TextFieldEditorBuilder()));
        c_type_handler.setCellFactory((param) -> new GenericEditableTreeTableCell<>(new TextFieldEditorBuilder()));


        //set onEditEmit
        c_java_type.setOnEditCommit((t) -> t.getTreeTableView().getTreeItem(t.getTreeTablePosition().getRow()).getValue().javaTypeProperty().set(t.getNewValue()));
        c_property_name.setOnEditCommit((t) -> t.getTreeTableView().getTreeItem(t.getTreeTablePosition().getRow()).getValue().propertyNameProperty().set(t.getNewValue()));
        c_type_handler.setOnEditCommit((t) -> t.getTreeTableView().getTreeItem(t.getTreeTablePosition().getRow()).getValue().typeHandleProperty().set(t.getNewValue()));

        //search
        filterField.textProperty().addListener((o, oldVal, newVal) -> treeView.setPredicate(
                column -> column.getValue().getColumnName().contains(newVal)
                        || column.getValue().getJdbcType().contains(newVal)
        ));
    }

    public void setColumnList(List<TableColumnMetaData> columnList) {
        // build tree
        final TreeItem<TableColumnMetaData> root = new RecursiveTreeItem<>(FXCollections.observableList(columnList), RecursiveTreeObject::getChildren);
        treeView.setRoot(root);
    }

    public void on_btn_confirm_clicked(ActionEvent actionEvent) {
        closeMyself();
    }
}

class CheckBoxTreeTableCell<S, T> extends TreeTableCell<S, T> {

    private final JFXCheckBox checkBox;
    private boolean showLabel;
    private ObservableValue<Boolean> booleanProperty;
    private ObjectProperty<StringConverter<T>> converter =
            new SimpleObjectProperty<StringConverter<T>>(this, "converter") {
                protected void invalidated() {
                    updateShowLabel();
                }
            };
    private ObjectProperty<Callback<Integer, ObservableValue<Boolean>>>
            selectedStateCallback =
            new SimpleObjectProperty<Callback<Integer, ObservableValue<Boolean>>>(
                    this, "selectedStateCallback");

    public CheckBoxTreeTableCell(
            final Callback<Integer, ObservableValue<Boolean>> getSelectedProperty,
            final StringConverter<T> converter) {
        // we let getSelectedProperty be null here, as we can always defer to the
        // TreeTableColumn
        this.getStyleClass().add("check-box-tree-table-cell");

        this.checkBox = new JFXCheckBox();

        // by default the graphic is null until the cell stops being empty
        setGraphic(null);

        setSelectedStateCallback(getSelectedProperty);
        setConverter(converter);

    }

    public static <S> Callback<TreeTableColumn<S, Boolean>, TreeTableCell<S, Boolean>> forTreeTableColumn(
            final TreeTableColumn<S, Boolean> column) {
        return forTreeTableColumn(null, null);
    }

    public static <S, T> Callback<TreeTableColumn<S, T>, TreeTableCell<S, T>> forTreeTableColumn(final Callback<Integer, ObservableValue<Boolean>> getSelectedProperty, final StringConverter<T> converter) {
        return list -> new CheckBoxTreeTableCell<>(getSelectedProperty, converter);
    }

    public final ObjectProperty<StringConverter<T>> converterProperty() {
        return converter;
    }

    public final StringConverter<T> getConverter() {
        return converterProperty().get();
    }

    public final void setConverter(StringConverter<T> value) {
        converterProperty().set(value);
    }

    public final ObjectProperty<Callback<Integer, ObservableValue<Boolean>>> selectedStateCallbackProperty() {
        return selectedStateCallback;
    }

    public final Callback<Integer, ObservableValue<Boolean>> getSelectedStateCallback() {
        return selectedStateCallbackProperty().get();
    }

    public final void setSelectedStateCallback(Callback<Integer, ObservableValue<Boolean>> value) {
        selectedStateCallbackProperty().set(value);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void updateItem(T item, boolean empty) {
        super.updateItem(item, empty);

        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            StringConverter<T> c = getConverter();

            if (showLabel) {
                setText(c.toString(item));
            }
            setGraphic(checkBox);

            if (booleanProperty instanceof BooleanProperty) {
                checkBox.selectedProperty().unbindBidirectional((BooleanProperty) booleanProperty);
            }
            ObservableValue<?> obsValue = getSelectedProperty();
            if (obsValue instanceof BooleanProperty) {
                booleanProperty = (ObservableValue<Boolean>) obsValue;
                checkBox.selectedProperty().bindBidirectional((BooleanProperty) booleanProperty);
            }

            checkBox.disableProperty().bind(Bindings.not(
                    getTreeTableView().editableProperty().and(
                            getTableColumn().editableProperty()).and(
                            editableProperty())
            ));
        }
    }

    private void updateShowLabel() {
        this.showLabel = converter != null;
        this.checkBox.setAlignment(showLabel ? Pos.CENTER_LEFT : Pos.CENTER);
    }

    private ObservableValue<?> getSelectedProperty() {
        return getSelectedStateCallback() != null ?
                getSelectedStateCallback().call(getIndex()) :
                getTableColumn().getCellObservableValue(getIndex());
    }
}
