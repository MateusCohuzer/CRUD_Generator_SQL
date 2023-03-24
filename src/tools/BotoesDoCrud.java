package tools;

//@author Radames J Halmeman  - rjhalmeman@gmail.com
public class BotoesDoCrud {

    private String create;
    private String read;
    private String update;
    private String delete;
    private String list;
    private String procurar;
    private String save;
    private String cancelar;
    private String readOnly;

    public BotoesDoCrud() {
    }

    public BotoesDoCrud(String create, String read, String update, String delete, String list, String procurar, String save, String cancelar, String readOnly) {
        this.create = create;
        this.read = read;
        this.update = update;
        this.delete = delete;
        this.list = list;
        this.procurar = procurar;
        this.save = save;
        this.cancelar = cancelar;
        this.readOnly = readOnly;
    }

    public String getReadOnly() {
        return readOnly;
    }

    public void setReadOnly(String readOnly) {
        this.readOnly = readOnly;
    }

    public String getCancelar() {
        return cancelar;
    }

    public void setCancelar(String cancelar) {
        this.cancelar = cancelar;
    }

    public String getSave() {
        return save;
    }

    public void setSave(String save) {
        this.save = save;
    }

    public String getCreate() {
        return create;
    }

    public void setCreate(String create) {
        this.create = create;
    }

    public String getRead() {
        return read;
    }

    public void setRead(String read) {
        this.read = read;
    }

    public String getUpdate() {
        return update;
    }

    public void setUpdate(String update) {
        this.update = update;
    }

    public String getDelete() {
        return delete;
    }

    public void setDelete(String delete) {
        this.delete = delete;
    }

    public String getList() {
        return list;
    }

    public void setList(String list) {
        this.list = list;
    }

    public String getProcurar() {
        return procurar;
    }

    public void setProcurar(String procurar) {
        this.procurar = procurar;
    }

}
